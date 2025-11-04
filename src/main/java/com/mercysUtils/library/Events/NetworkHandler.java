package com.mercysUtils.library.Events;

import com.mercysUtils.library.Packets.InventorySortPacket;
import com.mercysUtils.library.Packets.TeleporterScrollPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
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

        CHANNEL.messageBuilder(TeleporterScrollPacket.class, id++, NetworkDirection.PLAY_TO_SERVER)
                .encoder(TeleporterScrollPacket::encode)
                .decoder(TeleporterScrollPacket::decode)
                .consumerMainThread(TeleporterScrollPacket::handle)
                .add();
    }
}
