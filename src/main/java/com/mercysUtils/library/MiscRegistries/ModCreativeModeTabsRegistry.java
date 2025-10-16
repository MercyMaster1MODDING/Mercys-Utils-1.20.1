package com.mercysUtils.library.MiscRegistries;

import com.mercysUtils.library.Blocks.ModBlocks;
import com.mercysUtils.library.Enchantments.AdvancedSharpnessEnchantment;
import com.mercysUtils.library.Enchantments.ModEnchantments;
import com.mercysUtils.library.FoodItems.ModFoods;
import com.mercysUtils.library.Items.ModItems;
import com.mercysUtils.library.MercysUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabsRegistry {

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MercysUtils.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MOD_TAB = TABS.register("mercys_utils_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable( "mercysutils.mercys_utils_tab"))
                    .icon(() -> new ItemStack(ModItems.RAW_MERCINIUM.get()))
                    .displayItems((itemDisplayParameters, output) -> {
                        //Add items to the tab

                        //Ores
                        output.accept(ModItems.RAW_MERCINIUM.get());
                        output.accept(ModItems.RAW_JELLYINIUM.get());
                        output.accept(ModItems.MERCINIUM_INGOT.get());
                        output.accept(ModItems.JELLYINIUM_INGOT.get());
//                        output.accept(ModBlocks.MERCINIUM_ORE.get());
//                        output.accept(ModBlocks.JELLYINIUM_ORE.get());

                        //Tools
                        output.accept(ModItems.MERCINIUM_SWORD.get());
                        output.accept(ModItems.MERCINIUM_PICKAXE.get());
                        output.accept(ModItems.JELLYINIUM_SWORD.get());
                        output.accept(ModItems.JELLYINIUM_PICKAXE.get());

                        //Armor
                        output.accept(ModItems.MERCINIUM_HELMET.get());
                        output.accept(ModItems.MERCINIUM_CHESTPLATE.get());
                        output.accept(ModItems.MERCINIUM_LEGGINGS.get());
                        output.accept(ModItems.MERCINIUM_BOOTS.get());

                        //Food
                        output.accept(ModItems.MERCINIUM_APPLE.get());
                        output.accept(ModItems.SUPER_MERCINIUM_APPLE.get());
                        output.accept(ModItems.JELLO.get());
                        output.accept(ModItems.JELLO_PIE.get());

                        //Wood and Variants
                        output.accept(ModItems.MERCINIUM_APPLE_TREE_SAPLING.get());
                        output.accept(ModItems.MERCINIUM_APPLE_TREE_LOG.get());
                        output.accept(ModItems.MERCINIUM_APPLE_TREE_LEAVES.get());

                        //Block Entities
                        output.accept(ModBlocks.TUTORIAL_BLOCK_WORKSTATION.get());

//                        Enchantments
//                        output.accept(ModItems.ADVANCED_SHARPNESS_BOOK.get());


                    })
                    .build());


}

