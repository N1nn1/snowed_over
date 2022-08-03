package com.ninni.snowed_over.init;

import com.mojang.serialization.Codec;
import com.ninni.snowed_over.SnowedOver;
import com.ninni.snowed_over.world.SnowedOverBiomeModifier;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = SnowedOver.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SnowedOverBiomeModifiers {

    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, SnowedOver.MOD_ID);

    public static final RegistryObject<Codec<SnowedOverBiomeModifier>> SNOWED_OVER_BIOME_MODIFIER = BIOME_MODIFIERS.register("snowed_over_biome_modifier", () -> Codec.unit(SnowedOverBiomeModifier::new));

}
