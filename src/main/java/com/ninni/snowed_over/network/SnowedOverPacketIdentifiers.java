package com.ninni.snowed_over.network;

import com.ninni.snowed_over.SnowedOver;
import net.minecraft.util.Identifier;

public interface SnowedOverPacketIdentifiers {
    Identifier OPEN_REINDEER_SCREEN = create("open_reindeer_screen");

    static Identifier create(String id) {
        return new Identifier(SnowedOver.MOD_ID, id);
    }
}
