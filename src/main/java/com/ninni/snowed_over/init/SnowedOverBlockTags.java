package com.ninni.snowed_over.init;

import com.ninni.snowed_over.SnowedOver;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class SnowedOverBlockTags {

    public static final TagKey<Block> PENGUIN_SPAWNABLE_ON = create("penguin_spawnable_on");

    private static TagKey<Block> create(String id) {
        return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(SnowedOver.MOD_ID, id));
    }

}
