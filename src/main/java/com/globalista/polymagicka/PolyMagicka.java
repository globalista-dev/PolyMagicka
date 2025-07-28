package com.globalista.polymagicka;

import com.globalista.polymagicka.item.BoundItemHandler;
import com.globalista.polymagicka.item.BoundItems;
import com.globalista.polymagicka.item.Scroll;
import com.globalista.polymagicka.item.Wand;
import com.globalista.polymagicka.magic.components.SpellDataComponent;
import com.globalista.polymagicka.magic.spell.DestructionSpells;
import com.globalista.polymagicka.gui.GUIBackgroundCreator;
import com.globalista.polymagicka.util.ModRegistry;
import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import net.fabricmc.api.ModInitializer;

import net.minecraft.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PolyMagicka implements ModInitializer {
	public static final String MOD_ID = "polymagicka";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Scroll TEST_SCROLL = (Scroll) ModRegistry.registerItem("test_scroll", settings ->
			new Scroll(settings, "test_scroll", DestructionSpells.GUST), new Item.Settings());

	public static final Wand TEST_WAND = (Wand) ModRegistry.registerItem("test_wand", Wand::new, new Item.Settings());

	public static final Item TEST_GRIMOIRE = ModRegistry.registerItem("test_grimoire", SimplePolymerItem::new, new Item.Settings());

	@Override
	public void onInitialize() {

		SpellDataComponent.initialize();
		ModRegistry.registerEntities();
		BoundItems.init();
		BoundItemHandler.register();
		GUIBackgroundCreator.init();

		LOGGER.info("PolyMagicka");
	}
}