package com.ninni.snowed_over;

import com.ninni.snowed_over.events.MiscEvents;
import com.ninni.snowed_over.events.MobEvents;
import com.ninni.snowed_over.init.SnowedOverBiomeModifiers;
import com.ninni.snowed_over.init.SnowedOverBlocks;
import com.ninni.snowed_over.init.SnowedOverEnchantments;
import com.ninni.snowed_over.init.SnowedOverEntityTypes;
import com.ninni.snowed_over.init.SnowedOverItems;
import com.ninni.snowed_over.init.SnowedOverSoundEvents;
import com.ninni.snowed_over.network.SnowedOverNetworkHandler;
import com.ninni.snowed_over.util.SnowedOverCreativeModeTab;
import com.ninni.snowed_over.util.SnowedOverVanillaIntegration;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SnowedOver.MOD_ID)
public class SnowedOver {
	public static final String MOD_ID = "snowed_over";
	public static final CreativeModeTab TAB = new SnowedOverCreativeModeTab("snowed_over");

	public SnowedOver() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		IEventBus eventBus = MinecraftForge.EVENT_BUS;
		modEventBus.addListener(this::commonSetup);

		SnowedOverBlocks.BLOCKS.register(modEventBus);
		SnowedOverBiomeModifiers.BIOME_MODIFIERS.register(modEventBus);
		SnowedOverEntityTypes.ENTITY_TYPES.register(modEventBus);
		SnowedOverEnchantments.ENCHANTMENTS.register(modEventBus);
		SnowedOverItems.ITEMS.register(modEventBus);
		SnowedOverSoundEvents.SOUNDS.register(modEventBus);

		eventBus.register(this);

		eventBus.register(new MiscEvents());
		eventBus.register(new MobEvents());
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			SnowedOverVanillaIntegration.init();
			SnowedOverNetworkHandler.init();
		});
	}
}
