package com.globalista.polymagicka.magic.spell;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;

import java.util.List;

public class IllusionSpells {

    // Line 1
    public static Spell LESSER_TELEPORTATION = new Spell("lesser_teleportation", null, null,
            ParticleTypes.ASH, 480);

    public static Spell GREATER_TELEPORTATION = new Spell("greater_teleportation", null, null,
            ParticleTypes.ASH, 1200);

    public static Spell LESSER_INVISIBILITY = new Spell("invisibility", null,
            List.of(new StatusEffectInstance(StatusEffects.INVISIBILITY, 200, 1)), ParticleTypes.ASH, 240);

    public static Spell GREATER_INVISIBILITY = new Spell("invisibility", null,
            List.of(new StatusEffectInstance(StatusEffects.INVISIBILITY, 400, 2)), ParticleTypes.ASH, 480);

    public static Spell COURAGE = new Spell("courage", null,
            List.of(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 2)), ParticleTypes.CRIT, 240);

    public static Spell CALL_TO_ARMS = new Spell("call_to_arms", null,
            List.of(new StatusEffectInstance(StatusEffects.STRENGTH, 1200, 1),
                    new StatusEffectInstance(StatusEffects.SPEED, 1200, 1),
                    new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 1200, 2)), ParticleTypes.WAX_OFF, 1800);

    public static Spell CATS_EYES = new Spell("cats_eyes", null,
            List.of(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 1200, 1)), ParticleTypes.WAX_OFF, 1200);

    // Line 2
    public static Spell BLINDING = new Spell("blinding", "regular",
            List.of(new StatusEffectInstance(StatusEffects.BLINDNESS, 100, 1)), ParticleTypes.ENCHANTED_HIT, 240);

    public static Spell DARKENING = new Spell("darkening", "regular",
            List.of(new StatusEffectInstance(StatusEffects.DARKNESS, 100, 1)), ParticleTypes.ENCHANTED_HIT, 240);

    public static Spell UMBRAL_MAW = new Spell("umbral_maw", null, null, ParticleTypes.ASH, 240);

    public static Spell MINOR_ILLUSION = new Spell("minor_illusion", "illusion", null, ParticleTypes.ENCHANTED_HIT, 240);

    public static Spell MIRROR_IMAGE = new Spell("mirror_image", "illusion", null, ParticleTypes.ENCHANTED_HIT, 1200);

    public static Spell MISLEAD = new Spell("mislead", "illusion", null, ParticleTypes.ENCHANTED_HIT, 2400);

    public static Spell MENTAL_PRISON = new Spell("mental_prison", null, null, ParticleTypes.ENCHANT, 2400);

}
