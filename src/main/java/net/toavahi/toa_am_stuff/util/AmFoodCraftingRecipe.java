package net.toavahi.toa_am_stuff.util;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import net.toavahi.toa_am_stuff.ToaAmethystStuff;
import net.toavahi.toa_am_stuff.item.ModItems;

public class AmFoodCraftingRecipe extends SpecialCraftingRecipe {
    public AmFoodCraftingRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        int i = 0;
        int j = 0;
        for (int k = 0; k < input.getSize(); k++) {
            ItemStack itemStack = input.getStackInSlot(k);
            if (!itemStack.isEmpty()) {
                if (itemStack.getItem().getComponents().contains(DataComponentTypes.FOOD)
                && !itemStack.getComponents().contains(ModDataComponents.POISON_FOOD)) {
                    i++;
                } else {
                    if (!(itemStack.getItem() == ModItems.AM_DUST)) {
                        return false;
                    }
                    j++;
                }
                if (j > 1 || i > 1) {
                    return false;
                }
            }
        }
        return i == 1 && j == 1;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        ItemStack itemStack = ItemStack.EMPTY;
        for (int i = 0; i < input.getSize(); i++) {
            ItemStack itemStack2 = input.getStackInSlot(i);
            if (!itemStack2.isEmpty()) {
                if (itemStack2.getItem().getComponents().contains(DataComponentTypes.FOOD)
                && !itemStack2.getComponents().contains(ModDataComponents.POISON_FOOD)) {
                    itemStack = itemStack2.copyWithCount(1);
                }
            }
        }
        itemStack.set(ModDataComponents.POISON_FOOD, true);
        return itemStack;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ToaAmethystStuff.AM_FOOD;
    }
}
