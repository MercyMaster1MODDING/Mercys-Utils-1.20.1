package com.mercysUtils.library.Packets;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.network.simple.SimpleChannel.MessageBuilder;
import net.minecraftforge.network.NetworkDirection;
import com.mercysUtils.library.MercysUtils; // your mod main class

public class NetworkHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MercysUtils.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {
        int id = 0;
        CHANNEL.messageBuilder(InventorySortPacket.class, id++, NetworkDirection.PLAY_TO_SERVER)
                .encoder(InventorySortPacket::encode)
                .decoder(InventorySortPacket::decode)
                .consumerMainThread(InventorySortPacket::handle)
                .add();
    }
}
