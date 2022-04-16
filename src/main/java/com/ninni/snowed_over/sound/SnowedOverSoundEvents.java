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

    public static final SoundEvent BLOCK_SNOW_BRICKS_BREAK = snow_bricks("break");
    public static final SoundEvent BLOCK_SNOW_BRICKS_STEP = snow_bricks("step");
    public static final SoundEvent BLOCK_SNOW_BRICKS_PLACE = snow_bricks("place");
    public static final SoundEvent BLOCK_SNOW_BRICKS_HIT = snow_bricks("hit");
    public static final SoundEvent BLOCK_SNOW_BRICKS_FALL = snow_bricks("fall");
    private static SoundEvent snow_bricks(String type) {
        return createBlockSound("snow_bricks", type);
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
