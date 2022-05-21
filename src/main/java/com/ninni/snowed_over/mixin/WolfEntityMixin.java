package com.ninni.snowed_over.mixin;

import com.ninni.snowed_over.entity.ReindeerEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Wolf.class)
public abstract class WolfEntityMixin extends TamableAnimal implements NeutralMob {

    private WolfEntityMixin(EntityType<?  extends TamableAnimal> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(method = "registerGoals", at = @At("TAIL"))
    private void chaseDogsGoal(CallbackInfo ci) {
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, ReindeerEntity.class, false));
    }
}

