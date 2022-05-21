package com.ninni.snowed_over;

import com.ninni.snowed_over.init.SnowedOverEnchantments;
import com.ninni.snowed_over.init.SnowedOverEntityTypes;
import com.ninni.snowed_over.events.MiscEvents;
import com.ninni.snowed_over.init.SnowedOverBlocks;
import com.ninni.snowed_over.init.SnowedOverItems;
import com.ninni.snowed_over.init.SnowedOverSoundEvents;
import com.ninni.snowed_over.init.SnowedOverStructures;
import com.ninni.snowed_over.util.SnowedOverVanillaIntegration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SnowedOver.MOD_ID)
public class SnowedOver {
	public static final String MOD_ID = "snowed_over";

	public SnowedOver() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		IEventBus eventBus = MinecraftForge.EVENT_BUS;
		modEventBus.addListener(this::commonSetup);

		SnowedOverBlocks.BLOCKS.register(modEventBus);
		SnowedOverEntityTypes.ENTITY_TYPES.register(modEventBus);
		SnowedOverEnchantments.ENCHANTMENTS.register(modEventBus);
		SnowedOverItems.ITEMS.register(modEventBus);
		SnowedOverSoundEvents.SOUNDS.register(modEventBus);
		SnowedOverStructures.STRUCTURES.register(modEventBus);

		eventBus.register(this);
		eventBus.register(new MiscEvents());
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		SnowedOverVanillaIntegration.init();
	}
}
