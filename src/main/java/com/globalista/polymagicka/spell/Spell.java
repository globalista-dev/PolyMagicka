package com.globalista.polymagicka.spell;

import com.globalista.polymagicka.spell.effects.SpellEffect;
import com.globalista.polymagicka.util.ModRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Spell {

    private String name;
    private String projectile;
    private List<SpellEffect> effects;
    private SimpleParticleType particle;
    private int cooldown;

    public Spell(String name, String projectile, List<SpellEffect> effects, SimpleParticleType particle, int cooldown) {
        this.name = name;
        this.projectile = projectile;
        this.effects = effects;
        this.particle = particle;
        this.cooldown = cooldown;
    }

    public String getName() {
        return name;
    }

    public String getProjectile() {
        return projectile;
    }

    public List<SpellEffect> getEffects() {
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
            Codec.STRING.fieldOf("entities").forGetter(Spell::getProjectile),
            Codec.list(SpellEffect.CODEC).optionalFieldOf("effects", List.of()).forGetter(Spell::getEffects),
            ModRegistry.SIMPLE_PARTICLE_TYPE_CODEC.fieldOf("particle").forGetter(Spell::getParticle),
            Codec.INT.fieldOf("cooldown").forGetter(Spell::getCooldown)
    ).apply(instance, Spell::new));

    public static void cast(Spell spell, World world, PlayerEntity user, Hand hand) {}

}
