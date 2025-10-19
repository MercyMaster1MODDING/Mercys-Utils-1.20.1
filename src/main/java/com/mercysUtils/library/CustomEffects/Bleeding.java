package com.mercysUtils.library.CustomEffects;

import com.mercysUtils.library.ModDamageTypes.ModDamageTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class Bleeding extends MobEffect {
    public static int bleeding_interval = 4;

    protected Bleeding() {
        super(MobEffectCategory.BENEFICIAL, 0x3d9fc3);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % bleeding_interval == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide()) return;
        if (!(entity.level() instanceof ServerLevel serverLevel)) return;

        // Calculate your damage amount
        float damage = 1.0F + amplifier * 0.5F;

        // Apply your custom bleeding damage!
        entity.hurt(ModDamageTypes.BLEEDING_DAMAGE(serverLevel), damage);
    }
}
