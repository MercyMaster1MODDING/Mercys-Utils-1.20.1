package com.mercysUtils.library.Enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

public class TrueDamageEnchantment extends Enchantment {

    public TrueDamageEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinCost(int level) {
        return 100;
    }

    @Override
    public int getMaxCost(int level) {
        return 300;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean checkCompatibility(Enchantment other) {
        return super.checkCompatibility(other);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return category.canEnchant(stack.getItem());
    }
}
