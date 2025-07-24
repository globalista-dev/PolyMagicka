package com.globalista.polymagicka.spell;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;

public class DestructionSpells {

    public static Spell FIREBOLT = new Spell("firebolt", "small_fireball",
            null, ParticleTypes.FLAME, 120);

    public static Spell FIREBALL = new Spell("fireball", "fireball",
            null, ParticleTypes.FLAME, 240);

    public static Spell THUNDERBOLT = new Spell("thunderbolt", "lightning",
            null, ParticleTypes.FLASH, 240);

    public static Spell DRAGONBOLT = new Spell("dragonbolt", "dragon_fireball",
            null, ParticleTypes.DRAGON_BREATH, 240);




}
