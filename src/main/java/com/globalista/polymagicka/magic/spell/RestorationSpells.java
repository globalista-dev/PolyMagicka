package com.globalista.polymagicka.magic.spell;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;

import java.util.List;

public class RestorationSpells {

    public static Spell LESSER_RESTORATION = new Spell("lesser_restoration", null,
            List.of(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1)), ParticleTypes.HEART, 120);

    public static Spell GREATER_RESTORATION = new Spell("greater_restoration", null,
            List.of(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 2)), ParticleTypes.HEART, 240);

    public static Spell LIFE = new Spell("life", null,
            List.of(new StatusEffectInstance(StatusEffects.ABSORPTION, 400, 1)), ParticleTypes.HEART, 120);

    public static Spell NOURISHMENT = new Spell("nourishment", null,
            List.of(new StatusEffectInstance(StatusEffects.SATURATION, 400, 1)), ParticleTypes.HEART, 120);


}
