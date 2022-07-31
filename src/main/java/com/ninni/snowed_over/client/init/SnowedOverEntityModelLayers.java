package com.ninni.snowed_over.client.init;

import com.ninni.snowed_over.client.model.entity.PenguinEntityModel;
import com.ninni.snowed_over.client.model.entity.ReindeerEntityModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import static com.ninni.snowed_over.SnowedOver.*;

@Environment(EnvType.CLIENT)
public interface SnowedOverEntityModelLayers {

    EntityModelLayer REINDEER = main("reindeer", ReindeerEntityModel::getTexturedModelData);
    EntityModelLayer REINDEER_ARMOR = register("reindeer", "armor", ReindeerEntityModel::getTexturedModelData);
    EntityModelLayer PENGUIN  = main("penguin", PenguinEntityModel::getTexturedModelData);

    private static EntityModelLayer register(String id, String name, EntityModelLayerRegistry.TexturedModelDataProvider provider) {
        EntityModelLayer layer = new EntityModelLayer(new Identifier(MOD_ID, id), name);
        EntityModelLayerRegistry.registerModelLayer(layer, provider);
        return layer;
    }

    private static EntityModelLayer main(String id, EntityModelLayerRegistry.TexturedModelDataProvider provider) {
        return register(id, "main", provider);
    }
}

