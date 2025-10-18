package com.mercysUtils.library.Items;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;

public class UnbreakableShieldItems extends ModShieldItems {
    public UnbreakableShieldItems(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

}
