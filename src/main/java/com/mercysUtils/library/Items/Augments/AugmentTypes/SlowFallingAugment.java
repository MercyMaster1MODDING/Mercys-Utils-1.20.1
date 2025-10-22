package com.mercysUtils.library.Items.Augments.AugmentTypes;

import com.mercysUtils.library.Items.Augments.ModAugments;
import com.mercysUtils.library.MercysUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SlowFallingAugment extends ModAugments {
    public SlowFallingAugment(Properties properties) {
        super(properties, new ResourceLocation(MercysUtils.MOD_ID, "slow_falling_augment"), 1);
    }

    @Override
    public void onApply(ItemStack toolStack) {

        CompoundTag tag = toolStack.getOrCreateTag();
        tag.putBoolean("HasSlowFalling", true);



    }

    @Override
    public void onRemove(ItemStack toolStack) {

        if (!toolStack.isEmpty()) {
            toolStack.getOrCreateTag().remove("HasSlowFalling");
        }

    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);

        if (!level.isClientSide && entity instanceof LivingEntity livingEntity) {
            CompoundTag tag = stack.getOrCreateTag();

            if (tag.getBoolean("HasSlowFalling")) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40, 0, true, false));
            }
        }
    }




}
