package com.mercysUtils.library.Entity.Custom;// Made with Blockbench 5.0.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mercysUtils.library.MercysUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

import net.minecraft.world.entity.animal.IronGolem;

public class StarGolem <T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MercysUtils.MOD_ID, "star_golem_entity"),  "main");
	private final ModelPart star_golem;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart right_arm;
	private final ModelPart left_arm;
	private final ModelPart right_leg;
	private final ModelPart left_leg;

	public StarGolem (ModelPart root) {
		this.star_golem = root.getChild("star_golem");
		this.body = this.star_golem.getChild("body");
		this.head = this.star_golem.getChild("head");
		this.right_arm = this.star_golem.getChild("right_arm");
		this.left_arm = this.star_golem.getChild("left_arm");
		this.right_leg = this.star_golem.getChild("right_leg");
		this.left_leg = this.star_golem.getChild("left_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition star_golem = partdefinition.addOrReplaceChild("star_golem", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = star_golem.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 40).addBox(-9.0F, -2.0F, -6.0F, 18.0F, 12.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(0, 70).addBox(-4.5F, 10.0F, -3.0F, 9.0F, 5.0F, 6.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -31.0F, 0.0F));

		PartDefinition head = star_golem.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -12.0F, -5.5F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(24, 0).addBox(-1.0F, -5.0F, -7.5F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -31.0F, -2.0F));

		PartDefinition right_arm = star_golem.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(60, 21).addBox(-13.0F, -2.5F, -3.0F, 4.0F, 30.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -31.0F, 0.0F));

		PartDefinition left_arm = star_golem.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(60, 58).addBox(9.0F, -2.5F, -3.0F, 4.0F, 30.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -31.0F, 0.0F));

		PartDefinition right_leg = star_golem.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(37, 0).addBox(-3.5F, -3.0F, -3.0F, 6.0F, 16.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -13.0F, 0.0F));

		PartDefinition left_leg = star_golem.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(60, 0).addBox(-3.5F, -3.0F, -3.0F, 6.0F, 16.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -13.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		// Head rotation (looking around)
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);

		// Walking animation
		this.right_leg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.left_leg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

		// Arm swing animation
		this.right_arm.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		this.left_arm.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;

		// Idle arm motion (subtle breathing)
		this.right_arm.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
		this.left_arm.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;

		// ----- Attack animation (Iron Golem style) -----
		if (entity instanceof IronGolem golem) {
			float attackTime = golem.attackAnim - ageInTicks;
			if (attackTime > 0.0F) {
				float f = (1.0F - attackTime / 10.0F);
				f = Mth.clamp(f, 0.0F, 1.0F);
				f = f * f;
				float angle = -(float)Math.PI / 2.0F + f * (float)Math.PI * 1.5F;
				this.right_arm.xRot = angle;
				this.left_arm.xRot = angle;
			}
		}
	}



	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		star_golem.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return star_golem;
	}
}