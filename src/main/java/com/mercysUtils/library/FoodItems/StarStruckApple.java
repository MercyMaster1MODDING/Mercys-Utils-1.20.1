package com.mercysUtils.library.FoodItems;

import com.mercysUtils.library.Sounds.SoundRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundSource;

public class StarStruckApple extends Item {

    public StarStruckApple(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);

        if (!level.isClientSide || !(entity instanceof Player player)) return;

        boolean holding = selected || player.getOffhandItem().getItem() == this;

        // Use a stack tag to track if we've already played the sound for this stack
        if (holding) {
            if (!stack.getOrCreateTag().getBoolean("StarStruckAppleSoundPlayed")) {
                player.playSound(SoundRegistry.STAR_STRUCK_APPLE.get(), 1.0F, 1.0F);
                stack.getOrCreateTag().putBoolean("StarStruckAppleSoundPlayed", true);
            }
        } else {
            stack.getOrCreateTag().putBoolean("StarStruckAppleSoundPlayed", false);
        }
    }
}
