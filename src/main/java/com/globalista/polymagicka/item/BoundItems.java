package com.globalista.polymagicka.item;

import com.globalista.polymagicka.util.ModRegistry;
import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

import java.util.ArrayList;
import java.util.List;

public class BoundItems {

    public static final List<Item> BOUND_ITEMS = new ArrayList<>();

    public static final Item BOUND_SWORD = ModRegistry.registerItem("bound_sword", SimplePolymerItem::new,
            new Item.Settings().sword(ToolMaterial.NETHERITE, 3f, -2.4f));

    public static final Item BOUND_SHOVEL = ModRegistry.registerItem("bound_shovel", SimplePolymerItem::new,
            new Item.Settings().shovel(ToolMaterial.NETHERITE, 1.5f, -3.0f));

    public static final Item BOUND_PICKAXE = ModRegistry.registerItem("bound_pickaxe", SimplePolymerItem::new,
            new Item.Settings().pickaxe(ToolMaterial.NETHERITE, 1.0f, -2.8f));

    public static final Item BOUND_AXE = ModRegistry.registerItem("bound_axe", SimplePolymerItem::new,
            new Item.Settings().shovel(ToolMaterial.NETHERITE, 5.0f, -3.0f));

    public static final Item BOUND_HOE = ModRegistry.registerItem("bound_hoe", SimplePolymerItem::new,
            new Item.Settings().shovel(ToolMaterial.NETHERITE, -4.0f, 0f));

    public static final Item BOUND_BOW = ModRegistry.registerItem("bound_bow", BowItem::new,
            new Item.Settings().maxDamage(384).enchantable(1));

    public static final Item BOUND_CROSSBOW = ModRegistry.registerItem("bound_crossbow", CrossbowItem::new,
            new Item.Settings().maxCount(1).maxDamage(465).component(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT).enchantable(1));

    public static void init(){
        BOUND_ITEMS.add(BOUND_SWORD);
        BOUND_ITEMS.add(BOUND_SHOVEL);
        BOUND_ITEMS.add(BOUND_PICKAXE);
        BOUND_ITEMS.add(BOUND_AXE);
        BOUND_ITEMS.add(BOUND_HOE);
        BOUND_ITEMS.add(BOUND_BOW);
        BOUND_ITEMS.add(BOUND_CROSSBOW);
    }




}
