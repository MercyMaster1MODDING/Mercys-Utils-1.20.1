package com.mercysUtils.library.Packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import com.mercysUtils.library.Items.DimensionalTeleporter;

import java.util.function.Supplier;

public class TeleporterScrollPacket {
    private final int selectedIndex;

    public TeleporterScrollPacket(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    // Encoder
    public static void encode(TeleporterScrollPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.selectedIndex);
    }

    // Decoder
    public static TeleporterScrollPacket decode(FriendlyByteBuf buf) {
        return new TeleporterScrollPacket(buf.readInt());
    }

    // Handle packet on server
    public static void handle(TeleporterScrollPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            if (player.getMainHandItem().getItem() instanceof DimensionalTeleporter) {
                DimensionalTeleporter.setSelectedIndex(player.getMainHandItem(), msg.selectedIndex);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
