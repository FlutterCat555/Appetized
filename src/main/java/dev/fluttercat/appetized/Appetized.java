package dev.fluttercat.appetized;

import dev.fluttercat.appetized.block.ModBlocks;
import dev.fluttercat.appetized.effect.ModMobEffects;
import dev.fluttercat.appetized.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;

import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Appetized implements ModInitializer {

	public static final String MOD_ID = "appetized";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModMobEffects.registerModEffects();

		LOGGER.info("i burnt the water");


		//ref: https://docs.fabricmc.net/develop/events#adding-items-to-the-loot-table
		//https://www.reddit.com/r/fabricmc/comments/1d3v185/i_cannot_modify_vanilla_loot_tables_with/, check comment by krisis_9302. mojmap but it same pattern
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            // Let's only modify built-in loot tables and leave data pack loot tables untouched by checking the source.
            // We also check that the loot table ID is equal to the ID we want.
            if (source.isBuiltin() && key.equals(BuiltInLootTables.SIMPLE_DUNGEON)) {
                // We make the pool and add an item
                LootPool.Builder poolBuilder = LootPool.lootPool()
						.add(LootItem.lootTableItem(ModItems.TARNISHED_CLEAVER))
						.when(LootItemRandomChanceCondition.randomChance(0.25f));
				//						.setRolls(ConstantValue.exactly(1)) //might need later
				tableBuilder.withPool(poolBuilder);
            }
        });

		LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
			if (source.isBuiltin() && key.equals(BuiltInLootTables.SIMPLE_DUNGEON)) {
				LootPool.Builder poolBuilder = LootPool.lootPool()
						.add(LootItem.lootTableItem(ModItems.TARNISHED_SCYTHE))
						.when(LootItemRandomChanceCondition.randomChance(0.25f));
				tableBuilder.withPool(poolBuilder);
			}
		});
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
