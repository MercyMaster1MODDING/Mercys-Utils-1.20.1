package com.mercysUtils.library.Packets;

import com.mercysUtils.library.Client.InventorySorter;
import com.mercysUtils.library.Client.InventorySorter.SortMode;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class InventorySortPacket {
    private final SortMode mode;

    public InventorySortPacket(SortMode mode) {
        this.mode = mode;
    }

    public static void encode(InventorySortPacket msg, FriendlyByteBuf buf) {
        buf.writeEnum(msg.mode);
    }

    public static InventorySortPacket decode(FriendlyByteBuf buf) {
        return new InventorySortPacket(buf.readEnum(SortMode.class));
    }

    public static void handle(InventorySortPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            var player = ctx.get().getSender(); // <-- server player
            if (player != null) {
                InventorySorter.sortInventory(player, msg.mode);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
