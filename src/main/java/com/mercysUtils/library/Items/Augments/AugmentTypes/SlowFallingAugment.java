package com.mercysUtils.library.Items.Augments.AugmentTypes;

import com.mercysUtils.library.Items.Augments.ModAugments;
import com.mercysUtils.library.MercysUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class SlowFallingAugment extends ModAugments {
    public SlowFallingAugment(Properties properties) {
        super(properties, new ResourceLocation(MercysUtils.MOD_ID, "slow_falling_augment"), 1);
    }

    @Override
    public void onApply(ItemStack toolStack) {

    }

    @Override
    public void onRemove(ItemStack toolStack) {

    }
}
