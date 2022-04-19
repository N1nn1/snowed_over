package com.ninni.snowed_over.tag;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.ninni.snowed_over.SnowedOver.*;

public interface SnowedOverItemTags {

    TagKey<Item> REINDEER_TEMPTS = create("reindeer_tempts");

    private static TagKey<Item> create(String id) {
        return TagKey.of(Registry.ITEM_KEY, new Identifier(MOD_ID, id));
    }
}
