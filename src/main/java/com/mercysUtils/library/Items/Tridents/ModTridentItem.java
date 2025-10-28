package com.mercysUtils.library.Items.Tridents;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;

public class ModTridentItem extends TridentItem {
    public ModTridentItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

}
