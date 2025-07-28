package com.globalista.polymagicka.magic.spell;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;

import java.util.List;

public class AlterationSpells {

    public static Spell OAKFLESH = new Spell("oakflesh", null,
            List.of(new StatusEffectInstance(StatusEffects.RESISTANCE, 400, 1)), ParticleTypes.FLASH, 120);

    public static Spell STONEFLESH = new Spell("stoneflesh", null,
            List.of(new StatusEffectInstance(StatusEffects.RESISTANCE, 400, 2)), ParticleTypes.FLASH, 240);

    public static Spell IRONFLESH = new Spell("ironflesh", null,
            List.of(new StatusEffectInstance(StatusEffects.RESISTANCE, 400, 3)), ParticleTypes.FLASH, 480);

    public static Spell DIAMONDFLESH = new Spell("diamondflesh", null,
            List.of(new StatusEffectInstance(StatusEffects.RESISTANCE, 400, 3), new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 400, 1)), ParticleTypes.FLASH, 960);

    public static Spell WATERBREATHING = new Spell("waterbreathing", null,
            List.of(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 400, 2)), ParticleTypes.DRIPPING_WATER, 240);

    public static Spell LESSER_HASTE = new Spell("lesser_haste", null,
            List.of(new StatusEffectInstance(StatusEffects.HASTE, 400, 1)), ParticleTypes.ENCHANTED_HIT, 240);

    public static Spell GREATER_HASTE = new Spell("greater_haste", null,
            List.of(new StatusEffectInstance(StatusEffects.HASTE, 400, 2)), ParticleTypes.ENCHANTED_HIT, 480);

    public static Spell PARALYSIS = new Spell("paralysis", "regular",
            List.of(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 10), new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 100, 40)), ParticleTypes.CRIT, 240);

    public static Spell TRANSMUTE = new Spell("transmute", null, null, ParticleTypes.ENCHANT, 2000);

    public static Spell MAGELIGHT = new Spell("magelight", null,
            List.of(new StatusEffectInstance(StatusEffects.GLOWING, 200, 1)), ParticleTypes.ENCHANT, 60);

    public static Spell LONGSTRIDE = new Spell("longstride", null,
            List.of(new StatusEffectInstance(StatusEffects.SPEED, 200, 1)), ParticleTypes.ENCHANT, 120);

    public static Spell DOLPHINS_GRACE = new Spell("dolphins_grace", null,
            List.of(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 200, 1)), ParticleTypes.ENCHANT, 120);

    public static Spell WITHER = new Spell("wither", "regular",
            List.of(new StatusEffectInstance(StatusEffects.WITHER, 100, 1)), ParticleTypes.ENCHANT, 120);

    public static Spell DEEP_STORAGE = new Spell("deep_storage", null,
            null, ParticleTypes.ENCHANT, 120);



}
