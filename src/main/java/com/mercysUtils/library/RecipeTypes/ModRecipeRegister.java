package com.mercysUtils.library.RecipeTypes;

import com.mercysUtils.library.MercysUtils;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeRegister {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
        DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MercysUtils.MOD_ID);

    public static final RegistryObject<RecipeSerializer<StoveTypeCraftingType>> STOVE_TOP_CRAFTING_TYPE =
            SERIALIZERS.register("stove_top_crafting_type", () -> StoveTypeCraftingType.Serializer.INSTANCE);

    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}
