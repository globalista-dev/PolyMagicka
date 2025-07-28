package com.globalista.polymagicka.magic.summons;

import eu.pb4.polymer.core.api.entity.PolymerEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.EnumSet;

public class WindAtronach extends IronGolemEntity implements PolymerEntity {

    private int attackTicksLeft;
    private int lookingAtVillagerTicksLeft;


    public WindAtronach(EntityType<? extends IronGolemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public EntityType<?> getPolymerEntityType(PacketContext context) {
        return EntityType.BREEZE;
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MOVEMENT_SPEED, (double)0.63F)
                .add(EntityAttributes.MAX_HEALTH, (double)30.0F)
                .add(EntityAttributes.FOLLOW_RANGE, (double)24.0F)
                .add(EntityAttributes.ATTACK_DAMAGE, (double)3.0F);
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
        if(source.isOf(DamageTypes.FALL)) {
            return false;
        } else {
            boolean bl = super.damage(world, source, amount);
            this.playSound(SoundEvents.ENTITY_BREEZE_HURT, 1.0F, 1.0F);
            return bl;
        }

    }

    @Override
    public void handleStatus(byte status) {
        if (status == 4) {
            this.attackTicksLeft = 10;
            this.playSound(SoundEvents.ENTITY_BREEZE_IDLE_AIR, 1.0F, 1.0F);
        } else if (status == 11) {
            this.lookingAtVillagerTicksLeft = 400;
            this.playSound(SoundEvents.ENTITY_BREEZE_IDLE_AIR, 1.0F, 1.0F);
        } else if (status == 34) {
            this.lookingAtVillagerTicksLeft = 0;
            this.playSound(SoundEvents.ENTITY_BREEZE_IDLE_AIR, 1.0F, 1.0F);
        } else {
            super.handleStatus(status);
        }

    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(2, new WanderNearTargetGoal(this, 0.9, 32.0F));
        this.goalSelector.add(2, new WanderAroundPointOfInterestGoal(this, 0.6, false));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(1, new TrackIronGolemTargetGoal(this));
        this.targetSelector.add(2, new RevengeGoal(this, new Class[0]));
        this.targetSelector.add(3, new ActiveTargetGoal(this, PlayerEntity.class, 10, true, false, this::shouldAngerAt));
        this.targetSelector.add(3, new ActiveTargetGoal(this, MobEntity.class, 5, false, false, (entity, world) -> entity instanceof Monster && !(entity instanceof CreeperEntity)));
        this.targetSelector.add(4, new UniversalAngerGoal(this, false));
        this.goalSelector.add(4, new ShootWindChargeGoal(this));
        this.goalSelector.add(5, new GoToWalkTargetGoal(this, (double)1.0F));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, (double)1.0F, 0.0F));
        this.targetSelector.add(2, new ActiveTargetGoal(this, PlayerEntity.class, true));
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        return ActionResult.PASS;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_BREEZE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BREEZE_DEATH;
    }

    @Override
    public void playAmbientSound() {
        this.playSound(SoundEvents.ENTITY_BREEZE_IDLE_GROUND);
    }

    public class ShootWindChargeGoal extends Goal {
        private final MobEntity mob;
        private int cooldown;

        public ShootWindChargeGoal(MobEntity mob) {
            this.mob = mob;
            this.setControls(EnumSet.of(Control.TARGET, Control.LOOK));
        }

        @Override
        public boolean canStart() {
            LivingEntity target = mob.getTarget();
            return target != null && target.isAlive() && mob.canSee(target) && target != mob;
        }

        @Override
        public void tick() {
            LivingEntity target = mob.getTarget();
            if (target == null || !target.isAlive() || target == mob) return;

            mob.getLookControl().lookAt(target, 10.0F, 10.0F);

            if (--cooldown <= 0) {
                cooldown = 20;

                Vec3d direction = new Vec3d(
                        target.getX() - mob.getX(),
                        target.getBodyY(0.5) - mob.getBodyY(0.5),
                        target.getZ() - mob.getZ()
                ).normalize();

                // Spawn the wind charge
                WindChargeEntity charge = new WindChargeEntity(EntityType.BREEZE_WIND_CHARGE, mob.getWorld());
                charge.setVelocity(direction.multiply(0.7)); // Breeze shoots faster than Blaze
                charge.setPos(
                        mob.getX(),
                        mob.getBodyY(0.5) + 0.5,
                        mob.getZ()
                );

                mob.getWorld().spawnEntity(charge);
                mob.playSound(SoundEvents.ENTITY_BREEZE_SHOOT, 1.0F, 1.0F);
            }
        }

        @Override
        public boolean shouldContinue() {
            return canStart();
        }
    }


}
