package com.ninni.snowed_over.criterion;

import com.google.gson.JsonObject;
import com.ninni.snowed_over.SnowedOver;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class SWCriterion extends SimpleCriterionTrigger<SWCriterion.TriggerInstance> {
    private final ResourceLocation ID;

    public SWCriterion(String name) {
        ID = new ResourceLocation(SnowedOver.MOD_ID, name);
    }

    @Override
    protected TriggerInstance createInstance(JsonObject obj, EntityPredicate.Composite playerPredicate, DeserializationContext predicateDeserializer) {
        return new SWCriterion.TriggerInstance(ID, playerPredicate);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, conditions -> true);
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {

        public TriggerInstance(ResourceLocation id, EntityPredicate.Composite playerPredicate) {
            super(id, playerPredicate);
        }

    }
}
