package com.mercysUtils.library.Client;

import com.mercysUtils.library.MercysUtils;
import com.mercysUtils.library.Sounds.SoundRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MercysUtils.MOD_ID, value = Dist.CLIENT)
public class ClientMusicHandler {

    private static final ResourceLocation ELVEN_FOREST_ID =
            new ResourceLocation(MercysUtils.MOD_ID, "elven_forest");
    private static Music ELVEN_FOREST_MUSIC;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) return;

        // Check player biome
        ResourceLocation currentBiome = mc.level.getBiome(mc.player.blockPosition())
                .unwrapKey().orElseThrow().location();

        // Lazy-init the music holder once
        if (ELVEN_FOREST_MUSIC == null && mc.level != null) {
            Holder<SoundEvent> holder = mc.level.registryAccess()
                    .registryOrThrow(Registries.SOUND_EVENT)
                    .getHolderOrThrow(ResourceKey.create(Registries.SOUND_EVENT,
                            new ResourceLocation(MercysUtils.MOD_ID, "music/elven_forest_background")));

            ELVEN_FOREST_MUSIC = new Music(holder, 20, 100, true);
        }

        // If player is in Elven Forest biome
        if (currentBiome.equals(ELVEN_FOREST_ID)) {
            if (!mc.getMusicManager().isPlayingMusic(ELVEN_FOREST_MUSIC)) {
                mc.getMusicManager().startPlaying(ELVEN_FOREST_MUSIC);
            }
        }
    }
}
