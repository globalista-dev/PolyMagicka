package com.globalista.polymagicka.magic.components.projectiles;

import eu.pb4.polymer.core.api.entity.PolymerEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.List;

public class SpellProjectileEntity extends SnowballEntity implements PolymerEntity {

    private List<StatusEffectInstance> effects;

    public SpellProjectileEntity(EntityType<? extends SnowballEntity> entityType, World world) {
        super(entityType, world);
        this.setNoGravity(true);
    }

    public SpellProjectileEntity(World world, LivingEntity owner, ItemStack stack) {
        super(world, owner, stack);
        this.setNoGravity(true);
    }

    public SpellProjectileEntity(World world, double x, double y, double z, ItemStack stack) {
        super(world, x, y, z, stack);
        this.setNoGravity(true);
    }


    public void setEffects(List<StatusEffectInstance> effects) {
        this.effects = effects;
    }

    public List<StatusEffectInstance> getEffects() {
        return effects;
    }

    @Override
    public EntityType<?> getPolymerEntityType(PacketContext context) {
        return EntityType.SHULKER_BULLET;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {

        LivingEntity target = (LivingEntity) entityHitResult.getEntity();
        if (this.getEffects() != null) {
            for (StatusEffectInstance instance : this.getEffects()) {
                if(instance != null) {
                    target.addStatusEffect(instance);
                    if (instance.getDuration() == 101 && getOwner() != null && getOwner() instanceof LivingEntity caster) {
                        caster.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 101, 1));
                    }
                }
                target.damage((ServerWorld) this.getWorld(), this.getDamageSources().indirectMagic(this, this.getOwner()), 1f);
            }
        } else {
            target.damage((ServerWorld) this.getWorld(), this.getDamageSources().indirectMagic(this, this.getOwner()), 2f);
        }
        getWorld().playSound(this, this.getBlockPos(), SoundEvents.BLOCK_IRON_PLACE, SoundCategory.BLOCKS, 1f, 1f);
        if(getOwner() != null) { getWorld().playSound(this, this.getOwner().getBlockPos(), SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.BLOCKS, 1f, 1f); }
        this.discard();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        getWorld().playSound(this, this.getBlockPos(), SoundEvents.BLOCK_IRON_PLACE, SoundCategory.BLOCKS, 1f, 1f);
        if(getOwner() != null) { getWorld().playSound(this, this.getOwner().getBlockPos(), SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.BLOCKS, 1f, 1f); }
        this.discard();
    }
}
