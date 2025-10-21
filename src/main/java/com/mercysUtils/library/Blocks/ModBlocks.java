package com.mercysUtils.library.Blocks;

import com.mercysUtils.library.Blocks.Custom.AugmentTableClass;
import com.mercysUtils.library.Blocks.Custom.StoveTopClass;
import com.mercysUtils.library.Blocks.TreeBlocks.ModFlammableRotateablePillar;
import com.mercysUtils.library.Datagen.Trees.CandyAppleTree;
import com.mercysUtils.library.Datagen.Trees.MerciniumAppleTree;
import com.mercysUtils.library.MercysUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks extends Blocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MercysUtils.MOD_ID);

    //Register Blocks
    public static final RegistryObject<Block> MERCINIUM_ORE = BLOCKS.register("mercinium_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .strength(15)
                    .explosionResistance(1200)));

    public static final RegistryObject<Block> JELLYINIUM_ORE = BLOCKS.register("jellyinium_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_RED)
                    .strength(15)
                    .explosionResistance(1200)));

    //Register Saplings and Wood
    public static final RegistryObject<Block> MERCINIUM_APPLE_TREE_SAPLING = BLOCKS.register("mercinium_apple_tree_sapling",
            () -> new SaplingBlock(new MerciniumAppleTree(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    //Register Wood Blocks
    public static final RegistryObject<Block> MERCINIUM_APPLE_TREE_LOG = BLOCKS.register("mercinium_apple_tree_log",
            () -> new ModFlammableRotateablePillar(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)
                    .strength(3)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> MERCINIUM_APPLE_WOOD = BLOCKS.register("mercinium_apple_tree_wood",
            () -> new ModFlammableRotateablePillar(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
                    .strength(3)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> STRIPPED_MERCINIUM_APPLE_TREE_LOG = BLOCKS.register("stripped_mercinium_apple_tree_log",
            () -> new ModFlammableRotateablePillar(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)
                    .strength(3)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> STRIPPED_MERCINIUM_APPLE_WOOD = BLOCKS.register("stripped_mercinium_apple_wood",
            () -> new ModFlammableRotateablePillar(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)
                    .strength(3)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> MERCINIUM_APPLE_TREE_PLANKS = BLOCKS.register("mercinium_apple_tree_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;


                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<LeavesBlock> MERCINIUM_APPLE_TREE_LEAVES = BLOCKS.register("mercinium_apple_tree_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;


                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });
    public static final RegistryObject<Block> CANDY_APPLE_TREE_SAPLING = BLOCKS.register("candy_apple_tree_sapling",
            () -> new SaplingBlock(new CandyAppleTree(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    //Register Wood Blocks
    public static final RegistryObject<Block> CANDY_APPLE_TREE_LOG = BLOCKS.register("candy_apple_tree_log",
            () -> new ModFlammableRotateablePillar(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)
                    .strength(3)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CANDY_APPLE_WOOD = BLOCKS.register("candy_apple_tree_wood",
            () -> new ModFlammableRotateablePillar(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
                    .strength(3)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> STRIPPED_CANDY_APPLE_TREE_LOG = BLOCKS.register("stripped_candy_apple_tree_log",
            () -> new ModFlammableRotateablePillar(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)
                    .strength(3)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> STRIPPED_CANDY_APPLE_WOOD = BLOCKS.register("stripped_candy_apple_wood",
            () -> new ModFlammableRotateablePillar(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)
                    .strength(3)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CANDY_APPLE_TREE_PLANKS = BLOCKS.register("candy_apple_tree_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;


                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<LeavesBlock> CANDY_APPLE_TREE_LEAVES = BLOCKS.register("candy_apple_tree_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;


                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final RegistryObject<Block> STOVE_TOP_BLOCK = BLOCKS.register("stove_top_block",
            () -> new StoveTopClass(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));

    public static final RegistryObject<Block> AUGMENT_TABLE_BLOCK = BLOCKS.register("augment_table_block",
            () -> new AugmentTableClass(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));



    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

