package com.globalista.polymagicka.magic.components;

import com.globalista.polymagicka.magic.components.projectiles.FreezingSnowballEntity;
import com.globalista.polymagicka.magic.components.projectiles.SpellProjectileEntity;
import com.globalista.polymagicka.magic.spell.Spell;
import com.globalista.polymagicka.magic.summons.*;
import com.globalista.polymagicka.util.Helper;
import com.globalista.polymagicka.util.ModRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.*;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.*;

public class SpellHandler {

    public static void projectile(String name, World world, PlayerEntity user, Hand hand, Spell spell) {

        switch (name){
            case "regular" -> {
                Vec3d eyePos = user.getEyePos();
                Vec3d look = user.getRotationVec(1.0F);
                Vec3d spawnPos = eyePos.add(look.multiply(2));

                SpellProjectileEntity entity = new SpellProjectileEntity(ModRegistry.SPELL_PROJECTILE, world);

                entity.setOwner(user);
                entity.setVelocity(look.multiply(2));
                entity.setPosition(spawnPos);
                if (spell != null) { entity.setEffects(spell.getEffects()); }
                world.spawnEntity(entity);
            }
            case "iron_golem" -> {
                BlockPos frontOfPlayer = user.getBlockPos().offset(user.getHorizontalFacing(), 2).offset(Direction.Axis.Y, 1);

                IronGolemEntity entity = new IronGolemEntity(EntityType.IRON_GOLEM, world);
                entity.setPosition(frontOfPlayer.toCenterPos());
                world.spawnEntity(entity);
            }
            case "snow_golem" -> {
                BlockPos frontOfPlayer = user.getBlockPos().offset(user.getHorizontalFacing(), 2).offset(Direction.Axis.Y, 1);

                SnowGolemEntity entity = new SnowGolemEntity(EntityType.SNOW_GOLEM, world);
                entity.setPosition(frontOfPlayer.toCenterPos());
                world.spawnEntity(entity);
            }
            case "lesser_familiar" -> {
                BlockPos frontOfPlayer = user.getBlockPos().offset(user.getHorizontalFacing(), 2).offset(Direction.Axis.Y, 1);

                Familiar entity = new Familiar(ModRegistry.FAMILIAR, world);
                entity.setPosition(frontOfPlayer.toCenterPos());
                world.spawnEntity(entity);
            }
            case "greater_familiar" -> {
                BlockPos frontOfPlayer = user.getBlockPos().offset(user.getHorizontalFacing(), 2).offset(Direction.Axis.Y, 1);

                GreaterFamiliar entity = new GreaterFamiliar(ModRegistry.GREATER_FAMILIAR, world);
                entity.setPosition(frontOfPlayer.toCenterPos());
                world.spawnEntity(entity);
            }
            case "flame_atronach" -> {
                BlockPos frontOfPlayer = user.getBlockPos().offset(user.getHorizontalFacing(), 2).offset(Direction.Axis.Y, 1);

                FlameAtronach entity = new FlameAtronach(ModRegistry.FLAME_ATRONACH, world);
                entity.setPosition(frontOfPlayer.toCenterPos());
                world.spawnEntity(entity);
            }
            case "wind_atronach" -> {
                BlockPos frontOfPlayer = user.getBlockPos().offset(user.getHorizontalFacing(), 2).offset(Direction.Axis.Y, 1);

                WindAtronach entity = new WindAtronach(ModRegistry.WIND_ATRONACH, world);
                entity.setPosition(frontOfPlayer.toCenterPos());
                world.spawnEntity(entity);
            }
            case "raised_zombie" -> {
                BlockPos frontOfPlayer = user.getBlockPos().offset(user.getHorizontalFacing(), 2).offset(Direction.Axis.Y, 1);

                RaisedZombie entity = new RaisedZombie(ModRegistry.RAISED_ZOMBIE, world);
                entity.setPosition(frontOfPlayer.toCenterPos());
                world.spawnEntity(entity);
            }
            case "raised_skeleton" -> {
                BlockPos frontOfPlayer = user.getBlockPos().offset(user.getHorizontalFacing(), 2).offset(Direction.Axis.Y, 1);

                RaisedSkeleton entity = new RaisedSkeleton(ModRegistry.RAISED_SKELETON, world);
                entity.setPosition(frontOfPlayer.toCenterPos());
                world.spawnEntity(entity);
            }
            case "spider_swarm" -> {
                double x = user.getX();
                double y = user.getY();
                double z = user.getZ();

                Vec3d[] offsets = new Vec3d[] {
                        new Vec3d(3, 0, 0),
                        new Vec3d(-3, 0, 0),
                        new Vec3d(0, 0, 3),
                        new Vec3d(0, 0, -3),
                        new Vec3d(2.5, 0, 2.5),
                        new Vec3d(-2.5, 0, -2.5),
                        new Vec3d(-2.5, 0, 2.5),
                        new Vec3d(2.5, 0, -2.5),
                };

                for (Vec3d offset : offsets) {
                    SwarmSpider spider = new SwarmSpider(ModRegistry.SWARM_SPIDER, world);
                    spider.refreshPositionAndAngles(x + offset.x, y + offset.y, z + offset.z, world.random.nextFloat() * 360F, 0);

                    world.spawnEntity(spider);
                }
            }
            case "spectral_arrow" -> {
                BlockPos frontOfPlayer = user.getBlockPos().offset(user.getHorizontalFacing(), 2).offset(Direction.Axis.Y, 1);

                SpectralArrowEntity entity = new SpectralArrowEntity(EntityType.SPECTRAL_ARROW, world);
                entity.setPosition(frontOfPlayer.toCenterPos());
                world.spawnEntity(entity);
            }
            case "arrow" -> {
                Vec3d look = user.getRotationVec(1.0F).normalize();
                Vec3d right = look.crossProduct(new Vec3d(0, 1, 0)).normalize();
                Vec3d up = right.crossProduct(look).normalize();

                Vec3d origin = user.getEyePos().add(look.multiply(2));

                int horizontalCount = 5;
                int verticalCount = 5;
                double spacing = 0.25;

                for (int i = 0; i < horizontalCount; i++) {
                    for (int j = 0; j < verticalCount; j++) {
                        double xOffset = (i - (horizontalCount - 1) / 2.0) * spacing;
                        double yOffset = (j - (verticalCount - 1) / 2.0) * spacing;

                        Vec3d spawnPos = origin.add(right.multiply(xOffset)).add(up.multiply(yOffset));

                        ArrowEntity arrow = new ArrowEntity(world, spawnPos.x, spawnPos.y, spawnPos.z, ItemStack.EMPTY, user.getStackInHand(hand));
                        arrow.setOwner(user);
                        arrow.setVelocity(look.x, look.y, look.z, 2.5F, 0.0F); // speed, inaccuracy

                        world.spawnEntity(arrow);
                    }
                }
            }
            case "small_fireball" -> {

                BlockPos frontOfPlayer = user.getBlockPos().offset(user.getHorizontalFacing(), 1).offset(Direction.Axis.Y, 1);

                SmallFireballEntity fireball = new SmallFireballEntity(EntityType.SMALL_FIREBALL, world);
                fireball.setPosition(frontOfPlayer.toCenterPos());
                fireball.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 2.0f + 0.5f, 0.5f);

                world.spawnEntity(fireball);

            }
            case "fireball" -> {
                if(spell.getName().equals("meteor")) {
                    HitResult hitResult = user.raycast(20.0, 1.0F, false);
                    Vec3d eyePos = user.getEyePos();
                    Vec3d look = user.getRotationVec(1.0F).normalize();

                    Vec3d hitPos = hitResult.getType() == HitResult.Type.BLOCK ? hitResult.getPos() : eyePos.add(look.multiply(15));
                    double distance = MathHelper.clamp(eyePos.distanceTo(hitPos), 3.0, 15.0);
                    Vec3d clampedTarget = eyePos.add(look.multiply(distance));

                    Vec3d right = look.crossProduct(new Vec3d(0, 1, 0)).normalize();
                    Vec3d spawnPos = clampedTarget.add(right.multiply(3)).add(0, 10, 0);
                    Vec3d velocity = clampedTarget.subtract(spawnPos).normalize().multiply(0.1);

                    world.playSound(user, user.getBlockPos(), SoundEvents.UI_BUTTON_CLICK.value(), SoundCategory.PLAYERS, 1.0F, 10.0F);

                    if (world instanceof ServerWorld serverWorld) {

                        List<Vec3d> path = new ArrayList<>();
                        int steps = 20;
                        for (int i = 0; i <= steps; i++) {
                            double t = (i / (double) steps) * (2.0 / 3.0) + (1.0 / 3.0);
                            Vec3d point = spawnPos.lerp(clampedTarget, t);
                            path.add(point);
                            serverWorld.spawnParticles(ParticleTypes.WAX_OFF, point.x, point.y, point.z, 1, 0, 0, 0, 0);
                        }

                        FireballEntity fireball = new FireballEntity(EntityType.FIREBALL, world);
                        fireball.setOwner(user);
                        fireball.setPosition(spawnPos);
                        fireball.setVelocity(velocity);
                        world.spawnEntity(fireball);
                    }
                } else {
                    BlockPos frontOfPlayer = user.getBlockPos().offset(user.getHorizontalFacing(), 1).offset(Direction.Axis.Y, 1);

                    FireballEntity fireball = new FireballEntity(EntityType.FIREBALL, world);
                    fireball.setOwner(user);
                    fireball.setPosition(frontOfPlayer.toCenterPos());
                    fireball.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 0.2f + 0.5f, 1.0f);
                    world.spawnEntity(fireball);
                }
            }
            case "freezing_snowball" -> {
                Vec3d eyePos = user.getEyePos();
                Vec3d look = user.getRotationVec(1.0F);
                Vec3d spawnPos = eyePos.add(look.multiply(3));

                FreezingSnowballEntity entity = new FreezingSnowballEntity(ModRegistry.FREEZING_SNOWBALL, world);

                entity.setOwner(user);
                entity.setVelocity(look.multiply(2));
                entity.setPosition(spawnPos);
                world.spawnEntity(entity);
            }
            case "living_armor" -> {

                BlockPos frontOfPlayer = user.getBlockPos().offset(user.getHorizontalFacing(), 2).offset(Direction.Axis.Y, 1);

                LivingArmor armor = new LivingArmor(ModRegistry.LIVING_ARMOR, world);

                armor.setPosition(frontOfPlayer.toCenterPos());



                if (spell.getName().contains("armor")) {
                    armor.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, -1, 1));
                    Random random = new Random();
                    int variant = random.nextInt(5);

                    switch (variant) {
                        case 0 -> {
                            armor.equipStack(EquipmentSlot.HEAD, Helper.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzE4N2RlMjMxNmM4YjY1YmVlZGRlNzA0Y2VmMDU0OWRjODIyYTcxMTFmOTljMTJlMDBjNDgxNDY0ZTQ4N2RhMiJ9fX0="));
                            armor.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.CHAINMAIL_CHESTPLATE));
                            armor.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.CHAINMAIL_LEGGINGS));
                            armor.equipStack(EquipmentSlot.FEET, new ItemStack(Items.CHAINMAIL_BOOTS));
                            armor.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
                        }
                        case 1 -> {
                            armor.equipStack(EquipmentSlot.HEAD, Helper.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTVlYjBiZDg1YWFkZGYwZDI5ZWQwODJlYWMwM2ZjYWRlNDNkMGVlODAzYjBlODE2MmFkZDI4YTYzNzlmYjU0ZSJ9fX0="));
                            armor.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
                            armor.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.IRON_LEGGINGS));
                            armor.equipStack(EquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS));
                            armor.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
                        }
                        case 2 -> {
                            armor.equipStack(EquipmentSlot.HEAD, Helper.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTM2NmYyOTFmNTVmYmFkMjg0NmI0NjA5OTZmNzM2MWY0NTRiOWUzZDk0MDE1OTc4YThlNTFiNjU1YmU5MTMyOSJ9fX0="));
                            armor.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.GOLDEN_CHESTPLATE));
                            armor.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.GOLDEN_LEGGINGS));
                            armor.equipStack(EquipmentSlot.FEET, new ItemStack(Items.GOLDEN_BOOTS));
                            armor.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
                        }
                        case 3 -> {
                            armor.equipStack(EquipmentSlot.HEAD, Helper.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjgxNWZjMWNkNjQzY2I1YTA4YWE5YmRjNjZhNjU1MTU3MmY2NDYzMDNmMGNhYTNjZmJjZjNjM2EyNWU1MTFkNCJ9fX0="));
                            armor.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.DIAMOND_CHESTPLATE));
                            armor.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.DIAMOND_LEGGINGS));
                            armor.equipStack(EquipmentSlot.FEET, new ItemStack(Items.DIAMOND_BOOTS));
                            armor.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_SWORD));
                        }
                        case 4 -> {
                            armor.equipStack(EquipmentSlot.HEAD, Helper.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjM2NjM1OTk5YWM4YzNlYjNmOWRjMDNlNTZkMGRjMmZlZmNiY2NlYTM1NDlmYzYzMTU0N2U5YThhNTFhNjE0OSJ9fX0="));
                            armor.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.NETHERITE_CHESTPLATE));
                            armor.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.NETHERITE_LEGGINGS));
                            armor.equipStack(EquipmentSlot.FEET, new ItemStack(Items.NETHERITE_BOOTS));
                            armor.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.NETHERITE_SWORD));
                        }
                    }

                    armor.equipStack(EquipmentSlot.OFFHAND, new ItemStack(Items.SHIELD));

                }
                if (spell.getName().contains("statue")){
                    armor.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.MACE));

                    armor.equipStack(EquipmentSlot.HEAD, Helper.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWRkOWEzNTFhNjMzNjZjODRjNjQzOGRkYTUzNmQzZDliYmI4MWUwMTY0MGIzZDAwZTRkZDg4OWRmYjg2OGQ4MyJ9fX0="));
                    armor.equipStack(EquipmentSlot.CHEST, Helper.getColoredArmor(Items.LEATHER_CHESTPLATE, 8618883));
                    armor.equipStack(EquipmentSlot.LEGS, Helper.getColoredArmor(Items.LEATHER_LEGGINGS, 8618883));
                    armor.equipStack(EquipmentSlot.FEET, Helper.getColoredArmor(Items.LEATHER_BOOTS, 8618883));
                }
                world.spawnEntity(armor);
            }
            case "lightning" -> {
                BlockPos frontOfPlayer = user.getBlockPos().offset(user.getHorizontalFacing(), 10);
                LightningEntity lightningBolt = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
                lightningBolt.setPosition(frontOfPlayer.toCenterPos());
                world.spawnEntity(lightningBolt);
            }
            case "dragon_fireball" -> {
                BlockPos frontOfPlayer = user.getBlockPos().offset(user.getHorizontalFacing(), 1).offset(Direction.Axis.Y, 1);

                DragonFireballEntity projectile = new DragonFireballEntity(EntityType.DRAGON_FIREBALL, world);
                projectile.setPosition(frontOfPlayer.toCenterPos());
                projectile.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 0.2f + 0.5f, 1.0f);
                world.spawnEntity(projectile);
            }
            case "wind_charge" -> {
                BlockPos frontOfPlayer = user.getBlockPos().offset(user.getHorizontalFacing(), 1).offset(Direction.Axis.Y, 1);

                WindChargeEntity windCharge = new WindChargeEntity(EntityType.BREEZE_WIND_CHARGE, world);
                windCharge.setPosition(frontOfPlayer.toCenterPos());
                windCharge.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 0.2f + 0.5f, 1.0f);
                world.spawnEntity(windCharge);
            }
            default -> {
                world.playSound(user, user.getBlockPos(), SoundEvents.ENTITY_VILLAGER_NO, SoundCategory.PLAYERS);
                System.out.println("There is no projectile named " + name + "!");
                System.out.println("Please report this error to yGlobalista!");
            }
        }

    }

    public static void aoe(ServerWorld world, PlayerEntity user, Spell spell) {
        double maxDistance = 20.0;
        double radius = 3.0;

        Vec3d cameraPos = user.getCameraPosVec(1.0F);
        Vec3d rotation = user.getRotationVec(1.0F);
        Vec3d targetPos = cameraPos.add(rotation.multiply(maxDistance));

        HitResult result = world.raycast(new RaycastContext(
                cameraPos,
                targetPos,
                RaycastContext.ShapeType.OUTLINE,
                RaycastContext.FluidHandling.NONE,
                user
        ));

        Vec3d center = result.getPos();
        double y = center.y;

        Box awayBox = Box.of(center, radius, 2.0, radius);
        Box onCasterBox = Box.of(user.getBlockPos().toCenterPos(), radius, 2.0, 10);

        List<LivingEntity> hostileTargets = world.getEntitiesByClass(
                LivingEntity.class,
                awayBox,
                entity -> entity instanceof HostileEntity && entity.isAlive()
        );

        List<LivingEntity> hostileTargetsNear = world.getEntitiesByClass(
                LivingEntity.class,
                onCasterBox,
                entity -> entity instanceof HostileEntity && entity.isAlive()
        );

        List<LivingEntity> friendlyTargets = world.getEntitiesByClass(
                LivingEntity.class,
                onCasterBox,
                entity -> !(entity instanceof HostileEntity) && entity.isAlive()
        );

        for (LivingEntity target : hostileTargets) {
            switch (spell.getName()) {
                case "fire_burst" -> {
                    target.damage(world, world.getDamageSources().magic(), 6);
                    target.setOnFireFor(5);
                    target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 1));
                }
                case "ice_burst" -> {
                    target.damage(world, world.getDamageSources().freeze(), 4);
                    target.setInPowderSnow(true);
                    target.setFrozenTicks(100);
                    target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 1));
                }
                case "wind_burst" -> {
                    target.damage(world, world.getDamageSources().magic(), 3);
                    Vec3d push = target.getPos().subtract(center).normalize().multiply(1.5);
                    target.addVelocity(push.x, 0.8, push.z);
                    target.velocityModified = true;
                    target.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 50, 1));
                }
                case "shock_burst" -> {
                    target.damage(world, world.getDamageSources().lightningBolt(), 5);
                    LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world, SpawnReason.EVENT);
                    if (lightning != null) {
                        lightning.refreshPositionAfterTeleport(target.getX(), target.getY(), target.getZ());
                        world.spawnEntity(lightning);
                    }
                    target.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 100, 1));
                }
                case "ancient_burst" -> {
                    target.damage(world, world.getDamageSources().wither(), 8);
                    target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 1));
                    target.setOnFireFor(5);
                }
            }
        }

        // Bounding circle
        double stepAngle = Math.PI / 12; // 24 points around the circle
        for (double angle = 0; angle < Math.PI * 2; angle += stepAngle) {
            double x = center.x + radius * Math.cos(angle);
            double z = center.z + radius * Math.sin(angle);
            world.spawnParticles(ParticleTypes.WAX_OFF, x, y, z, 0, 0, 0, 0, 0);
        }

        // Spell particles
        for (int i = 0; i < 100; i++) {
            double angle = world.random.nextDouble() * 2 * Math.PI;
            double dist = world.random.nextDouble() * radius;
            double x = center.x + Math.cos(angle) * dist;
            double z = center.z + Math.sin(angle) * dist;
            world.spawnParticles(spell.getParticle(), x, y, z, 0, 0, 0, 0, 0);
        }
    }

    public static Entity getLookedAtEntity(PlayerEntity user, double maxDistance, Spell spell) {
        World world = user.getWorld();
        Vec3d start = user.getEyePos();
        Vec3d look = user.getRotationVec(1.0F);
        Vec3d end = start.add(look.multiply(maxDistance));

        Box searchBox = user.getBoundingBox().stretch(look.multiply(maxDistance)).expand(1.0);

        Entity closest = null;
        double closestDistance = maxDistance * maxDistance;

        for (Entity entity : world.getOtherEntities(user, searchBox, e -> e instanceof LivingEntity)) {
            Box entityBox = entity.getBoundingBox().expand(0.3);
            Optional<Vec3d> intersection = entityBox.raycast(start, end);

            if (intersection.isPresent()) {
                double distance = start.squaredDistanceTo(intersection.get());
                if (distance < closestDistance) {
                    closest = entity;
                    closestDistance = distance;
                }
            }
        }

        return closest;
    }

    public static void spawnParticleLine(ServerWorld world, Vec3d start, Vec3d end, double spacing, SimpleParticleType particle) {
        Vec3d delta = end.subtract(start);
        double distance = delta.length();
        Vec3d step = delta.normalize().multiply(spacing);

        int steps = (int) (distance / spacing);
        Vec3d current = start;

        for (int i = 0; i < steps; i++) {
            world.spawnParticles(particle, current.x, current.y, current.z, 1, 0, 0, 0, 0);
            current = current.add(step);
        }
    }

    public static void spawnParticleLine(ServerWorld world, PlayerEntity caster, LivingEntity target, SimpleParticleType particle, int steps, double spacing) {
        Vec3d start = caster.getEyePos();
        Vec3d end = target.getEyePos();
        Vec3d direction = end.subtract(start).normalize();

        for (int i = 0; i < steps; i++) {
            double distance = i * spacing;
            Vec3d pos = start.add(direction.multiply(distance));
            world.spawnParticles(particle, pos.x, pos.y, pos.z, 0, 0, 0, 0, 0);
        }
    }

    public static void spawnParticleBubble(ServerWorld world, Vec3d center, SimpleParticleType particle, double radius, int points) {
        net.minecraft.util.math.random.Random random = world.getRandom();

        for (int i = 0; i < points; i++) {
            double theta = Math.acos(2 * random.nextDouble() - 1);
            double phi = 2 * Math.PI * random.nextDouble();

            double x = center.x + radius * Math.sin(theta) * Math.cos(phi);
            double y = center.y + radius * Math.cos(theta);
            double z = center.z + radius * Math.sin(theta) * Math.sin(phi);

            world.spawnParticles(particle, x, y, z, 0, 0, 0, 0, 0);
        }
    }



}
