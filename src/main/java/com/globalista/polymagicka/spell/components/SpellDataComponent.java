package com.globalista.polymagicka.spell.components;

import com.globalista.polymagicka.spell.Spell;
import eu.pb4.polymer.core.api.other.PolymerComponent;
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

}
