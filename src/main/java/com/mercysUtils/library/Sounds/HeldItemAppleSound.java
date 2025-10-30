package com.mercysUtils.library.Sounds;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;

public class HeldItemAppleSound extends AbstractTickableSoundInstance {
    private final LocalPlayer player;
    private final ItemStack itemStack;

    public HeldItemAppleSound(LocalPlayer player, ItemStack itemStack) {
        super(SoundRegistry.STAR_STRUCK_APPLE.get(), SoundSource.PLAYERS, player.level().getRandom());
        this.player = player;
        this.itemStack = itemStack.copy();
        this.looping = true;
        this.volume = 1.0F;
        this.pitch = 1.0F;
    }

    @Override
    public void tick() {
        // Stop if player isn't holding the item
        if (player == null || !player.isAlive() || !isHoldingItem()) {
            this.stop();
        }
    }

    private boolean isHoldingItem() {
        return player.getMainHandItem().is(itemStack.getItem()) ||
                player.getOffhandItem().is(itemStack.getItem());
    }
}
