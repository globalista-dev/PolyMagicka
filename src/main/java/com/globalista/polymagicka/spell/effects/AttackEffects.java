package com.globalista.polymagicka.spell.effects;

import com.nimbusds.openid.connect.sdk.assurance.Status;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.ArrayList;
import java.util.List;

public class AttackEffects {

    public static List<SpellEffect> ATTACK_EFFECTS = new ArrayList<>();

    public static final SpellEffect SLOWNESS  = SpellEffect.register("slowness", StatusEffects.SLOWNESS);

    public static final SpellEffect FATIGUE  = SpellEffect.register("fatigue", StatusEffects.MINING_FATIGUE);

    public static final SpellEffect NAUSEA  = SpellEffect.register("nausea", StatusEffects.NAUSEA);

    public static final SpellEffect BLINDNESS = SpellEffect.register("blindness", StatusEffects.BLINDNESS);

    public static final SpellEffect HUNGER = SpellEffect.register("hunger", StatusEffects.HUNGER);

    public static final SpellEffect WEAKNESS = SpellEffect.register("weakness", StatusEffects.WEAKNESS);

    public static final SpellEffect POISON = SpellEffect.register("poison", StatusEffects.POISON);

    public static final SpellEffect WITHER = SpellEffect.register("wither", StatusEffects.WITHER);

    public static final SpellEffect DARKNESS = SpellEffect.register("darkness", StatusEffects.DARKNESS);

    public static final SpellEffect DAMAGE = SpellEffect.register("damage", StatusEffects.INSTANT_DAMAGE);

    public static final SpellEffect UNLUCK = SpellEffect.register("unluck", StatusEffects.UNLUCK);

    static {
        ATTACK_EFFECTS.add(SLOWNESS);
        ATTACK_EFFECTS.add(FATIGUE);
        ATTACK_EFFECTS.add(NAUSEA);
        ATTACK_EFFECTS.add(BLINDNESS);
        ATTACK_EFFECTS.add(HUNGER);
        ATTACK_EFFECTS.add(WEAKNESS);
        ATTACK_EFFECTS.add(POISON);
        ATTACK_EFFECTS.add(WITHER);
        ATTACK_EFFECTS.add(DARKNESS);
        ATTACK_EFFECTS.add(DAMAGE);
        ATTACK_EFFECTS.add(UNLUCK);
    }

}
