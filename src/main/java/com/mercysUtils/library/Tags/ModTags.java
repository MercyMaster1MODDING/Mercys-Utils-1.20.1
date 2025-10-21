package com.mercysUtils.library.Tags;

import com.mercysUtils.library.MercysUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks{

        public static final TagKey<Block> NEEDS_MERCINIUM_TOOL = tag("needs_mercinium_tool");

        public static final TagKey<Block> NEEDS_JELLYINIUM_TOOL = tag("needs_jellyinium_tool");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(MercysUtils.MOD_ID, name));
        }

    }

    public static class Items {

        public static final TagKey<Item> AUGMENTABLE = tag("can_be_augmented");

        public static final TagKey<Item> AUGMENTS = tag("augments");

        public static final TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(MercysUtils.MOD_ID, name));
        }
    }

}
