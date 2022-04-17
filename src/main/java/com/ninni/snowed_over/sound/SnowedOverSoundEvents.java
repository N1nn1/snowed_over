package com.ninni.snowed_over.sound;

import com.ninni.snowed_over.SnowedOver;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SnowedOverSoundEvents {

    public static final SoundEvent ITEM_PINECONE_EAT = pinecone("eat");
    private static SoundEvent pinecone(String type) {
        return createItemSound("pinecone", type);
    }

    public static final SoundEvent BLOCK_COMPACTED_SNOW_BREAK = compacted_snow("break");
    public static final SoundEvent BLOCK_COMPACTED_SNOW_STEP = compacted_snow("step");
    public static final SoundEvent BLOCK_COMPACTED_SNOW_PLACE = compacted_snow("place");
    public static final SoundEvent BLOCK_COMPACTED_SNOW_HIT = compacted_snow("hit");
    public static final SoundEvent BLOCK_COMPACTED_SNOW_FALL = compacted_snow("fall");
    private static SoundEvent compacted_snow(String type) {
        return createBlockSound("compacted_snow", type);
    }

    private static SoundEvent register(String id) {
        Identifier identifier = new Identifier(SnowedOver.MOD_ID, id);
        return Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
    }

    private static SoundEvent createBlockSound(String block, String type) {
        return register("block." + block + "." + type);
    }
    private static SoundEvent createItemSound(String item, String type) {
        return register("item." + item + "." + type);
    }
}
