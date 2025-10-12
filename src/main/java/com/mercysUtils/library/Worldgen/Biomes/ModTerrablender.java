package com.mercysUtils.library.Worldgen.Biomes;

import com.mercysUtils.library.MercysUtils;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(new ResourceLocation(MercysUtils.MOD_ID, "overworld"), 5));
    }
}