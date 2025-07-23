package com.globalista.polymagicka.spell.effects;

import net.minecraft.entity.effect.StatusEffects;

import java.util.ArrayList;
import java.util.List;

public class SupportEffects {

    public static List<SpellEffect> SUPPORT_EFFECTS = new ArrayList<>();

    public static final SpellEffect SPEED = SpellEffect.register("speed", StatusEffects.SPEED);

    public static final SpellEffect HASTE = SpellEffect.register("haste", StatusEffects.HASTE);

    public static final SpellEffect STRENGTH  = SpellEffect.register("strength", StatusEffects.STRENGTH);

    public static final SpellEffect LEAPING = SpellEffect.register("leaping", StatusEffects.JUMP_BOOST);

    public static final SpellEffect RESISTANCE = SpellEffect.register("resistance", StatusEffects.RESISTANCE);

    public static final SpellEffect FIRE_RESISTANCE = SpellEffect.register("fire_resistance", StatusEffects.FIRE_RESISTANCE);

    public static final SpellEffect WATER_BREATHING = SpellEffect.register("water_breathing", StatusEffects.WATER_BREATHING);

    public static final SpellEffect INVISIBILITY = SpellEffect.register("invisibility", StatusEffects.INVISIBILITY);

    public static final SpellEffect NIGHT_VISION = SpellEffect.register("night_vision", StatusEffects.NIGHT_VISION);

    public static final SpellEffect LEVITATION = SpellEffect.register("", StatusEffects.LEVITATION);

    public static final SpellEffect LUCK = SpellEffect.register("luck", StatusEffects.LUCK);

    public static final SpellEffect SLOW_FALLING = SpellEffect.register("", StatusEffects.SLOW_FALLING);

    static {
        SUPPORT_EFFECTS.add(SPEED);
        SUPPORT_EFFECTS.add(HASTE);
        SUPPORT_EFFECTS.add(STRENGTH);
        SUPPORT_EFFECTS.add(LEAPING);
        SUPPORT_EFFECTS.add(RESISTANCE);
        SUPPORT_EFFECTS.add(FIRE_RESISTANCE);
        SUPPORT_EFFECTS.add(WATER_BREATHING);
        SUPPORT_EFFECTS.add(INVISIBILITY);
        SUPPORT_EFFECTS.add(NIGHT_VISION);
        SUPPORT_EFFECTS.add(LEVITATION);
        SUPPORT_EFFECTS.add(LUCK);
        SUPPORT_EFFECTS.add(SLOW_FALLING);


    }

}
