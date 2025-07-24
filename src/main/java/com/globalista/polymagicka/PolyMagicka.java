package com.globalista.polymagicka;

import com.globalista.polymagicka.spell.components.SpellDataComponent;
import net.fabricmc.api.ModInitializer;

import net.minecraft.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PolyMagicka implements ModInitializer {
	public static final String MOD_ID = "polymagicka";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		SpellDataComponent.initialize();

		LOGGER.info("Hello Fabric world!");
	}
}