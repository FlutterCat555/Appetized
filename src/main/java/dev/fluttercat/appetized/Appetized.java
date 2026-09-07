package dev.fluttercat.appetized;

import dev.fluttercat.appetized.block.ModBlocks;
import dev.fluttercat.appetized.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Appetized implements ModInitializer {

	public static final String MOD_ID = "appetized";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModBlocks.registerModBlocks();
		ModItems.registerModItems();

		LOGGER.info("mxnjuisherdfgtefiswjfktgle4swlk3oi");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
