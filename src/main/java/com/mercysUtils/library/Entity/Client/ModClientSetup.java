package com.mercysUtils.library.Entity.Client;

import com.mercysUtils.library.Entity.Custom.StarGolem;
import com.mercysUtils.library.Entity.Villagers.CandyMerchant;
import com.mercysUtils.library.Entity.Villagers.CandyMerchantRenderer;
import com.mercysUtils.library.MercysUtils;
import com.mercysUtils.library.Entity.ModEntity;
import com.mercysUtils.library.Screen.AugmentTableMenuScreen;
import com.mercysUtils.library.Screen.ModMenuTypes;
import com.mercysUtils.library.Screen.StoveTopMenuScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * Handles all client-side setup for the MercysUtils mod.
 * This class is loaded only on the client and registers renderers, models, and screens.
 */
@Mod.EventBusSubscriber(modid = MercysUtils.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientSetup {

    /**
     * Called during the client setup phase.
     * Register GUI screens and other client-only initializations here.
     */
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // Register custom block entity screens
            MenuScreens.register(ModMenuTypes.STOVE_TOP_MENU_TYPE.get(), StoveTopMenuScreen::new);
            MenuScreens.register(ModMenuTypes.AUGMENT_TABLE_MENU_TYPE.get(), AugmentTableMenuScreen::new);
        });
    }

    /**
     * Registers the model layer definitions for custom entities.
     * This tells Minecraft how to bake the model layers.
     */
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(StarGolem.LAYER_LOCATION, StarGolem::createBodyLayer);
        event.registerLayerDefinition(CandyMerchant.LAYER_LOCATION, CandyMerchant::createBodyLayer);
    }

    /**
     * Registers custom entity renderers for the mod.
     */
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntity.STAR_GOLEM_ENTITY.get(), StarGolemRenderer::new);
        event.registerEntityRenderer(ModEntity.CANDY_MERCHANT_ENTITY.get(), CandyMerchantRenderer::new);
    }
}
