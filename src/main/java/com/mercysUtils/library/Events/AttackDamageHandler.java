package com.mercysUtils.library.Events;

import com.mercysUtils.library.Items.Augments.AugmentTypes.DamageAugOne;
import com.mercysUtils.library.ModDamageTypes.ModDamageTypes;
import com.mercysUtils.library.Enchantments.ModEnchantments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Interaction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "mercysutils")
public class AttackDamageHandler {

    @SubscribeEvent
    public static void onAttack(AttackEntityEvent event) {

        float augmentDamageOne = 0f;
        Player player = event.getEntity();
        if (!(player.level() instanceof ServerLevel serverLevel)) return;
        if (!(event.getTarget() instanceof LivingEntity target)) return;

        ItemStack weapon = player.getMainHandItem();

        if (weapon.getEnchantmentLevel(ModEnchantments.TRUE_DAMAGE_ENCHANTMENT.get()) > 0){

            int trueLevel = weapon.getEnchantmentLevel(ModEnchantments.TRUE_DAMAGE_ENCHANTMENT.get());

            if (trueLevel <= 0) return; // no enchantment, skip

            // Cancel vanilla attack
            event.setCanceled(true);

            // Calculate base attack damage
            float baseDamage = (float) player.getAttribute(Attributes.ATTACK_DAMAGE).getValue();

            if (weapon.getTag().getBoolean("HasDamageOne")) {
                augmentDamageOne = 1f; // or whatever your augment value is
            }
            else{
                augmentDamageOne = 0f;
            }

            // Determine how much is true damage (e.g., 50%)
            float truePart = baseDamage + weapon.getEnchantmentLevel(ModEnchantments.SHARPNESS) +
                    weapon.getEnchantmentLevel(ModEnchantments.ADVANCED_SHARPNESS_ENCHANT.get()) +
                    augmentDamageOne +
                    weapon.getEnchantmentLevel(ModEnchantments.BANE_OF_ARTHROPODS) +
                    weapon.getEnchantmentLevel(ModEnchantments.SMITE);


            // Normal (armor-reduced) portion

            // True (armor-bypassing) portion
            DamageSource trueSource = ModDamageTypes.TRUE_DAMAGE(serverLevel, player);
            target.hurt(trueSource, truePart);

//        // Optional: particles for feedback
//        serverLevel.sendParticles(ParticleTypes.CRIT,
//                target.getX(), target.getY() + target.getBbHeight() / 2, target.getZ(),
//                5, 0.2, 0.2, 0.2, 0.01);

            // Optional: action bar message for player
//            player.displayClientMessage(
//                    Component.literal("§cTrue Damage: §f" + String.format("%.1f", truePart)),
//                    true
//            );
        }

//        if (weapon.getEnchantmentLevel(ModEnchantments.TRUE_DAMAGE_ENCHANTMENT.get()) <= 0){
//
//            float baseDamage = (float) player.getAttribute(Attributes.ATTACK_DAMAGE).getValue();
//            player.displayClientMessage(
//                    Component.literal("§cBase Damage: §f" + String.format("%.1f", baseDamage)),
//                    true
//            );
//        }
//        else {
//            return;
//        }
    }
}
