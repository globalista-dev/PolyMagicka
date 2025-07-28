package com.globalista.polymagicka.magic.components;

import com.globalista.polymagicka.magic.spell.Spell;
import com.globalista.polymagicka.util.Helper;
import com.mojang.serialization.Codec;
import eu.pb4.polymer.core.api.other.PolymerComponent;
import net.minecraft.component.Component;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SpellDataComponent {

    public static final ComponentType<Spell> SPELL = register("selected_spell",
            ComponentType.<Spell>builder().codec(Spell.CODEC).build());

    public static void initialize() {
    }

    private static <T> ComponentType<T> register(String path, ComponentType<T> block) {
        PolymerComponent.registerDataComponent(block);
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(path), block);
    }

    public static final ComponentType<Long> CREATION_TICK = register("creation_tick",
            ComponentType.<Long>builder().codec(Codec.LONG).build());
}
