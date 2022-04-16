package com.ninni.snowed_over;

import com.google.common.reflect.Reflection;
import com.ninni.snowed_over.block.SnowedOverBlocks;
import com.ninni.snowed_over.item.SnowedOverItems;
import com.ninni.snowed_over.sound.SnowedOverSoundEvents;
import net.fabricmc.api.ModInitializer;

public class SnowedOver implements ModInitializer {
	public static final String MOD_ID = "snowed_over";

	@SuppressWarnings("UnstableApiUsage")
	@Override
	public void onInitialize() {
		Reflection.initialize(
			SnowedOverItems.class,
			SnowedOverBlocks.class,
			SnowedOverSoundEvents.class
		);
	}
}
