package com.globalista.polymagicka.gui;

import com.globalista.polymagicka.item.Wand;
import com.globalista.polymagicka.magic.spell.Spell;
import com.globalista.polymagicka.util.Helper;
import com.globalista.polymagicka.util.ModRegistry;
import eu.pb4.sgui.api.elements.GuiElementBuilder;
import eu.pb4.sgui.api.gui.SimpleGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GUIElements {

    public static void getCommon(int infoPosition, SimpleGui gui, ItemStack offhand, World world, PlayerEntity playerEntity, Hand hand, Wand wand, int size){

        for (int i = 1; i <= size; i++){
            gui.setSlot(i, new GuiElementBuilder().setItem(Items.WHITE_STAINED_GLASS_PANE).setCount(1).hideDefaultTooltip().hideTooltip().setName(Text.empty()));
        }

        if(offhand.isIn(ModRegistry.GRIMOIRES)) {
            gui.setSlot(0, new GuiElementBuilder()
                    .setItem(Items.PLAYER_HEAD).setSkullOwner("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2RjOWU0ZGNmYTQyMjFhMWZhZGMxYjViMmIxMWQ4YmVlYjU3ODc5YWYxYzQyMzYyMTQyYmFlMWVkZDUifX19")
                    .setName(Helper.t("gui.back").formatted(Formatting.BOLD, Formatting.WHITE))
                    .addLoreLine(Text.literal(""))
                    .addLoreLine(Helper.t("gui.back.desc.1"))
                    .addLoreLine(Helper.t("gui.back.desc.2"))
                    .hideDefaultTooltip()
                    .setCount(1)
                    .setCallback((index, clickType, actionType) -> {
                        world.playSound(null, playerEntity.getBlockPos(), SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.PLAYERS, 1f, 1f);
                        gui.close();
                        GrimoireGUI.buildGui(playerEntity, world, hand, wand);
                    })
            );
        } else {
            gui.setSlot(0, new GuiElementBuilder().setItem(Items.WHITE_STAINED_GLASS_PANE).setCount(1).hideDefaultTooltip().hideTooltip().setName(Text.empty()));
        }

        gui.setSlot(infoPosition, new GuiElementBuilder()
                .setItem(Items.PLAYER_HEAD).setSkullOwner("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWM5OWRmYjI3MDRlMWJkNmU3ZmFjZmI0M2IzZTZmYmFiYWYxNmViYzdlMWZhYjA3NDE3YTZjNDY0ZTFkIn19fQ==")
                .setName(Helper.t("gui.info").formatted(Formatting.BOLD, Formatting.WHITE))
                .addLoreLine(Text.literal(""))
                .addLoreLine(Helper.t("gui.info.desc.1").formatted(Formatting.RED))
                .addLoreLine(Helper.t("gui.info.desc.2").formatted(Formatting.GREEN))
                .addLoreLine(Helper.t("gui.info.desc.3").formatted(Formatting.LIGHT_PURPLE))
                .addLoreLine(Helper.t("gui.info.desc.4").formatted(Formatting.WHITE))
                .addLoreLine(Text.literal(""))
                .addLoreLine(Helper.t("gui.info.desc.5").formatted(Formatting.WHITE))
                .addLoreLine(Helper.t("gui.info.self").formatted(Formatting.DARK_AQUA).append(Helper.t("gui.info.self.desc").formatted(Formatting.WHITE)))
                .addLoreLine(Helper.t("gui.info.single").formatted(Formatting.GOLD).append(Helper.t("gui.info.single.desc").formatted(Formatting.WHITE)))
                .addLoreLine(Helper.t("gui.info.aoe").formatted(Formatting.GOLD).append(Helper.t("gui.info.aoe.desc").formatted(Formatting.WHITE)))
                .addLoreLine(Helper.t("gui.info.fire").formatted(Formatting.DARK_RED).append(Helper.t("gui.info.fire.desc").formatted(Formatting.WHITE)))
                .addLoreLine(Helper.t("gui.info.water").formatted(Formatting.AQUA).append(Helper.t("gui.info.water.desc").formatted(Formatting.WHITE)))
                .addLoreLine(Helper.t("gui.info.wind").formatted(Formatting.GRAY).append(Helper.t("gui.info.wind.desc").formatted(Formatting.WHITE)))
                .addLoreLine(Helper.t("gui.info.shock").formatted(Formatting.YELLOW).append(Helper.t("gui.info.shock.desc").formatted(Formatting.WHITE)))
                .addLoreLine(Helper.t("gui.info.ancient").formatted(Formatting.DARK_PURPLE).append(Helper.t("gui.info.ancient.desc").formatted(Formatting.WHITE)))
                .hideDefaultTooltip()
                .setCount(1)
        );


    }

    public static void build(int position, SimpleGui gui, World world, PlayerEntity playerEntity, ItemStack stack, Wand wand, Spell spell, Formatting color, Item guiItem, int count, String skullOwner, boolean glow, Text type) {

        if(skullOwner != null){
            gui.setSlot(position, new GuiElementBuilder()
                    .setItem(guiItem).setSkullOwner(skullOwner)
                    .setName(Helper.t(spell.getName() + ".title").formatted(Formatting.BOLD, color))
                    .addLoreLine(type)
                    .addLoreLine(Text.literal(""))
                    .addLoreLine(Helper.t(spell.getName() + ".desc.1").formatted(Formatting.GRAY))
                    .addLoreLine(Helper.t(spell.getName() + ".desc.2").formatted(Formatting.GRAY))
                    .addLoreLine(Helper.t("cooldown").append(Text.literal(" " + spell.getCooldown() / 20 + " ")).formatted(Formatting.DARK_GRAY))
                    .hideDefaultTooltip()
                    .setCount(count)
                    .setCallback((index, clickType, actionType) -> {
                        world.playSound(null, playerEntity.getBlockPos(), SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.PLAYERS, 1f, 1f);
                        wand.setCurrentSpell(stack, spell);
                        gui.close();
                    })
            );
            return;
        }

        if (glow) {
            gui.setSlot(position, new GuiElementBuilder()
                    .setItem(guiItem).glow()
                    .setName(Helper.t(spell.getName() + ".title").formatted(Formatting.BOLD, color))
                    .addLoreLine(type)
                    .addLoreLine(Text.literal(""))
                    .addLoreLine(Helper.t(spell.getName() + ".desc.1").formatted(Formatting.GRAY))
                    .addLoreLine(Helper.t(spell.getName() + ".desc.2").formatted(Formatting.GRAY))
                    .addLoreLine(Helper.t("cooldown").append(Text.literal(" " + spell.getCooldown() / 20 + " ")).formatted(Formatting.DARK_GRAY))
                    .hideDefaultTooltip()
                    .setCount(count)
                    .setCallback((index, clickType, actionType) -> {
                        world.playSound(null, playerEntity.getBlockPos(), SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.PLAYERS, 1f, 1f);
                        wand.setCurrentSpell(stack, spell);
                        gui.close();
                    })
            );
        } else {
            gui.setSlot(position, new GuiElementBuilder()
                    .setItem(guiItem)
                    .setName(Helper.t(spell.getName() + ".title").formatted(Formatting.BOLD, color))
                    .addLoreLine(type)
                    .addLoreLine(Text.literal(""))
                    .addLoreLine(Helper.t(spell.getName() + ".desc.1").formatted(Formatting.GRAY))
                    .addLoreLine(Helper.t(spell.getName() + ".desc.2").formatted(Formatting.GRAY))
                    .addLoreLine(Helper.t("cooldown").append(Text.literal(" " + spell.getCooldown() / 20 + " ")).formatted(Formatting.DARK_GRAY))
                    .hideDefaultTooltip()
                    .setCount(count)
                    .setCallback((index, clickType, actionType) -> {
                        world.playSound(null, playerEntity.getBlockPos(), SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.PLAYERS, 1f, 1f);
                        wand.setCurrentSpell(stack, spell);
                        gui.close();
                    })
            );
        }
    }

    public static void build(int position, SimpleGui gui, World world, PlayerEntity playerEntity, ItemStack stack, Wand wand, Spell spell, Formatting color, Item guiItem, int count, boolean glow, Text type){
        build(position, gui, world, playerEntity, stack, wand, spell, color, guiItem, count, null, glow, type);
    }

    public static void build(int position, SimpleGui gui, World world, PlayerEntity playerEntity, ItemStack stack, Wand wand, Spell spell, Formatting color, Item guiItem, int count, Text type){
        build(position, gui, world, playerEntity, stack, wand, spell, color, guiItem, count, null, false, type);
    }

    public static void build(int position, SimpleGui gui, World world, PlayerEntity playerEntity, ItemStack stack, Wand wand, Spell spell, Formatting color, Item guiItem, Text type){
        build(position, gui, world, playerEntity, stack, wand, spell, color, guiItem, 1, null, false, type);
    }

    public static void build(int position, SimpleGui gui, World world, PlayerEntity playerEntity, ItemStack stack, Wand wand, Spell spell, Formatting color, Item guiItem, boolean glow, Text type){
        build(position, gui, world, playerEntity, stack, wand, spell, color, guiItem, 1, null, glow, type);
    }

    public static void build(int position, SimpleGui gui, World world, PlayerEntity playerEntity, ItemStack stack, Wand wand, Spell spell, Formatting color, Item guiItem, String skullOwner, Text type){
        build(position, gui, world, playerEntity, stack, wand, spell, color, guiItem, 1, skullOwner, false, type);
    }



}
