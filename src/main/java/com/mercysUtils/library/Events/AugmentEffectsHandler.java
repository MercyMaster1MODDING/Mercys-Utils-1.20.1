package com.mercysUtils.library.Events;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.mercysUtils.library.MercysUtils;

@Mod.EventBusSubscriber(modid = MercysUtils.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AugmentEffectsHandler {

    public float augmentDamageOne = 1;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;

        // Optional: Check if in survival
        if (player.isSpectator() || player.isCreative()) return;

        boolean hasEffect = false;
        boolean hasDamageOneAug = false;

        ItemStack mainHand = player.getMainHandItem();
        if (hasSlowFallingAugment(mainHand)) {
            hasEffect = true;
        }
        else {
            for (ItemStack armor : player.getArmorSlots()) {
                if (hasSlowFallingAugment(armor)) {
                    hasEffect = true;
                    break;
                }
            }
        }

        if (hasEffect) {
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40, 0, true, false));
        }
    }

    private static boolean hasSlowFallingAugment(ItemStack stack) {
        if (!stack.hasTag()) return false;
        CompoundTag tag = stack.getTag();
        return tag.getBoolean("HasSlowFalling");
    }

    private static boolean hasDamageOneAugment(ItemStack stack) {
        if (!stack.hasTag()) return false;
        CompoundTag tag = stack.getTag();
        return tag.getBoolean("HasDamageOne");
    }
}
