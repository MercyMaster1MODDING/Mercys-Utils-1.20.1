package com.mercysUtils.library.Blocks.Entity;

import com.mercysUtils.library.Blocks.ModBlocks;
import com.mercysUtils.library.MercysUtils;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MercysUtils.MOD_ID);

    public static final RegistryObject<BlockEntityType<TutorialBlockEntityWorkstationEntity>> TUTORIAL_BLOCK_ENTITY_WORKSTATION_BE =
            BLOCK_ENTITIES.register("tutorial_block_entity_workstation_be", () ->
                    BlockEntityType.Builder.of(TutorialBlockEntityWorkstationEntity::new,
                            ModBlocks.TUTORIAL_BLOCK_WORKSTATION.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
