package com.mercysUtils.library.CustomEffects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class SugarRush extends MobEffect {


    protected SugarRush() {
        super(MobEffectCategory.BENEFICIAL, 0xFFFFFF);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int num) {
                // Continuously refresh scaled effects
        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 100, false, false, true));
        entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 10, false, false, true));

    }

    @Override
    public int getColor() {

        long time = System.currentTimeMillis();
        float hue = (time % 3000L) / 3000.0f; // Full cycle every 5 seconds
        return java.awt.Color.HSBtoRGB(hue, 1.0f, 1.0f);
    }

    public void SugarRushEffectFunction(LivingEntity entity){



    }
}
