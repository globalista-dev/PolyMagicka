package com.globalista.polymagicka.gui;

import com.globalista.polymagicka.item.Wand;
import com.globalista.polymagicka.magic.spell.AlterationSpells;
import com.globalista.polymagicka.util.Helper;
import eu.pb4.sgui.api.elements.GuiElementBuilder;
import eu.pb4.sgui.api.gui.SimpleGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import static com.globalista.polymagicka.util.Helper.*;

public class AlterationGUI {

    public static void buildGui(PlayerEntity playerEntity, World world, Hand hand, Wand wand) {

        ServerPlayerEntity player = playerEntity.getCommandSource((ServerWorld) world).getPlayer();
        ItemStack stack = playerEntity.getStackInHand(hand);
        ItemStack offhand = playerEntity.getOffHandStack();

        SimpleGui gui = new SimpleGui(ScreenHandlerType.GENERIC_9X4, player, false);

        gui.setTitle(Helper.t("alt.gui.title").formatted(Formatting.BOLD, Formatting.GREEN));

        /*
            0  1  2  3  4  5  6  7  8
            9  10 11 12 13 14 15 16 17
            18 19 20 21 22 23 24 25 26
            27 28 29 30 31 32 33 34 35
        */

        GUIElements.getCommon(27, gui, offhand, world, playerEntity, hand, wand, 35);

        GUIElements.build(10, gui, world, playerEntity, stack, wand,
                AlterationSpells.OAKFLESH,
                Formatting.GREEN,
                Items.OAK_PLANKS,
                Helper.text(self));

        GUIElements.build(11, gui, world, playerEntity, stack, wand,
                AlterationSpells.STONEFLESH,
                Formatting.GREEN,
                Items.STONE,
                Helper.text(self));

        GUIElements.build(12, gui, world, playerEntity, stack, wand,
                AlterationSpells.IRONFLESH,
                Formatting.GREEN,
                Items.IRON_BLOCK,
                Helper.text(self));

        GUIElements.build(13, gui, world, playerEntity, stack, wand,
                AlterationSpells.DIAMONDFLESH,
                Formatting.GREEN,
                Items.DIAMOND_BLOCK,
                Helper.text(self));

        GUIElements.build(14, gui, world, playerEntity, stack, wand,
                AlterationSpells.LESSER_HASTE,
                Formatting.GREEN,
                Items.GOLDEN_PICKAXE,
                Helper.text(self).append(" ").append(Helper.text(wind)));

        GUIElements.build(15, gui, world, playerEntity, stack, wand,
                AlterationSpells.GREATER_HASTE,
                Formatting.GREEN,
                Items.NETHERITE_PICKAXE,
                Helper.text(self).append(" ").append(Helper.text(wind)));

        GUIElements.build(16, gui, world, playerEntity, stack, wand,
                AlterationSpells.WATERBREATHING,
                Formatting.GREEN,
                Items.HEART_OF_THE_SEA,
                Helper.text(self).append(" ").append(Helper.text(water)));

        GUIElements.build(19, gui, world, playerEntity, stack, wand,
                AlterationSpells.PARALYSIS,
                Formatting.RED,
                Items.COBWEB,
                Helper.text(self).append(" ").append(Helper.text(shock)));

        GUIElements.build(20, gui, world, playerEntity, stack, wand,
                AlterationSpells.TRANSMUTE,
                Formatting.LIGHT_PURPLE,
                Items.GOLD_INGOT,
                Helper.text(self).append(" ").append(Helper.text(ancient)));

        GUIElements.build(21, gui, world, playerEntity, stack, wand,
                AlterationSpells.MAGELIGHT,
                Formatting.GREEN,
                Items.GLOWSTONE_DUST,
                Helper.text(self).append(" ").append(Helper.text(fire)));

        GUIElements.build(22, gui, world, playerEntity, stack, wand,
                AlterationSpells.LONGSTRIDE,
                Formatting.GREEN,
                Items.CHAINMAIL_BOOTS,
                Helper.text(self).append(" ").append(Helper.text(wind)));

        GUIElements.build(23, gui, world, playerEntity, stack, wand,
                AlterationSpells.DOLPHINS_GRACE,
                Formatting.GREEN,
                Items.PRISMARINE_SHARD,
                Helper.text(self).append(" ").append(Helper.text(water)));

        GUIElements.build(24, gui, world, playerEntity, stack, wand,
                AlterationSpells.WITHER,
                Formatting.RED,
                Items.WITHER_ROSE,
                Helper.text(single).append(" ").append(Helper.text(ancient)));

        GUIElements.build(25, gui, world, playerEntity, stack, wand,
                AlterationSpells.DEEP_STORAGE,
                Formatting.WHITE,
                Items.ENDER_CHEST,
                Helper.text(self));


        gui.open();
    }



}
