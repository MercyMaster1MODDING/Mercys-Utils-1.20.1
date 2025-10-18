package com.mercysUtils.library;

import com.mercysUtils.library.Blocks.Entity.ModBlockEntities;
import com.mercysUtils.library.Blocks.ModBlocks;
import com.mercysUtils.library.Enchantments.ModEnchantments;
import com.mercysUtils.library.Entity.Client.StarGolem;
import com.mercysUtils.library.Entity.ModEntity;
import com.mercysUtils.library.Entity.Client.StarGolemRenderer;
import com.mercysUtils.library.Items.ModItems;
import com.mercysUtils.library.MiscRegistries.ModCreativeModeTabsRegistry;
import com.mercysUtils.library.RecipeTypes.ModRecipeRegister;
import com.mercysUtils.library.Screen.ModMenuTypes;
import com.mercysUtils.library.Screen.TutorialBlockEntityWorkstationScreen;
import com.mercysUtils.library.Worldgen.Biomes.ModBiomes;
import com.mercysUtils.library.Worldgen.Dimension.ModDimension;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MercysUtils.MOD_ID)
public class MercysUtils
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "mercysutils";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public MercysUtils(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModEntity.register(modEventBus);
        ModEnchantments.ENCHANTMENTS.register(modEventBus);
        ModRecipeRegister.SERIALIZERS.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModBiomes.ELVEN_FOREST.registry();
        ModDimension.MERCYSDIMENSION.registry();
        ModCreativeModeTabsRegistry.TABS.register(modEventBus);




        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);



    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    // Add the example block item to the vanilla ingredients tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
//        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS){
//            event.accept(ModItems.RAW_MERCINIUM);
//        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
    }


    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {

        @SubscribeEvent
        public static void registerAttributes(net.minecraftforge.event.entity.EntityAttributeCreationEvent event) {
            event.put(ModEntity.STAR_GOLEM_ENTITY.get(),
                    com.mercysUtils.library.Entity.Custom.StarGolemEntity.createAttributes().build());
        }
    }

}
