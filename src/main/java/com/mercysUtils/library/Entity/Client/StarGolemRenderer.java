package com.mercysUtils.library.Entity.Client;

import com.mercysUtils.library.Entity.Custom.StarGolemEntity;
import com.mercysUtils.library.Interfaces.IronGolemInterface;
import com.mercysUtils.library.MercysUtils;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IronGolemRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class StarGolemRenderer extends MobRenderer<StarGolemEntity, StarGolem<StarGolemEntity>> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(MercysUtils.MOD_ID, "textures/entity/star_golem.png");

    public StarGolemRenderer(EntityRendererProvider.Context context) {

        super(context, new StarGolem<>(context.bakeLayer(StarGolem.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(StarGolemEntity entity) {
       return TEXTURE;
    }
}
