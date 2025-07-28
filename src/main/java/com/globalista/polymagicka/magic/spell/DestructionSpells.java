package com.globalista.polymagicka.magic.spell;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;

import java.util.ArrayList;
import java.util.List;

public class DestructionSpells {

    // Line 1
    //      No element
    public static Spell MAGIC_MISSILE = new Spell("magic_missile", "regular",
            null, ParticleTypes.CRIT, 60);

    public static Spell LIFE_STEAL = new Spell("life_steal", "regular",
            List.of(new StatusEffectInstance(StatusEffects.WITHER, 101, 1)), ParticleTypes.HEART, 120);

    //      Fire
    public static Spell FLAMES = new Spell("flames", null,
            null, ParticleTypes.FLAME, 60);

    public static Spell FIREBOLT = new Spell("firebolt", "small_fireball",
            null, ParticleTypes.FLAME, 120);

    public static Spell FIREBALL = new Spell("fireball", "fireball",
            null, ParticleTypes.FLAME, 240);

    public static Spell METEOR = new Spell("meteor", "fireball",
            null, ParticleTypes.FLAME, 240);

    public static Spell FIRE_BURST = new Spell("fire_burst", null, null, ParticleTypes.FLAME, 480);

    // Line 2
    //      Lightning
    public static Spell SPARKS = new Spell("sparks", null,
            null, ParticleTypes.ELECTRIC_SPARK, 60);

    public static Spell THUNDERBOLT = new Spell("thunderbolt", "lightning",
            null, ParticleTypes.ELECTRIC_SPARK, 240);

    public static Spell SHOCK_BURST = new Spell("shock_burst", null,
            null, ParticleTypes.ELECTRIC_SPARK, 480);

    public static Spell CHAIN_LIGHTNING = new Spell("chain_lightning", null,
            null, ParticleTypes.ELECTRIC_SPARK, 120);

    //      Ancient Magic
    public static Spell TOUCH_OF_ENDER = new Spell("touch_of_ender", null, null, ParticleTypes.DRAGON_BREATH, 60);

    public static Spell DRAGONBOLT = new Spell("dragonbolt", "dragon_fireball",
            null, ParticleTypes.DRAGON_BREATH, 240);

    public static Spell ANCIENT_BURST = new Spell("ancient_burst", null, null, ParticleTypes.DRAGON_BREATH, 480);

    // Line 3
    //      Wind
    public static Spell GUST = new Spell("gust", null,
            null, ParticleTypes.CLOUD, 120);

    public static Spell WIND_BARRAGE = new Spell("wind_barrage", "wind_charge",
            null, ParticleTypes.CLOUD, 480);

    public static Spell WIND_BURST = new Spell("wind_burst", null, null, ParticleTypes.CLOUD, 480);

    //      Water/Ice
    public static Spell FROSTBITE = new Spell("frostbite", null,
            null, ParticleTypes.SNOWFLAKE, 60);

    public static Spell FREEZE = new Spell("freeze", "freezing_snowball",
            null, ParticleTypes.SNOWFLAKE, 480);

    public static Spell ICE_BURST = new Spell("ice_burst", null, null, ParticleTypes.SNOWFLAKE, 480);

    public static Spell BUBBLE = new Spell("bubble", null, null, ParticleTypes.UNDERWATER, 240);

    public static List<Spell> LINE_SPELLS = new ArrayList<>();

    static {
        LINE_SPELLS.add(FLAMES);
        LINE_SPELLS.add(SPARKS);
        LINE_SPELLS.add(TOUCH_OF_ENDER);
        LINE_SPELLS.add(GUST);
        LINE_SPELLS.add(FROSTBITE);
        LINE_SPELLS.add(BUBBLE);
    }








}
