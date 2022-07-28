package com.ninni.snowed_over;

import com.google.common.collect.ImmutableMap;
import com.ninni.snowed_over.client.init.SnowedOverEntityModelLayers;
import com.ninni.snowed_over.client.model.entity.PenguinEntityModel;
import com.ninni.snowed_over.client.model.entity.ReindeerEntityModel;
import com.ninni.snowed_over.client.renderer.PenguinEntityRenderer;
import com.ninni.snowed_over.client.renderer.ReindeerEntityRenderer;
import com.ninni.snowed_over.client.screen.ReindeerScreen;
import com.ninni.snowed_over.client.screen.ReindeerScreenHandler;
import com.ninni.snowed_over.entity.ReindeerEntity;
import com.ninni.snowed_over.entity.SnowedOverEntities;
import com.ninni.snowed_over.network.SnowedOverPacketIdentifiers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.world.World;

import java.util.Optional;

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

        ClientPlayNetworking.registerGlobalReceiver(SnowedOverPacketIdentifiers.OPEN_REINDEER_SCREEN, (client, handler, buf, responseSender) -> {
            int id = buf.readInt();
            World level = client.world;
            Optional.ofNullable(level).ifPresent(world -> {
                Entity entity = world.getEntityById(id);
                if (entity instanceof ReindeerEntity reindeer) {
                    int slotCount = buf.readInt();
                    int syncId = buf.readInt();
                    ClientPlayerEntity clientPlayerEntity = client.player;
                    SimpleInventory simpleInventory = new SimpleInventory(slotCount);
                    ReindeerScreenHandler reindeerScreenHandler = new ReindeerScreenHandler(syncId, clientPlayerEntity.getInventory(), simpleInventory, reindeer);
                    clientPlayerEntity.currentScreenHandler = reindeerScreenHandler;
                    client.execute(() -> client.setScreen(new ReindeerScreen(reindeerScreenHandler, clientPlayerEntity.getInventory(), reindeer)));
                }
            });
        });
    }
}
