package com.ninni.snowed_over.events;

import com.ninni.snowed_over.SnowedOver;
import com.ninni.snowed_over.entity.PenguinEntity;
import com.ninni.snowed_over.entity.ReindeerEntity;
import com.ninni.snowed_over.init.SnowedOverEntityTypes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SnowedOver.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MobEvents {

    @SubscribeEvent
    public static void registerEntityAttribute(EntityAttributeCreationEvent event) {
        event.put(SnowedOverEntityTypes.PENGUIN.get(), PenguinEntity.createPenguinAttributes().build());
        event.put(SnowedOverEntityTypes.REINDEER.get(), ReindeerEntity.createReindeerAttributes().build());
    }

}
