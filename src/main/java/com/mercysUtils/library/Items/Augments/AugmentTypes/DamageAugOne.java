package com.mercysUtils.library.Items.Augments.AugmentTypes;

import com.mercysUtils.library.Events.AttackDamageHandler;
import com.mercysUtils.library.Items.Augments.ModAugments;
import com.mercysUtils.library.MercysUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DamageAugOne extends ModAugments {

    float augmentDamageOne = 0f;

    public DamageAugOne(Properties properties) {
        super(properties, new ResourceLocation(MercysUtils.MOD_ID, "damage_augment_tier_one"), 1);
    }

    @Override
    public void onApply(ItemStack toolStack, Player player) {

        CompoundTag tag = toolStack.getOrCreateTag();
        tag.putBoolean("HasDamageOne", true);

    }

    @Override
    public void onRemove(ItemStack toolStack) {

        if (!toolStack.isEmpty()) {
            toolStack.getOrCreateTag().remove("HasDamageOne");
        }

    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);

        if (!level.isClientSide && entity instanceof LivingEntity livingEntity) {
            CompoundTag tag = stack.getOrCreateTag();

            if (tag.getBoolean("HasDamageOne")) {
                ;
            }
        }
    }




}
