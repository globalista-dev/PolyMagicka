package com.globalista.polymagicka.magic.summons;

import eu.pb4.polymer.core.api.entity.PolymerEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.WolfSoundVariants;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.nucleoid.packettweaker.PacketContext;

public class Familiar extends IronGolemEntity implements PolymerEntity {

    private int attackTicksLeft;
    private int lookingAtVillagerTicksLeft;


    public Familiar(EntityType<? extends IronGolemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public EntityType<?> getPolymerEntityType(PacketContext context) {
        return EntityType.WOLF;
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.MAX_HEALTH, (double)10.0F)
                .add(EntityAttributes.MOVEMENT_SPEED, (double)0.5F)
                .add(EntityAttributes.ATTACK_DAMAGE, (double)2.0F)
                .add(EntityAttributes.STEP_HEIGHT, (double)1.0F);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new MeleeAttackGoal(this, (double)1.0F, true));
        this.goalSelector.add(2, new WanderNearTargetGoal(this, 0.9, 32.0F));
        this.goalSelector.add(3, new WanderAroundPointOfInterestGoal(this, 0.6, false));
        this.goalSelector.add(3, new WanderAroundGoal(this, 0.6));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(1, new TrackIronGolemTargetGoal(this));
        this.targetSelector.add(2, new RevengeGoal(this, new Class[0]));
        this.targetSelector.add(3, new ActiveTargetGoal(this, MobEntity.class, 5, false, false, (entity, world) -> entity instanceof Monster && !(entity instanceof CreeperEntity)));
    }

    @Override
    public boolean tryAttack(ServerWorld world, Entity target) {
        boolean bl = super.tryAttack(world, target);
        if (bl) {
            float f = this.getWorld().getLocalDifficulty(this.getBlockPos()).getLocalDifficulty();
            if (this.getMainHandStack().isEmpty() && this.isOnFire() && this.random.nextFloat() < f * 0.3F) {
                target.setOnFireFor((float)(2 * (int)f));
            }
        }

        return bl;
    }

    @Override
    protected void pushAway(Entity entity) {

    }

    @Override
    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        boolean bl = super.damage(world, source, amount);
        this.playSound(SoundEvents.WOLF_SOUNDS.get(WolfSoundVariants.Type.CLASSIC).hurtSound().value(), 1.0F, 1.0F);
        return bl;
    }

    @Override
    public void handleStatus(byte status) {
        if (status == 4) {
            this.attackTicksLeft = 10;
            this.playSound(SoundEvents.WOLF_SOUNDS.get(WolfSoundVariants.Type.CLASSIC).ambientSound().value(), 1.0F, 1.0F);
        } else if (status == 11) {
            this.lookingAtVillagerTicksLeft = 400;
        } else if (status == 34) {
            this.lookingAtVillagerTicksLeft = 0;
        } else {
            super.handleStatus(status);
        }

    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        return ActionResult.PASS;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 1.0F, 1.0F);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.WOLF_SOUNDS.get(WolfSoundVariants.Type.CLASSIC).hurtSound().value();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WOLF_SOUNDS.get(WolfSoundVariants.Type.CLASSIC).deathSound().value();
    }

    @Override
    public void playAmbientSound() {
        this.playSound(SoundEvents.WOLF_SOUNDS.get(WolfSoundVariants.Type.CLASSIC).ambientSound().value());
    }

}
