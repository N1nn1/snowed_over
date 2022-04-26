package com.ninni.snowed_over.entity.ai.goal;

import com.ninni.snowed_over.entity.PenguinEntity;
import com.ninni.snowed_over.entity.PenguinMood;
import com.ninni.snowed_over.sound.SnowedOverSoundEvents;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

public class PenguinSlideGoal extends WanderAroundFarGoal {
    public PenguinSlideGoal(PathAwareEntity penguin, double speed) {
        super(penguin, speed);
    }

    @Override
    @Nullable
    protected Vec3d getWanderTarget() { return NoPenaltyTargeting.find(this.mob, 10, 0); }

    @Override
    public boolean canStart() {
        if (this.mob instanceof PenguinEntity penguin && penguin.hasEgg() || mob.isSubmergedInWater()) return false;
        return super.canStart();
    }

    @Override
    public boolean shouldContinue() {
        if(this.mob.isSubmergedInWater()) return false;
        return super.shouldContinue();
    }

    @Override
    public void start() {
        this.mob.getNavigation().startMovingTo(this.targetX, this.targetY, this.targetZ, this.speed);
        if (this.mob instanceof PenguinEntity penguin) {
            penguin.setMood(PenguinMood.FOCUSED);
            penguin.setSliding(true);
            penguin.playSound(SnowedOverSoundEvents.ENTITY_PENGUIN_SLIDE, 0.5F, 1);
        }
    }

    @Override
    public void stop() {
        super.stop();
        //TODO: this goal doesn't end correctly
        if (this.mob instanceof PenguinEntity penguin) {
            penguin.setMood(PenguinMood.HAPPY);
            penguin.setSliding(false);
        }
    }
}
