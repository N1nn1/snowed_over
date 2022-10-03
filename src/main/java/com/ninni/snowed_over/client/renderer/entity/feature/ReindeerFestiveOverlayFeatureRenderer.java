package com.ninni.snowed_over.client.renderer.entity.feature;

import com.ninni.snowed_over.client.model.entity.ReindeerEntityModel;
import com.ninni.snowed_over.client.renderer.ReindeerEntityRenderer;
import com.ninni.snowed_over.entity.ReindeerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

import static com.ninni.snowed_over.SnowedOver.*;

import com.mojang.blaze3d.vertex.PoseStack;

@Environment(EnvType.CLIENT)
public class ReindeerFestiveOverlayFeatureRenderer extends EyesLayer<ReindeerEntity, ReindeerEntityModel<ReindeerEntity>> {
    private static final RenderType OVERLAY = RenderType.eyes(new ResourceLocation(MOD_ID, "textures/entity/reindeer/reindeer_festive_overlay.png"));

    public ReindeerFestiveOverlayFeatureRenderer(RenderLayerParent<ReindeerEntity, ReindeerEntityModel<ReindeerEntity>> context) { super(context); }

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, int light, ReindeerEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (ReindeerEntityRenderer.isFestive(entity)) super.render(matrices, vertexConsumers, light, entity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch);
    }

    @Override
    public RenderType renderType() {
        return OVERLAY;
    }
}
