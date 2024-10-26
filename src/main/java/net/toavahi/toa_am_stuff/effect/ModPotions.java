package net.toavahi.toa_am_stuff.effect;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.toavahi.toa_am_stuff.ToaAmethystStuff;
import net.toavahi.toa_am_stuff.item.ModItems;

public class ModPotions {
    public static RegistryEntry<Potion> AM_POTION;
    public static RegistryEntry<Potion> L_AM_POTION;

    public static RegistryEntry<Potion> registerPotion(String name, RegistryEntry<StatusEffect> effect, int time){
        return Registry.registerReference(Registries.POTION, Identifier.of(ToaAmethystStuff.MOD_ID, name), new Potion(new StatusEffectInstance(effect, time)));
    }

    public static void registerPotions(){
        AM_POTION = registerPotion("am_potion", ModEffects.AM_EFFECT, 600);
        L_AM_POTION = registerPotion("long_am_potion", ModEffects.AM_EFFECT, 1200);
        registerPotionRecipes();
    }
    public static void registerPotionRecipes(){
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(Potions.AWKWARD, ModItems.AM_DUST, AM_POTION);
            builder.registerPotionRecipe(AM_POTION, Items.REDSTONE, L_AM_POTION);
        });
    }
}
