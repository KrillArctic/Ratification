package com.krillarctic.ratify.entity.client;

import com.krillarctic.ratify.RatMod;
import com.krillarctic.ratify.entity.custom.RatEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.minecraft.resources.ResourceLocation;

public class RatRenderer extends MobRenderer<RatEntity, RatModel<RatEntity>> {
    public RatRenderer(EntityRendererProvider.Context context) {
        super(context, new RatModel<>(context.bakeLayer((RatModel.LAYER_LOCATION))), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(RatEntity ratEntity) {
        return ResourceLocation.fromNamespaceAndPath(RatMod.MOD_ID, "textures/entity/rat/rat.png");
    }

    @Override
    public void render(RatEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.45f, 0.45f, 0.45f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
