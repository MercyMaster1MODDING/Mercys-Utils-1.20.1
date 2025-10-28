package com.mercysUtils.library.Blocks.Entity.PipesEntity;

import com.mercysUtils.library.Blocks.Entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PipesEntityClass extends BlockEntity {

    public PipesEntityClass(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.PIPE_BLOCK_ENTITY_TYPE.get(), blockPos, blockState);
    }

    /** Check if this pipe can connect to a neighboring pipe */
    public boolean canConnectTo(Direction direction) {
        BlockPos neighborPos = worldPosition.relative(direction);
        BlockEntity neighbor = level.getBlockEntity(neighborPos);
        return neighbor instanceof PipesEntityClass;
    }

    /** Call this manually when you want the pipe to “flow” something */
    public void transferItemsOrEnergy() {
        if (level == null || level.isClientSide) return;

        for (Direction dir : Direction.values()) {
            BlockEntity neighbor = level.getBlockEntity(worldPosition.relative(dir));
            if (neighbor instanceof PipesEntityClass) {
                // Example: transfer logic here
            }
        }
    }

//    @Override
//    protected void saveAdditional(CompoundTag tag) {
//        super.saveAdditional(tag);
//        // Example: save custom data
//        // tag.putInt("Pressure", this.pressure);
//    }
//
//    @Override
//    public void load(CompoundTag tag) {
//        super.load(tag);
//        // this.pressure = tag.getInt("Pressure");
//    }




}
