package com.ninni.snowed_over.criterion;

import net.minecraft.advancement.criterion.Criteria;

public class SnowedOverCriteria {

    public static SWCriterion CLOUD_JUMPER_BOOST;

    public static void init() {
        CLOUD_JUMPER_BOOST = Criteria.register(new SWCriterion("cloud_jumper_boost"));
    }

}
