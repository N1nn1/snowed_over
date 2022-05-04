package com.ninni.snowed_over;

import com.google.common.collect.ImmutableMap;
import com.ninni.snowed_over.client.init.SnowedOverEntityModelLayers;
import com.ninni.snowed_over.client.model.entity.PenguinEntityModel;
import com.ninni.snowed_over.client.model.entity.ReindeerEntityModel;
import com.ninni.snowed_over.client.renderer.PenguinEntityRenderer;
import com.ninni.snowed_over.client.renderer.ReindeerEntityRenderer;
import com.ninni.snowed_over.entity.SnowedOverEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class SnowedOverClient implements ClientModInitializer {

    @Override
    @SuppressWarnings({ "deprecation" })
    public void onInitializeClient() {
        EntityRendererRegistry erri = EntityRendererRegistry.INSTANCE;
        erri.register(SnowedOverEntities.REINDEER, ReindeerEntityRenderer::new);
        erri.register(SnowedOverEntities.PENGUIN, PenguinEntityRenderer::new);

        new ImmutableMap.Builder<EntityModelLayer, EntityModelLayerRegistry.TexturedModelDataProvider>()
            .put(SnowedOverEntityModelLayers.REINDEER, ReindeerEntityModel::getTexturedModelData)
            .put(SnowedOverEntityModelLayers.REINDEER_ARMOR, ReindeerEntityModel::getTexturedModelData)
            .put(SnowedOverEntityModelLayers.PENGUIN, PenguinEntityModel::getTexturedModelData)
            .build().forEach(EntityModelLayerRegistry::registerModelLayer);
    }
}
