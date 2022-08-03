package com.ninni.snowed_over.events;

import com.ninni.snowed_over.SnowedOver;
import com.ninni.snowed_over.entity.PenguinEntity;
import com.ninni.snowed_over.entity.ReindeerEntity;
import com.ninni.snowed_over.init.SnowedOverEntityTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SnowedOver.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MobEvents {

    @SubscribeEvent
    public static void registerEntityAttribute(EntityAttributeCreationEvent event) {
        SpawnPlacements.register(SnowedOverEntityTypes.PENGUIN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE_WG, PenguinEntity::canSpawn);
        SpawnPlacements.register(SnowedOverEntityTypes.REINDEER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE_WG, ReindeerEntity::canSpawn);

        event.put(SnowedOverEntityTypes.PENGUIN.get(), PenguinEntity.createPenguinAttributes().build());
        event.put(SnowedOverEntityTypes.REINDEER.get(), ReindeerEntity.createReindeerAttributes().build());
    }

    @SubscribeEvent
    public void onEntityJoinWorldEvent(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Wolf wolf) {
            wolf.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(wolf, ReindeerEntity.class, false));
        }
    }

}
