package com.mercysUtils.library.Screen;

import com.mercysUtils.library.MercysUtils;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, MercysUtils.MOD_ID);


    public static final RegistryObject<MenuType<TutorialBlockEntityWorkstationMenu>> TUTORIAL_BLOCK_ENTITY_WORKSTATION_MENU =
            registerMenuType("tutorial_block_entity_workstation_menu", TutorialBlockEntityWorkstationMenu::new);


    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name,
         IContainerFactory<T> factory)
    {
        return  MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus){
        MENUS.register(eventBus);
    }
}
