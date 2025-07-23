package com.globalista.polymagicka.spell.components;

import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;

public class Element {
    private String name;
    private ItemConvertible attunementItem;

    private Element(String name, ItemConvertible attunementItem){
        this.name = name;
        this.attunementItem = attunementItem;
    }

    public static Element FIRE = new Element("fire", Items.BLAZE_POWDER);
    public static Element AIR = new Element("air", Items.WIND_CHARGE);
    public static Element WATER = new Element("water", Items.PRISMARINE_SHARD);
    public static Element EARTH = new Element("earth", Items.BONE_MEAL);

    public String getName() {
        return name;
    }

    public ItemConvertible getAttunementItem() {
        return attunementItem;
    }
}
