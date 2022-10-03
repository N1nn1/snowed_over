package com.ninni.snowed_over.criterion;

import net.minecraft.advancements.CriteriaTriggers;

public class SnowedOverCriteria {

    public static SWCriterion CLOUD_JUMPER_BOOST;

    public static void init() {
        CLOUD_JUMPER_BOOST = CriteriaTriggers.register(new SWCriterion("cloud_jumper_boost"));
    }

}
