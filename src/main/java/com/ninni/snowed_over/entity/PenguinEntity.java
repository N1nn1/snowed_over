package com.ninni.snowed_over.entity;

import com.ninni.snowed_over.entity.ai.goal.PenguinEscapeDangerGoal;
import com.ninni.snowed_over.entity.ai.goal.PenguinFleeEntityGoal;
import com.ninni.snowed_over.entity.ai.goal.PenguinLookAtEntityGoal;
import com.ninni.snowed_over.entity.ai.goal.PenguinMateGoal;
import com.ninni.snowed_over.entity.ai.goal.PenguinMeleeAttackGoal;
import com.ninni.snowed_over.entity.ai.goal.PenguinSlideGoal;
import com.ninni.snowed_over.entity.ai.goal.PenguinSwimAroundGoal;
import com.ninni.snowed_over.entity.ai.goal.PenguinTemptGoal;
import com.ninni.snowed_over.entity.ai.goal.PenguinWanderAroundFarGoal;
import com.ninni.snowed_over.sound.SnowedOverSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.pathing.AmphibiousPathNodeMaker;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeNavigator;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CodEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Random;

public class PenguinEntity extends AnimalEntity {
    private static final TrackedData<Integer> MOOD = DataTracker.registerData(PenguinEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> HAS_EGG = DataTracker.registerData(PenguinEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> EGG_TICKS = DataTracker.registerData(PenguinEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> SLIDING = DataTracker.registerData(PenguinEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public static final Ingredient TEMPT_INGREDIENT = Ingredient.fromTag(ItemTags.FISHES);
    public int WingsFlapTicks;

    public PenguinEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
        this.moveControl = new AquaticMoveControl(this, 85, 10, 0.4F, 1.0F, false);
        this.stepHeight = 1F;
    }

    @Override
    protected void initGoals() {
        this.targetSelector.add(0, new ActiveTargetGoal<>(this, CodEntity.class, false));

        this.goalSelector.add(0, new PenguinMeleeAttackGoal(this, 1.0, true));
        this.goalSelector.add(1, new PenguinMateGoal(this, 1.0));
        this.goalSelector.add(2, new PenguinFleeEntityGoal(this, PolarBearEntity.class, 6.0F, 1.2, 1.5));
        this.goalSelector.add(2, new PenguinEscapeDangerGoal(this, 1.4));
        this.goalSelector.add(3, new FollowParentGoal(this, 1.2));
        this.goalSelector.add(4, new PenguinTemptGoal(this, 1.1,TEMPT_INGREDIENT, false));
        this.goalSelector.add(6, new PenguinSwimAroundGoal(this, 1, 10));
        this.goalSelector.add(6, new PenguinWanderAroundFarGoal(this, 1));
        this.goalSelector.add(7, new PenguinSlideGoal(this, 1.8));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.goalSelector.add(9, new PenguinLookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(10, new PenguinLookAtEntityGoal(this, PenguinEntity.class, 6.0F));
    }

    public static DefaultAttributeContainer.Builder createPenguinAttributes() {
        return createMobAttributes()
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0D)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0D);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) { return stack.isIn(ItemTags.FISHES); }

    @Override
    public boolean canBreatheInWater() { return true; }
    @Override
    public boolean isPushedByFluids() { return false; }

    @Override
    protected int computeFallDamage(float fallDistance, float damageMultiplier) { return super.computeFallDamage(fallDistance, damageMultiplier) - 5; }

    private void flapWing() { this.WingsFlapTicks = 1; }

    @Override
    public void travel(Vec3d movementInput) {
        if (this.canMoveVoluntarily() && this.isSubmergedInWater()) {
            this.updateVelocity(this.getMovementSpeed(), movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.9D));
        } else { super.travel(movementInput); }
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) { return dimensions.height * 0.75F; }

    @Override
    public void tickMovement() {
        super.tickMovement();
        if (this.random.nextInt(200) == 0) { this.flapWing(); }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.WingsFlapTicks > 0 && ++this.WingsFlapTicks > 8) { this.WingsFlapTicks = 0; }

        if (this.getMood() == PenguinMood.AGITATED) {
            for(int i = 0; i < 1; ++i) {
                double velocityX = this.random.nextGaussian() * -5;
                double velocityY = this.random.nextGaussian() * -5;
                double velocityZ = this.random.nextGaussian() * -5;
                this.world.addParticle(ParticleTypes.SPLASH, this.getParticleX(0.5), this.getRandomBodyY() + 0.5, this.getParticleZ(0.5), velocityX, velocityY, velocityZ);
            }
        }

        if (this.hasEgg()) setEggTicks(getEggTicks() - 1);

        if (this.getEggTicks() == 0 && !this.isAiDisabled()) {
            setHasEgg(false);
            this.playSound(SnowedOverSoundEvents.ENTITY_PENGUIN_HATCH, 1, 1);
            Optional.ofNullable(SnowedOverEntities.PENGUIN.create(world)).ifPresent(entity -> {
                entity.setBreedingAge(-24000);
                entity.refreshPositionAndAngles(this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ(), 0.0F, 0.0F);
                world.spawnEntity(entity);
            });
            setEggTicks(1);
        }

        if (this.isSliding() && !this.touchingWater){
            for(int i = 0; i < 1; ++i) {
                double velocityX = this.random.nextGaussian() * 0.15;
                double velocityY = this.random.nextGaussian() * 0.15;
                double velocityZ = this.random.nextGaussian() * 0.15;
                this.world.addParticle(new BlockStateParticleEffect(ParticleTypes.BLOCK, this.getLandingBlockState()), this.getParticleX(1), this.getRandomBodyY() - 0.5, this.getParticleZ(1) - 0.75, velocityX, velocityY, velocityZ);
            }
        }
        if (this.submergedInWater && this.isNavigating()){
            for(int i = 0; i < 1; ++i) {
                double velocityX = this.random.nextGaussian() * 0.15;
                double velocityY = this.random.nextGaussian() * 0.15;
                double velocityZ = this.random.nextGaussian() * 0.15;
                this.world.addParticle(ParticleTypes.BUBBLE, this.getParticleX(1), this.getRandomBodyY() - 0.5, this.getParticleZ(1) - 0.75, velocityX, velocityY, velocityZ);
            }
        }
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(MOOD, 0);
        this.dataTracker.startTracking(HAS_EGG, false);
        this.dataTracker.startTracking(EGG_TICKS, 1);
        this.dataTracker.startTracking(SLIDING, false);
    }
    public boolean hasEgg() { return this.dataTracker.get(HAS_EGG); }
    public void setHasEgg(boolean hasEgg) { this.dataTracker.set(HAS_EGG, hasEgg); }
    public int getEggTicks() { return this.dataTracker.get(EGG_TICKS); }
    public void setEggTicks(int eggTicks) { this.dataTracker.set(EGG_TICKS, eggTicks); }
    public PenguinMood getMood() { return PenguinMood.MOODS[this.dataTracker.get(MOOD)]; }
    public void setMood(PenguinMood mood) { this.dataTracker.set(MOOD, mood.getId()); }
    public boolean isSliding() { return this.dataTracker.get(SLIDING); }
    public void setSliding(boolean sliding) { this.dataTracker.set(SLIDING, sliding); }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("HasEgg", this.hasEgg());
        nbt.putInt("EggTicks", this.getEggTicks());
    }
    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setHasEgg(nbt.getBoolean("HasEgg"));
        this.setEggTicks(nbt.getInt("EggTicks"));
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (this.hasEgg() && this.random.nextInt(4) == 0) return SnowedOverSoundEvents.ENTITY_PENGUIN_EGG_CRACK;
        else return SnowedOverSoundEvents.ENTITY_PENGUIN_AMBIENT;
    }
    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_FISH_SWIM;
    }
    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) { return SnowedOverSoundEvents.ENTITY_PENGUIN_HURT; }
    @Nullable
    @Override
    protected SoundEvent getDeathSound() { return SnowedOverSoundEvents.ENTITY_PENGUIN_DEATH; }
    @Override
    protected float getSoundVolume() { return 0.6F; }

    @Override
    protected EntityNavigation createNavigation(World world) { return new PenguinSwimNavigation(this, world); }

    static class PenguinSwimNavigation extends SwimNavigation {
        PenguinSwimNavigation(PenguinEntity penguin, World world) { super(penguin, world); }

        @Override
        protected PathNodeNavigator createPathNodeNavigator(int range) {
            this.nodeMaker = new AmphibiousPathNodeMaker(true);
            return new PathNodeNavigator(this.nodeMaker, range);
        }

        @Override
        protected boolean isAtValidPosition() { return true; }
        @Override
        public boolean isValidPosition(BlockPos pos) { return !this.world.getBlockState(pos.down()).isAir(); }
    }

    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) { return SnowedOverEntities.PENGUIN.create(world); }

    @SuppressWarnings("unused")
    public static boolean canSpawn(EntityType <PenguinEntity> entity, ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random random){
        BlockState state = world.getBlockState(pos.down());
        return state.isOf(Blocks.GRASS_BLOCK) && world.getBaseLightLevel(pos, 0) > 8;
    }

}
