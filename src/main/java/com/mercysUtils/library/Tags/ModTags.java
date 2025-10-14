package com.mercysUtils.library.Tags;

import com.mercysUtils.library.MercysUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static final TagKey<Block> NEEDS_MERCINIUM_TOOL = tag("needs_mercinium_tool");

    public static final TagKey<Block> NEEDS_JELLYINIUM_TOOL = tag("needs_jellyinium_tool");

    private static TagKey<Block> tag(String name) {
        return BlockTags.create(new ResourceLocation(MercysUtils.MOD_ID, name));
    }
}
