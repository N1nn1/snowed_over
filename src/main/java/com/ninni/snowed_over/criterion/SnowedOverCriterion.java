package com.ninni.snowed_over.criterion;

import com.google.gson.JsonObject;
import com.ninni.snowed_over.SnowedOver;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class SOCriterion extends SimpleCriterionTrigger<SOCriterion.Conditions> {
    private final ResourceLocation ID;

    public SOCriterion(String name) {
        ID = new ResourceLocation(SnowedOver.MOD_ID, name);
    }

    @Override
    protected Conditions createInstance(JsonObject jsonObject, EntityPredicate.Composite composite, DeserializationContext deserializationContext) {
        return new SOCriterion.Conditions(ID, composite);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, (conditions)->true);
    }

    public static class Conditions extends AbstractCriterionTriggerInstance {
        public Conditions(ResourceLocation resourceLocation, EntityPredicate.Composite composite) {
            super(resourceLocation, composite);
        }
    }
}
