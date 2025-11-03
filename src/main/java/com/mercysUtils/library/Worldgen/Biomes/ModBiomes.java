package com.mercysUtils.library.Worldgen.Biomes;

import com.mercysUtils.library.MercysUtils;
import com.mercysUtils.library.Sounds.MusicRegistry;
import com.mercysUtils.library.Sounds.SoundRegistry;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomes {

    public static final ResourceKey<Biome> ELVEN_FOREST = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(MercysUtils.MOD_ID, "elven_forest"));

    public static final ResourceKey<Biome> DENSE_ELVEN_FOREST = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(MercysUtils.MOD_ID, "dense_elven_forest"));

    public static final ResourceKey<Biome> CANDY_FOREST = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(MercysUtils.MOD_ID, "candy_forest"));





    public static void bootstrap(BootstapContext<Biome> context) {
        context.register(ModBiomes.ELVEN_FOREST, elvenForest(context));
        context.register(ModBiomes.DENSE_ELVEN_FOREST, denseElvenForest(context));
        context.register(ModBiomes.CANDY_FOREST, candyForest(context));
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultOres(builder, true);
    }

    //Elven Forest Biome
    public static Biome elvenForest(BootstapContext<Biome> context) {

        // Lookup for sound registry
        Holder<SoundEvent> elvenForestSoundHolder =
                context.lookup(Registries.SOUND_EVENT)
                        .getOrThrow(SoundRegistry.ELVEN_FOREST_BACKGROUND.getKey());

        Music elvenForestMusic = new Music(elvenForestSoundHolder, 20, 100, true);

        // Biome setup
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.MERCINIUM_APPLE_TREE_PLACED_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x87CEEB)
                        .waterFogColor(0x87CEEB)
                        .skyColor(0x87CEEB)
                        .grassColorOverride(0xdde26a)
                        .foliageColorOverride(0xdde26a)
                        .fogColor(0xbdbdbd)
                        .ambientLoopSound(elvenForestSoundHolder)
                        .backgroundMusic(elvenForestMusic)
                        .build())
                .build();
    }

    public static Biome denseElvenForest(BootstapContext<Biome> context) {

        // Lookup for sound registry
        Holder<SoundEvent> elvenForestTwoSoundHolder =
                context.lookup(Registries.SOUND_EVENT)
                        .getOrThrow(SoundRegistry.ELVEN_FOREST_BACKGROUND_TWO.getKey());

        Music elvenForestTwoMusic = new Music(elvenForestTwoSoundHolder, 20, 100, true);

        // Biome setup
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.DENSE_MERCINIUM_APPLE_TREE_PLACED_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x87CEEB)
                        .waterFogColor(0x87CEEB)
                        .skyColor(0x87CEEB)
                        .grassColorOverride(0xdde26a)
                        .foliageColorOverride(0xdde26a)
                        .fogColor(0xbdbdbd)
                        .ambientLoopSound(elvenForestTwoSoundHolder)
                        .backgroundMusic(elvenForestTwoMusic)
                        .build())
                .build();
    }


    //Candy Forest Biome
    public static Biome candyForest(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);

        //biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_PLAINS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.CANDY_APPLE_TREE_PLACED_KEY);

        //BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0xe82e3b)
                        .waterFogColor(0xbf1b26)
                        .skyColor(0x30c918)
                        .grassColorOverride(0xFF2400)
                        .foliageColorOverride(0xd203fc)
                        .fogColor(0x22a1e6)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(null).build())
                .build();
    }
}