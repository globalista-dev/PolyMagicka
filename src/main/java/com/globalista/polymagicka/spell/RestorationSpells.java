package com.globalista.polymagicka.spell;

import com.globalista.polymagicka.spell.effects.HealingEffects;
import net.minecraft.particle.ParticleTypes;

import java.util.List;

public class RestorationSpells {

    public static Spell LESSER_RESTORATION = new Spell("lesser_restoration", null,
            List.of(HealingEffects.HEALING), ParticleTypes.HEART, 120);

    public static Spell GREATER_RESTORATION = new Spell("greater_restoration", null,
            List.of(HealingEffects.HEALING, HealingEffects.REGENERATION), ParticleTypes.HEART, 240);

    public static Spell LIFE = new Spell("life", null,
            List.of(HealingEffects.ABSORPTION), ParticleTypes.HEART, 120);

    public static Spell SATURATION = new Spell("saturation", null,
            List.of(HealingEffects.SATURATION), ParticleTypes.HEART, 120);


}
