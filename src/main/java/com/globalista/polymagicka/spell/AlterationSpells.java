package com.globalista.polymagicka.spell;

import com.globalista.polymagicka.spell.effects.SupportEffects;
import net.minecraft.particle.ParticleTypes;

import java.util.List;

public class AlterationSpells {

    public static Spell OAKFLESH = new Spell("oakflesh", null,
            List.of(SupportEffects.RESISTANCE), ParticleTypes.FLASH, 120);

    public static Spell STONEFLESH = new Spell("stoneflesh", null,
            List.of(SupportEffects.RESISTANCE, SupportEffects.FIRE_RESISTANCE), ParticleTypes.FLASH, 240);

    public static Spell WATERBREATHING = new Spell("waterbreathing", null,
            List.of(SupportEffects.WATER_BREATHING), ParticleTypes.DRIPPING_WATER, 240);

    public static Spell HASTE = new Spell("haste", null,
            List.of(SupportEffects.HASTE), ParticleTypes.ENCHANTED_HIT, 240);



}
