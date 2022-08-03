package com.ninni.snowed_over.init;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

import static com.ninni.snowed_over.SnowedOver.MOD_ID;

public class SnowedOverBiomeTags {

    public static final TagKey<Biome> REVAMPED_IGLOO_HAS_STRUCTURE = create("revamped_igloo");
    public static final TagKey<Biome> PENGUIN_SPAWNS_IN = create("penguin_spawns_in");
    public static final TagKey<Biome> REINDEER_SPAWNS_IN = create("reindeer_spawns_in");

    private static TagKey<Biome> create(String id) {
        return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(MOD_ID, id));
    }

}
