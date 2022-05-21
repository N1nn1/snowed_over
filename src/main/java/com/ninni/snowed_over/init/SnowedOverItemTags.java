package com.ninni.snowed_over.init;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static com.ninni.snowed_over.SnowedOver.MOD_ID;

public class SnowedOverItemTags {

    public static final TagKey<Item> REINDEER_TEMPTS = create("reindeer_tempts");

    private static TagKey<Item> create(String id) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(MOD_ID, id));
    }

}
