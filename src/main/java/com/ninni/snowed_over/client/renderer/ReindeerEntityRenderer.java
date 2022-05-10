package com.ninni.snowed_over.client.renderer;

import com.ninni.snowed_over.client.init.SnowedOverEntityModelLayers;
import com.ninni.snowed_over.client.model.entity.ReindeerEntityModel;
import com.ninni.snowed_over.client.renderer.entity.feature.ReindeerArmorFeatureRenderer;
import com.ninni.snowed_over.client.renderer.entity.feature.ReindeerFestiveOverlayFeatureRenderer;
import com.ninni.snowed_over.entity.ReindeerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.SaddleFeatureRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.Calendar;

import static com.ninni.snowed_over.SnowedOver.*;

@Environment(EnvType.CLIENT)
public class ReindeerEntityRenderer<T extends LivingEntity> extends MobEntityRenderer<ReindeerEntity, ReindeerEntityModel<ReindeerEntity>> {
    public static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/entity/reindeer/reindeer.png");
    public static final Identifier TEXTURE_FESTIVE = new Identifier(MOD_ID, "textures/entity/reindeer/reindeer_festive.png");
    private boolean festivity;
    private static ReindeerEntity reindeer;

    public ReindeerEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new ReindeerEntityModel<>(ctx.getPart(SnowedOverEntityModelLayers.REINDEER)), 0.6F);
        this.addFeature(new ReindeerArmorFeatureRenderer<>(this, new ReindeerEntityModel<>(ctx.getPart(SnowedOverEntityModelLayers.REINDEER_ARMOR)), new Identifier(MOD_ID, "textures/entity/reindeer/reindeer_armor.png")));
        this.addFeature(new SaddleFeatureRenderer<>(this, new ReindeerEntityModel<>(ctx.getPart(SnowedOverEntityModelLayers.REINDEER_ARMOR)), new Identifier(MOD_ID, "textures/entity/reindeer/reindeer_saddle.png")));

        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.MONTH) + 1 == 12 && calendar.get(Calendar.DATE) >= 24 && calendar.get(Calendar.DATE) <= 26 || calendar.get(Calendar.MONTH) + 1 == 8 && calendar.get(Calendar.DATE) == 2 || "Rudolph".equals(Formatting.strip(reindeer.getName().getString()))) {
            this.addFeature(new ReindeerFestiveOverlayFeatureRenderer(this));
            this.festivity = true;
        } //checks if it's Christmas, if it's the world reindeer day or if the reindeer's caller Rudolph to apply a custom skin
    }

    @Override
    public Identifier getTexture(ReindeerEntity entity) {
        String string = Formatting.strip(entity.getName().getString());
        if (festivity || "Rudolph".equals(Formatting.strip(entity.getName().getString()))) { return TEXTURE_FESTIVE;}
        return TEXTURE;
    }
}

