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

    private static SoundEvent register(String id) {
        Identifier identifier = new Identifier(SnowedOver.MOD_ID, id);
        return Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
    }

    private static SoundEvent createItemSound(String item, String type) {
        return register("item." + item + "." + type);
    }
}
