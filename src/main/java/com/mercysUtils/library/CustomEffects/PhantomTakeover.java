package com.mercysUtils.library.CustomEffects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class PhantomTakeover extends MobEffect {
    protected PhantomTakeover(MobEffectCategory category, int num) {
        super(category, num);
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        return super.isDurationEffectTick(p_19455_, p_19456_);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int num) {



    }
}
