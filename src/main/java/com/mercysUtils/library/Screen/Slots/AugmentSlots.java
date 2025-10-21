package com.mercysUtils.library.Screen.Slots;

import com.mercysUtils.library.Tags.ModTags;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class AugmentSlots extends SlotItemHandler {
    public AugmentSlots(IItemHandler iItemHandler, int index, int x, int y) {
        super(iItemHandler, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.is(ModTags.Items.AUGMENTS);
    }
}
