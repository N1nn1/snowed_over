package com.ninni.snowed_over;

import com.google.common.reflect.Reflection;
import com.ninni.snowed_over.block.SnowedOverBlocks;
import com.ninni.snowed_over.criterion.SnowedOverCriteria;
import com.ninni.snowed_over.enchantments.SnowedOverEnchantments;
import com.ninni.snowed_over.item.SnowedOverItems;
import com.ninni.snowed_over.sound.SnowedOverSoundEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class SnowedOver implements ModInitializer {
	public static final String MOD_ID = "snowed_over";
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "item_group"), () -> new ItemStack(SnowedOverItems.PINECONE));

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
