package com.mercysUtils.library.Client;

import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;

import java.util.Comparator;

public class InventorySorter {

    public enum SortMode{
        Name,
        Count,
        Default,
    }

    public static void sortInventory(Player player, SortMode sortMode){

        NonNullList<ItemStack> itemList = NonNullList.create();
        Inventory playerInventory = player.getInventory();

        // Only main inventory (ignore hotbar, armor, offhand)
        itemList.addAll(playerInventory.items.subList(9, playerInventory.items.size()));

        // Stack identical items first
        NonNullList<ItemStack> stackedList = NonNullList.create();
        for (int i = 0; i < itemList.size(); i++) {
            ItemStack stack = itemList.get(i);
            if (stack.isEmpty()) continue;

            boolean merged = false;
            for (ItemStack s : stackedList) {
                if (ItemStack.isSameItemSameTags(stack, s) && s.getCount() < s.getMaxStackSize()) {
                    int transfer = Math.min(stack.getCount(), s.getMaxStackSize() - s.getCount());
                    s.grow(transfer);
                    stack.shrink(transfer);
                    if (stack.isEmpty()) {
                        merged = true;
                        break;
                    }
                }
            }
            if (!stack.isEmpty()) {
                stackedList.add(stack.copy());
            }
        }

        // Fill empty slots at the end
        while (stackedList.size() < itemList.size()) {
            stackedList.add(ItemStack.EMPTY);
        }

        // Sort according to mode, empty slots automatically go to bottom
        Comparator<ItemStack> comparator = switch (sortMode) {
            case Name -> Comparator
                    .comparing(ItemStack::isEmpty)
                    .thenComparing(s -> s.getItem().getDescriptionId());
            case Count -> Comparator
                    .comparing(ItemStack::isEmpty)
                    .thenComparingInt(ItemStack::getCount).reversed();
            default -> Comparator.comparing(ItemStack::isEmpty);
        };

        stackedList.sort(comparator);

        // Write back to main inventory
        for (int index = 0; index < stackedList.size(); index++ ){
            playerInventory.setItem(9 + index, stackedList.get(index));
        }

        // Leave hotbar, armor, offhand untouched
        playerInventory.setChanged();
        player.containerMenu.broadcastChanges();
    }

    public static void sortChestInventory(Player player, Container container, SortMode sortMode) {
        if (container == null) return;

        // Copy chest contents
        NonNullList<ItemStack> itemList = NonNullList.withSize(container.getContainerSize(), ItemStack.EMPTY);
        for (int i = 0; i < container.getContainerSize(); i++) {
            itemList.set(i, container.getItem(i).copy());
        }

        // Stack identical items
        NonNullList<ItemStack> stackedList = NonNullList.create();
        for (ItemStack stack : itemList) {
            if (stack.isEmpty()) continue;

            boolean merged = false;
            for (ItemStack s : stackedList) {
                if (ItemStack.isSameItemSameTags(stack, s) && s.getCount() < s.getMaxStackSize()) {
                    int transfer = Math.min(stack.getCount(), s.getMaxStackSize() - s.getCount());
                    s.grow(transfer);
                    stack.shrink(transfer);
                    if (stack.isEmpty()) {
                        merged = true;
                        break;
                    }
                }
            }
            if (!stack.isEmpty()) {
                stackedList.add(stack.copy());
            }
        }

        // Fill empty slots
        while (stackedList.size() < itemList.size()) {
            stackedList.add(ItemStack.EMPTY);
        }

        // Sorting logic
        Comparator<ItemStack> comparator = switch (sortMode) {
            case Name -> Comparator
                    .comparing(ItemStack::isEmpty)
                    .thenComparing(s -> s.getItem().getDescriptionId(), Comparator.nullsLast(String::compareTo));
            case Count -> Comparator
                    .comparing(ItemStack::isEmpty)
                    .thenComparingInt(ItemStack::getCount).reversed();
            default -> Comparator.comparing(ItemStack::isEmpty);
        };

        stackedList.sort(comparator);

        // Write back sorted contents
        for (int i = 0; i < stackedList.size(); i++) {
            container.setItem(i, stackedList.get(i));
        }

        container.setChanged();
    }


}
