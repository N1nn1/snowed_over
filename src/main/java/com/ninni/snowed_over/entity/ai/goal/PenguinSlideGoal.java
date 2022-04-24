package com.ninni.snowed_over.entity.ai.goal;

import com.ninni.snowed_over.entity.PenguinEntity;
import com.ninni.snowed_over.entity.PenguinMood;
import net.minecraft.entity.ai.FuzzyTargeting;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

public class PenguinSlideGoal extends PenguinWanderAroundFarGoal {
    public PenguinSlideGoal(PathAwareEntity pathAwareEntity, double d) {
        super(pathAwareEntity, d);
    }


    @Override
    @Nullable
    protected Vec3d getWanderTarget() {
        if (this.mob.isInsideWaterOrBubbleColumn()) {
            Vec3d vec3d = FuzzyTargeting.find(this.mob, 15, 0);
            return vec3d == null ? super.getWanderTarget() : vec3d;
        } else {
            return this.mob.getRandom().nextFloat() >= this.probability ? FuzzyTargeting.find(this.mob, 10, 0) : super.getWanderTarget();
        }
    }

    @Override
    public void start() {
        super.start();
        if (this.mob instanceof PenguinEntity penguin) {
            penguin.setMood(PenguinMood.FOCUSED);
            penguin.setSliding(true);
        }
    }
    @Override
    public void stop() {
        super.stop();
        if (this.mob instanceof PenguinEntity penguin) {
            penguin.setMood(PenguinMood.HAPPY);
            penguin.setSliding(false);
        }
    }
}
