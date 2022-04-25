package com.ninni.snowed_over.entity.ai.goal;

import com.ninni.snowed_over.entity.PenguinEntity;
import com.ninni.snowed_over.entity.PenguinMood;
import com.ninni.snowed_over.sound.SnowedOverSoundEvents;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

public class PenguinSlideGoal extends PenguinWanderAroundFarGoal {
    public PenguinSlideGoal(PathAwareEntity penguin, double speed) {
        super(penguin, speed);
    }

    @Override
    @Nullable
    protected Vec3d getWanderTarget() {
        return NoPenaltyTargeting.find(this.mob, 10, 0);
    }

    @Override
    public boolean canStart() {
        if (mob.isNavigating() || this.mob instanceof PenguinEntity penguin && penguin.hasEgg()) return false;
        else {
            if (!this.ignoringChance) {
                if (this.mob.getRandom().nextInt(toGoalTicks(this.chance)) != 0) {
                    return false;
                }
            }
            Vec3d vec3d = this.getWanderTarget();
            if (vec3d == null) {
                return false;
            } else {
                this.targetX = vec3d.x;
                this.targetZ = vec3d.z;
                this.ignoringChance = false;
                return true;
            }
        }
    }

    @Override
    public void start() {
        this.mob.getNavigation().startMovingTo(this.targetX, 0, this.targetZ, this.speed);
        if (this.mob instanceof PenguinEntity penguin) {
            penguin.setMood(PenguinMood.FOCUSED);
            penguin.setSliding(true);
            penguin.playSound(SnowedOverSoundEvents.ENTITY_PENGUIN_SLIDE, 0.5F, 1);
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
