package dev.fluttercat.appetized.datagen;

import dev.fluttercat.appetized.block.ModBlocks;
import dev.fluttercat.appetized.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                shapeless(RecipeCategory.MISC, ModItems.CLEAVER, 1)
                        .requires(ModItems.TARNISHED_CLEAVER)
                        .requires(Items.NETHERITE_SCRAP)
                        .unlockedBy(getHasName(ModItems.TARNISHED_CLEAVER), has(ModItems.TARNISHED_CLEAVER))
                        .save(output);
            }
        };
    }

    @Override
    public String getName() {
        return "Appetized Recipes";
    }
}
