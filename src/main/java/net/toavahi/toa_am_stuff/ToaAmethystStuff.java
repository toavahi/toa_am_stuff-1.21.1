package net.toavahi.toa_am_stuff;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.toavahi.toa_am_stuff.block.ModBlocks;
import net.toavahi.toa_am_stuff.effect.ModEffects;
import net.toavahi.toa_am_stuff.effect.ModPotions;
import net.toavahi.toa_am_stuff.entity.AmGolemEntity;
import net.toavahi.toa_am_stuff.entity.ModEntities;
import net.toavahi.toa_am_stuff.item.ModItems;
import net.toavahi.toa_am_stuff.util.AmFoodCraftingRecipe;
import net.toavahi.toa_am_stuff.util.ModDataComponents;
import net.toavahi.toa_am_stuff.util.PlayerTickHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToaAmethystStuff implements ModInitializer {

	public static final String MOD_ID = "toa_am_stuff";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static RecipeSerializer<AmFoodCraftingRecipe> AM_FOOD = register("making_am_food", new SpecialRecipeSerializer<>(AmFoodCraftingRecipe::new));
	private static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String id, S serializer) {
		return Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(MOD_ID, id), serializer);
	}

	@Override
	public void onInitialize() {
		ModItems.registerItems();
		ModBlocks.registerBlocks();
		ModDataComponents.registerComponents();
		FabricDefaultAttributeRegistry.register(ModEntities.AM_GOLEM_ENTITY, AmGolemEntity.createAmGolemEntityAttributes());
		ModEffects.registerEffects();
		ModPotions.registerPotions();
		ServerTickEvents.START_SERVER_TICK.register(new PlayerTickHandler());
		LOGGER.info("toa amethyst stuff");
		LOGGER.info("wtf is data fixer");
	}
}