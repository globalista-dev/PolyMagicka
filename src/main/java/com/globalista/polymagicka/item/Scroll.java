package com.globalista.polymagicka.item;

import com.globalista.polymagicka.magic.components.SpellHandler;
import com.globalista.polymagicka.magic.spell.Spell;
import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.ArrayList;
import java.util.List;

public class Scroll extends Item implements PolymerItem {

    private String name;
    private Spell spell;

    public static List<Scroll> SCROLLS = new ArrayList<>();

    public Scroll(Settings settings, String name, Spell spell) {
        super(settings);
        this.name = name;
        this.spell = spell;
    }

    public static Scroll create(String name, Spell spell) {
        Scroll scroll = new Scroll(new Settings().rarity(Rarity.RARE).maxCount(1), name, spell);
        SCROLLS.add(scroll);
        return scroll;
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {

        world.playSound(null, user.getBlockPos(), SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.PLAYERS);


        Spell.cast(spell, world, user, hand);

        return ActionResult.SUCCESS;
    }

    public String getScrollName() {
        return name;
    }

    public Spell getSpell() {
        return spell;
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext packetContext) {
        return Items.PAPER;
    }
}
