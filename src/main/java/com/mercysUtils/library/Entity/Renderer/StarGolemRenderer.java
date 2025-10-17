package com.mercysUtils.library.Entity.Renderer;

import com.mercysUtils.library.Entity.Custom.StarGolemEntity;
import com.mercysUtils.library.Interfaces.IronGolemInterface;
import com.mercysUtils.library.MercysUtils;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IronGolemRenderer;
import net.minecraft.resources.ResourceLocation;

public class StarGolemRenderer extends IronGolemRenderer implements IronGolemInterface {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(MercysUtils.MOD_ID, "textures/entity/star_golem.png");

    public StarGolemRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(StarGolemEntity entity) {
        System.out.println("StarGolemRenderer texture loaded!");
        return TEXTURE;
    }
}
