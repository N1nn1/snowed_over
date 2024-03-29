package com.ninni.snowed_over.entity.ai.goal;

import com.ninni.snowed_over.entity.PenguinEntity;
import java.util.EnumSet;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.ai.goal.Goal;

public class PenguinSwimGoal extends Goal {
    private final PenguinEntity penguin;

    public PenguinSwimGoal(PenguinEntity penguin) {
        this.penguin = penguin;
        this.setFlags(EnumSet.of(Flag.JUMP));
        penguin.getNavigation().setCanFloat(true);
    }

    @Override
    public boolean canUse() { return (this.penguin.hasEgg() && this.penguin.isInWater() && this.penguin.getFluidHeight(FluidTags.WATER) > this.penguin.getFluidJumpThreshold() || this.penguin.isInLava()); }

    @Override
    public boolean requiresUpdateEveryTick() { return true; }

    @Override
    public void tick() { if (this.penguin.getRandom().nextFloat() < 0.8F)  this.penguin.getJumpControl().jump(); }
}
