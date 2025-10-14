package com.mercysUtils.library.Datagen;

import com.mercysUtils.library.Blocks.ModBlocks;
import com.mercysUtils.library.MercysUtils;
import net.minecraft.core.Vec3i;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MercysUtils.MOD_ID, exFileHelper);
    }

    @Override
    public void registerStatesAndModels(){

        saplingBlock(ModBlocks.MERCINIUM_APPLE_TREE_SAPLING);

    }

    private void saplingBlock(RegistryObject<Block> merciniumAppleTreeSapling) {
        saplingBlock(merciniumAppleTreeSapling.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(merciniumAppleTreeSapling.get()).getPath(), blockTexture(merciniumAppleTreeSapling.get()));

    }


//    @Override
//    protected BlockStateProviderType<?> type() {
//        return null;
//    }
//
//    @Override
//    public BlockState getState(RandomSource randomSource, BlockPos blockPos) {
//        return null;
//    }
}
