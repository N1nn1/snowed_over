package com.ninni.snowed_over.client.init;

import com.ninni.snowed_over.client.model.entity.PenguinEntityModel;
import com.ninni.snowed_over.client.model.entity.ReindeerEntityModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

import static com.ninni.snowed_over.SnowedOver.*;

@Environment(EnvType.CLIENT)
public interface SnowedOverEntityModelLayers {

    ModelLayerLocation REINDEER = main("reindeer", ReindeerEntityModel::getTexturedModelData);
    ModelLayerLocation REINDEER_ARMOR = register("reindeer", "armor", ReindeerEntityModel::getTexturedModelData);
    ModelLayerLocation PENGUIN  = main("penguin", PenguinEntityModel::getTexturedModelData);

    private static ModelLayerLocation register(String id, String name, EntityModelLayerRegistry.TexturedModelDataProvider provider) {
        ModelLayerLocation layer = new ModelLayerLocation(new ResourceLocation(MOD_ID, id), name);
        EntityModelLayerRegistry.registerModelLayer(layer, provider);
        return layer;
    }

    private static ModelLayerLocation main(String id, EntityModelLayerRegistry.TexturedModelDataProvider provider) {
        return register(id, "main", provider);
    }
}

