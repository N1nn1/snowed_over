package com.ninni.snowed_over.init;

import com.ninni.snowed_over.SnowedOver;
import com.ninni.snowed_over.util.world.gen.structures.RevampedIglooFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = SnowedOver.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SnowedOverStructures {

    public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, SnowedOver.MOD_ID);

    public static final RegistryObject<StructureFeature<?>> REVAMPED_IGLOO = STRUCTURES.register("revamped_igloo", RevampedIglooFeature::new);

}
