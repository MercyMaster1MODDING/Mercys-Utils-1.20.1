package com.mercysUtils.library.CustomEffects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class PhantomTakeover extends MobEffect {
    protected PhantomTakeover() {
        super(MobEffectCategory.BENEFICIAL, 0x454545);
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int num) {
        entity.setInvulnerable(true);
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap p_19470_, int p_19471_) {
        entity.setInvulnerable(false);
    }
}
