package dev.fluttercat.appetized.datagen;

import dev.fluttercat.appetized.block.ModBlocks;
import dev.fluttercat.appetized.block.ModTags;
import dev.fluttercat.appetized.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ItemTags.HOES).add(ModItems.getRK(ModItems.SCYTHE));
        tag(ItemTags.SWORDS).add(ModItems.getRK(ModItems.SCYTHE));
        tag(ItemTags.AXES).add(ModItems.getRK(ModItems.CLEAVER));
        tag(ItemTags.AXES).add(ModItems.getRK(ModItems.TARNISHED_CLEAVER));



    }
}
