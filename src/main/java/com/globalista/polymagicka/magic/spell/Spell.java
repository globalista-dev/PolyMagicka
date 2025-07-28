package com.globalista.polymagicka.magic.spell;

import com.globalista.polymagicka.magic.components.SpellDataComponent;
import com.globalista.polymagicka.magic.components.SpellHandler;
import com.globalista.polymagicka.util.Helper;
import com.globalista.polymagicka.util.ModRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.*;

public class Spell {

    private String name;
    private String projectile;
    private List<StatusEffectInstance> effects;
    private SimpleParticleType particle;
    private int cooldown;

    public Spell(String name, String projectile, List<StatusEffectInstance> effects, SimpleParticleType particle, int cooldown) {
        this.name = name;
        this.projectile = projectile;
        this.effects = effects != null ? effects : List.of();
        this.particle = particle;
        this.cooldown = cooldown;
    }

    public String getName() {
        return name;
    }

    public String getProjectile() {
        return projectile;
    }

    public List<StatusEffectInstance> getEffects() {
        return effects;
    }

    public SimpleParticleType getParticle() {
        return particle;
    }

    public int getCooldown() {
        return cooldown;
    }

    public static final Codec<Spell> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("name").forGetter(Spell::getName),
            Codec.STRING.optionalFieldOf("projectile").forGetter(spell -> Optional.ofNullable(spell.getProjectile())),
            Codec.list(StatusEffectInstance.CODEC).optionalFieldOf("effects", List.of()).forGetter(Spell::getEffects),
            ModRegistry.SIMPLE_PARTICLE_TYPE_CODEC.fieldOf("particle").forGetter(Spell::getParticle),
            Codec.INT.fieldOf("cooldown").forGetter(Spell::getCooldown)
    ).apply(instance, (name, projectileOpt, effects, particle, cooldown) ->
            new Spell(name, projectileOpt.orElse(null), effects, particle, cooldown)
    ));

    public static void cast(Spell spell, World world, PlayerEntity user, Hand hand) {

        if (world.isClient) {
            return;
        }

        boolean scroll = user.getStackInHand(hand).isIn(ModRegistry.SCROLLS);
        boolean success = false;

        if(spell.getProjectile() != null) {

            // Projectiles or entity spawning
            SpellHandler.projectile(spell.getProjectile(), world, user, hand, spell);
            world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, SoundCategory.PLAYERS);
            success = true;
            return;

        } else if (!spell.getEffects().isEmpty()) {

            // Self spells
            for (StatusEffectInstance effect : spell.getEffects()) {
                user.addStatusEffect(effect);
            }
            world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_ILLUSIONER_PREPARE_MIRROR, SoundCategory.PLAYERS);
            success = true;
            return;

        }

        if (spell == AlterationSpells.TRANSMUTE) {

            if(user.getInventory().contains(new ItemStack(Items.IRON_INGOT))) {
                int slot = user.getInventory().getSlotWithStack(new ItemStack(Items.IRON_INGOT));
                user.giveItemStack(new ItemStack(Items.GOLD_INGOT));
                user.getInventory().getStack(slot).decrement(1);
                world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS);
                success = true;
            } else {
                world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_IRON_BREAK, SoundCategory.PLAYERS);
                success = false;
            }

        }

        if (spell.getName().contains("bound")) {

            ItemStack item = new ItemStack(Helper.get(spell.getName()));
            item.addEnchantment(Helper.getEnchant(Enchantments.FORTUNE, world), 5);
            item.addEnchantment(Helper.getEnchant(Enchantments.BINDING_CURSE, world), 1);
            item.set(SpellDataComponent.CREATION_TICK, (long)world.getServer().getTicks());
            user.giveItemStack(item);
            world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON, SoundCategory.PLAYERS);
            success = true;

        }

        if (spell == AlterationSpells.DEEP_STORAGE) {

            EnderChestInventory enderChestInventory = user.getEnderChestInventory();
            if (enderChestInventory != null && world instanceof ServerWorld){
                user.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, playerx) -> GenericContainerScreenHandler.createGeneric9x3(syncId, playerInventory, enderChestInventory), Text.translatable("deep.storage.title")));
                user.incrementStat(Stats.OPEN_ENDERCHEST);
            }
            world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_ENDER_CHEST_OPEN, SoundCategory.PLAYERS);
            success = true;

        }

        if (spell == ConjurationSpells.CONJURE_BUNDLE_OF_ARROWS) {

            ItemStack item = new ItemStack(Items.ARROW, 16);
            user.giveItemStack(item);
            world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS);
            world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_AMETHYST_BLOCK_HIT, SoundCategory.PLAYERS);
            success = true;

        }

        if (spell.getName().contains("burst") || spell.getName().contains("others")) {
            SpellHandler.aoe((ServerWorld) world, user, spell);
            success = true;
        }

        if (DestructionSpells.LINE_SPELLS.contains(spell)) {
            Entity entity = SpellHandler.getLookedAtEntity(user, 15, spell);

            if (entity instanceof LivingEntity living) {
                SpellHandler.spawnParticleLine((ServerWorld) world, user, living, spell.getParticle(), 20, 0.5);
                SpellHandler.spawnParticleBubble((ServerWorld) world, living.getBlockPos().toCenterPos(), spell.getParticle(), 3, 100);
                switch (spell.getName()) {
                    case "flames" -> {
                        world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS);
                        living.damage((ServerWorld) world, world.getDamageSources().indirectMagic(user, user), 5.0f);
                        living.setOnFireForTicks(40);
                        success = true;
                    }
                    case "frostbite" -> {
                        world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_BREEZE_SHOOT, SoundCategory.PLAYERS);
                        living.damage((ServerWorld) world, world.getDamageSources().indirectMagic(user, user), 5.0f);
                        living.setInPowderSnow(true);
                        living.setFrozenTicks(40);
                        success = true;
                    }
                    case "sparks" -> {
                        world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.PLAYERS);
                        living.damage((ServerWorld) world, world.getDamageSources().indirectMagic(user, user), 5.0f);
                        living.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 40, 1));
                        success = true;
                    }
                    case "bubble" -> {
                        world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, SoundCategory.PLAYERS);
                        living.damage((ServerWorld) world, world.getDamageSources().indirectMagic(user, user), 5.0f);
                        living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 40, 1));
                        success = true;
                    }
                    case "gust" -> {
                        world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_BREEZE_WHIRL, SoundCategory.PLAYERS);
                        living.damage((ServerWorld) world, world.getDamageSources().indirectMagic(user, user), 5.0f);
                        living.setVelocity(0, 1, 0);
                        success = true;
                    }
                    case "touch_of_ender" -> {
                        world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_ENDERMAN_SCREAM, SoundCategory.PLAYERS);
                        living.damage((ServerWorld) world, world.getDamageSources().indirectMagic(user, user), 5.0f);
                        living.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 40, 1));
                        success = true;
                    }
                }
            } else {
                user.sendMessage(Text.translatable("polymagicka.no.target.found").formatted(Formatting.RED, Formatting.BOLD), true);
                world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_ILLUSIONER_MIRROR_MOVE, SoundCategory.PLAYERS);
                success = false;
            }
        }

        if (spell == DestructionSpells.CHAIN_LIGHTNING) {
            int maxChains = 10;
            double range = 20.0;

            Set<LivingEntity> alreadyHit = new HashSet<>();
            LivingEntity current = findNearestTarget(world, user.getPos(), user, alreadyHit, range);

            if (current == null) {
                user.sendMessage(Text.translatable("polymagicka.no.target.found").formatted(Formatting.RED, Formatting.BOLD), true);
                world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_ILLUSIONER_MIRROR_MOVE, SoundCategory.PLAYERS);
                success = false;
            } else {
                Vec3d from = user.getEyePos();
                alreadyHit.add(current);
                current.damage((ServerWorld) world, world.getDamageSources().indirectMagic(user, user), 5.0f);

                spawnLightningParticles((ServerWorld) world, from, current.getEyePos());
                world.playSound(null, current.getBlockPos(), SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.PLAYERS, 1.0f, 1.6f);

                for (int i = 0; i < maxChains - 1; i++) {
                    LivingEntity next = findNearestTarget(world, current.getPos(), current, alreadyHit, range);
                    if (next == null) break;

                    alreadyHit.add(next);
                    next.damage((ServerWorld) world, world.getDamageSources().indirectMagic(user, user), 4.0f);

                    spawnLightningParticles((ServerWorld) world, current.getEyePos(), next.getEyePos());
                    current = next;
                }
                success = true;
            }
        }




        // Last thing in method! Determines the cooldown and stack decrease.
        if (success && !user.isCreative()) {
            ItemStack stack = user.getStackInHand(hand);
            user.getItemCooldownManager().set(stack, spell.getCooldown());
            if (scroll) {
                stack.decrement(1);
            }
        }
    }

    // Chain lightning logic
    private static LivingEntity findNearestTarget(World world, Vec3d origin, Entity exclude, Set<LivingEntity> ignore, double range) {
        return world.getEntitiesByClass(LivingEntity.class, Box.of(origin, range, range, range), e ->
                        e.isAlive() && e != exclude && !ignore.contains(e) && e instanceof HostileEntity)
                .stream()
                .min(Comparator.comparingDouble(e -> e.getPos().distanceTo(origin)))
                .orElse(null);
    }

    // Chain lightning logic
    private static void spawnLightningParticles(ServerWorld world, Vec3d from, Vec3d to) {
        net.minecraft.util.math.random.Random random = world.getRandom();
        int segments = 12;

        Vec3d diff = to.subtract(from);
        double segmentLength = 1.0 / segments;

        Vec3d last = from;

        for (int i = 1; i <= segments; i++) {
            double t = i * segmentLength;
            Vec3d point = from.lerp(to, t);

            double scale = (1.0 - t) * 0.5; // taper off
            double offsetX = (random.nextDouble() - 0.5) * scale;
            double offsetY = (random.nextDouble() - 0.5) * scale;
            double offsetZ = (random.nextDouble() - 0.5) * scale;

            Vec3d jagged = point.add(offsetX, offsetY, offsetZ);

            SpellHandler.spawnParticleLine(world, last, jagged, 0.2, ParticleTypes.ELECTRIC_SPARK);

            last = jagged;
        }
    }



}
