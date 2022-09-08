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
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
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
            Level level = client.level;
            Optional.ofNullable(level).ifPresent(world -> {
                Entity entity = world.getEntity(id);
                if (entity instanceof ReindeerEntity reindeer) {
                    int slotCount = buf.readInt();
                    int syncId = buf.readInt();
                    LocalPlayer clientPlayerEntity = client.player;
                    SimpleContainer simpleInventory = new SimpleContainer(slotCount);
                    ReindeerScreenHandler reindeerScreenHandler = new ReindeerScreenHandler(syncId, clientPlayerEntity.getInventory(), simpleInventory, reindeer);
                    clientPlayerEntity.containerMenu = reindeerScreenHandler;
                    client.execute(() -> client.setScreen(new ReindeerScreen(reindeerScreenHandler, clientPlayerEntity.getInventory(), reindeer)));
                }
            });
        });

        FabricLoader.getInstance().getModContainer("snowed_over").ifPresent(modContainer -> {
            ResourceManagerHelper.registerBuiltinResourcePack(new ResourceLocation("snowed_over:modcompat"), modContainer, ResourcePackActivationType.ALWAYS_ENABLED);
        });
    }
}
