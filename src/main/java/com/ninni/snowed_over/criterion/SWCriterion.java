package com.ninni.snowed_over.criterion;

import com.google.gson.JsonObject;
import com.ninni.snowed_over.SnowedOver;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class SWCriterion extends AbstractCriterion<SWCriterion.TriggerInstance> {
    private final Identifier ID;

    public SWCriterion(String name) {
        ID = new Identifier(SnowedOver.MOD_ID, name);
    }

    @Override
    protected TriggerInstance conditionsFromJson(JsonObject obj, EntityPredicate.Extended playerPredicate, AdvancementEntityPredicateDeserializer predicateDeserializer) {
        return new SWCriterion.TriggerInstance(ID, playerPredicate);
    }

    @Override
    public Identifier getId() {
        return ID;
    }

    public void trigger(ServerPlayerEntity player) {
        this.trigger(player, conditions -> true);
    }

    public static class TriggerInstance extends AbstractCriterionConditions {

        public TriggerInstance(Identifier id, EntityPredicate.Extended playerPredicate) {
            super(id, playerPredicate);
        }

    }
}
