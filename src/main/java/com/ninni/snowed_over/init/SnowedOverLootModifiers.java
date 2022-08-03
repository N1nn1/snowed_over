package com.ninni.snowed_over.init;

import com.mojang.serialization.Codec;
import com.ninni.snowed_over.SnowedOver;
import com.ninni.snowed_over.util.PineconeLootModifier;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = SnowedOver.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SnowedOverLootModifiers {

    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, SnowedOver.MOD_ID);

    public static final RegistryObject<Codec<PineconeLootModifier>> PINECONE_LOOT_MODIFIER = LOOT_MODIFIERS.register("pinecone_loot_modifier", () -> PineconeLootModifier.CODEC);

}
