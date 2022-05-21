package com.ninni.snowed_over.client.init;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

import static com.ninni.snowed_over.SnowedOver.MOD_ID;

public class SnowedOverEntityModelLayers {

    public static final ModelLayerLocation REINDEER = registerMain("reindeer");
    public static final ModelLayerLocation REINDEER_ARMOR = register("reindeer", "armor");
    public static final ModelLayerLocation PENGUIN  = registerMain("penguin");

    private static ModelLayerLocation registerMain(String id) {
        return new ModelLayerLocation(new ResourceLocation(MOD_ID, id), "main");
    }

    private static ModelLayerLocation register(String id, String layer) {
        return new ModelLayerLocation(new ResourceLocation(MOD_ID, id), layer);
    }
}

