package com.mercysUtils.library.Worldgen.Biomes;

import com.mercysUtils.library.MercysUtils;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModDimensionRegion(new ResourceLocation(MercysUtils.MOD_ID, "mercys_dimension"), 5));
    }
}