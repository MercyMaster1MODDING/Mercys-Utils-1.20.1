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

<<<<<<< Updated upstream
=======
    public static final RegistryObject<BlockItem> JELLYINIUM_ORE = ITEMS.register("jellyinium_ore",
            () -> new BlockItem(ModBlocks.JELLYINIUM_ORE.get(), new Item.Properties()));

    public static final RegistryObject<BlockItem> MERCINIUM_APPLE_TREE_LOG = ITEMS.register("mercinium_apple_tree_log",
            () -> new BlockItem(ModBlocks.MERCINIUM_APPLE_TREE_LOG.get(), new Item.Properties()));

    public static final RegistryObject<BlockItem> MERCINIUM_APPLE_TREE_SAPLING = ITEMS.register("mercinium_apple_tree_sapling",
            () -> new BlockItem(ModBlocks.MERCINIUM_APPLE_TREE_SAPLING.get(), new Item.Properties()));

    //Register Tools
    public static final RegistryObject<SwordItem> MERCINIUM_SWORD = ITEMS.register("mercinium_sword",
            () -> new SwordItem(
                    ModTiers.MERCINIUM_TIER,
                    10,
                    -2.4f,
                    new Item.Properties()));

    public static final RegistryObject<PickaxeItem> MERCINIUM_PICKAXE = ITEMS.register("mercinium_pickaxe",
            () -> new PickaxeItem(
                    ModTiers.MERCINIUM_TIER,
                     1,
                    -3f,
                    new Item.Properties()));

    public static final RegistryObject<SwordItem> JELLYINIUM_SWORD = ITEMS.register("jellyinium_sword",
            () -> new SwordItem(
                    ModTiers.JELLYINIUM_TIER,
                    10,
                    -2.4f,
                    new Item.Properties()));

    public static final RegistryObject<PickaxeItem> JELLYINIUM_PICKAXE = ITEMS.register("jellyinium_pickaxe",
            () -> new PickaxeItem(
                    ModTiers.JELLYINIUM_TIER,
                    1,
                    -3f,
                    new Item.Properties()));

    //Register Armor
    public static final RegistryObject<ArmorItem> MERCINIUM_HELMET = ITEMS.register("mercinium_helmet",
            () -> new ArmorItem(
                    ModArmorMaterialRegistry.MERCINIUM,
                    ArmorItem.Type.HELMET,
                    new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<ArmorItem> MERCINIUM_CHESTPLATE = ITEMS.register("mercinium_chestplate",
            () -> new ArmorItem(
                    ModArmorMaterialRegistry.MERCINIUM,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<ArmorItem> MERCINIUM_LEGGINGS = ITEMS.register("mercinium_leggings",
            () -> new ArmorItem(
                    ModArmorMaterialRegistry.MERCINIUM,
                    ArmorItem.Type.LEGGINGS,
                    new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<ArmorItem> MERCINIUM_BOOTS = ITEMS.register("mercinium_boots",
            () -> new ArmorItem(
                    ModArmorMaterialRegistry.MERCINIUM,
                    ArmorItem.Type.BOOTS,
                    new Item.Properties().rarity(Rarity.EPIC)));




>>>>>>> Stashed changes
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
