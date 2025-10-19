package com.mercysUtils.library.CustomEffects;

import com.mercysUtils.library.MercysUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {

    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, MercysUtils.MOD_ID);

    public static final RegistryObject<MobEffect> SUGAR_RUSH =
            EFFECTS.register("sugar_rush_effect", SugarRush::new);

    public static final RegistryObject<MobEffect> BLEEDING_EFFECT =
            EFFECTS.register("bleeding_effect", Bleeding::new);


    public static void register(IEventBus eventBus){
        EFFECTS.register(eventBus);
    }
}