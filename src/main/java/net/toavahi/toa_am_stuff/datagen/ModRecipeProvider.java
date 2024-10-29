package net.toavahi.toa_am_stuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.toavahi.toa_am_stuff.block.ModBlocks;
import net.toavahi.toa_am_stuff.item.ModItems;
import net.toavahi.toa_am_stuff.util.AmFoodCraftingRecipe;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.AM_TRIANGLE)
                .pattern(" A ")
                .pattern("A A")
                .pattern(" S ")
                .input('A', Items.AMETHYST_SHARD)
                .input('S', Items.STICK)
                .criterion("has_amethyst_shard", conditionsFromItem(Items.AMETHYST_SHARD))
                .criterion("has_stick", conditionsFromItem(Items.STICK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.AM_GRENADE)
                .pattern(" B ")
                .pattern("BAB")
                .pattern(" B ")
                .input('A', ModItems.AM_DUST)
                .input('B', Items.IRON_NUGGET)
                //.criterion("has_iron_nugget", conditionsFromItem(Items.IRON_NUGGET))
                //.criterion("has_am_dust", conditionsFromItem(ModItems.AM_DUST))
                .criterion("find_in_the_world_not_crafting_table", conditionsFromItem(Blocks.BARRIER))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.AM_CHISEL)
                .pattern("A")
                .pattern("I")
                .input('A', Items.AMETHYST_SHARD)
                .input('I', Items.IRON_INGOT)
                //.criterion("has_iron_ingot", conditionsFromItem(Items.IRON_INGOT))
                //.criterion("has_am_shard", conditionsFromItem(Items.AMETHYST_SHARD))
                .criterion("find_in_the_world_not_crafting_table", conditionsFromItem(Blocks.BARRIER))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.AM_SHIELD)
                .pattern(" A ")
                .pattern("ASA")
                .pattern(" A ")
                .input('A', Items.AMETHYST_SHARD)
                .input('S', Items.SHIELD)
                //.criterion("has_am_shard", conditionsFromItem(Items.AMETHYST_SHARD))
                //.criterion("has_shield", conditionsFromItem(Items.SHIELD))
                .criterion("find_in_the_world_not_crafting_table", conditionsFromItem(Blocks.BARRIER))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.AM_DUST, 4)
                .input(Items.AMETHYST_SHARD)
                //.criterion("has_am_shard", conditionsFromItem(Items.AMETHYST_SHARD))
                .criterion("find_in_the_world_not_crafting_table", conditionsFromItem(Blocks.BARRIER))
                .offerTo(exporter);
        ComplexRecipeJsonBuilder.create(AmFoodCraftingRecipe::new).offerTo(exporter, "making_am_food");
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.AM_BRUSH)
                .pattern("F")
                .pattern("A")
                .pattern("S")
                .input('S', Items.STICK)
                .input('F', Items.FEATHER)
                .input('A', Items.AMETHYST_SHARD)
                .criterion("find_in_the_world_not_crafting_table", conditionsFromItem(Blocks.BARRIER))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.AM_COPPER_INGOT)
                .input(ModItems.AM_DUST)
                .input(Items.COPPER_INGOT)
                .criterion("find_in_the_world_not_crafting_table", conditionsFromItem(Blocks.BARRIER))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.AM_COPPER_BLOCK)
                .pattern("AA")
                .pattern("AA")
                .input('A', ModItems.AM_COPPER_INGOT)
                .criterion("has_am_copper_ingot", conditionsFromItem(ModItems.AM_COPPER_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.AM_COPPER_SLAB, 6)
                .pattern("AAA")
                .input('A', ModBlocks.AM_COPPER_BLOCK)
                .criterion("has_am_copper_block", conditionsFromItem(ModBlocks.AM_COPPER_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.AM_COPPER_STAIRS, 4)
                .pattern("A ")
                .pattern("AA")
                .input('A', ModBlocks.AM_COPPER_BLOCK)
                .criterion("has_am_copper_block", conditionsFromItem(ModBlocks.AM_COPPER_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.AM_COPPER_WALL, 6)
                .pattern("AAA")
                .pattern("AAA")
                .input('A', ModBlocks.AM_COPPER_BLOCK)
                .criterion("has_am_copper_block", conditionsFromItem(ModBlocks.AM_COPPER_BLOCK))
                .offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.AM_COPPER_STAIRS, ModBlocks.AM_COPPER_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.AM_COPPER_SLAB, ModBlocks.AM_COPPER_BLOCK, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, ModBlocks.AM_COPPER_WALL, ModBlocks.AM_COPPER_BLOCK);
    }
}
