package com.globalista.polymagicka.spell.effects;

import net.minecraft.entity.effect.StatusEffects;

import java.util.ArrayList;
import java.util.List;

public class HealingEffects {

    public static List<SpellEffect> HEALING_EFFECTS = new ArrayList<>();

    public static final SpellEffect HEALING = SpellEffect.register("healing", StatusEffects.INSTANT_HEALTH);

    public static final SpellEffect REGENERATION = SpellEffect.register("regeneration", StatusEffects.REGENERATION);

    public static final SpellEffect HEALTH = SpellEffect.register("health", StatusEffects.HEALTH_BOOST);

    public static final SpellEffect ABSORPTION = SpellEffect.register("absorption", StatusEffects.ABSORPTION);

    public static final SpellEffect SATURATION = SpellEffect.register("saturation", StatusEffects.SATURATION);

    static {
        HEALING_EFFECTS.add(HEALING);
        HEALING_EFFECTS.add(REGENERATION);
        HEALING_EFFECTS.add(HEALTH);
        HEALING_EFFECTS.add(ABSORPTION);
        HEALING_EFFECTS.add(SATURATION);
    }


}
