package com.ninni.snowed_over.entity.ai.goal;

import com.ninni.snowed_over.entity.PenguinEntity;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.mob.PathAwareEntity;

public class PenguinWanderAroundFarGoal extends WanderAroundFarGoal {
    public PenguinWanderAroundFarGoal(PathAwareEntity pathAwareEntity, double d) {
        super(pathAwareEntity, d);
    }

    @Override
    public boolean canStart() {
        if (this.mob instanceof PenguinEntity penguin && penguin.hasEgg()) return false;
        return super.canStart();
    }
}
