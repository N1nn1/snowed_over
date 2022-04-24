package com.ninni.snowed_over.client.renderer;

import com.ninni.snowed_over.client.init.SnowedOverEntityModelLayers;
import com.ninni.snowed_over.client.model.entity.PenguinEntityModel;
import com.ninni.snowed_over.entity.PenguinEntity;
import com.ninni.snowed_over.entity.PenguinMood;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

import static com.ninni.snowed_over.SnowedOver.*;

@Environment(EnvType.CLIENT)
public class PenguinEntityRenderer<T extends LivingEntity> extends MobEntityRenderer<PenguinEntity, PenguinEntityModel<PenguinEntity>> {
    public static final Identifier HAPPY_TEXTURE    = new Identifier(MOD_ID, "textures/entity/penguin/penguin_happy.png");
    public static final Identifier CONFUSED_TEXTURE = new Identifier(MOD_ID, "textures/entity/penguin/penguin_confused.png");
    public static final Identifier FOCUSED_TEXTURE  = new Identifier(MOD_ID, "textures/entity/penguin/penguin_focused.png");
    public static final Identifier ANGRY_TEXTURE    = new Identifier(MOD_ID, "textures/entity/penguin/penguin_angry.png");
    public static final Identifier AGITATED_TEXTURE    = new Identifier(MOD_ID, "textures/entity/penguin/penguin_agitated.png");

    public PenguinEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new PenguinEntityModel<>(ctx.getPart(SnowedOverEntityModelLayers.PENGUIN)), 0.3F);
    }

    @Override
    public Identifier getTexture(PenguinEntity entity) {
        PenguinMood mood = entity.getMood();
        return switch (mood) {
            case AGITATED -> AGITATED_TEXTURE;
            case ANGRY -> ANGRY_TEXTURE;
            case FOCUSED -> FOCUSED_TEXTURE;
            case CONFUSED -> CONFUSED_TEXTURE;
            default -> HAPPY_TEXTURE;
        };
    }
}

