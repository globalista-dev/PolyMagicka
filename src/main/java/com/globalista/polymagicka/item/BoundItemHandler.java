package com.globalista.polymagicka.item;

import com.globalista.polymagicka.magic.components.SpellDataComponent;
import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.component.ComponentChanges;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.collection.DefaultedList;

public class BoundItemHandler {
    private static final int LIFETIME_TICKS = 180 * 20; // 180 seconds

    public static void register() {
        ServerTickEvents.END_WORLD_TICK.register(world -> {
            if (world.isClient()) return;

            long currentTick = world.getServer().getTicks();

            for (ServerPlayerEntity player : world.getPlayers()) {
                DefaultedList<ItemStack> inv = player.getInventory().getMainStacks();

                for (int i = 0; i < inv.size(); i++) {
                    ItemStack stack = inv.get(i);
                    if (stack.isEmpty()) continue;

                    if (isBoundItem(stack)) {
                        Long tickCreated = stack.get(SpellDataComponent.CREATION_TICK);

                        if (tickCreated == null) {
                            stack.applyChanges(ComponentChanges.builder()
                                    .add(SpellDataComponent.CREATION_TICK, currentTick)
                                    .build());
                        } else if (currentTick - tickCreated > LIFETIME_TICKS) {
                            inv.set(i, ItemStack.EMPTY);
                            player.sendMessage(Text.translatable("polymagicka.bound.item.expired").formatted(Formatting.RED, Formatting.BOLD), true);
                            world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_ILLUSIONER_MIRROR_MOVE, SoundCategory.PLAYERS);
                        }
                    }
                }
            }
        });
    }

    private static boolean isBoundItem(ItemStack stack) {
        return BoundItems.BOUND_ITEMS.contains(stack.getItem());
    }
}


