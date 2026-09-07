package dev.fluttercat.appetized.datagen;

import dev.fluttercat.appetized.block.ModBlocks;
import dev.fluttercat.appetized.block.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ModTags.Blocks.PLACEABLE_FOOD_BLOCKS)
                .add(Blocks.CAKE.properties().blockIdOrThrow())
                .add(ModBlocks.PLACED_BREAD.properties().blockIdOrThrow())
        ;

    }
}
