package com.ninni.snowed_over.entity.ai.goal;

import com.ninni.snowed_over.entity.PenguinEntity;
import com.ninni.snowed_over.entity.PenguinMood;
import com.ninni.snowed_over.sound.SnowedOverSoundEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;

public class PenguinLookAtEntityGoal  extends LookAtPlayerGoal {
    public PenguinLookAtEntityGoal(Mob penguin, Class<? extends LivingEntity> targetType, float range) {
        super(penguin, targetType, range);
    }

    @Override
    public boolean canUse() {
        if (this.mob instanceof PenguinEntity penguin && penguin.isSliding()) return false;
        return super.canUse();
    }

    @Override
    public void start() {
        super.start();
        if ("Dwayne".equals(ChatFormatting.stripFormatting(mob.getName().getString()))) { mob.playSound(SnowedOverSoundEvents.ENTITY_PENGUIN_BOOM, 0.35F, 1);}
        if (this.mob instanceof PenguinEntity penguin) penguin.setMood(PenguinMood.CONFUSED);
    }
    @Override
    public void stop() {
        super.stop();
        if (this.mob instanceof PenguinEntity penguin) penguin.setMood(PenguinMood.HAPPY);
    }
}
