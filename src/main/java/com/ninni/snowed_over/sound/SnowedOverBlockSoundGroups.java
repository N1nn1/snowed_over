package com.ninni.snowed_over.sound;

import net.minecraft.sound.BlockSoundGroup;

public class SnowedOverBlockSoundGroups {

    public static final BlockSoundGroup SNOW_BRICKS = new BlockSoundGroup(
        0.3F, 1.0F,

        SnowedOverSoundEvents.BLOCK_SNOW_BRICKS_BREAK,
        SnowedOverSoundEvents.BLOCK_SNOW_BRICKS_STEP,
        SnowedOverSoundEvents.BLOCK_SNOW_BRICKS_PLACE,
        SnowedOverSoundEvents.BLOCK_SNOW_BRICKS_HIT,
        SnowedOverSoundEvents.BLOCK_SNOW_BRICKS_FALL
    );
}
