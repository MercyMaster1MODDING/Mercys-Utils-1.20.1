package com.mercysUtils.library.Items;

import com.mercysUtils.library.Blocks.ModBlocks;
import com.mercysUtils.library.MercysUtils;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, MercysUtils.MOD_ID);

    //Register Items
    public static final RegistryObject<Item> RAW_MERCINIUM = ITEMS.register("raw_mercinium",
            () -> new Item(new Item.Properties()));

    //Register BlockItems
    public static final RegistryObject<BlockItem> MERCINIUM_ORE_ITEM = ITEMS.register("mercinium_ore",
            () -> new BlockItem(ModBlocks.MERCINIUM_ORE.get(), new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
