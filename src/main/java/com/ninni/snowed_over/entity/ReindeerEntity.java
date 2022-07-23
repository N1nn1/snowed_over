package com.ninni.snowed_over.entity;

import com.ninni.snowed_over.criterion.SnowedOverCriteria;
import com.ninni.snowed_over.enchantments.SnowedOverEnchantments;
import com.ninni.snowed_over.item.SnowedOverItems;
import com.ninni.snowed_over.sound.SnowedOverSoundEvents;
import com.ninni.snowed_over.tag.SnowedOverItemTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.HorseBondWithPlayerGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.UUID;

public class ReindeerEntity extends HorseBaseEntity {
    public static final Ingredient TEMPT_INGREDIENT = Ingredient.fromTag(SnowedOverItemTags.REINDEER_TEMPTS);
    private static final UUID HASTY_HOOVES_SPEED_BOOST_ID = UUID.fromString("d9f1b970-be2b-4d4b-8978-e9f54bc1b04e");

    protected ReindeerEntity(EntityType<? extends HorseBaseEntity> entityType, World world) { super(entityType, world); }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new HorseBondWithPlayerGoal(this, 0.85));
        this.goalSelector.add(2, new FleeEntityGoal<>(this, WolfEntity.class, 6.0F, 1.6, 1.8));
        this.goalSelector.add(2, new EscapeDangerGoal(this, 1.0));
        this.goalSelector.add(3, new TemptGoal(this, 1.0, TEMPT_INGREDIENT, false));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 0.85));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        if (!this.items.getStack(1).isEmpty()) { nbt.put("ArmorItem", this.items.getStack(1).writeNbt(new NbtCompound())); }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        ItemStack itemStack;
        if (nbt.contains("ArmorItem", 10) && !(itemStack = ItemStack.fromNbt(nbt.getCompound("ArmorItem"))).isEmpty() && this.isHorseArmor(itemStack)) { this.items.setStack(1, itemStack); }
    }

    @Override
    protected void updateSaddle() {
        if (!this.world.isClient()) {
            super.updateSaddle();
            this.equipArmor(this.items.getStack(1));
            this.setEquipmentDropChance(EquipmentSlot.CHEST, 0.0f);
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.setNoDrag(canCloudJump() && !this.isLeashed());
        if (!world.isClient()) {
            if (this.hasFrostWalker(this.getEquippedStack(EquipmentSlot.CHEST))) {
                freezeWater();
            }
        }
        if (this.world.isClient && canCloudJump() && this.getVelocity().lengthSquared() > 0.03 ) {
            Vec3d vec3d = this.getRotationVec(0.0f);
            float f = MathHelper.cos(this.getYaw() * ((float)Math.PI / 180)) * 0.3f;
            float g = MathHelper.sin(this.getYaw() * ((float)Math.PI / 180)) * 0.3f;
            float h = 1.2f - this.random.nextFloat() * 0.7f;
            for (int i = 0; i < 2; ++i) {
                this.world.addParticle(ParticleTypes.END_ROD, this.getX() - vec3d.x * (double)h * 0.75 + (double)f, this.getY() - vec3d.y + 1, this.getZ() - vec3d.z * (double)h + (double)g, 0.0, 0.0, 0.0);
                this.world.addParticle(ParticleTypes.END_ROD, this.getX() - vec3d.x * (double)h * 0.75 - (double)f, this.getY() - vec3d.y + 1, this.getZ() - vec3d.z * (double)h - (double)g, 0.0, 0.0, 0.0);
            }
        }
    }

    private void freezeWater() {
        BlockState blockState = Blocks.FROSTED_ICE.getDefaultState();
        int i = EnchantmentHelper.getEquipmentLevel(Enchantments.FROST_WALKER, this);
        float f = Math.min(16, 2 + i);
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (BlockPos blockPos : BlockPos.iterate(this.getBlockPos().add(-f, -1.0, -f), this.getBlockPos().add(f, -1.0, f))) {
            BlockState blockState3;
            if (!blockPos.isWithinDistance(this.getPos(), f)) continue;
            mutable.set(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ());
            BlockState blockState2 = this.world.getBlockState(mutable);
            if (!blockState2.isAir() || (blockState3 = this.world.getBlockState(blockPos)).getMaterial() != Material.WATER || blockState3.get(FluidBlock.LEVEL) != 0 || !blockState.canPlaceAt(this.world, blockPos) || !this.world.canPlace(blockState, blockPos, ShapeContext.absent())) continue;
            this.world.setBlockState(blockPos, blockState);
            this.world.createAndScheduleBlockTick(blockPos, Blocks.FROSTED_ICE, MathHelper.nextInt(this.getRandom(), 60, 120));
        }
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (canCloudJump()) { this.setVelocity(getVelocity().add(0, 0.07, 0)); }
        super.travel(movementInput);
    }

    @Override
    public boolean hasArmorSlot() { return true; }

    private void equipArmor(ItemStack stack) {
        this.equipStack(EquipmentSlot.CHEST, stack);
        this.setEquipmentDropChance(EquipmentSlot.CHEST, 0.0f);
    }

    public static DefaultAttributeContainer.Builder createReindeerAttributes() {
        return createBaseHorseAttributes()
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.175)
            .add(EntityAttributes.HORSE_JUMP_STRENGTH, 0.4)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 16.0D);
    }

    @Override
    protected void applyMovementEffects(BlockPos pos) {
        if (this.isHorseArmor(this.getEquippedStack(EquipmentSlot.CHEST)) && EnchantmentHelper.getLevel(SnowedOverEnchantments.HASTY_HOOVES, this.getEquippedStack(EquipmentSlot.CHEST)) > 0 && this.hasPassengers() && this.isOnGround()) {
            this.addHastyHoovesEnchantment();
        } else { this.removeHastyHoovesSpeedBoost(); }
    }

    protected void removeHastyHoovesSpeedBoost() {
        EntityAttributeInstance entityAttributeInstance = this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if (entityAttributeInstance != null) { if (entityAttributeInstance.getModifier(HASTY_HOOVES_SPEED_BOOST_ID) != null) { entityAttributeInstance.removeModifier(HASTY_HOOVES_SPEED_BOOST_ID); } }
    }

    protected void addHastyHoovesEnchantment() {
        if (hasHastyHooves(this.getEquippedStack(EquipmentSlot.CHEST))) {
            EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
            float level = getHastyHooves(this);
            if (speed != null && level == 1) {
                EntityAttributeModifier attributeModifier = new EntityAttributeModifier(HASTY_HOOVES_SPEED_BOOST_ID, "Hasty hooves speed boost", 0.025, EntityAttributeModifier.Operation.ADDITION);
                if (!speed.hasModifier(attributeModifier)) { speed.addTemporaryModifier(attributeModifier); }
            }
            if (speed != null && level == 2) {
                EntityAttributeModifier attributeModifier = new EntityAttributeModifier(HASTY_HOOVES_SPEED_BOOST_ID, "Hasty hooves speed boost", 0.075, EntityAttributeModifier.Operation.ADDITION);
                if (!speed.hasModifier(attributeModifier)) { speed.addTemporaryModifier(attributeModifier); }
            }
        }
    }
    public boolean hasCloudJumper(ItemStack stack) { return EnchantmentHelper.getLevel(SnowedOverEnchantments.CLOUD_JUMPER, stack) > 0; }
    public boolean canCloudJump() { return this.hasCloudJumper(getEquippedStack(EquipmentSlot.CHEST)) && this.world.getBlockState(this.getBlockPos().down(3)).isOf(Blocks.AIR) && this.world.getBlockState(this.getBlockPos().down(2)).isOf(Blocks.AIR) && this.world.getBlockState(this.getBlockPos().down(1)).isOf(Blocks.AIR) && !this.isOnGround(); }
    public boolean hasHastyHooves(ItemStack stack) { return EnchantmentHelper.getLevel(SnowedOverEnchantments.HASTY_HOOVES, stack) > 0; }
    public boolean hasFrostWalker(ItemStack stack) { return EnchantmentHelper.getLevel(Enchantments.FROST_WALKER, stack) > 0; }
    public static int getHastyHooves(LivingEntity entity) { return EnchantmentHelper.getEquipmentLevel(SnowedOverEnchantments.HASTY_HOOVES, entity); }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (this.isTame() && player.shouldCancelInteraction()) {
            this.openInventory(player);
            return ActionResult.success(this.world.isClient);
        }

        if (this.hasPassengers()) { return super.interactMob(player, hand); }

        if (!itemStack.isEmpty()) {
            if (this.isBreedingItem(itemStack)) { return this.interactReindeer(player, itemStack); }

            ActionResult actionResult = itemStack.useOnEntity(player, this, hand);
            if (actionResult.isAccepted()) { return actionResult; }

            if (!this.isTame()) {
                this.playAngrySound();
                return ActionResult.success(this.world.isClient);
            }

            boolean bl = !this.isSaddled() && itemStack.isOf(Items.SADDLE);
            if (this.isHorseArmor(itemStack) || bl) {
                this.openInventory(player);
                return ActionResult.success(this.world.isClient);
            }
        }
        this.putPlayerOnBack(player);
        return ActionResult.success(this.world.isClient);
    }

    public ActionResult interactReindeer(PlayerEntity player, ItemStack stack) {
        boolean bl = this.receiveFood(player, stack);
        if (!player.getAbilities().creativeMode) { stack.decrement(1); }
        if (this.world.isClient) { return ActionResult.CONSUME; } else { return bl ? ActionResult.SUCCESS : ActionResult.PASS; }
    }

    @Override
    protected boolean receiveFood(PlayerEntity player, ItemStack item) {
        boolean bl = false;
        float f = 0.0F;
        int j = 0;
        if (item.isOf(Items.WHEAT)) {
            f = 2.0F;
            j = 2;
        } else if (item.isOf(Items.CARROT)) {
            f = 2.0F;
            j = 3;
        } else if (item.isOf(Items.SUGAR)) {
            f = 1.0F;
            j = 1;
        } else if (item.isOf(Blocks.GLOW_LICHEN.asItem())) {
            f = 20.0F;
            j = 15;
        } else if (item.isOf(Items.APPLE)) {
            f = 3.0F;
            j = 3;
        } else if (item.isOf(Items.GOLDEN_CARROT)) {
            f = 4.0F;
            j = 6;
        } else if (item.isOf(Items.GOLDEN_APPLE) || item.isOf(Items.ENCHANTED_GOLDEN_APPLE)) {
            f = 10.0F;
            j = 10;
        }

        if (this.getHealth() < this.getMaxHealth() && f > 0.0F) {
            this.heal(f);
            bl = true;
        }

        if (j > 0 && (bl || !this.isTame()) && this.getTemper() < this.getMaxTemper()) {
            bl = true;
            if (!this.world.isClient) { this.addTemper(j); }
        }

        if (bl) {
            this.playEatingAnimation();
            this.emitGameEvent(GameEvent.EAT, this.getCameraBlockPos());
        }

        return bl;
    }

    private void playEatingAnimation() {
        this.setEating();
        if (!this.isSilent()) {
            SoundEvent soundEvent = this.getEatSound();
            if (soundEvent != null) { this.world.playSound(null, this.getX(), this.getY(), this.getZ(), soundEvent, this.getSoundCategory(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F); }
        }
    }

    @Override
    public boolean isHorseArmor(ItemStack item) { return item.getItem() == SnowedOverItems.HOOF_ARMOR; }

    private void setEating() { if (!this.world.isClient) { this.setHorseFlag(64, true); } }


    @Override
    public void updatePassengerPosition(Entity passenger) {
        if (this.hasPassenger(passenger)) {
            float f = MathHelper.cos(this.bodyYaw * 0.0175F);
            float g = MathHelper.sin(this.bodyYaw * 0.0175F);
            passenger.setPosition(this.getX() + (double)(0.3F * g), this.getY() + this.getMountedHeightOffset() + passenger.getHeightOffset(), this.getZ() - (double)(0.3F * f));
        }
    }

    @Override
    public double getMountedHeightOffset() { return (double)this.getHeight() * 0.7; }

    @Override
    public boolean isBreedingItem(ItemStack stack) { return TEMPT_INGREDIENT.test(stack); }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        if (hasCloudJumper(this.getEquippedStack(EquipmentSlot.CHEST))) { return false; }
        else return super.handleFallDamage(fallDistance, damageMultiplier, damageSource);
    }

    @Override
    protected void playWalkSound(BlockSoundGroup group) {
        this.playSound(SnowedOverSoundEvents.ENTITY_REINDEER_GALLOP, group.getVolume() * 0.15F, group.getPitch());
        if (this.random.nextInt(10) == 0) {
            this.playSound(SnowedOverSoundEvents.ENTITY_REINDEER_BREATHE, group.getVolume() * 0.6F, group.getPitch());
        }

    }
    @Override
    protected SoundEvent getAmbientSound() {
        super.getAmbientSound();
        return SnowedOverSoundEvents.ENTITY_REINDEER_AMBIENT;
    }
    @Override
    protected SoundEvent getDeathSound() {
        super.getDeathSound();
        return SnowedOverSoundEvents.ENTITY_REINDEER_DEATH;
    }
    @Nullable
    @Override
    protected SoundEvent getEatSound() {
        return SnowedOverSoundEvents.ENTITY_REINDEER_EAT;
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        super.getHurtSound(source);
        return SnowedOverSoundEvents.ENTITY_REINDEER_HURT;
    }
    @Override
    protected SoundEvent getAngrySound() {
        super.getAngrySound();
        return SnowedOverSoundEvents.ENTITY_REINDEER_ANGRY;
    }
    //TODO: make so it plays a new custom jump sound when in the air with the enchantment
    @Override
    protected void playJumpSound() {
        if (canCloudJump()) this.playSound(SnowedOverSoundEvents.ENTITY_REINDEER_CLOUD_JUMP, 1F, 1.0F);
        else this.playSound(SnowedOverSoundEvents.ENTITY_REINDEER_JUMP, 0.25F, 1.0F);
    }

    @Override
    public void startJumping(int height) {
        super.startJumping(height);
        if (!this.world.isClient() && canCloudJump()) {
            SnowedOverCriteria.CLOUD_JUMPER_BOOST.trigger((ServerPlayerEntity) this.getPrimaryPassenger());
        }
    }

    @SuppressWarnings("unused")
    public static boolean canSpawn(EntityType <ReindeerEntity> entity, ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random random){
        BlockState state = world.getBlockState(pos.down());
        return state.isOf(Blocks.GRASS_BLOCK) && world.getBaseLightLevel(pos, 0) > 8;
    }
}
