package com.mercysUtils.library.FoodItems;

import com.mercysUtils.library.Items.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods extends ModItems {

    public static final FoodProperties MERCINIUM_APPLE = new FoodProperties.Builder()
            .nutrition(8)
            .saturationMod(8)
            .effect(() -> new MobEffectInstance(MobEffects.SATURATION, 40), 0.8f).build();

    public static final FoodProperties STAR_STRUCK_APPLE = new FoodProperties.Builder()
            .nutrition(16)
            .saturationMod(16)
            .effect(() -> new MobEffectInstance(MobEffects.SATURATION, 60), 1f).build();

    public static final FoodProperties JELLO = new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(2)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 60), 1f).build();

    public static final FoodProperties JELLO_PIE = new FoodProperties.Builder()
            .nutrition(8)
            .saturationMod(8)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 60, 10), 1f).build();
}