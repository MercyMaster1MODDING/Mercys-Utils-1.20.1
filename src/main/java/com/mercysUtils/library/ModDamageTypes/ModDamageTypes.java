package com.mercysUtils.library.ModDamageTypes;

import com.mercysUtils.library.MercysUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;

public class ModDamageTypes implements DamageTypes {

    //Adds Bleeding Damage
    public static final ResourceKey<DamageType> BLEEDING_DAMAGE_TYPE =
            ResourceKey.create(net.minecraft.core.registries.Registries.DAMAGE_TYPE,
                    new ResourceLocation(MercysUtils.MOD_ID, "bleeding"));

    public static DamageSource BLEEDING_DAMAGE(ServerLevel level) {
        Holder<DamageType> holder = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(BLEEDING_DAMAGE_TYPE);

        return new DamageSource(holder);
    }

    //Adds True Damage
    public static final ResourceKey<DamageType> TRUE_DAMAGE_TYPE =
            ResourceKey.create(net.minecraft.core.registries.Registries.DAMAGE_TYPE,
                    new ResourceLocation(MercysUtils.MOD_ID, "true_damage"));

    public static DamageSource TRUE_DAMAGE(ServerLevel level, Player player) {
        Holder<DamageType> holder = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(TRUE_DAMAGE_TYPE);

        return new DamageSource(holder);
    }
}
