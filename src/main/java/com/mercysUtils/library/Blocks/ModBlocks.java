package com.mercysUtils.library.Blocks;

import com.mercysUtils.library.MercysUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraftforge.registries.ForgeRegistries.BLOCKS;

public class ModBlocks extends Blocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MercysUtils.MOD_ID);

    public static final RegistryObject<Block> MERCINIUM_ORE = BLOCKS.register("mercinium_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .strength(50)
                    .explosionResistance(1200)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

