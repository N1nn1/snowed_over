package com.ninni.snowed_over.init;

import com.ninni.snowed_over.entity.PenguinEntity;
import com.ninni.snowed_over.entity.ReindeerEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.ninni.snowed_over.SnowedOver.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SnowedOverEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, MOD_ID);

    public static final RegistryObject<EntityType<ReindeerEntity>> REINDEER = ENTITY_TYPES.register("reindeer", () -> EntityType.Builder.of(ReindeerEntity::new, MobCategory.CREATURE).sized(1.0F, 1.6F).clientTrackingRange(8).build("reindeer"));
    public static final RegistryObject<EntityType<PenguinEntity>> PENGUIN = ENTITY_TYPES.register("penguin", () -> EntityType.Builder.of(PenguinEntity::new, MobCategory.CREATURE).sized(0.55F, 0.9F).clientTrackingRange(8).build("penguin"));

}
