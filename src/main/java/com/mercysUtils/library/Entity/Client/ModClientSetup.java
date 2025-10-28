package com.mercysUtils.library.Entity.Client;

import com.mercysUtils.library.Blocks.ModBlocks;
import com.mercysUtils.library.Entity.Custom.StarGolem;
import com.mercysUtils.library.Entity.Villagers.CandyMerchant;
import com.mercysUtils.library.Entity.Villagers.CandyMerchantRenderer;
import com.mercysUtils.library.MercysUtils;
import com.mercysUtils.library.Entity.ModEntity;
import com.mercysUtils.library.Screen.AugmentTableMenuScreen;
import com.mercysUtils.library.Screen.ModMenuTypes;
import com.mercysUtils.library.Screen.StoveTopMenuScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownTridentRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = MercysUtils.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientSetup {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // Register custom block entity screens
            MenuScreens.register(ModMenuTypes.STOVE_TOP_MENU_TYPE.get(), StoveTopMenuScreen::new);
            MenuScreens.register(ModMenuTypes.AUGMENT_TABLE_MENU_TYPE.get(), AugmentTableMenuScreen::new);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.PIPE.get(), RenderType.cutout());

        });
    }
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
        event.registerEntityRenderer(ModEntity.POSEIDONS_TRIDENT_ENTITY.get(), ThrownTridentRenderer::new);


    }
}
