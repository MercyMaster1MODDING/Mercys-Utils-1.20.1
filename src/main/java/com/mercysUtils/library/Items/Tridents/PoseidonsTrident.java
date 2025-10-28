package com.mercysUtils.library.Items.Tridents;

import com.mercysUtils.library.Entity.Throwable.ModTridentEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class PoseidonsTrident extends ModTridentItem {

    public PoseidonsTrident(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // Always apply Loyalty III
        if (!EnchantmentHelper.getEnchantments(stack).containsKey(Enchantments.LOYALTY)) {
            stack.enchant(Enchantments.LOYALTY, 3);
            System.out.println("Loyalty Applied");
        }

        // Durability and Riptide check
//        if (stack.getDamageValue() >= stack.getMaxDamage() - 1) return InteractionResultHolder.fail(stack);
//        if (EnchantmentHelper.getRiptide(stack) > 0 && !player.isInWaterOrRain()) return InteractionResultHolder.fail(stack);

        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public void releaseUsing(ItemStack itemStack, Level level, LivingEntity entity, int timeLeft) {
        if (!(entity instanceof Player player)) return;

        int useDuration = this.getUseDuration(itemStack) - timeLeft;
        if (useDuration < 10) return;

        if (!level.isClientSide) {
            // Damage the trident
            itemStack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(player.getUsedItemHand()));

            // Spawn our custom trident entity
            ModTridentEntity thrownTrident = new ModTridentEntity(level, player, itemStack);
            thrownTrident.setOwner(player);

            // Shoot the trident forward
            thrownTrident.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);

            level.addFreshEntity(thrownTrident);

            // Play throw sound
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);

            // Remove item from inventory if not in creative
            if (!player.getAbilities().instabuild) {
                player.getInventory().removeItem(itemStack);
            }
        }

        player.awardStat(Stats.ITEM_USED.get(this));
    }

}
