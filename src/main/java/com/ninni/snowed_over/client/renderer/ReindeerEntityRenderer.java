package com.ninni.snowed_over.client.renderer;

import com.ninni.snowed_over.client.init.SnowedOverEntityModelLayers;
import com.ninni.snowed_over.client.model.entity.ReindeerEntityModel;
import com.ninni.snowed_over.entity.ReindeerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

import static com.ninni.snowed_over.SnowedOver.*;

public class ReindeerEntityRenderer<T extends LivingEntity> extends MobEntityRenderer<ReindeerEntity, ReindeerEntityModel<ReindeerEntity>> {
    public static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/entity/reindeer/reindeer.png");
    public static final Identifier SADDLED_TEXTURE = new Identifier(MOD_ID, "textures/entity/reindeer/reindeer_saddle.png");

    public ReindeerEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new ReindeerEntityModel<>(ctx.getPart(SnowedOverEntityModelLayers.REINDEER)), 0.6F);
    }

    @Override
    public Identifier getTexture(ReindeerEntity entity) {
        return entity.isSaddled() ? SADDLED_TEXTURE : TEXTURE;
    }
}

