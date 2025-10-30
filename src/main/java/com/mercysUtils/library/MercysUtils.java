package com.mercysUtils.library;

import com.mercysUtils.library.Blocks.Entity.ModBlockEntities;
import com.mercysUtils.library.Blocks.ModBlocks;
import com.mercysUtils.library.CustomEffects.ModEffects;
import com.mercysUtils.library.Enchantments.ModEnchantments;
import com.mercysUtils.library.Entity.ModEntity;
import com.mercysUtils.library.Entity.Villagers.ModVillagers;
import com.mercysUtils.library.Events.AttackDamageHandler;
import com.mercysUtils.library.Items.Augments.AugmentRegistry;
import com.mercysUtils.library.Items.ModItems;
import com.mercysUtils.library.MiscRegistries.ModCreativeModeTabsRegistry;
import com.mercysUtils.library.RecipeTypes.ModRecipeRegister;
import com.mercysUtils.library.Screen.ModMenuTypes;
import com.mercysUtils.library.Sounds.SoundRegistry;
import com.mercysUtils.library.Titles.IPlayerTitles;
import com.mercysUtils.library.Worldgen.Biomes.ModBiomes;
import com.mercysUtils.library.Worldgen.Dimension.CandyDimension;
import com.mercysUtils.library.Worldgen.Dimension.ModDimension;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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

        ModEffects.EFFECTS.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModEntity.register(modEventBus);
        ModVillagers.register(modEventBus);
        SoundRegistry.register(modEventBus);
        ModEnchantments.ENCHANTMENTS.register(modEventBus);
        ModRecipeRegister.SERIALIZERS.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModBiomes.ELVEN_FOREST.registry();
        ModDimension.MERCYSDIMENSION.registry();
        CandyDimension.CANDY_DIMENSION.registry();
        ModCreativeModeTabsRegistry.TABS.register(modEventBus);





        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        //Register Attack Handler
        MinecraftForge.EVENT_BUS.register(AttackDamageHandler.class);


    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            // Register your augment here AFTER items are constructed
            AugmentRegistry.register(ModItems.SLOW_FALLING_AUGMENT.get());
        });

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
            event.put(ModEntity.CANDY_MERCHANT_ENTITY.get(),
                    com.mercysUtils.library.Entity.Villagers.CandyMerchantEntity.createAttributes().build());
        }
    }

}
