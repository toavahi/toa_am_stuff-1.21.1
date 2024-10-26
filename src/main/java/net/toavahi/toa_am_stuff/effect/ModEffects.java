package net.toavahi.toa_am_stuff.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.toavahi.toa_am_stuff.ToaAmethystStuff;

public class ModEffects {
    public static RegistryEntry<StatusEffect> AM_EFFECT;

    public static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect effect){
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(ToaAmethystStuff.MOD_ID, name), effect);
    }
    public static void registerEffects(){
        AM_EFFECT = registerStatusEffect("am_effect", new AmEffect(StatusEffectCategory.HARMFUL, 16122092));
    }
}
