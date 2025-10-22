package com.mercysUtils.library.Items.Augments;

import net.minecraft.resources.ResourceLocation;
import java.util.HashMap;
import java.util.Map;

public class AugmentRegistry {
    private static final Map<ResourceLocation, ModAugments> AUGMENTS = new HashMap<>();

    public static void register(ModAugments augment) {
        AUGMENTS.put(augment.getId(), augment);
    }

    public static ModAugments getAugmentById(ResourceLocation id) {
        return AUGMENTS.get(id);
    }
}
