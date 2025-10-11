package com.mercysUtils.library.tiers;

import com.mercysUtils.library.Items.ModItems;
import com.mercysUtils.library.Tags.ModTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {

    public static final ForgeTier MERCINIUM_TIER = new ForgeTier(
            5,
            2000,
            0f,
            0,
            25,
            ModTags.NEEDS_MERCINIUM_TOOL,
            () -> Ingredient.of(ModItems.MERCINIUM_INGOT.get())
    );

    public static final ForgeTier JELLYINIUM_TIER = new ForgeTier(
            5,
            2500,
            0f,
            0,
            25,
            ModTags.NEEDS_JELLYINIUM_TOOL,
            () -> Ingredient.of(ModItems.JELLYINIUM_INGOT.get())


    );
}
