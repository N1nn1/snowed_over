package com.ninni.snowed_over.world.gen.structures;

import com.ninni.snowed_over.SnowedOver;
import com.ninni.snowed_over.mixin.StructureFeatureAccessor;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class SnowedOverStructures {

    public static final StructureFeature<StructurePoolFeatureConfig> REVAMPED_IGLOO = new RevampedIglooFeature();

    public static void init() {
        StructureFeatureAccessor.callRegister(new Identifier(SnowedOver.MOD_ID, "revamped_igloo").toString(), REVAMPED_IGLOO, GenerationStep.Feature.SURFACE_STRUCTURES);
    }

}
