package com.mercysUtils.library.Items;

import com.mercysUtils.library.Blocks.Entity.ModBlockEntities;
import com.mercysUtils.library.Blocks.ModBlocks;
import com.mercysUtils.library.FoodItems.ModFoods;
import com.mercysUtils.library.MercysUtils;
import com.mercysUtils.library.MiscRegistries.ModArmorMaterialRegistry;
import com.mercysUtils.library.tiers.ModTiers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, MercysUtils.MOD_ID);

    //Register Items
    public static final RegistryObject<Item> RAW_MERCINIUM = ITEMS.register("raw_mercinium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_JELLYINIUM = ITEMS.register("raw_jellyinium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MERCINIUM_INGOT = ITEMS.register("mercinium_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> JELLYINIUM_INGOT = ITEMS.register("jellyinium_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GELATIN = ITEMS.register("gelatin",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAR_FRAGMENT = ITEMS.register("star_fragment",
            () -> new Item(new Item.Properties()
                    .stacksTo(128)));
    public static final RegistryObject<Item> CANDY_FRAGMENT = ITEMS.register("candy_fragment",
            () -> new Item(new Item.Properties()
                    .stacksTo(128)));

    public static final RegistryObject<Item> DIMENSIONAL_TELEPORTER = ITEMS.register("dimensional_teleporter",
            () -> new DimensionalTeleporter(new Item.Properties()));

    //Register Shields
    public static final RegistryObject<Item> ANTI_GOLEM_SHIELD = ITEMS.register("anti_golem_shield",
            () -> new UnbreakableShieldItems(new Item.Properties()));

    public static final RegistryObject<Item> MERCINIUM_SHIELD = ITEMS.register("mercinium_shield",
            () -> new UnbreakableShieldItems(new Item.Properties()));

    public static final RegistryObject<Item> JELLYINIUM_SHIELD = ITEMS.register("jellyinium_shield",
            () -> new UnbreakableShieldItems(new Item.Properties()));

    public static final RegistryObject<Item> NETHERITE_SHIELD = ITEMS.register("netherite_shield",
            () -> new ModShieldItems(new Item.Properties().defaultDurability(600)));


    //Register FoodItems
    public static final RegistryObject<Item> MERCINIUM_APPLE = ITEMS.register("mercinium_apple",
            () -> new Item(new Item.Properties().food(ModFoods.MERCINIUM_APPLE)));
    public static final RegistryObject<Item> SUPER_MERCINIUM_APPLE = ITEMS.register("super_mercinium_apple",
            () -> new Item(new Item.Properties().food(ModFoods.SUPER_MERCINIUM_APPLE)));
    public static final RegistryObject<Item> JELLO = ITEMS.register("jello",
            () -> new Item(new Item.Properties().food(ModFoods.JELLO)));
    public static final RegistryObject<Item> JELLO_PIE = ITEMS.register("jello_pie",
            () -> new Item(new Item.Properties().food(ModFoods.JELLO_PIE)));


    //Register Enchanted Book Items
//    public static final RegistryObject<EnchantedBookItem> ADVANCED_SHARPNESS_BOOK = ITEMS.register("advanced_sharpness_book",
//            () -> new EnchantedBookItem(new Item.Properties()));

    //Register BlockItems
    public static final RegistryObject<BlockItem> MERCINIUM_ORE = ITEMS.register("mercinium_ore",
            () -> new BlockItem(ModBlocks.MERCINIUM_ORE.get(), new Item.Properties()));

    public static final RegistryObject<BlockItem> JELLYINIUM_ORE = ITEMS.register("jellyinium_ore",
            () -> new BlockItem(ModBlocks.JELLYINIUM_ORE.get(), new Item.Properties()));

    public static final RegistryObject<BlockItem> MERCINIUM_APPLE_TREE_LOG = ITEMS.register("mercinium_apple_tree_log",
            () -> new BlockItem(ModBlocks.MERCINIUM_APPLE_TREE_LOG.get(), new Item.Properties()));

    public static final RegistryObject<BlockItem> MERCINIUM_APPLE_TREE_LEAVES = ITEMS.register("mercinium_apple_tree_leaves",
            () -> new BlockItem(ModBlocks.MERCINIUM_APPLE_TREE_LEAVES.get(), new Item.Properties()));



    public static final RegistryObject<BlockItem> MERCINIUM_APPLE_TREE_SAPLING = ITEMS.register("mercinium_apple_tree_sapling",
            () -> new BlockItem(ModBlocks.MERCINIUM_APPLE_TREE_SAPLING.get(), new Item.Properties()));

    //Register Tools
    public static final RegistryObject<TridentItem> POSEIDONS_TRIDENT = ITEMS.register("poseidons_trident",
            () -> new ModTridentItem(
                    new Item.Properties()));

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
    public static final RegistryObject<Item> MERCINIUM_HELMET = ITEMS.register("mercinium_helmet",
            () -> new ArmorItem(
                    ModArmorMaterialRegistry.MERCINIUM,
                    ArmorItem.Type.HELMET,
                    new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> MERCINIUM_CHESTPLATE = ITEMS.register("mercinium_chestplate",
            () -> new ArmorItem(
                    ModArmorMaterialRegistry.MERCINIUM,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> MERCINIUM_LEGGINGS = ITEMS.register("mercinium_leggings",
            () -> new ArmorItem(
                    ModArmorMaterialRegistry.MERCINIUM,
                    ArmorItem.Type.LEGGINGS,
                    new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> MERCINIUM_BOOTS = ITEMS.register("mercinium_boots",
            () -> new ArmorItem(
                    ModArmorMaterialRegistry.MERCINIUM,
                    ArmorItem.Type.BOOTS,
                    new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> STOVE_TOP_ITEM =
            ITEMS.register("stove_top_block",
                    () -> new BlockItem(ModBlocks.STOVE_TOP_BLOCK.get(), new Item.Properties()));


 
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
