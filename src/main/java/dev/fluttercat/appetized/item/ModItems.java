package dev.fluttercat.appetized.item;

import dev.fluttercat.appetized.Appetized;
import dev.fluttercat.appetized.block.ModBlocks;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;

import java.util.function.Function;

public class ModItems {

    public static final Item CLEAVER = registerItem("cleaver",properties -> new CleaverItem(properties.stacksTo(1).repairable(Items.IRON_INGOT).durability(400).axe(ToolMaterial.IRON,3.5f,-2.25f)));
//    public static final Item TARNISHED_CLEAVER_BLADE = registerItem("tarnished_cleaver_blade")
    public static final Item TARNISHED_CLEAVER = registerItem("tarnished_cleaver",properties -> new CleaverItem(properties.stacksTo(1).repairable(Items.IRON_INGOT).durability(200).axe(ToolMaterial.IRON,2.5f,-2.75f)));

    public static final Item SCYTHE = registerItem("scythe",properties -> new ScytheItem(properties.durability(450).stacksTo(1).repairable(Items.IRON_INGOT).sword(ToolMaterial.IRON,5,-3f)));

    public static final Item SLICED_BREAD = registerItem("sliced_bread",properties -> new Item(properties.stacksTo(64).food(ModFoods.SLICED_BREAD,ModConsumables.SLICED_BREAD)));

    public static ResourceKey<Item> getRK(Item item) {
        return BuiltInRegistries.ITEM.getResourceKey(item).get();
    }

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Appetized.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Appetized.MOD_ID, name)))));
    }

    public static void registerModItems() {
        Appetized.LOGGER.info("Registering Mod Items for " + Appetized.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(output -> {
            output.insertBefore(Items.TRIDENT,TARNISHED_CLEAVER);
            output.insertAfter(TARNISHED_CLEAVER,CLEAVER);

            output.insertAfter(Items.MACE,SCYTHE);

        });

    }
}
