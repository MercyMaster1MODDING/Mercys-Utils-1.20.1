package com.mercysUtils.library.Enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class AdvancedSharpnessEnchantment extends DamageEnchantment{
    private int level;
    private MobType mobType;

    public AdvancedSharpnessEnchantment() {
        super(Rarity.VERY_RARE, 0, EquipmentSlot.MAINHAND);
    }

    @Override
    public int getMinCost(int level) {
        return 1;
    }

    @Override
    public int getMaxCost(int level) {
        return 2;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    // This mimics Sharpness logic:
    @Override
    public float getDamageBonus(int level, MobType mobType) {
        this.level = level;
        this.mobType = mobType;
        //return 1.0F + (float) Math.max(0, level - 1) * 0.5f;
        return 1.0F + (level - 1) * 1.5F;
    }

//    @Override
//    public boolean canApplyAtEnchantingTable(ItemStack stack) {
//        return
//    }


    @Override
    public boolean checkCompatibility(Enchantment other) {
        return super.checkCompatibility(other)
                && other != Enchantments.SHARPNESS
                && other != Enchantments.SMITE
                && other != Enchantments.BANE_OF_ARTHROPODS;

    }



}
