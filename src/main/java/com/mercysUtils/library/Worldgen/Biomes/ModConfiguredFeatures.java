package com.mercysUtils.library.Worldgen.Biomes;

import com.mercysUtils.library.Blocks.ModBlocks;
import com.mercysUtils.library.MercysUtils;
import com.mojang.blaze3d.shaders.Uniform;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.lang.reflect.Array;
import java.util.stream.IntStream;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> MERCINIUM_APPLE_TREE_KEY = registerKey("mercinium_apple_tree_key");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JELLYINIUM_APPLE_TREE_KEY = registerKey("jellyinium_apple_tree_key");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context){

        register(context, MERCINIUM_APPLE_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.MERCINIUM_APPLE_TREE_LOG.get()),
                new StraightTrunkPlacer(2, 1, 2),

                BlockStateProvider.simple(ModBlocks.MERCINIUM_APPLE_TREE_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 2),

                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, JELLYINIUM_APPLE_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.JELLYINIUM_APPLE_TREE_LOG.get()),
                new StraightTrunkPlacer(3, 1, 1),

                BlockStateProvider.simple(ModBlocks.JELLYINIUM_APPLE_TREE_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 2),

                new TwoLayersFeatureSize(1, 0, 2)).build());
    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(MercysUtils.MOD_ID, name));
    }
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
