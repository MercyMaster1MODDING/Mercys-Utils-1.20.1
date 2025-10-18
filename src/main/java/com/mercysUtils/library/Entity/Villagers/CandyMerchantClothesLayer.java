package com.mercysUtils.library.Entity.Villagers;

import com.mercysUtils.library.Entity.Villagers.CandyMerchantEntity;
import com.mercysUtils.library.MercysUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class CandyMerchantClothesLayer extends RenderLayer<CandyMerchantEntity, CandyMerchant<CandyMerchantEntity>> {


    private static final ResourceLocation CLOTHES_TEXTURE =
            new ResourceLocation(MercysUtils.MOD_ID, "textures/entity/candy_merchant_clothes.png");

    public CandyMerchantClothesLayer(RenderLayerParent<CandyMerchantEntity, CandyMerchant<CandyMerchantEntity>> parent) {
        super(parent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, CandyMerchantEntity entity,
                       float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks,
                       float netHeadYaw, float headPitch) {

        // Renders the overlay texture on top of the base model
        VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(CLOTHES_TEXTURE));
        this.getParentModel().renderToBuffer(poseStack, vertexconsumer, packedLight,
                LivingEntityRenderer.getOverlayCoords(entity, 0.0F),
                1.0F, 1.0F, 1.0F, 1.0F);
    }
}
