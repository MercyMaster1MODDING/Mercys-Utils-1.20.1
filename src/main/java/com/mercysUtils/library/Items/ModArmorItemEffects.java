package com.mercysUtils.library.Items;

import com.google.common.collect.ImmutableMap;
import com.mercysUtils.library.CustomEffects.ModEffects;
import com.mercysUtils.library.MiscRegistries.ModArmorMaterialRegistry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

import java.util.Map;

public class ModArmorItemEffects extends ArmorItem {

    private static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(ModArmorMaterialRegistry.JELLYINIUM, new MobEffectInstance(ModEffects.SUGAR_RUSH.get())).build();


    public ModArmorItemEffects(ArmorMaterial armorMaterial, Type type, Properties properties) {
        super(armorMaterial, type, properties);
    }
}
