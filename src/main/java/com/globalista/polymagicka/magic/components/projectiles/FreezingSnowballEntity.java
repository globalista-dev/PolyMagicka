package com.globalista.polymagicka.magic.components.projectiles;

import eu.pb4.polymer.core.api.entity.PolymerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.List;

public class FreezingSnowballEntity extends SnowballEntity implements PolymerEntity {

    private LivingEntity target = null;
    private int freezeParticleTicks = 0;

    private List<StatusEffectInstance> effects;

    public FreezingSnowballEntity(EntityType<? extends SnowballEntity> entityType, World world) {
        super(entityType, world);
        this.setNoGravity(true);
    }

    public FreezingSnowballEntity(World world, LivingEntity owner, ItemStack stack) {
        super(world, owner, stack);
        this.setNoGravity(true);
    }

    public FreezingSnowballEntity(World world, double x, double y, double z, ItemStack stack) {
        super(world, x, y, z, stack);
        this.setNoGravity(true);
    }

    @Override
    public EntityType<?> getPolymerEntityType(PacketContext context) {
        return EntityType.SNOWBALL;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {

        LivingEntity target = (LivingEntity) entityHitResult.getEntity();

        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 30 * 20, 2));

        target.damage((ServerWorld) this.getWorld(), this.getDamageSources().indirectMagic(this, this.getOwner()), 1f);

        target.setInPowderSnow(true);
        target.setFrozenTicks(30*20);
        this.target = target;
        this.freezeParticleTicks = 30 * 20;

        if (!this.getWorld().isClient) {
            ((ServerWorld) this.getWorld()).spawnParticles(ParticleTypes.SNOWFLAKE, getX(), getY(), getZ(), 10, 0.2, 0.2, 0.2, 0.05);
            this.getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.BLOCK_POWDER_SNOW_STEP, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        }
        this.discard();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if (!this.getWorld().isClient) {
            ((ServerWorld) this.getWorld()).spawnParticles(ParticleTypes.SNOWFLAKE, getX(), getY(), getZ(), 10, 0.2, 0.2, 0.2, 0.05);
            this.getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.BLOCK_POWDER_SNOW_STEP, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        }

        this.discard();
    }

    @Override
    public void tick() {
        super.tick();

        if (target != null && freezeParticleTicks > 0) {
            for (int i = 0; i < 5; i++) {
                ((ServerWorld) this.getWorld()).spawnParticles(
                        ParticleTypes.SNOWFLAKE,
                        target.getX() + (this.random.nextDouble() - 0.5) * target.getWidth(),
                        target.getY() + this.random.nextDouble() * target.getHeight(),
                        target.getZ() + (this.random.nextDouble() - 0.5) * target.getWidth(),
                        0, 0.01, 0, 0, 0.05
                );
            }
            freezeParticleTicks--;
        }
    }


}
