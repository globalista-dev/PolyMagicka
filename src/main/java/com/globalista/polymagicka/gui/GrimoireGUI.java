package com.globalista.polymagicka.gui;

import com.globalista.polymagicka.item.Wand;
import com.globalista.polymagicka.util.Helper;
import eu.pb4.sgui.api.elements.GuiElementBuilder;
import eu.pb4.sgui.api.gui.SimpleGui;
import net.minecraft.entity.player.PlayerEntity;
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

public class GrimoireGUI {

    public static void buildGui(PlayerEntity playerEntity, World world, Hand hand, Wand wand) {
        ServerPlayerEntity player = playerEntity.getCommandSource((ServerWorld) world).getPlayer();

        SimpleGui gui = new SimpleGui(ScreenHandlerType.GENERIC_9X3, player, false);

        /*
            0  1  2  3  4  5  6  7  8
            9  10 11 12 13 14 15 16 17
            18 19 20 21 22 23 24 25 26
         */

        gui.setTitle(Helper.t("sel.gui.title").formatted(Formatting.WHITE, Formatting.BOLD));

        gui.setSlot(11, new GuiElementBuilder()
                .setItem(Items.PLAYER_HEAD).setSkullOwner("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjBhNmZjOGFkYzQ3N2RiY2RiNDYxZDExZjQ0YmMxMDNjNmQwNGI2MDlkOTYyZWE0MTEyMTBmZTdhMDE1NGViNCJ9fX0=")
                .setName(Helper.t("alt.title").formatted(Formatting.BOLD, Formatting.GREEN))
                .addLoreLine(Text.literal(""))
                .addLoreLine(Helper.t("alt.desc.1"))
                .addLoreLine(Helper.t("alt.desc.2"))
                .hideDefaultTooltip()
                .setCount(1)
                .setCallback((index, clickType, actionType) -> {
                    world.playSound(null, playerEntity.getBlockPos(), SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.PLAYERS, 1f, 1f);
                    gui.close();
                    AlterationGUI.buildGui(playerEntity, world, hand, wand);
                })
        );

        gui.setSlot(12, new GuiElementBuilder()
                .setItem(Items.PLAYER_HEAD).setSkullOwner("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzNhOGU0MDJkYWQxYjdkYWQ5YWFlNmY0MDE1OTMyMTgzNDI5Y2U4N2JiYmVjZWQzMTE5MDI2ZjgyOTYzMzZjMiJ9fX0=")
                .setName(Helper.t("con.title").formatted(Formatting.BOLD, Formatting.DARK_PURPLE))
                .addLoreLine(Text.literal(""))
                .addLoreLine(Helper.t("con.desc.1"))
                .addLoreLine(Helper.t("con.desc.2"))
                .hideDefaultTooltip()
                .setCount(1)
                .setCallback((index, clickType, actionType) -> {
                    world.playSound(null, playerEntity.getBlockPos(), SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.PLAYERS, 1f, 1f);
                    gui.close();
                    ConjurationGUI.buildGui(playerEntity, world, hand, wand);
                })
        );

        gui.setSlot(13, new GuiElementBuilder()
                .setItem(Items.PLAYER_HEAD).setSkullOwner("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTkwY2JkNzJlNDFhOWJkNDExYmU5MjliNzNmZDI2OTIwNjM2OGIyODEwZDZjNjgxOTkxOGNiOGViNjYyMjRmNCJ9fX0=")
                .setName(Helper.t("des.title").formatted(Formatting.BOLD, Formatting.YELLOW))
                .addLoreLine(Text.literal(""))
                .addLoreLine(Helper.t("des.desc.1"))
                .addLoreLine(Helper.t("des.desc.2"))
                .hideDefaultTooltip()
                .setCount(1)
                .setCallback((index, clickType, actionType) -> {
                    world.playSound(null, playerEntity.getBlockPos(), SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.PLAYERS, 1f, 1f);
                    gui.close();
                    DestructionGUI.buildGUI(playerEntity, world, hand, wand);
                })
        );

        gui.setSlot(14, new GuiElementBuilder()
                .setItem(Items.PLAYER_HEAD).setSkullOwner("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTg4YjFjZDk1NzQ2NzJlOGUzMjYyZjIxMGMwZGRkYmMwODJlYTc1NjllOGU3MGYwYzA3YjRiZWU3NWUzMmY2MiJ9fX0=")
                .setName(Helper.t("ill.title").formatted(Formatting.BOLD, Formatting.DARK_AQUA))
                .addLoreLine(Text.literal(""))
                .addLoreLine(Helper.t("ill.desc.1"))
                .addLoreLine(Helper.t("ill.desc.2"))
                .hideDefaultTooltip()
                .setCount(1)
                .setCallback((index, clickType, actionType) -> {
                    world.playSound(null, playerEntity.getBlockPos(), SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.PLAYERS, 1f, 1f);
                    gui.close();
                    //buildIllGui(playerEntity, world, hand);
                })
        );

        gui.setSlot(15, new GuiElementBuilder()
                .setItem(Items.PLAYER_HEAD).setSkullOwner("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTE5MTcyMmJiMTNiMWYxZmUzYmJlOGY0OTQ4NTU3YTE0N2JmOWU0Zjc3ODZlZjFkOTM4MmUyZmU3ZTIyYjdiMyJ9fX0=")
                .setName(Helper.t("res.title").formatted(Formatting.BOLD, Formatting.RED))
                .addLoreLine(Text.literal(""))
                .addLoreLine(Helper.t("res.desc.1"))
                .addLoreLine(Helper.t("res.desc.2"))
                .hideDefaultTooltip()
                .setCount(1)
                .setCallback((index, clickType, actionType) -> {
                    world.playSound(null, playerEntity.getBlockPos(), SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.PLAYERS, 1f, 1f);
                    gui.close();
                    //buildResGui(playerEntity, world, hand);
                })
        );

        gui.setSlot(2, new GuiElementBuilder().setItem(Items.GREEN_STAINED_GLASS_PANE).setCount(1).hideDefaultTooltip().hideTooltip().setName(Text.empty()));
        gui.setSlot(3, new GuiElementBuilder().setItem(Items.PURPLE_STAINED_GLASS_PANE).setCount(1).hideDefaultTooltip().hideTooltip().setName(Text.empty()));
        gui.setSlot(4, new GuiElementBuilder().setItem(Items.ORANGE_STAINED_GLASS_PANE).setCount(1).hideDefaultTooltip().hideTooltip().setName(Text.empty()));
        gui.setSlot(5, new GuiElementBuilder().setItem(Items.CYAN_STAINED_GLASS_PANE).setCount(1).hideDefaultTooltip().hideTooltip().setName(Text.empty()));
        gui.setSlot(6, new GuiElementBuilder().setItem(Items.RED_STAINED_GLASS_PANE).setCount(1).hideDefaultTooltip().hideTooltip().setName(Text.empty()));
        gui.setSlot(20, new GuiElementBuilder().setItem(Items.GREEN_STAINED_GLASS_PANE).setCount(1).hideDefaultTooltip().hideTooltip().setName(Text.empty()));
        gui.setSlot(21, new GuiElementBuilder().setItem(Items.PURPLE_STAINED_GLASS_PANE).setCount(1).hideDefaultTooltip().hideTooltip().setName(Text.empty()));
        gui.setSlot(22, new GuiElementBuilder().setItem(Items.ORANGE_STAINED_GLASS_PANE).setCount(1).hideDefaultTooltip().hideTooltip().setName(Text.empty()));
        gui.setSlot(23, new GuiElementBuilder().setItem(Items.CYAN_STAINED_GLASS_PANE).setCount(1).hideDefaultTooltip().hideTooltip().setName(Text.empty()));
        gui.setSlot(24, new GuiElementBuilder().setItem(Items.RED_STAINED_GLASS_PANE).setCount(1).hideDefaultTooltip().hideTooltip().setName(Text.empty()));

        var pane = Items.WHITE_STAINED_GLASS_PANE;

        gui.setSlot(0, new GuiElementBuilder().setItem(pane).setCount(1).hideDefaultTooltip().hideTooltip().setName(Text.empty()));
        gui.setSlot(1, new GuiElementBuilder().setItem(pane).setCount(1).hideDefaultTooltip().hideTooltip().setName(Text.empty()));
        gui.setSlot(7, new GuiElementBuilder().setItem(pane).setCount(1).hideDefaultTooltip().hideTooltip().setName(Text.empty()));
        gui.setSlot(8, new GuiElementBuilder().setItem(pane).setCount(1).hideDefaultTooltip().hideTooltip().setName(Text.empty()));
        gui.setSlot(9, new GuiElementBuilder().setItem(pane).setCount(1).hideDefaultTooltip().hideTooltip().setName(Text.empty()));
        gui.setSlot(10, new GuiElementBuilder().setItem(pane).setCount(1).hideDefaultTooltip().hideTooltip().setName(Text.empty()));
        gui.setSlot(16, new GuiElementBuilder().setItem(pane).setCount(1).hideDefaultTooltip().hideTooltip().setName(Text.empty()));
        gui.setSlot(17, new GuiElementBuilder().setItem(pane).setCount(1).hideDefaultTooltip().hideTooltip().setName(Text.empty()));
        gui.setSlot(18, new GuiElementBuilder().setItem(pane).setCount(1).hideDefaultTooltip().hideTooltip().setName(Text.empty()));
        gui.setSlot(19, new GuiElementBuilder().setItem(pane).setCount(1).hideDefaultTooltip().hideTooltip().setName(Text.empty()));
        gui.setSlot(25, new GuiElementBuilder().setItem(pane).setCount(1).hideDefaultTooltip().hideTooltip().setName(Text.empty()));
        gui.setSlot(26, new GuiElementBuilder().setItem(pane).setCount(1).hideDefaultTooltip().hideTooltip().setName(Text.empty()));

        gui.open();
    }



}
