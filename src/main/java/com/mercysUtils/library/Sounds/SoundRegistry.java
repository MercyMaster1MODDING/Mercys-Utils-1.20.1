package com.mercysUtils.library.Sounds;

import com.mercysUtils.library.MercysUtils;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.resources.ResourceLocation;

public class SoundRegistry {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MercysUtils.MOD_ID);

    public static final RegistryObject<SoundEvent> STAR_STRUCK_APPLE =
            SOUND_EVENTS.register("star_struck_apple",
                    () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MercysUtils.MOD_ID, "star_struck_apple")));

    public static final RegistryObject<SoundEvent> ELVEN_FOREST_BACKGROUND =
            SOUND_EVENTS.register("music/elven_forest_background",
                    () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MercysUtils.MOD_ID, "music/elven_forest_background")));


    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
