package com.ninni.snowed_over.entity.ai.goal;

import com.ninni.snowed_over.entity.PenguinEntity;
import com.ninni.snowed_over.entity.PenguinMood;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.PolarBearEntity;

@SuppressWarnings("RawTypes")
public class PenguinFleeEntityGoal extends FleeEntityGoal<PolarBearEntity> {

    public PenguinFleeEntityGoal(PathAwareEntity penguin, Class<PolarBearEntity> fleeFromType, float distance, double slowSpeed, double fastSpeed) {
        super(penguin, fleeFromType, distance, slowSpeed, fastSpeed);
    }

    @Override
    public boolean canStart() {
        if (this.mob instanceof PenguinEntity penguin && penguin.hasEgg()) return false;
        return super.canStart();
    }

    @Override
    public void start() {
        super.start();
        if (this.mob instanceof PenguinEntity penguin) penguin.setMood(PenguinMood.AGITATED);
    }
    @Override
    public void stop() {
        super.stop();
        if (this.mob instanceof PenguinEntity penguin) penguin.setMood(PenguinMood.HAPPY);
    }
}
