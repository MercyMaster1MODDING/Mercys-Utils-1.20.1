package com.mercysUtils.library.Entity.Villagers;

import com.mercysUtils.library.Entity.Custom.StarGolemEntity;
import com.mercysUtils.library.Items.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Interaction;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class CandyMerchantEntity extends Villager {


    public CandyMerchantEntity(EntityType<? extends Villager> p_35267_, Level p_35268_) {
        super(p_35267_, p_35268_);
    }

    @Override
    protected void rewardTradeXp(MerchantOffer merchantOffer) {

    }

    @Override
    protected void updateTrades() {

        offers.add(new MerchantOffer(
                new net.minecraft.world.item.ItemStack(ModItems.CANDY_FRAGMENT.get(), 20),
                new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.COOKIE, 8),
                10, // max uses
                5,  // villager XP
                0.05F // price multiplier
        ));

    }

    public static AttributeSupplier.Builder createAttributes(){
        return CandyMerchantEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 1000)
                .add(Attributes.KNOCKBACK_RESISTANCE, 500)
                .add(Attributes.FOLLOW_RANGE, 0)
                .add(Attributes.MOVEMENT_SPEED, 0);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!this.level().isClientSide && !this.getOffers().isEmpty()) {
            this.setTradingPlayer(player);
            this.openTradingScreen(player, this.getDisplayName(), 1);
        }
        return InteractionResult.sidedSuccess(this.level().isClientSide);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false; // So it doesn't despawn
    }
}
