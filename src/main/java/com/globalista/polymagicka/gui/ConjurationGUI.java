package com.globalista.polymagicka.gui;

import com.globalista.polymagicka.item.BoundItems;
import com.globalista.polymagicka.item.Wand;
import com.globalista.polymagicka.magic.spell.ConjurationSpells;
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

public class ConjurationGUI {

    public static void buildGui(PlayerEntity playerEntity, World world, Hand hand, Wand wand) {

        ServerPlayerEntity player = playerEntity.getCommandSource((ServerWorld) world).getPlayer();
        ItemStack stack = playerEntity.getStackInHand(hand);
        ItemStack offhand = playerEntity.getOffHandStack();

        SimpleGui gui = new SimpleGui(ScreenHandlerType.GENERIC_9X5, player, false);

        gui.setTitle(Helper.t("con.gui.title").formatted(Formatting.BOLD, Formatting.AQUA));

        /*
            0  1  2  3  4  5  6  7  8
            9  10 11 12 13 14 15 16 17
            18 19 20 21 22 23 24 25 26
            27 28 29 30 31 32 33 34 35
            36 37 38 39 40 41 42 43 44
        */

        GUIElements.getCommon(36, gui, offhand, world, playerEntity, hand, wand, 44);

        GUIElements.build(10, gui, world, playerEntity, stack, wand,
                ConjurationSpells.CONJURE_IRON_GOLEM,
                Formatting.LIGHT_PURPLE,
                Items.IRON_GOLEM_SPAWN_EGG,
                Helper.text(single));

        GUIElements.build(11, gui, world, playerEntity, stack, wand,
                ConjurationSpells.CONJURE_SNOW_GOLEM,
                Formatting.LIGHT_PURPLE,
                Items.SNOW_GOLEM_SPAWN_EGG,
                Helper.text(single).append(" ").append(Helper.text(water)));

        GUIElements.build(12, gui, world, playerEntity, stack, wand,
                ConjurationSpells.CONJURE_LESSER_FAMILIAR,
                Formatting.LIGHT_PURPLE,
                Items.WOLF_SPAWN_EGG,
                Helper.text(single));

        GUIElements.build(13, gui, world, playerEntity, stack, wand,
                ConjurationSpells.CONJURE_GREATER_FAMILIAR,
                Formatting.LIGHT_PURPLE,
                Items.POLAR_BEAR_SPAWN_EGG,
                Helper.text(single).append(Helper.text(water)));

        GUIElements.build(14, gui, world, playerEntity, stack, wand,
                ConjurationSpells.CONJURE_FLAME_ATRONACH,
                Formatting.LIGHT_PURPLE,
                Items.BLAZE_SPAWN_EGG,
                Helper.text(single).append(" ").append(Helper.text(fire)));

        GUIElements.build(15, gui, world, playerEntity, stack, wand,
                ConjurationSpells.CONJURE_WIND_ATRONACH,
                Formatting.LIGHT_PURPLE,
                Items.BREEZE_SPAWN_EGG,
                Helper.text(single).append(" ").append(Helper.text(wind)));

        GUIElements.build(16, gui, world, playerEntity, stack, wand,
                ConjurationSpells.CONJURE_BUNDLE_OF_ARROWS,
                Formatting.LIGHT_PURPLE,
                Items.ARROW,
                16,
                Helper.text(self));

        GUIElements.build(19, gui, world, playerEntity, stack, wand,
                ConjurationSpells.RAISE_ZOMBIE,
                Formatting.LIGHT_PURPLE,
                Items.ZOMBIE_SPAWN_EGG,
                Helper.text(single));

        GUIElements.build(20, gui, world, playerEntity, stack, wand,
                ConjurationSpells.RAISE_SKELETON,
                Formatting.LIGHT_PURPLE,
                Items.SKELETON_SPAWN_EGG,
                Helper.text(single));

        GUIElements.build(21, gui, world, playerEntity, stack, wand,
                ConjurationSpells.SUMMON_SPIDER_SWARM,
                Formatting.LIGHT_PURPLE,
                Items.SPIDER_SPAWN_EGG,
                Helper.text(aoe));

        GUIElements.build(22, gui, world, playerEntity, stack, wand,
                ConjurationSpells.SUMMON_LIVING_ARMOR,
                Formatting.LIGHT_PURPLE,
                Items.PLAYER_HEAD,
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTVlYjBiZDg1YWFkZGYwZDI5ZWQwODJlYWMwM2ZjYWRlNDNkMGVlODAzYjBlODE2MmFkZDI4YTYzNzlmYjU0ZSJ9fX0=",
                Helper.text(single).append(" ").append(Helper.text(ancient)));

        GUIElements.build(23, gui, world, playerEntity, stack, wand,
                ConjurationSpells.SUMMON_ANIMATED_STATUE,
                Formatting.LIGHT_PURPLE,
                Items.PLAYER_HEAD,
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWRkOWEzNTFhNjMzNjZjODRjNjQzOGRkYTUzNmQzZDliYmI4MWUwMTY0MGIzZDAwZTRkZDg4OWRmYjg2OGQ4MyJ9fX0=",
                Helper.text(single).append(" ").append(Helper.text(ancient)));

        GUIElements.build(24, gui, world, playerEntity, stack, wand,
                ConjurationSpells.SUMMON_SPECTRAL_ARROW,
                Formatting.RED,
                Items.SPECTRAL_ARROW,
                Helper.text(single).append(" ").append(Helper.text(fire)));

        GUIElements.build(25, gui, world, playerEntity, stack, wand,
                ConjurationSpells.SUMMON_ARROW_BARRAGE,
                Formatting.RED,
                Items.ARROW,
                true,
                Helper.text(aoe).append(" ").append(Helper.text(wind)));

        GUIElements.build(28, gui, world, playerEntity, stack, wand,
                ConjurationSpells.BOUND_SWORD,
                Formatting.LIGHT_PURPLE,
                BoundItems.BOUND_SWORD,
                true,
                Helper.text(self).append(" ").append(Helper.text(ancient)));

        GUIElements.build(29, gui, world, playerEntity, stack, wand,
                ConjurationSpells.BOUND_PICKAXE,
                Formatting.LIGHT_PURPLE,
                BoundItems.BOUND_PICKAXE,
                true,
                Helper.text(self).append(" ").append(Helper.text(ancient)));

        GUIElements.build(30, gui, world, playerEntity, stack, wand,
                ConjurationSpells.BOUND_AXE,
                Formatting.LIGHT_PURPLE,
                BoundItems.BOUND_AXE,
                true,
                Helper.text(self).append(" ").append(Helper.text(ancient)));

        GUIElements.build(31, gui, world, playerEntity, stack, wand,
                ConjurationSpells.BOUND_SHOVEL,
                Formatting.LIGHT_PURPLE,
                BoundItems.BOUND_SHOVEL,
                true,
                Helper.text(self).append(" ").append(Helper.text( ancient)));

        GUIElements.build(32, gui, world, playerEntity, stack, wand,
                ConjurationSpells.BOUND_HOE,
                Formatting.LIGHT_PURPLE,
                BoundItems.BOUND_HOE,
                true,
                Helper.text(self).append(" ").append(Helper.text(ancient)));

        GUIElements.build(33, gui, world, playerEntity, stack, wand,
                ConjurationSpells.BOUND_BOW,
                Formatting.LIGHT_PURPLE,
                BoundItems.BOUND_BOW,
                true,
                Helper.text(self).append(" ").append(Helper.text(ancient)));

        GUIElements.build(34, gui, world, playerEntity, stack, wand,
                ConjurationSpells.BOUND_CROSSBOW,
                Formatting.LIGHT_PURPLE,
                BoundItems.BOUND_CROSSBOW,
                true,
                Helper.text(self).append(" ").append(Helper.text(ancient)));

        gui.open();

    }

}
