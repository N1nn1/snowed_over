package com.ninni.snowed_over.client.renderer;

import com.ninni.snowed_over.client.init.SnowedOverEntityModelLayers;
import com.ninni.snowed_over.client.model.entity.PenguinEntityModel;
import com.ninni.snowed_over.entity.PenguinEntity;
import com.ninni.snowed_over.entity.PenguinMood;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

import static com.ninni.snowed_over.SnowedOver.*;

import com.mojang.blaze3d.vertex.PoseStack;

@Environment(EnvType.CLIENT)
public class PenguinEntityRenderer<T extends LivingEntity> extends MobRenderer<PenguinEntity, PenguinEntityModel<PenguinEntity>> {
    public static final ResourceLocation HAPPY_TEXTURE    = new ResourceLocation(MOD_ID, "textures/entity/penguin/penguin_happy.png");
    public static final ResourceLocation CONFUSED_TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/penguin/penguin_confused.png");
    public static final ResourceLocation FOCUSED_TEXTURE  = new ResourceLocation(MOD_ID, "textures/entity/penguin/penguin_focused.png");
    public static final ResourceLocation AGITATED_TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/penguin/penguin_agitated.png");

    public static final ResourceLocation BABY_HAPPY_TEXTURE    = new ResourceLocation(MOD_ID, "textures/entity/penguin/hatchling/penguin_happy.png");
    public static final ResourceLocation BABY_CONFUSED_TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/penguin/hatchling/penguin_confused.png");
    public static final ResourceLocation BABY_FOCUSED_TEXTURE  = new ResourceLocation(MOD_ID, "textures/entity/penguin/hatchling/penguin_focused.png");
    public static final ResourceLocation BABY_AGITATED_TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/penguin/hatchling/penguin_agitated.png");

    public PenguinEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new PenguinEntityModel<>(ctx.bakeLayer(SnowedOverEntityModelLayers.PENGUIN)), 0.3F);
    }

    @Override
    protected void setupRotations(PenguinEntity penguin, PoseStack matrixStack, float f, float g, float h) {
        super.setupRotations(penguin, matrixStack, f, g, h);
        if (penguin.isInWater() && !penguin.hasEgg()) {
            matrixStack.translate(0, 0.2, 0);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(PenguinEntity entity) {
        PenguinMood mood = entity.getMood();
        if (entity.isBaby()) {
            return switch (mood) {
                case AGITATED -> BABY_AGITATED_TEXTURE;
                case FOCUSED -> BABY_FOCUSED_TEXTURE;
                case CONFUSED -> BABY_CONFUSED_TEXTURE;
                default -> BABY_HAPPY_TEXTURE;
            };
        } else
            return switch (mood) {
            case AGITATED -> AGITATED_TEXTURE;
            case FOCUSED -> FOCUSED_TEXTURE;
            case CONFUSED -> CONFUSED_TEXTURE;
            default -> HAPPY_TEXTURE;
        };
    }
}

