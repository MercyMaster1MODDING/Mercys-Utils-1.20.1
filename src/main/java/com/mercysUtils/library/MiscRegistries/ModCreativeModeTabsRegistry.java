package com.mercysUtils.library.MiscRegistries;

import com.mercysUtils.library.Items.ModItems;
import com.mercysUtils.library.MercysUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabsRegistry {

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MercysUtils.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MOD_INGREDIENT_TAB = TABS.register("mercys_ingredients",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("mercys_ingredients"))
                    .icon(() -> new ItemStack(ModItems.RAW_MERCINIUM.get()))
                    .displayItems((itemDisplayParameters, output) -> {
                        //Add items to the tab
                        output.accept(ModItems.RAW_MERCINIUM.get());
                    })
                    .build());


}

