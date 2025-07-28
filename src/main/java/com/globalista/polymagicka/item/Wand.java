package com.globalista.polymagicka.item;

import com.globalista.polymagicka.gui.DestructionGUI;
import com.globalista.polymagicka.magic.spell.Spell;
import com.globalista.polymagicka.magic.components.SpellDataComponent;
import com.globalista.polymagicka.util.Helper;
import com.globalista.polymagicka.util.ModRegistry;
import com.globalista.polymagicka.gui.AlterationGUI;
import com.globalista.polymagicka.gui.ConjurationGUI;
import com.globalista.polymagicka.gui.GrimoireGUI;
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

        ItemStack mainhand = user.getStackInHand(hand);
        ItemStack offhand = user.getOffHandStack();

        // Checks if the offhand item is an item that permits casting by using the tag
        if (offhand.isIn(ModRegistry.PERMITS_CASTING)) {

            if(user.isSneaking()) {
                // If the player is sneaking, the GUI opens
                world.playSound(null, user.getBlockPos(), SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.PLAYERS, 1.0f, 1.0f);

                if(offhand.isIn(ModRegistry.GRIMOIRES)) { GrimoireGUI.buildGui(user, world, hand, this); }
                else if(offhand.isIn(ModRegistry.ALTERATION_TOMES)) { AlterationGUI.buildGui(user, world, hand, this); }
                else if(offhand.isIn(ModRegistry.CONJURATION_TOMES)) { ConjurationGUI.buildGui(user, world, hand, this); }
                else if(offhand.isIn(ModRegistry.DESTRUCTION_TOMES)) { DestructionGUI.buildGUI(user, world, hand, this); }
                else if(offhand.isIn(ModRegistry.ILLUSION_TOMES)) { buildIllGui(user, world, hand); }
                else if(offhand.isIn(ModRegistry.RESTORATION_TOMES)) { buildResGui(user, world, hand); }

                return ActionResult.SUCCESS;
            } else {

                if (this.getCurrentSpell(mainhand) != null){
                    Spell.cast(this.getCurrentSpell(mainhand), world, user, hand);
                    //user.getItemCooldownManager().set(mainhand, this.getCurrentSpell(mainhand).getCooldown());
                } else {
                    System.out.println(Helper.t("error.no.spell"));
                }


            }


        } else {
            // No Grimoire/Tome = no spellcasting
            world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_NOTE_BLOCK_SNARE.value(), SoundCategory.PLAYERS, 1.0f, 0.5f);
            return ActionResult.FAIL;
        }
        return ActionResult.PASS;
    }



    private void buildIllGui(PlayerEntity playerEntity, World world, Hand hand) {}

    private void buildResGui(PlayerEntity playerEntity, World world, Hand hand) {}
}
