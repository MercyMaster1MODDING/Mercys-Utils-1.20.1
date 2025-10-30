package com.mercysUtils.library.Sounds;

import com.mercysUtils.library.MercysUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;

public class MusicRegistry {


    public static final ResourceKey<SoundEvent> ELVEN_FOREST_BACKGROUND_KEY =
            ResourceKey.create(Registries.SOUND_EVENT, new ResourceLocation(MercysUtils.MOD_ID, "music/elven_forest_background"));



}
