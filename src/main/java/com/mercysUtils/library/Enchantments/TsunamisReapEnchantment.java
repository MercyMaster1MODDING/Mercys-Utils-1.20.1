package com.mercysUtils.library.Enchantments;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.*;

public class TsunamisReapEnchantment extends Enchantment{
    private int level;

    public TsunamisReapEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});

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
        return 3;
    }

    @Override
    public void doPostAttack(LivingEntity self, Entity otherEntity, int level){

        if (otherEntity instanceof LivingEntity livingEntity){
            livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 4));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 6));

        }


    }

    @Override
    public boolean canEnchant(ItemStack other){

        return this.canApplyAtEnchantingTable(other);

    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack other) {

        return other.canApplyAtEnchantingTable(this);
    }


//    @Override
//    public boolean checkCompatibility(Enchantment other) {
//        return super.checkCompatibility(other);
//
//
//    }



}
