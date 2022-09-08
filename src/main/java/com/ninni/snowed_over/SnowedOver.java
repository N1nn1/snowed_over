package com.ninni.snowed_over;

import com.google.common.reflect.Reflection;
import com.ninni.snowed_over.block.SnowedOverBlocks;
import com.ninni.snowed_over.criterion.SnowedOverCriteria;
import com.ninni.snowed_over.enchantments.SnowedOverEnchantments;
import com.ninni.snowed_over.item.SnowedOverItems;
import com.ninni.snowed_over.sound.SnowedOverSoundEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class SnowedOver implements ModInitializer {
	public static final String MOD_ID = "snowed_over";
	public static final CreativeModeTab ITEM_GROUP = FabricItemGroupBuilder.build(new ResourceLocation(MOD_ID, "item_group"), () -> new ItemStack(SnowedOverItems.SNOWED_OVER));

	@SuppressWarnings("UnstableApiUsage")
	@Override
	public void onInitialize() {
		SnowedOverCriteria.init();
		Reflection.initialize(
			SnowedOverItems.class,
			SnowedOverBlocks.class,
			SnowedOverLootTableAdditions.class,
			SnowedOverSoundEvents.class,
			SnowedOverEnchantments.class
		);
	}
}
