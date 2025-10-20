package com.mercysUtils.library.Blocks.TreeBlocks;

import com.mercysUtils.library.Blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.Nullable;

public class ModFlammableRotateablePillar extends RotatedPillarBlock {
    public ModFlammableRotateablePillar(Properties rotatePillarProperties) {
        super(rotatePillarProperties);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if (context.getItemInHand().getItem() instanceof AxeItem) {
            if (state.is(ModBlocks.MERCINIUM_APPLE_TREE_LOG.get())){
                return ModBlocks.STRIPPED_MERCINIUM_APPLE_TREE_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if (state.is(ModBlocks.MERCINIUM_APPLE_WOOD.get())){
                return ModBlocks.STRIPPED_MERCINIUM_APPLE_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(ModBlocks.CANDY_APPLE_TREE_LOG.get())){
                return ModBlocks.STRIPPED_CANDY_APPLE_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if (state.is(ModBlocks.CANDY_APPLE_WOOD.get())){
                return ModBlocks.STRIPPED_CANDY_APPLE_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

        }
        return state;
    }
}