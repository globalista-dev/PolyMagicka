package com.globalista.polymagicka.gui;

import com.globalista.polymagicka.item.Wand;
import com.globalista.polymagicka.magic.spell.ConjurationSpells;
import com.globalista.polymagicka.magic.spell.DestructionSpells;
import com.globalista.polymagicka.util.Helper;
import eu.pb4.sgui.api.gui.SimpleGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import static com.globalista.polymagicka.util.Helper.*;

public class DestructionGUI {

    public static void buildGUI(PlayerEntity playerEntity, World world, Hand hand, Wand wand) {

        ServerPlayerEntity player = playerEntity.getCommandSource((ServerWorld) world).getPlayer();
        ItemStack stack = playerEntity.getStackInHand(hand);
        ItemStack offhand = playerEntity.getOffHandStack();

        SimpleGui gui = new SimpleGui(ScreenHandlerType.GENERIC_9X5, player, false);

        gui.setTitle(Helper.t("des.gui.title").formatted(Formatting.BOLD, Formatting.YELLOW));

        /*
            0  1  2  3  4  5  6  7  8
            9  10 11 12 13 14 15 16 17
            18 19 20 21 22 23 24 25 26
            27 28 29 30 31 32 33 34 35
            36 37 38 39 40 41 42 43 44
        */

        GUIElements.getCommon(36, gui, offhand, world, playerEntity, hand, wand, 44);

        GUIElements.build(10, gui, world, playerEntity, stack, wand,
                DestructionSpells.MAGIC_MISSILE,
                Formatting.RED,
                Items.FIREWORK_ROCKET,
                Helper.text(single));

        GUIElements.build(11, gui, world, playerEntity, stack, wand,
                DestructionSpells.LIFE_STEAL,
                Formatting.RED,
                Items.SPLASH_POTION,
                Helper.text(single));

        GUIElements.build(12, gui, world, playerEntity, stack, wand,
                DestructionSpells.FLAMES,
                Formatting.RED,
                Items.BLAZE_POWDER,
                Helper.text(single).append(" ").append(Helper.text(fire)));

        GUIElements.build(13, gui, world, playerEntity, stack, wand,
                DestructionSpells.FIREBOLT,
                Formatting.RED,
                Items.BLAZE_ROD,
                Helper.text(single).append(" ").append(Helper.text(fire)));

        GUIElements.build(14, gui, world, playerEntity, stack, wand,
                DestructionSpells.FIREBALL,
                Formatting.RED,
                Items.FIRE_CHARGE,
                Helper.text(aoe).append(" ").append(Helper.text(fire)));

        GUIElements.build(15, gui, world, playerEntity, stack, wand,
                DestructionSpells.METEOR,
                Formatting.RED,
                Items.CAMPFIRE,
                Helper.text(aoe).append(" ").append(Helper.text(fire)));

        GUIElements.build(16, gui, world, playerEntity, stack, wand,
                DestructionSpells.FIRE_BURST,
                Formatting.RED,
                Items.FLINT_AND_STEEL,
                Helper.text(aoe).append(" ").append(Helper.text(fire)));

        GUIElements.build(19, gui, world, playerEntity, stack, wand,
                DestructionSpells.SPARKS,
                Formatting.RED,
                Items.FLINT,
                Helper.text(single).append(" ").append(Helper.text(shock)));

        GUIElements.build(20, gui, world, playerEntity, stack, wand,
                DestructionSpells.THUNDERBOLT,
                Formatting.RED,
                Items.LIGHTNING_ROD,
                Helper.text(single).append(" ").append(Helper.text(shock)));

        GUIElements.build(21, gui, world, playerEntity, stack, wand,
                DestructionSpells.SHOCK_BURST,
                Formatting.RED,
                Items.COPPER_BULB,
                Helper.text(aoe).append(" ").append(Helper.text(shock)));

        GUIElements.build(22, gui, world, playerEntity, stack, wand,
                DestructionSpells.CHAIN_LIGHTNING,
                Formatting.RED,
                Items.CHAIN,
                Helper.text(aoe).append(" ").append(Helper.text(shock)));

        GUIElements.build(23, gui, world, playerEntity, stack, wand,
                DestructionSpells.TOUCH_OF_ENDER,
                Formatting.RED,
                Items.ENDER_EYE,
                Helper.text(single).append(" ").append(Helper.text(ancient)));

        GUIElements.build(24, gui, world, playerEntity, stack, wand,
                DestructionSpells.DRAGONBOLT,
                Formatting.RED,
                Items.FIREWORK_STAR,
                Helper.text(aoe).append(" ").append(Helper.text(ancient)));

        GUIElements.build(25, gui, world, playerEntity, stack, wand,
                DestructionSpells.ANCIENT_BURST,
                Formatting.RED,
                Items.DRAGON_BREATH,
                Helper.text(aoe).append(" ").append(Helper.text(ancient)));

        GUIElements.build(28, gui, world, playerEntity, stack, wand,
                DestructionSpells.GUST,
                Formatting.RED,
                Items.FEATHER,
                Helper.text(single).append(" ").append(Helper.text(wind)));

        GUIElements.build(29, gui, world, playerEntity, stack, wand,
                DestructionSpells.WIND_BARRAGE,
                Formatting.RED,
                Items.WIND_CHARGE,
                Helper.text(single).append(" ").append(Helper.text(wind)));

        GUIElements.build(30, gui, world, playerEntity, stack, wand,
                DestructionSpells.WIND_BURST,
                Formatting.RED,
                Items.BREEZE_ROD,
                Helper.text(single).append(" ").append(Helper.text(wind)));

        GUIElements.build(31, gui, world, playerEntity, stack, wand,
                DestructionSpells.FROSTBITE,
                Formatting.RED,
                Items.SNOWBALL,
                Helper.text(single).append(" ").append(Helper.text(water)));

        GUIElements.build(32, gui, world, playerEntity, stack, wand,
                DestructionSpells.FREEZE,
                Formatting.RED,
                Items.POWDER_SNOW_BUCKET,
                Helper.text(single).append(" ").append(Helper.text(water)));

        GUIElements.build(33, gui, world, playerEntity, stack, wand,
                DestructionSpells.ICE_BURST,
                Formatting.RED,
                Items.ICE,
                Helper.text(single).append(" ").append(Helper.text(water)));

        GUIElements.build(34, gui, world, playerEntity, stack, wand,
                DestructionSpells.BUBBLE,
                Formatting.RED,
                Items.WATER_BUCKET,
                Helper.text(single).append(" ").append(Helper.text(water)));

        gui.open();


    }


}
