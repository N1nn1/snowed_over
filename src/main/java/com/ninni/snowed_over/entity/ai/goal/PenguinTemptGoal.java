package com.ninni.snowed_over.entity.ai.goal;

import com.ninni.snowed_over.entity.PenguinEntity;
import com.ninni.snowed_over.entity.PenguinMood;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.recipe.Ingredient;

public class PenguinTemptGoal extends TemptGoal {
    public PenguinTemptGoal(PathAwareEntity entity, double speed, Ingredient food, boolean canBeScared) {
        super(entity, speed, food, canBeScared);
    }

    @Override
    public boolean canStart() {
        if (this.mob instanceof PenguinEntity penguin && penguin.hasEgg()) return false;
        return super.canStart();
    }

    @Override
    public void start() {
        super.start();
        if (this.mob instanceof PenguinEntity penguin) penguin.setMood(PenguinMood.CONFUSED);
    }
    @Override
    public void stop() {
        super.stop();
        if (this.mob instanceof PenguinEntity penguin) penguin.setMood(PenguinMood.HAPPY);
    }
}
