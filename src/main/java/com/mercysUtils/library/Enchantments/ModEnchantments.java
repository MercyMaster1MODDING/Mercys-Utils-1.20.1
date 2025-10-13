package com.mercysUtils.library.Enchantments;

import com.mercysUtils.library.MercysUtils;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments extends Enchantments {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MercysUtils.MOD_ID);

    public static final RegistryObject<Enchantment> ADVANCED_SHARPNESS_ENCHANT =
            ENCHANTMENTS.register("advanced_sharpness", AdvancedSharpnessEnchantment::new);



}

