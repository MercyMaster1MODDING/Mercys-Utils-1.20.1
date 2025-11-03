package com.mercysUtils.library.Worldgen.Biomes;

import com.mercysUtils.library.MercysUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> MERCINIUM_APPLE_TREE_PLACED_KEY =
            registerKey("mercinium_apple_tree_placed_key");
    public static final ResourceKey<PlacedFeature> DENSE_MERCINIUM_APPLE_TREE_PLACED_KEY =
            registerKey("dense_mercinium_apple_tree_placed_key");
    public static final ResourceKey<PlacedFeature> CANDY_APPLE_TREE_PLACED_KEY =
            registerKey("candy_apple_tree_placed_key");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        var lookup = context.lookup(Registries.CONFIGURED_FEATURE);

        // Look up the configured feature you already made
        var merciniumAppleTree = lookup.getOrThrow(ModConfiguredFeatures.MERCINIUM_APPLE_TREE_KEY);
        var denseMerciniumAppleTree = lookup.getOrThrow(ModConfiguredFeatures.DENSE_MERCINIUM_APPLE_TREE_KEY);
        var candyAppleTree = lookup.getOrThrow(ModConfiguredFeatures.CANDY_APPLE_TREE_KEY);

        // Register placed versions with spawn rules
        context.register(MERCINIUM_APPLE_TREE_PLACED_KEY,
                new PlacedFeature(merciniumAppleTree, VegetationPlacements.treePlacement(
                        PlacementUtils.countExtra(1,0.5f, 2)))); // frequency + extra chance
        context.register(DENSE_MERCINIUM_APPLE_TREE_PLACED_KEY,
                new PlacedFeature(denseMerciniumAppleTree, VegetationPlacements.treePlacement(
                        PlacementUtils.RANGE_10_10)));

        context.register(CANDY_APPLE_TREE_PLACED_KEY,
                new PlacedFeature(candyAppleTree, VegetationPlacements.treePlacement(
                        PlacementUtils.FULL_RANGE)));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(MercysUtils.MOD_ID, name));
    }

}