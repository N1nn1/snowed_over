package com.ninni.snowed_over.sound;

import net.minecraft.world.level.block.SoundType;

public class SnowedOverBlockSoundGroups {

    public static final SoundType COMPACTED_SNOW = new SoundType(
        0.3F, 1.0F,

        SnowedOverSoundEvents.BLOCK_COMPACTED_SNOW_BREAK,
        SnowedOverSoundEvents.BLOCK_COMPACTED_SNOW_STEP,
        SnowedOverSoundEvents.BLOCK_COMPACTED_SNOW_PLACE,
        SnowedOverSoundEvents.BLOCK_COMPACTED_SNOW_HIT,
        SnowedOverSoundEvents.BLOCK_COMPACTED_SNOW_FALL
    );
}
