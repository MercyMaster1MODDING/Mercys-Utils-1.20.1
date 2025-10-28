package com.mercysUtils.library.Blocks.Custom.Pipes;

import com.mercysUtils.library.Blocks.Entity.PipesEntity.PipesEntityClass;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class PipesBlockClass extends BaseEntityBlock {

    public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 12, 16);

    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty EAST  = BooleanProperty.create("east");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty WEST  = BooleanProperty.create("west");
    public static final BooleanProperty UP    = BooleanProperty.create("up");
    public static final BooleanProperty DOWN  = BooleanProperty.create("down");

    public static final EnumProperty<PipesEnum> SHAPE_PROP = EnumProperty.create("shape", PipesEnum.class);

    public PipesBlockClass(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(NORTH, false)
                .setValue(EAST, false)
                .setValue(SOUTH, false)
                .setValue(WEST, false)
                .setValue(UP, false)
                .setValue(DOWN, false)
                .setValue(SHAPE_PROP, PipesEnum.SINGLE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, SHAPE_PROP);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PipesEntityClass(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return null;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = this.defaultBlockState();

        // Detect neighbors
        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = pos.relative(dir);
            BlockEntity neighbor = level.getBlockEntity(neighborPos);
            if (neighbor instanceof PipesEntityClass) {
                state = setConnection(state, dir, true);
            }
        }

        // Automatically calculate correct shape
        state = updateShape(state);

        return state;
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        boolean changed = false;

        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = pos.relative(dir);
            BlockEntity neighbor = level.getBlockEntity(neighborPos);
            boolean connected = neighbor instanceof PipesEntityClass;
            if (state.getValue(getPropertyForDirection(dir)) != connected) {
                state = setConnection(state, dir, connected);
                changed = true;
            }
        }

        if (changed) {
            state = updateShape(state);
            level.setBlock(pos, state, 3); // Update blockstate and render
        }
    }

    private BooleanProperty getPropertyForDirection(Direction dir) {
        return switch (dir) {
            case NORTH -> NORTH;
            case EAST  -> EAST;
            case SOUTH -> SOUTH;
            case WEST  -> WEST;
            case UP    -> UP;
            case DOWN  -> DOWN;
        };
    }

    private BlockState setConnection(BlockState state, Direction dir, boolean connected) {
        return state.setValue(getPropertyForDirection(dir), connected);
    }

    /** Automatically determines the correct PipeShape based on active connections */
    private BlockState updateShape(BlockState state) {
        int connections = 0;
        for (Direction dir : Direction.values()) {
            if (state.getValue(getPropertyForDirection(dir))) connections++;
        }

        PipesEnum shape;
        if (connections == 0) {
            shape = PipesEnum.SINGLE;
        } else if (connections == 1) {
            shape = PipesEnum.MIDDLE;
        } else if (connections == 2) {
            // Could be a corner or a straight connecting pipe
            if ((state.getValue(NORTH) && state.getValue(SOUTH)) || (state.getValue(EAST) && state.getValue(WEST)) || (state.getValue(UP) && state.getValue(DOWN))) {
                shape = PipesEnum.CONNECTING_LONG;
            } else {
                shape = PipesEnum.CORNER;
            }
        } else {
            shape = PipesEnum.CONNECTING_LONG;
        }

        return state.setValue(SHAPE_PROP, shape);
    }

    @Override
    public VoxelShape getShape(BlockState state, net.minecraft.world.level.BlockGetter level, BlockPos pos, net.minecraft.world.phys.shapes.CollisionContext context) {
        return SHAPE;
    }
}
