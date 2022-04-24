package com.ninni.snowed_over.entity;

import com.ninni.snowed_over.entity.ai.goal.PenguinEscapeDangerGoal;
import com.ninni.snowed_over.entity.ai.goal.PenguinFleeEntityGoal;
import com.ninni.snowed_over.entity.ai.goal.PenguinLookAtEntityGoal;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

import java.util.Random;

public class PenguinEntity extends AnimalEntity {
    private static final TrackedData<Integer> MOOD = DataTracker.registerData(PenguinEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public int WingsFlapTicks;

    public PenguinEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(2, new PenguinFleeEntityGoal(this, PolarBearEntity.class, 6.0F, 1.2, 1.4));
        this.goalSelector.add(2, new PenguinEscapeDangerGoal(this, 1.2));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1));
        this.goalSelector.add(5, new LookAroundGoal(this));
        this.goalSelector.add(6, new PenguinLookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(7, new PenguinLookAtEntityGoal(this, PolarBearEntity.class, 6.0F));
    }

    public static DefaultAttributeContainer.Builder createPenguinAttributes() {
        return createLivingAttributes()
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.225)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0D)
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 10.0D);
    }

    private void flapWing() {
        this.WingsFlapTicks = 1;
    }
    @Override
    public void tickMovement() {
        super.tickMovement();
        if (this.random.nextInt(200) == 0) {
            this.flapWing();
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.WingsFlapTicks > 0 && ++this.WingsFlapTicks > 8) {
            this.WingsFlapTicks = 0;
        }
        if (this.getMood() == PenguinMood.AGITATED) {
            this.produceParticles(ParticleTypes.SPLASH);
        }
    }

    protected void produceParticles(ParticleEffect parameters) {
        for(int i = 0; i < 1; ++i) {
            double velocityX = this.random.nextGaussian() * -5;
            double velocityY = this.random.nextGaussian() * -5;
            double velocityZ = this.random.nextGaussian() * -5;
            this.world.addParticle(parameters, this.getParticleX(0.5), this.getRandomBodyY() + 0.5, this.getParticleZ(0.5), velocityX, velocityY, velocityZ);
        }

    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(MOOD, 0);
    }
    public PenguinMood getMood() { return PenguinMood.MOODS[this.dataTracker.get(MOOD)]; }
    public void setMood(PenguinMood mood) { this.dataTracker.set(MOOD, mood.getId()); }

    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return SnowedOverEntities.PENGUIN.create(world);
    }

    @SuppressWarnings("unused")
    public static boolean canSpawn(EntityType <PenguinEntity> entity, ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random random){
        BlockState state = world.getBlockState(pos.down());
        return state.isOf(Blocks.GRASS_BLOCK) && world.getBaseLightLevel(pos, 0) > 8;
    }

}
