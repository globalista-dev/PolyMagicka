package com.globalista.polymagicka.spell.effects;

import com.globalista.polymagicka.spell.components.Element;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.ArrayList;
import java.util.List;

public class SpellEffect {

    private String name;
    private StatusEffectInstance effectInstance;

    public static List<SpellEffect> ALL_EFFECTS = new ArrayList<>();

    public SpellEffect(String name, StatusEffectInstance effectInstance) {
        this.name = name;
        this.effectInstance = effectInstance;
    }

    public static SpellEffect register(String name, RegistryEntry<StatusEffect> effect) {
        SpellEffect spellEffect = new SpellEffect(name, new StatusEffectInstance(effect, 1, 1));
        ALL_EFFECTS.add(spellEffect);
        return spellEffect;
    }

    public String getName() {
        return name;
    }

    public StatusEffectInstance getEffectInstance() {
        return effectInstance;
    }
}
