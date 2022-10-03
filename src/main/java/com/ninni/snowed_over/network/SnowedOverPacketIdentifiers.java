package com.ninni.snowed_over.network;

import com.ninni.snowed_over.SnowedOver;
import net.minecraft.resources.ResourceLocation;

public interface SnowedOverPacketIdentifiers {
    ResourceLocation OPEN_REINDEER_SCREEN = create("open_reindeer_screen");

    static ResourceLocation create(String id) {
        return new ResourceLocation(SnowedOver.MOD_ID, id);
    }
}
