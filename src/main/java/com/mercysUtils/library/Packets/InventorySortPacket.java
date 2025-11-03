package com.mercysUtils.library.Packets;

import com.mercysUtils.library.Client.InventorySorter;
import com.mercysUtils.library.Client.InventorySorter.SortMode;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
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
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            // Get the menu/container the player currently has open
            AbstractContainerMenu menu = player.containerMenu;

            // Try to detect if the open container is a chest (single or double)
            if (menu instanceof ChestMenu chestMenu) {
                Container container = chestMenu.getContainer();

                if (container instanceof ChestBlockEntity chestEntity) {
                    // Sort chest inventory (single or double)
                    InventorySorter.sortChestInventory(player, chestEntity, msg.mode);
                }
                else {
                    InventorySorter.sortChestInventory(player, container, msg.mode);
                }


            }
            else {
                // Not a chest — sort player inventory instead
                InventorySorter.sortInventory(player, msg.mode);
            }
        });
        ctx.get().setPacketHandled(true);
    }

}
