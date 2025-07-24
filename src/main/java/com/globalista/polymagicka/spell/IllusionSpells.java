package com.globalista.polymagicka.spell;

import com.globalista.polymagicka.spell.effects.AttackEffects;
import com.globalista.polymagicka.spell.effects.SupportEffects;
import net.minecraft.particle.ParticleTypes;

import java.util.List;

public class IllusionSpells {

    public static Spell TELEPORTING = new Spell("teleporting", "ender_pearl", null,
            ParticleTypes.ASH, 480);

    public static Spell INVISIBILITY = new Spell("invisibility", null,
            List.of(SupportEffects.INVISIBILITY), ParticleTypes.ASH, 480);

    public static Spell BLINDING = new Spell("blinding", "default",
            List.of(AttackEffects.BLINDNESS), ParticleTypes.ENCHANTED_HIT, 240);

    public static Spell DARKENING = new Spell("darkening", "default",
            List.of(AttackEffects.DARKNESS), ParticleTypes.ENCHANTED_HIT, 240);


}
