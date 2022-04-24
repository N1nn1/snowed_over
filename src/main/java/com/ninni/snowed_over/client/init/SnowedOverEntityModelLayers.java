package com.ninni.snowed_over.client.init;

import com.ninni.snowed_over.mixin.client.EntityModelLayersInvoker;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import static com.ninni.snowed_over.SnowedOver.*;

public class SnowedOverEntityModelLayers {

    public static final EntityModelLayer REINDEER = registerMain("reindeer");
    public static final EntityModelLayer PENGUIN  = registerMain("penguin");

    private static EntityModelLayer registerMain(String id) {
        return EntityModelLayersInvoker.register(new Identifier(MOD_ID, id).toString(), "main");
    }

    private static EntityModelLayer register(String id, String layer) {
        return EntityModelLayersInvoker.register(new Identifier(MOD_ID, id).toString(), layer);
    }
}

