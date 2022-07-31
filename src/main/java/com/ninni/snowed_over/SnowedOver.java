package com.ninni.snowed_over;

import com.google.common.reflect.Reflection;
import com.ninni.snowed_over.block.SnowedOverBlocks;
import com.ninni.snowed_over.enchantments.SnowedOverEnchantments;
import com.ninni.snowed_over.entity.SnowedOverEntities;
import com.ninni.snowed_over.item.SnowedOverItems;
import com.ninni.snowed_over.sound.SnowedOverSoundEvents;
import com.ninni.snowed_over.world.gen.structures.SnowedOverStructures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.BiomeKeys;

public class SnowedOver implements ModInitializer {
	public static final String MOD_ID = "snowed_over";

	@SuppressWarnings("UnstableApiUsage")
	@Override
	public void onInitialize() {
		SnowedOverStructures.init();
		Reflection.initialize(
			SnowedOverItems.class,
			SnowedOverBlocks.class,
			SnowedOverSoundEvents.class,
			SnowedOverEnchantments.class
		);
	}
}
