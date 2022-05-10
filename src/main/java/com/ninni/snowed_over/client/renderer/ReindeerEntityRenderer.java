package com.ninni.snowed_over.client.renderer;

import com.ninni.snowed_over.client.init.SnowedOverEntityModelLayers;
import com.ninni.snowed_over.client.model.entity.ReindeerEntityModel;
import com.ninni.snowed_over.client.renderer.entity.feature.ReindeerArmorFeatureRenderer;
import com.ninni.snowed_over.entity.ReindeerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.SaddleFeatureRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

import static com.ninni.snowed_over.SnowedOver.*;

@Environment(EnvType.CLIENT)
public class ReindeerEntityRenderer<T extends LivingEntity> extends MobEntityRenderer<ReindeerEntity, ReindeerEntityModel<ReindeerEntity>> {
    public static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/entity/reindeer/reindeer.png");

    public ReindeerEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new ReindeerEntityModel<>(ctx.getPart(SnowedOverEntityModelLayers.REINDEER)), 0.6F);
        this.addFeature(new ReindeerArmorFeatureRenderer<>(this, new ReindeerEntityModel<>(ctx.getPart(SnowedOverEntityModelLayers.REINDEER_ARMOR)), new Identifier(MOD_ID, "textures/entity/reindeer/reindeer_armor.png")));
        this.addFeature(new SaddleFeatureRenderer<>(this, new ReindeerEntityModel<>(ctx.getPart(SnowedOverEntityModelLayers.REINDEER_ARMOR)), new Identifier(MOD_ID, "textures/entity/reindeer/reindeer_saddle.png")));
    }

    @Override
    public Identifier getTexture(ReindeerEntity entity) { return TEXTURE; }
}

