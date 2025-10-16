package com.mercysUtils.library.Blocks.Custom;

import com.mercysUtils.library.Blocks.Entity.ModBlockEntities;
import com.mercysUtils.library.Blocks.Entity.StoveTopEntityClass;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StoveTopClass extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16,12,16);

    public StoveTopClass(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState newState1, boolean bool) {
        if (blockState.getBlock() != newState1.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof StoveTopEntityClass) {
                ((StoveTopEntityClass) blockEntity).drops();
            }
        }

        super.onRemove(blockState, level, blockPos, newState1, bool);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, BlockState blockState) {
        return new StoveTopEntityClass(blockPos, blockState);
    }

//    @Override
//    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
//        return super.use(blockState, level, blockPos, player, hand, hitResult);
//    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos,
                                 Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof StoveTopEntityClass workstationEntity) {
                // 👇 This line actually opens your menu and sends the BlockPos through the buffer
                net.minecraftforge.network.NetworkHooks.openScreen(
                        (net.minecraft.server.level.ServerPlayer) player,
                        workstationEntity,
                        buf -> buf.writeBlockPos(blockPos)
                );
            } else {
                throw new IllegalStateException("Missing container provider for StoveTop!");
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }


    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> tBlockEntityType) {
        if (level.isClientSide){
            return null;
        }
        return createTickerHelper(tBlockEntityType, ModBlockEntities.STOVE_TOP_ENTITY_CLASS_BLOCK_ENTITY_TYPE.get(), (
                (level1, blockPos, blockState1, stoveTopEntityClass) ->
                        stoveTopEntityClass.tick(level1, blockPos, blockState1)));
    }
}
