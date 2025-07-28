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
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.particle.ParticleTypes;
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

public class FlameAtronach extends IronGolemEntity implements PolymerEntity {

    private int attackTicksLeft;
    private int lookingAtVillagerTicksLeft;


    public FlameAtronach(EntityType<? extends IronGolemEntity> entityType, World world) {
        super(entityType, world);
    }

    public boolean hurtByWater() {
        return true;
    }

    @Override
    public EntityType<?> getPolymerEntityType(PacketContext context) {
        return EntityType.BLAZE;
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.MAX_HEALTH, (double)30.0F)
                .add(EntityAttributes.MOVEMENT_SPEED, (double)0.23F)
                .add(EntityAttributes.ATTACK_DAMAGE, (double)6.0F)
                .add(EntityAttributes.STEP_HEIGHT, (double)1.0F);
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
        this.goalSelector.add(4, new ShootFireballGoal(this));
        this.goalSelector.add(5, new GoToWalkTargetGoal(this, (double)1.0F));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, (double)1.0F, 0.0F));
        this.targetSelector.add(2, new ActiveTargetGoal(this, PlayerEntity.class, true));
    }

    @Override
    public boolean tryAttack(ServerWorld world, Entity target) {
        boolean bl = super.tryAttack(world, target);
        if (bl) {
            new ShootFireballGoal(this);
        }

        return bl;
    }

    @Override
    public void tickMovement() {
        if (!this.isOnGround() && this.getVelocity().y < (double)0.0F) {
            this.setVelocity(this.getVelocity().multiply((double)1.0F, 0.6, (double)1.0F));
        }

        if (this.random.nextInt(24) == 0 && !this.isSilent()) {
            this.getWorld().playSoundClient(this.getX() + (double)0.5F, this.getY() + (double)0.5F, this.getZ() + (double)0.5F, SoundEvents.ENTITY_BLAZE_BURN, this.getSoundCategory(), 1.0F + this.random.nextFloat(), this.random.nextFloat() * 0.7F + 0.3F, false);
        }

        for(int i = 0; i < 2; ++i) {
            this.getWorld().addParticleClient(ParticleTypes.LARGE_SMOKE, this.getParticleX((double)0.5F), this.getRandomBodyY(), this.getParticleZ((double)0.5F), (double)0.0F, (double)0.0F, (double)0.0F);
        }

        super.tickMovement();
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
            this.playSound(SoundEvents.ENTITY_BLAZE_HURT, 1.0F, 1.0F);
            return bl;
        }

    }

    @Override
    public void handleStatus(byte status) {
        if (status == 4) {
            this.attackTicksLeft = 10;
            this.playSound(SoundEvents.ENTITY_BLAZE_AMBIENT, 1.0F, 1.0F);
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
    }

    @Override
    protected void mobTick(ServerWorld world) {

        LivingEntity livingEntity = this.getTarget();
        if (livingEntity != null && livingEntity.getEyeY() > this.getEyeY() + (double)0.5f && this.canTarget(livingEntity)) {
            Vec3d vec3d = this.getVelocity();
            this.setVelocity(this.getVelocity().add((double)0.0F, ((double)0.3F - vec3d.y) * (double)0.3F, (double)0.0F));
            this.velocityDirty = true;
        }

        super.mobTick(world);
    }



    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_BLAZE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BLAZE_DEATH;
    }

    @Override
    public void playAmbientSound() {
        this.playSound(SoundEvents.ENTITY_BLAZE_AMBIENT);
    }



    public class ShootFireballGoal extends Goal {
        private final FlameAtronach blaze;
        private int cooldown;

        public ShootFireballGoal(FlameAtronach blaze) {
            this.blaze = blaze;
            this.setControls(EnumSet.of(Control.TARGET, Control.LOOK));
        }

        @Override
        public boolean canStart() {
            LivingEntity target = blaze.getTarget();
            return target != null && target.isAlive() && blaze.canSee(target);
        }

        @Override
        public void tick() {
            LivingEntity target = blaze.getTarget();
            if (target == null) return;

            blaze.getLookControl().lookAt(target, 10.0F, 10.0F);

            if (--cooldown <= 0) {
                cooldown = 20; // fire every 2 seconds

                double dx = target.getX() - blaze.getX();
                double dy = target.getBodyY(0.5) - (blaze.getBodyY(0.5));
                double dz = target.getZ() - blaze.getZ();

                Vec3d direction = new Vec3d(dx, dy, dz).normalize();

                SmallFireballEntity fireball = new SmallFireballEntity(EntityType.SMALL_FIREBALL, blaze.getWorld());
                fireball.setOwner(blaze);
                fireball.setVelocity(direction.multiply(0.5));
                fireball.setPos(blaze.getX(), blaze.getBodyY(0.5) + 0.5, blaze.getZ());

                blaze.getWorld().spawnEntity(fireball);
                blaze.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 1.0F, 1.0F);
            }
        }

        @Override
        public boolean shouldContinue() {
            return canStart();
        }

    }

}
