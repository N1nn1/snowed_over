package com.ninni.snowed_over;

import static com.ninni.snowed_over.SnowedOver.*;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

@SuppressWarnings("unused")
public interface SnowedOverTags {

    //Item tags
    TagKey<Item> REINDEER_TEMPTS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(MOD_ID, "reindeer_tempts"));

    //Block tags
    TagKey<Block> PENGUIN_SPAWNABLE_ON = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(MOD_ID, "penguin_spawnable_on"));

    //Biome tags
    TagKey<Biome> PENGUIN_SPAWNS_IN = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(MOD_ID, "penguin_spawns_in"));
    TagKey<Biome> REVAMPED_IGLOO_GENERATES = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(MOD_ID, "has_structure/revamped_igloo"));
}
