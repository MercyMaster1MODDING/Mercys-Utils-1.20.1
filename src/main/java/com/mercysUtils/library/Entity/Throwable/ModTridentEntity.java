package com.mercysUtils.library.Entity.Throwable;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;


public class ModTridentEntity extends ThrownTrident {

    private final ItemStack tridentStack;
    private int ticksInAir = 0; // Counts how long the trident has been thrown

    public ModTridentEntity(EntityType<? extends ThrownTrident> type, Level level) {
        super(type, level);
        this.tridentStack = ItemStack.EMPTY;
    }

    public ModTridentEntity(Level level, LivingEntity owner, ItemStack stack) {
        super(level, owner, stack);
        this.tridentStack = stack.copy();
    }

    @Override
    public void tick() {
        super.tick();

        ticksInAir++;

        if (!this.level().isClientSide && this.getOwner() instanceof Player player) {

        // Make the trident fly back to the player
            Vec3 toPlayer = new Vec3(player.getX() - this.getX(),
                    player.getY() + player.getEyeHeight() - this.getY(),
                    player.getZ() - this.getZ());

            double distance = toPlayer.length();

            if (distance < 1.0 || ticksInAir >= 60) { // 60 ticks = 3 seconds
                // Try to return the trident to the player's inventory
                if (!tridentStack.isEmpty()) {
                    if (!player.getInventory().add(tridentStack.copy())) {
                        player.drop(tridentStack.copy(), false);
                    }
                }
                this.remove(RemovalReason.DISCARDED);
                return;
            }

            // Normalize and set velocity toward player
            Vec3 velocity = toPlayer.normalize().scale(0.5);
            this.setDeltaMovement(velocity);
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return tridentStack.copy();
    }
}
