package com.mercysUtils.library.Blocks.Custom;

import com.mercysUtils.library.Blocks.Entity.AugmentTableEntityClass;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AugmentTableClass extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16,12,16);

    public AugmentTableClass(Properties properties) {
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
            if (blockEntity instanceof AugmentTableEntityClass) {
                ((AugmentTableEntityClass) blockEntity).drops();
            }
        }

        super.onRemove(blockState, level, blockPos, newState1, bool);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, BlockState blockState) {
        return new AugmentTableEntityClass(blockPos, blockState);
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
            System.out.println(blockEntity + "Augment Table Class Print");
            if (blockEntity instanceof AugmentTableEntityClass workstationEntity) {
                // 👇 This line actually opens your menu and sends the BlockPos through the buffer
                net.minecraftforge.network.NetworkHooks.openScreen(
                        (net.minecraft.server.level.ServerPlayer) player,
                        workstationEntity,
                        buf -> buf.writeBlockPos(blockPos)
                );
            } else {
                throw new IllegalStateException("Missing container provider for AugmentTableEntity!");
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }


}
