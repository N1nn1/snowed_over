package com.ninni.snowed_over.tag;

import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.ninni.snowed_over.SnowedOver.*;

public interface SnowedOverBlockTags {

    TagKey<Block> PENGUIN_SPAWNABLE_ON = create("penguin_spawnable_on");

    private static TagKey<Block> create(String id) {
        return TagKey.of(Registry.BLOCK_KEY, new Identifier(MOD_ID, id));
    }

}
