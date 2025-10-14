package com.mercysUtils.library.MiscRegistries;

import com.mercysUtils.library.Items.ModItems;
import com.mercysUtils.library.tiers.ModArmorTiers;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.crafting.Ingredient;

public class ModArmorMaterialRegistry {
    public static final ModArmorTiers MERCINIUM = new ModArmorTiers(
            new int[] {2000,4000,3000,2000},
            new int[] {6, 10, 9, 6},
            25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(ModItems.MERCINIUM_INGOT::get),
            "mercinium",
            5,
            0.4f
    );
    public static final ModArmorTiers JELLYINIUM = new ModArmorTiers(
            new int[] {2000,4000,3000,2000},
            new int[] {6, 10, 9, 6},
            25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(ModItems.JELLYINIUM_INGOT::get),
            "jellyinium",
            5,
            0.4f
    );
}
