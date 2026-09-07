package dev.fluttercat.appetized.block;

import dev.fluttercat.appetized.Appetized;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;


public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> PLACEABLE_FOOD_BLOCKS = createTag("placeable_food_blocks");

        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Appetized.MOD_ID, name));
        }
    }
}
