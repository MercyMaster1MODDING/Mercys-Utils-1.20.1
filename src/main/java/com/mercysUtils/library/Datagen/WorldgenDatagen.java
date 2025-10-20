package com.mercysUtils.library.Datagen;

import com.mercysUtils.library.MercysUtils;
import com.mercysUtils.library.Worldgen.Biomes.ModBiomes;
import com.mercysUtils.library.Worldgen.Biomes.ModConfiguredFeatures;
import com.mercysUtils.library.Worldgen.Biomes.ModPlacedFeatures;
import com.mercysUtils.library.Worldgen.Dimension.CandyDimension;
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
            .add(Registries.DIMENSION_TYPE, context -> {
                ModDimension.bootstrapType(context);
                CandyDimension.bootstrapType(context);
            })
            .add(Registries.BIOME, ModBiomes::bootstrap)
            .add(Registries.LEVEL_STEM, context -> {
                ModDimension.bootstrapStem(context);
                CandyDimension.bootstrapStem(context);
            })

            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);

    public WorldgenDatagen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(MercysUtils.MOD_ID));
    }

}