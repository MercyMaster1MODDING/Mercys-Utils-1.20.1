package com.mercysUtils.library.Screen.Slots;

import com.mercysUtils.library.Tags.ModTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ToolSlot extends SlotItemHandler {
    public ToolSlot(IItemHandler itemHandler, int index, int x, int y) {
        super(itemHandler, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.is(ModTags.Items.AUGMENTABLE);
    }

    @Override
    public void onTake(Player player, ItemStack stack) {
        super.onTake(player, stack);

        // Get the container (your BlockEntity's item handler)
        IItemHandler handler = this.getItemHandler();

        // Get potential augments
        ItemStack augment1 = handler.getStackInSlot(1);
        ItemStack augment2 = handler.getStackInSlot(2);

        // Try applying one augment
        if (!augment1.isEmpty() && augment1.is(ModTags.Items.AUGMENTS)) {
            applyAugment(stack, augment1);
            augment1.shrink(1);
            return;
        }

        if (!augment2.isEmpty() && augment2.is(ModTags.Items.AUGMENTS)) {
            applyAugment(stack, augment2);
            augment2.shrink(1);
        }
    }

    private void applyAugment(ItemStack toolStack, ItemStack augmentStack) {
        // Get NBT tag
        var tag = toolStack.getOrCreateTag();
        String augmentKey = augmentStack.getItem().getDescriptionId(); // or custom ID

        if (!tag.getBoolean(augmentKey)) {
            tag.putBoolean(augmentKey, true);
        }

        // Optional: Handle special cases
        if (augmentKey.contains("slow_falling")) {
            tag.putBoolean("HasSlowFalling", true);
        }

        // Save NBT
        toolStack.setTag(tag);
    }
}
