package com.mercysUtils.library.Items.Augments;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Base class for all augments.
 * Each augment can define its own behavior when applied, removed, or ticking.
 */
public abstract class ModAugments extends Item {

    private final ResourceLocation id;
    public final int tier;
    private boolean hasAugments = false;

    public ModAugments(Properties properties, ResourceLocation id, int tier) {
        super(properties);
        this.id = id;
        this.tier = tier;
    }

    public ResourceLocation getId() { return id; }

    public int getTier() { return tier; }

    public boolean hasAugments() { return hasAugments; }

    public void setHasAugments(boolean hasAugments) {
        this.hasAugments = hasAugments;
    }

    // --- Abstract methods that subclasses must implement ---

    /** Called when the augment is applied to a tool/armor item. */
    public abstract void onApply(ItemStack toolStack, Player player);

    /** Called when the augment is removed from a tool/armor item. */
    public abstract void onRemove(ItemStack toolStack);

    /** Called every tick if this augment is active on an item. */
    public void onTick(ItemStack toolStack) {}

    public void onInventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {
        if (!level.isClientSide && entity instanceof LivingEntity livingEntity) {
            if (isSelected || slot == 0) { // adjust logic based on your needs

            }
        }
    }

}
