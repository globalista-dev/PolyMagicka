package com.globalista.polymagicka.item;

import com.globalista.polymagicka.spell.Spell;
import com.globalista.polymagicka.spell.components.SpellDataComponent;
import com.globalista.polymagicka.util.ModRegistry;
import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.nucleoid.packettweaker.PacketContext;

public class Wand extends Item implements PolymerItem {

    private String name;

    public Wand(Settings settings) {
        super(settings);
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext packetContext) {
        return Items.STICK;
    }

    public Spell getCurrentSpell(ItemStack stack) {
        return stack.get(SpellDataComponent.SPELL);
    }

    public void setCurrentSpell(ItemStack stack, Spell spell) {
        stack.set(SpellDataComponent.SPELL, spell);
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        ItemStack offhand = user.getOffHandStack();

        // Checks if the offhand item is a Grimoire by using the tag
        if (offhand.isIn(ModRegistry.GRIMOIRES)) {

            if(user.isSneaking()) {
                // If the player is sneaking, the GUI opens
                world.playSound(null, user.getBlockPos(), SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.PLAYERS, 1.0f, 1.0f);
                buildGui(user, world, hand);
                return ActionResult.SUCCESS;
            } else {

                Spell spell = this.getCurrentSpell(itemStack);
                // Casts spell
                Spell.cast(spell, world, user, hand);

            }


        } else {
            // No Grimoire = no spellcasting
            world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_NOTE_BLOCK_SNARE.value(), SoundCategory.PLAYERS, 1.0f, 0.5f);
            return ActionResult.FAIL;
        }


    }


}
