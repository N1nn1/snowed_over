package com.ninni.snowed_over.sound;

import com.ninni.snowed_over.SnowedOver;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public interface SnowedOverSoundEvents {

    SoundEvent ITEM_PINECONE_EAT = pinecone("eat");
    private static SoundEvent pinecone(String type) {
        return createItemSound("pinecone", type);
    }

    SoundEvent BLOCK_COMPACTED_SNOW_BREAK = compacted_snow("break");
    SoundEvent BLOCK_COMPACTED_SNOW_STEP  = compacted_snow("step");
    SoundEvent BLOCK_COMPACTED_SNOW_PLACE = compacted_snow("place");
    SoundEvent BLOCK_COMPACTED_SNOW_HIT   = compacted_snow("hit");
    SoundEvent BLOCK_COMPACTED_SNOW_FALL  = compacted_snow("fall");
    private static SoundEvent compacted_snow(String type) {
        return createBlockSound("compacted_snow", type);
    }

    SoundEvent ENTITY_REINDEER_GALLOP  = reindeer("gallop");
    SoundEvent ENTITY_REINDEER_BREATHE = reindeer("breathe");
    SoundEvent ENTITY_REINDEER_AMBIENT = reindeer("idle");
    SoundEvent ENTITY_REINDEER_HURT    = reindeer("hurt");
    SoundEvent ENTITY_REINDEER_DEATH   = reindeer("death");
    SoundEvent ENTITY_REINDEER_ANGRY   = reindeer("angry");
    SoundEvent ENTITY_REINDEER_EAT     = reindeer("eat");
    SoundEvent ENTITY_REINDEER_JUMP    = reindeer("jump");
    SoundEvent ENTITY_REINDEER_CLOUD_JUMP    = reindeer("cloud_jump");
    private static SoundEvent reindeer(String type) {
        return createEntitySound("reindeer", type);
    }

    SoundEvent ENTITY_PENGUIN_AMBIENT   = penguin("idle");
    SoundEvent ENTITY_PENGUIN_HURT      = penguin("hurt");
    SoundEvent ENTITY_PENGUIN_DEATH     = penguin("death");
    SoundEvent ENTITY_PENGUIN_EGG_CRACK = penguin("crack");
    SoundEvent ENTITY_PENGUIN_HATCH     = penguin("hatch");
    SoundEvent ENTITY_PENGUIN_SLIDE     = penguin("slide");
    SoundEvent ENTITY_PENGUIN_BOOM      = penguin("boom");
    private static SoundEvent penguin(String type) {
        return createEntitySound("penguin", type);
    }

    private static SoundEvent register(String id) {
        ResourceLocation identifier = new ResourceLocation(SnowedOver.MOD_ID, id);
        return Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
    }

    private static SoundEvent createBlockSound(String block, String type) {
        return register("block." + block + "." + type);
    }
    private static SoundEvent createItemSound(String item, String type) {
        return register("item." + item + "." + type);
    }
    private static SoundEvent createEntitySound(String entity, String id) {
        return register("entity." + entity + "." + id);
    }
}
