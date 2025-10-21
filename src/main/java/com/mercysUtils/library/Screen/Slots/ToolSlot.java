package com.mercysUtils.library.Screen.Slots;

import com.mercysUtils.library.Tags.ModTags;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ToolSlot extends SlotItemHandler {
    public ToolSlot(IItemHandler iitemHandler, int index, int x, int y) {
        super(iitemHandler, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.is(ModTags.Items.AUGMENTABLE);
    }
}
