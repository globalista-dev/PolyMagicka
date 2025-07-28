package com.globalista.polymagicka.magic.spell;

import net.minecraft.particle.ParticleTypes;

public class ConjurationSpells {

    // Line 1 = Conjuration
    public static Spell CONJURE_IRON_GOLEM = new Spell("conjure_iron_golem", "iron_golem", null, ParticleTypes.DRAGON_BREATH, 1200);

    public static Spell CONJURE_SNOW_GOLEM = new Spell("conjure_snow_golem", "snow_golem", null, ParticleTypes.DRAGON_BREATH, 1200);

    public static Spell CONJURE_LESSER_FAMILIAR = new Spell("conjure_lesser_familiar", "lesser_familiar", null, ParticleTypes.DRAGON_BREATH, 240);

    public static Spell CONJURE_GREATER_FAMILIAR = new Spell("conjure_lesser_familiar", "greater_familiar", null, ParticleTypes.DRAGON_BREATH, 480);

    public static Spell CONJURE_FLAME_ATRONACH = new Spell("conjure_flame_atronach", "flame_atronach", null, ParticleTypes.DRAGON_BREATH, 1200);

    public static Spell CONJURE_WIND_ATRONACH = new Spell("conjure_wind_atronach", "wind_atronach", null, ParticleTypes.DRAGON_BREATH, 1200);

    public static Spell CONJURE_BUNDLE_OF_ARROWS = new Spell("conjure_bundle_of_arrows", null, null, ParticleTypes.DRAGON_BREATH, 1200);


    // Line 2 = Raising + Damage
    public static Spell RAISE_ZOMBIE = new Spell("raise_zombie", "raised_zombie", null, ParticleTypes.DRAGON_BREATH, 240);

    public static Spell RAISE_SKELETON = new Spell("raise_skeleton", "raised_skeleton", null, ParticleTypes.DRAGON_BREATH, 240);

    public static Spell SUMMON_SPIDER_SWARM = new Spell("summon_spider_swarm", "spider_swarm", null, ParticleTypes.DRAGON_BREATH, 240);

    public static Spell SUMMON_LIVING_ARMOR = new Spell("summon_living_armor", "living_armor", null, ParticleTypes.DRAGON_BREATH, 240);

    public static Spell SUMMON_ANIMATED_STATUE = new Spell("summon_animated_statue", "living_armor", null, ParticleTypes.DRAGON_BREATH, 240);

    public static Spell SUMMON_SPECTRAL_ARROW = new Spell("summon_spectral_arrow", "spectral_arrow", null, ParticleTypes.DRAGON_BREATH, 240);

    public static Spell SUMMON_ARROW_BARRAGE = new Spell("summon_arrow_barrage", "arrow", null, ParticleTypes.DRAGON_BREATH, 240);


    // Line 3 = Bound
    public static Spell BOUND_SWORD = new Spell("bound_sword", null, null, ParticleTypes.DRAGON_BREATH, 1200);

    public static Spell BOUND_AXE = new Spell("bound_axe", null, null, ParticleTypes.DRAGON_BREATH, 1200);

    public static Spell BOUND_PICKAXE = new Spell("bound_pickaxe", null, null, ParticleTypes.DRAGON_BREATH, 1200);

    public static Spell BOUND_SHOVEL = new Spell("bound_shovel", null, null, ParticleTypes.DRAGON_BREATH, 1200);

    public static Spell BOUND_HOE = new Spell("bound_hoe", null, null, ParticleTypes.DRAGON_BREATH, 1200);

    public static Spell BOUND_BOW = new Spell("bound_bow", null, null, ParticleTypes.DRAGON_BREATH, 1200);

    public static Spell BOUND_CROSSBOW = new Spell("bound_crossbow", null, null, ParticleTypes.DRAGON_BREATH, 1200);



}
