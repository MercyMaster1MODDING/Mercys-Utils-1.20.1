package com.mercysUtils.library.Entity;

import com.mercysUtils.library.Entity.Custom.StarGolemEntity;
import com.mercysUtils.library.Entity.Throwable.ModTridentEntity;
import com.mercysUtils.library.Entity.Villagers.CandyMerchantEntity;
import com.mercysUtils.library.MercysUtils;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntity {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MercysUtils.MOD_ID);

    public static final RegistryObject<EntityType<StarGolemEntity>> STAR_GOLEM_ENTITY =
            ENTITY_TYPES.register("star_golem_entity", () -> EntityType.Builder.of(StarGolemEntity::new, MobCategory.CREATURE)
                    .sized(1.4f, 2.7f).build("star_golem"));

    public static final RegistryObject<EntityType<CandyMerchantEntity>> CANDY_MERCHANT_ENTITY =
            ENTITY_TYPES.register("candy_merchant_entity", () -> EntityType.Builder.of(CandyMerchantEntity::new, MobCategory.CREATURE)
                    .sized(1.4f, 2.7f).build("candy_merchant"));

    public static final RegistryObject<EntityType<ModTridentEntity>> POSEIDONS_TRIDENT_ENTITY =
            ENTITY_TYPES.register("poseidons_trident",
                    () -> EntityType.Builder.<ModTridentEntity>of(ModTridentEntity::new, MobCategory.MISC)
                            .sized(0.5F, 0.5F)
                            .clientTrackingRange(4)
                            .updateInterval(20)
                            .build("poseidons_trident")
            );



    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
