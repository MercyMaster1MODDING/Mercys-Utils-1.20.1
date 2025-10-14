package com.mercysUtils.library.Datagen;

import com.mercysUtils.library.MercysUtils;
import com.mercysUtils.library.Worldgen.Biomes.ModBiomes;
import com.mercysUtils.library.Worldgen.Biomes.ModConfiguredFeatures;
import com.mercysUtils.library.Worldgen.Dimension.ModDimension;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class WorldgenDatagen extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DIMENSION_TYPE, ModDimension::bootstrapType)
            .add(Registries.BIOME, ModBiomes::bootstrap)
            .add(Registries.LEVEL_STEM, ModDimension::bootstrapStem)
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
            .add(Registries.LEVEL_STEM, ModDimension::bootstrapStem);

    public WorldgenDatagen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(MercysUtils.MOD_ID));
    }

}