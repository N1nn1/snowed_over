package com.ninni.snowed_over.init;

import com.ninni.snowed_over.SnowedOver;
import com.ninni.snowed_over.criterion.SOCriterion;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SnowedOver.MOD_ID)
public class SnowedOverCriteriaTriggers {

    public static final SOCriterion CLOUD_JUMPER_BOOST = CriteriaTriggers.register(new SOCriterion("cloud_jumper_boost"));

}
