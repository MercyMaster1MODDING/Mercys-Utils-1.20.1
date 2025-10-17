package com.mercysUtils.library.Entity.Custom;

import com.mercysUtils.library.Interfaces.IronGolemInterface;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.data.SoundDefinition;

import java.util.function.Predicate;

public class StarGolemEntity extends IronGolem { ;

    public StarGolemEntity (EntityType<? extends StarGolemEntity> p_28834_, Level p_28835_) {
        super(p_28834_, p_28835_);
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, 10, true, false, (entity) -> true));

        this.goalSelector.addGoal(1,new MeleeAttackGoal(this, 4, true ));
    }

    public static AttributeSupplier.Builder createAttributes(){
        return StarGolemEntity.createLivingAttributes()
                .add(Attributes.ATTACK_DAMAGE, 15)
                .add(Attributes.MAX_HEALTH, 200)
                .add(Attributes.ARMOR, 20)
                .add(Attributes.ATTACK_SPEED, 0.5)
                .add(Attributes.FOLLOW_RANGE, 30)
                .add(Attributes.KNOCKBACK_RESISTANCE, 500);
    }

    @Override
    protected InteractionResult mobInteract(Player p_28861_, InteractionHand p_28862_) {
        return InteractionResult.PASS;
    }

    @Override
    public void offerFlower(boolean p_28886_) {
        return;
    }
}
