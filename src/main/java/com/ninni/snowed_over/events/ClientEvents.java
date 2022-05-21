package com.ninni.snowed_over.events;

import com.ninni.snowed_over.SnowedOver;
import com.ninni.snowed_over.client.init.SnowedOverEntityModelLayers;
import com.ninni.snowed_over.client.model.entity.PenguinEntityModel;
import com.ninni.snowed_over.client.model.entity.ReindeerEntityModel;
import com.ninni.snowed_over.client.renderer.PenguinEntityRenderer;
import com.ninni.snowed_over.client.renderer.ReindeerEntityRenderer;
import com.ninni.snowed_over.init.SnowedOverEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SnowedOver.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SnowedOverEntityModelLayers.REINDEER, ReindeerEntityModel::getTexturedModelData);
        event.registerLayerDefinition(SnowedOverEntityModelLayers.REINDEER_ARMOR, ReindeerEntityModel::getTexturedModelData);
        event.registerLayerDefinition(SnowedOverEntityModelLayers.PENGUIN, PenguinEntityModel::getTexturedModelData);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SnowedOverEntityTypes.REINDEER.get(), ReindeerEntityRenderer::new);
        event.registerEntityRenderer(SnowedOverEntityTypes.PENGUIN.get(), PenguinEntityRenderer::new);
    }

}
