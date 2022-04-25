package com.ninni.snowed_over.entity.ai.goal;

import com.ninni.snowed_over.entity.PenguinEntity;
import com.ninni.snowed_over.entity.PenguinMood;
import com.ninni.snowed_over.sound.SnowedOverSoundEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Formatting;

public class PenguinLookAtEntityGoal  extends LookAtEntityGoal {
    public PenguinLookAtEntityGoal(MobEntity penguin, Class<? extends LivingEntity> targetType, float range) {
        super(penguin, targetType, range);
    }

    @Override
    public boolean canStart() {
        if (this.mob instanceof PenguinEntity penguin && penguin.isSliding()) return false;
        return super.canStart();
    }

    @Override
    public void start() {
        super.start();
        String string = Formatting.strip(mob.getName().getString());
        if ("Dwayne".equals(string) || "dwayne".equals(string)) { mob.playSound(SnowedOverSoundEvents.ENTITY_PENGUIN_BOOM, 0.35F, 1);}
        if (this.mob instanceof PenguinEntity penguin) penguin.setMood(PenguinMood.CONFUSED);
    }
    @Override
    public void stop() {
        super.stop();
        if (this.mob instanceof PenguinEntity penguin) penguin.setMood(PenguinMood.HAPPY);
    }
}
