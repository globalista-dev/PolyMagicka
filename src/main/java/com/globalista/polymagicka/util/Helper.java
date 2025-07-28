package com.globalista.polymagicka.util;

import com.globalista.polymagicka.PolyMagicka;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

    public static String self = "🝐";
    public static String single = "🜏";
    public static String aoe = "🜔";
    public static String fire = "🜂";
    public static String water = "🜄";
    public static String wind = "🜁";
    public static String shock = "🜅";
    public static String ancient = "🝰";

    public static MutableText text(String string){
        switch (string) {
            case "🝐": return Text.literal(self).formatted(Formatting.DARK_AQUA, Formatting.BOLD);
            case "🜏": return Text.literal(single).formatted(Formatting.GOLD, Formatting.BOLD);
            case "🜔": return Text.literal(aoe).formatted(Formatting.GOLD, Formatting.BOLD);
            case "🜂": return Text.literal(fire).formatted(Formatting.DARK_RED, Formatting.BOLD);
            case "🜄": return Text.literal(water).formatted(Formatting.AQUA, Formatting.BOLD);
            case "🜁": return Text.literal(wind).formatted(Formatting.GRAY, Formatting.BOLD);
            case "🜅": return Text.literal(shock).formatted(Formatting.YELLOW, Formatting.BOLD);
            case "🝰": return Text.literal(ancient).formatted(Formatting.DARK_PURPLE, Formatting.BOLD);
            default: return Text.empty();
        }
    }

    public static Identifier id(String name){
        return Identifier.of(PolyMagicka.MOD_ID, name);
    }

    public static MutableText t(String translation){
        return Text.translatable(PolyMagicka.MOD_ID + "." + translation);
    }

    public static Item get(String name) {
        return Registries.ITEM.get(id(name));
    }

    public static String extractUsername(String input) {
        String pattern = "\\{([^}]*)\\}";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(input);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "no_username";
        }
    }

    public static RegistryEntry<Enchantment> getEnchant(RegistryKey<Enchantment> enchant, World world){
        return world.getRegistryManager().getEntryOrThrow(enchant);
    }

    public static ItemStack getHead(String string){
        ItemStack head = new ItemStack(Items.PLAYER_HEAD);
        PropertyMap map = new PropertyMap();
        map.put("textures", new Property("textures", string, null));
        head.set(DataComponentTypes.PROFILE, new ProfileComponent(Optional.empty(), Optional.empty(), map));
        return head;
    }

    public static ItemStack getColoredArmor(Item item, int dyeColor){
        ItemStack stack = new ItemStack(item);
        DyedColorComponent color = new DyedColorComponent(dyeColor);
        stack.set(DataComponentTypes.DYED_COLOR, color);
        return stack;
    }

}
