package com.mercysUtils.library.Datagen;

import com.mercysUtils.library.Items.ModItems;
import com.mercysUtils.library.MercysUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFutureProvider, CompletableFuture<TagsProvider.TagLookup<Block>> completableFutureTagLookup, String MOD_ID, ExistingFileHelper existingFileHelper) {
        super(output, completableFutureProvider, completableFutureTagLookup, MercysUtils.MOD_ID, existingFileHelper);
    }
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.MERCINIUM_HELMET.get(),
                        ModItems.MERCINIUM_CHESTPLATE.get(),
                        ModItems.MERCINIUM_LEGGINGS.get(),
                        ModItems.MERCINIUM_BOOTS.get());

    }
}
