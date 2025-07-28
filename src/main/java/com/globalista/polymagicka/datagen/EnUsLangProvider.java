package com.globalista.polymagicka.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static com.globalista.polymagicka.util.Helper.*;

public class EnUsLangProvider extends FabricLanguageProvider {

    protected EnUsLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    private String a = "polymagicka.";
    private String i = "item.polymagicka.";

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder t) {

        // Spell Selection GUI
        t.add(a + "sel.gui.title", "Grimoire");
        t.add(a + "alt.title", "ALTERATION");
        t.add(a + "alt.desc.1", "Alteration is the art of bending");
        t.add(a + "alt.desc.2", "reality to one's own accord.");

        t.add(a + "con.title", "CONJURATION");
        t.add(a + "con.desc.1", "Conjuration is the school that governs");
        t.add(a + "con.desc.2", "the summoning of creatures out of nothing.");

        t.add(a + "des.title", "DESTRUCTION");
        t.add(a + "des.desc.1", "Destruction involves harnessing");
        t.add(a + "des.desc.2", "nature to inflict untold damage.");

        t.add(a + "ill.title", "ILLUSION");
        t.add(a + "ill.desc.1", "Illusion is the art of tricking");
        t.add(a + "ill.desc.2", "the enemy's mind and perception.");

        t.add(a + "res.title", "RESTORATION");
        t.add(a + "res.desc.1", "Restoration controls life to");
        t.add(a + "res.desc.2", "cure one's physical ailments.");

        t.add(a + "gui.back", "BACK");
        t.add(a + "gui.back.desc.1", "Go back to the Grimoire's");
        t.add(a + "gui.back.desc.2", "School of Magic selection.");

        t.add(a + "gui.info", "INFORMATION");
        t.add(a + "gui.info.desc.1", "Red spells attack the target(s)");
        t.add(a + "gui.info.desc.2", "Green spells heal the caster or the target(s)");
        t.add(a + "gui.info.desc.3", "Lavender spells summon entities or items");
        t.add(a + "gui.info.desc.4", "White spells don't fall into these categories");
        t.add(a + "gui.info.desc.5", "Some spells might have other markers:");
        t.add(a + "gui.info.self", self);
        t.add(a + "gui.info.self.desc", " - Spells casted on self");
        t.add(a + "gui.info.single", single);
        t.add(a + "gui.info.single.desc", " - Single target spells");
        t.add(a + "gui.info.aoe", aoe);
        t.add(a + "gui.info.aoe.desc", " - Area of effect spells");
        t.add(a + "gui.info.fire", fire);
        t.add(a + "gui.info.fire.desc", " - Fire spells");
        t.add(a + "gui.info.water", water);
        t.add(a + "gui.info.water.desc", " - Water spells");
        t.add(a + "gui.info.wind", wind);
        t.add(a + "gui.info.wind.desc", " - Air spells");
        t.add(a + "gui.info.shock", shock);
        t.add(a + "gui.info.shock.desc", " - Shock spells");
        t.add(a + "gui.info.ancient", ancient);
        t.add(a + "gui.info.ancient.desc", " - Ancient Magic spells");
        t.add(a + "cooldown", "Cooldown (seconds):");


        // Tome of Alteration GUI
        t.add(a + "alt.gui.title", "TOME OF ALTERATION");

        t.add(a + "oakflesh.title", "Oakflesh");
        t.add(a + "oakflesh.desc.1", "Grants the caster");
        t.add(a + "oakflesh.desc.2", "Resistance I (20s)");

        t.add(a + "stoneflesh.title", "Stoneflesh");
        t.add(a + "stoneflesh.desc.1", "Grants the caster");
        t.add(a + "stoneflesh.desc.2", "Resistance II (20s)");

        t.add(a + "ironflesh.title", "Ironflesh");
        t.add(a + "ironflesh.desc.1", "Grants the caster");
        t.add(a + "ironflesh.desc.2", "Resistance III (20s)");

        t.add(a + "diamondflesh.title", "Diamondflesh");
        t.add(a + "diamondflesh.desc.1", "Grants the caster Resistance III");
        t.add(a + "diamondflesh.desc.2", "(20s) and Fire Resistance I (20s)");

        t.add(a + "waterbreathing.title", "Waterbreathing");
        t.add(a + "waterbreathing.desc.1", "Grants the caster Water");
        t.add(a + "waterbreathing.desc.2", "Breathing II (20s)");

        t.add(a + "lesser_haste.title", "Lesser Haste");
        t.add(a + "lesser_haste.desc.1", "Grants the caster");
        t.add(a + "lesser_haste.desc.2", "Haste I (20s)");

        t.add(a + "greater_haste.title", "Greater Haste");
        t.add(a + "greater_haste.desc.1", "Grants the caster");
        t.add(a + "greater_haste.desc.2", "Haste II (20s)");

        t.add(a + "paralysis.title", "Paralysis");
        t.add(a + "paralysis.desc.1", "Paralyzes the target");
        t.add(a + "paralysis.desc.2", "for five seconds");

        t.add(a + "transmute.title", "Transmutation");
        t.add(a + "transmute.desc.1", "Transmutes one iron ingot");
        t.add(a + "transmute.desc.2", "into one gold ingot");

        t.add(a + "magelight.title", "Magelight");
        t.add(a + "magelight.desc.1", "Makes the caster glow");
        t.add(a + "magelight.desc.2", "for 10 seconds");

        t.add(a + "longstride.title", "Longstride");
        t.add(a + "longstride.desc.1", "Grants the caster");
        t.add(a + "longstride.desc.2", "Speed I (10s)");

        t.add(a + "dolphins_grace.title", "Dolphin's Grace");
        t.add(a + "dolphins_grace.desc.1", "Grants the caster");
        t.add(a + "dolphins_grace.desc.2", "Dolphin's Grace I (10s)");

        t.add(a + "wither.title", "Rot");
        t.add(a + "wither.desc.1", "Inflicts Wither I");
        t.add(a + "wither.desc.2", "(5s) on a target");

        t.add(a + "deep_storage.title", "Deep Storage");
        t.add(a + "deep_storage.desc.1", "Opens the caster's");
        t.add(a + "deep_storage.desc.2", "Ender Chest inventory");

        // Tome of Conjuration GUI
        t.add(a + "con.gui.title", "TOME OF CONJURATION");

        t.add(a + "conjure_iron_golem.title", "Conjure Iron Golem");
        t.add(a + "conjure_iron_golem.desc.1", "Conjures a permanent");
        t.add(a + "conjure_iron_golem.desc.2", "Iron Golem in the world");

        t.add(a + "conjure_snow_golem.title", "Conjure Snow Golem");
        t.add(a + "conjure_snow_golem.desc.1", "Conjures a permanent");
        t.add(a + "conjure_snow_golem.desc.2", "Snow Golem in the world");

        t.add(a + "conjure_lesser_familiar.title", "Conjure Lesser Familiar ");
        t.add(a + "conjure_lesser_familiar.desc.1", "Conjures a permanent");
        t.add(a + "conjure_lesser_familiar.desc.2", "Lesser Familiar in the world");

        t.add(a + "conjure_greater_familiar.title", "Conjure Greater Familiar");
        t.add(a + "conjure_greater_familiar.desc.1", "Conjures a permanent");
        t.add(a + "conjure_greater_familiar.desc.2", "Greater Familiar in the world");

        t.add(a + "conjure_flame_atronach.title", "Conjure Flame Atronach");
        t.add(a + "conjure_flame_atronach.desc.1", "Conjures a permanent");
        t.add(a + "conjure_flame_atronach.desc.2", "Flame Atronach in the world");

        t.add(a + "conjure_wind_atronach.title", "Conjure Wind Atronach");
        t.add(a + "conjure_wind_atronach.desc.1", "Conjures a permanent");
        t.add(a + "conjure_wind_atronach.desc.2", "Wind Atronach in the world");

        t.add(a + "conjure_bundle_of_arrows.title", "Conjure Bundle of Arrows");
        t.add(a + "conjure_bundle_of_arrows.desc.1", "Conjures sixteen arrows in");
        t.add(a + "conjure_bundle_of_arrows.desc.2", "the caster's inventory");

        t.add(a + "raise_zombie.title", "Raise Zombie");
        t.add(a + "raise_zombie.desc.1", "Raises a friendly Zombie");
        t.add(a + "raise_zombie.desc.2", "in front of the caster");

        t.add(a + "raise_skeleton.title", "Raise Skeleton");
        t.add(a + "raise_skeleton.desc.1", "Raises a friendly Skeleton");
        t.add(a + "raise_skeleton.desc.2", "in front of the caster");

        t.add(a + "summon_spider_swarm.title", "Summon Spider Swarm");
        t.add(a + "summon_spider_swarm.desc.1", "Summons eight Cave Spiders in");
        t.add(a + "summon_spider_swarm.desc.2", "a circle around the caster");

        t.add(a + "summon_living_armor.title", "Summon Living Armor");
        t.add(a + "summon_living_armor.desc.1", "Summons a Living Armor");
        t.add(a + "summon_living_armor.desc.2", "in front of the caster");

        t.add(a + "summon_animated_statue.title", "Animated Statue");
        t.add(a + "summon_animated_statue.desc.1", "Summons an Animated Statue");
        t.add(a + "summon_animated_statue.desc.2", "in front of the caster");

        t.add(a + "summon_spectral_arrow.title", "Summon Spectral Arrow");
        t.add(a + "summon_spectral_arrow.desc.1", "Summons a Spectral Arrow");
        t.add(a + "summon_spectral_arrow.desc.2", "and hurls it at a target");

        t.add(a + "summon_arrow_barrage.title", "Summon Arrow Barrage");
        t.add(a + "summon_arrow_barrage.desc.1", "Summons a barrage of arrows");
        t.add(a + "summon_arrow_barrage.desc.2", "and hurls them at a target");

        t.add(a + "bound_sword.title", "Bound Sword");
        t.add(a + "bound_sword.desc.1", "Creates an enchanted magic");
        t.add(a + "bound_sword.desc.2", "sword for 180 seconds");

        t.add(a + "bound_pickaxe.title", "Bound Pickaxe");
        t.add(a + "bound_pickaxe.desc.1", "Creates an enchanted magic");
        t.add(a + "bound_pickaxe.desc.2", "pickaxe for 180 seconds");

        t.add(a + "bound_axe.title", "Bound Axe");
        t.add(a + "bound_axe.desc.1", "Creates an enchanted magic");
        t.add(a + "bound_axe.desc.2", "axe for 180 seconds");

        t.add(a + "bound_shovel.title", "Bound Shovel");
        t.add(a + "bound_shovel.desc.1", "Creates an enchanted magic");
        t.add(a + "bound_shovel.desc.2", "shovel for 180 seconds");

        t.add(a + "bound_hoe.title", "Bound Hoe");
        t.add(a + "bound_hoe.desc.1", "Creates an enchanted magic");
        t.add(a + "bound_hoe.desc.2", "how for 180 seconds");

        t.add(a + "bound_bow.title", "Bound Bow");
        t.add(a + "bound_bow.desc.1", "Creates an enchanted magic");
        t.add(a + "bound_bow.desc.2", "bow for 180 seconds");

        t.add(a + "bound_crossbow.title", "Bound Crossbow");
        t.add(a + "bound_crossbow.desc.1", "Creates an enchanted magic");
        t.add(a + "bound_crossbow.desc.2", "crossbow for 180 seconds");

        t.add(a + "magic_missile.title", "Magic Missile");
        t.add(a + "magic_missile.desc.1", "Hurls a ball of magical");
        t.add(a + "magic_missile.desc.2", "force at a target");

        t.add(a + "life_steal.title", "Life Steal");
        t.add(a + "life_steal.desc.1", "On hit, saps the vital force of");
        t.add(a + "life_steal.desc.2", "the target for 5 seconds");

        t.add(a + "flames.title", "Flames");
        t.add(a + "flames.desc.1", "Burns a single target");
        t.add(a + "flames.desc.2", "up to 15 blocks away");

        t.add(a + "firebolt.title", "Firebolt");
        t.add(a + "firebolt.desc.1", "Hurls a firebolt towards");
        t.add(a + "firebolt.desc.2", "a single target");

        t.add(a + "fireball.title", "Fireball");
        t.add(a + "fireball.desc.1", "Hurls a fireball towards an area");
        t.add(a + "fireball.desc.2", "creating an explosion and fire");

        t.add(a + "meteor.title", "Meteor");
        t.add(a + "meteor.desc.1", "Creates a meteor that falls from");
        t.add(a + "meteor.desc.2", "the sky up to 10 blocks away");

        t.add(a + "fire_burst.title", "Fire Burst");
        t.add(a + "fire_burst.desc.1", "Creates a circle (3 block radius) up to 20 blocks");
        t.add(a + "fire_burst.desc.2", "away that deals fire damage to hostiles inside");

        t.add(a + "sparks.title", "Sparks");
        t.add(a + "sparks.desc.1", "Shocks a single target");
        t.add(a + "sparks.desc.2", "up to 15 blocks away");

        t.add(a + "thunderbolt.title", "Thunderbolt");
        t.add(a + "thunderbolt.desc.1", "Strikes a lightning 10 blocks");
        t.add(a + "thunderbolt.desc.2", "in front of the caster");

        t.add(a + "shock_burst.title", "Shock Burst");
        t.add(a + "shock_burst.desc.1", "Creates a circle (3 block radius) up to 20 blocks");
        t.add(a + "shock_burst.desc.2", "away that deals shock damage to hostiles inside");

        t.add(a + "chain_lightning.title", "Chain Lightning");
        t.add(a + "chain_lightning.desc.1", "Generates a Lightning that jumps to up");
        t.add(a + "chain_lightning.desc.2", "to 10 targets in a 20 block radius");

        t.add(a + "touch_of_ender.title", "Touch of Ender");
        t.add(a + "touch_of_ender.desc.1", "Uses Ancient Magic to strike a single");
        t.add(a + "touch_of_ender.desc.2", "target up to 15 blocks away");

        t.add(a + "dragonbolt.title", "Dragonbolt");
        t.add(a + "dragonbolt.desc.1", "Hurls a Dragonbolt to an area");
        t.add(a + "dragonbolt.desc.2", "in front of the caster");

        t.add(a + "ancient_burst.title", "Ancient Burst");
        t.add(a + "ancient_burst.desc.1", "Creates a circle (3 block radius) up to 20 blocks away");
        t.add(a + "ancient_burst.desc.2", "that deals ancient magic damage to hostiles inside");

        t.add(a + "gust.title", "Gust");
        t.add(a + "gust.desc.1", "Uses wind to strike a single target up to 15");
        t.add(a + "gust.desc.2", "blocks away and throws them up in the air");

        t.add(a + "wind_barrage.title", "Wind Barrage");
        t.add(a + "wind_barrage.desc.1", "Hurls a Wind Charge to an area");
        t.add(a + "wind_barrage.desc.2", "in front of the caster");

        t.add(a + "wind_burst.title", "Wind Burst");
        t.add(a + "wind_burst.desc.1", "Creates a circle (3 block radius) up to 20 blocks");
        t.add(a + "wind_burst.desc.2", "away that deals wind damage to hostiles inside");

        t.add(a + "frostbite.title", "Frostbite");
        t.add(a + "frostbite.desc.1", "Freezes a single target up to");
        t.add(a + "frostbite.desc.2", "15 blocks away for 2 seconds");

        t.add(a + "freeze.title", "Freeze");
        t.add(a + "freeze.desc.1", "Hurls a magic snowball that, on hit,");
        t.add(a + "freeze.desc.2", "freezes a target for 30 seconds");

        t.add(a + "ice_burst.title", "Ice Burst");
        t.add(a + "ice_burst.desc.1", "Creates a circle (3 block radius) up to 20 blocks");
        t.add(a + "ice_burst.desc.2", "away that freezes hostiles inside for 5 seconds");

        t.add(a + "bubble.title", "Bubble");
        t.add(a + "bubble.desc.1", "Uses water to strike a target up to 15");
        t.add(a + "bubble.desc.2", "blocks away and slows it for 2 seconds");

        // Tome of Destruction GUI
        t.add(a + "des.gui.title", "TOME OF DESTRUCTION");

        // Tome of Illusion GUI
        t.add(a + "ill.gui.title", "TOME OF ILLUSION");

        // Tome of Restoration GUI
        t.add(a + "res.gui.title", "TOME OF RESTORATION");

        // Bound Items
        t.add(i + "bound_sword", "Bound Sword");
        t.add(a + "bound.item.expired", "Your bound item has expired");




    }

}
