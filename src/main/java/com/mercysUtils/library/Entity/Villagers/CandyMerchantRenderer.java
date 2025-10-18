package com.mercysUtils.library.Entity.Villagers;

import com.mercysUtils.library.MercysUtils;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CandyMerchantRenderer extends MobRenderer<CandyMerchantEntity, CandyMerchant<CandyMerchantEntity>> {

    // Base skin texture
    private static final ResourceLocation BASE_TEXTURE =
            new ResourceLocation(MercysUtils.MOD_ID, "textures/entity/villager.png");

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(MercysUtils.MOD_ID, "textures/entity/candy_merchant_clothes.png");

    public CandyMerchantRenderer(EntityRendererProvider.Context context) {

        super(context, new CandyMerchant<>(context.bakeLayer(CandyMerchant.LAYER_LOCATION)), 0.5F);

        this.addLayer(new CandyMerchantClothesLayer(this));

    }





    @Override
    public ResourceLocation getTextureLocation(CandyMerchantEntity candyMerchantEntity) {
        return BASE_TEXTURE;
    }

}
