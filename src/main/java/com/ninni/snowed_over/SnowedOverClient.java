package com.ninni.snowed_over;

import com.google.common.reflect.Reflection;
import com.ninni.snowed_over.client.init.SnowedOverEntityModelLayers;
import com.ninni.snowed_over.client.renderer.PenguinEntityRenderer;
import com.ninni.snowed_over.client.renderer.ReindeerEntityRenderer;
import com.ninni.snowed_over.client.screen.ReindeerScreen;
import com.ninni.snowed_over.client.screen.ReindeerScreenHandler;
import com.ninni.snowed_over.entity.ReindeerEntity;
import com.ninni.snowed_over.entity.SnowedOverEntities;
import com.ninni.snowed_over.network.SnowedOverPacketIdentifiers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.world.World;

import java.util.Optional;

public class SnowedOverClient implements ClientModInitializer {

    @Override
    @SuppressWarnings({ "UnstableApiUsage" })
    public void onInitializeClient() {
        Reflection.initialize(SnowedOverEntityModelLayers.class);
        EntityRendererRegistry.register(SnowedOverEntities.REINDEER, ReindeerEntityRenderer::new);
        EntityRendererRegistry.register(SnowedOverEntities.PENGUIN, PenguinEntityRenderer::new);

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
