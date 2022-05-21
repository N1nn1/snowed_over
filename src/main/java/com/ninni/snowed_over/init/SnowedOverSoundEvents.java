package com.ninni.snowed_over.init;

import com.ninni.snowed_over.SnowedOver;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = SnowedOver.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SnowedOverSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, SnowedOver.MOD_ID);

    public static final RegistryObject<SoundEvent> ITEM_PINECONE_EAT = register("item.pinecone.eat");

    public static final RegistryObject<SoundEvent> BLOCK_COMPACTED_SNOW_BREAK = register("block.compacted_snow.break");
    public static final RegistryObject<SoundEvent> BLOCK_COMPACTED_SNOW_STEP  = register("block.compacted_snow.step");
    public static final RegistryObject<SoundEvent> BLOCK_COMPACTED_SNOW_PLACE = register("block.compacted_snow.place");
    public static final RegistryObject<SoundEvent> BLOCK_COMPACTED_SNOW_HIT   = register("block.compacted_snow.hit");
    public static final RegistryObject<SoundEvent> BLOCK_COMPACTED_SNOW_FALL  = register("block.compacted_snow.fall");

    public static final RegistryObject<SoundEvent> ENTITY_PENGUIN_AMBIENT = register("entity.penguin.idle");
    public static final RegistryObject<SoundEvent> ENTITY_PENGUIN_HURT = register("entity.penguin.hurt");
    public static final RegistryObject<SoundEvent> ENTITY_PENGUIN_DEATH = register("entity.penguin.death");
    public static final RegistryObject<SoundEvent> ENTITY_PENGUIN_EGG_CRACK = register("entity.penguin.crack");
    public static final RegistryObject<SoundEvent> ENTITY_PENGUIN_HATCH = register("entity.penguin.hatch");
    public static final RegistryObject<SoundEvent> ENTITY_PENGUIN_SLIDE = register("entity.penguin.slide");
    public static final RegistryObject<SoundEvent> ENTITY_PENGUIN_BOOM = register("entity.penguin.boom");

    public static final RegistryObject<SoundEvent> ENTITY_REINDEER_GALLOP = register("entity.reindeer.gallop");
    public static final RegistryObject<SoundEvent> ENTITY_REINDEER_BREATHE = register("entity.reindeer.breathe");
    public static final RegistryObject<SoundEvent> ENTITY_REINDEER_AMBIENT = register("entity.reindeer.idle");
    public static final RegistryObject<SoundEvent> ENTITY_REINDEER_HURT = register("entity.reindeer.hurt");
    public static final RegistryObject<SoundEvent> ENTITY_REINDEER_DEATH = register("entity.reindeer.death");
    public static final RegistryObject<SoundEvent> ENTITY_REINDEER_ANGRY = register("entity.reindeer.angry");
    public static final RegistryObject<SoundEvent> ENTITY_REINDEER_EAT = register("entity.reindeer.eat");
    public static final RegistryObject<SoundEvent> ENTITY_REINDEER_JUMP = register("entity.reindeer.jump");
    public static final RegistryObject<SoundEvent> ENTITY_REINDEER_CLOUD_JUMP = register("entity.reindeer.cloud_jump");

    public static final SoundType COMPACTED_SNOW = new ForgeSoundType(0.3F, 1.0F, BLOCK_COMPACTED_SNOW_BREAK, BLOCK_COMPACTED_SNOW_STEP, BLOCK_COMPACTED_SNOW_PLACE, BLOCK_COMPACTED_SNOW_HIT, BLOCK_COMPACTED_SNOW_FALL);

    public static RegistryObject<SoundEvent> register(String key) {
        return SOUNDS.register(key, () -> new SoundEvent(new ResourceLocation(SnowedOver.MOD_ID, key)));
    }

}
