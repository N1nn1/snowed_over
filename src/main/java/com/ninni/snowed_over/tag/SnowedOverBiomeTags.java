package com.ninni.snowed_over.tag;

import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

import static com.ninni.snowed_over.SnowedOver.MOD_ID;

public class SnowedOverBiomeTags {

    public static final TagKey<Biome> REVAMPED_IGLOO_HAS_STRUCTURE = create("revamped_igloo");

    private static TagKey<Biome> create(String id) {
        return TagKey.of(Registry.BIOME_KEY, new Identifier(MOD_ID, id));
    }

}
