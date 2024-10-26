package net.toavahi.toa_am_stuff.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.toavahi.toa_am_stuff.ToaAmethystStuff;
import net.toavahi.toa_am_stuff.util.IEntityDataSaver;

public class AmEffect extends StatusEffect {
    EntityAttributeModifier modifier;

    public AmEffect(StatusEffectCategory statusEffectCategory, int i) {
        super(statusEffectCategory, i);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        super.onApplied(entity, amplifier);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        World world = entity.getWorld();
        if(!world.isClient()){
            if(((int) world.getTime()) % 20 * 5 == 0){
                ((IEntityDataSaver) entity).getPersistentData().putDouble("am_effect_bonus", ((IEntityDataSaver) entity).getPersistentData().getDouble("am_effect_bonus") - 0.5);
                modifier = new EntityAttributeModifier(Identifier.of(ToaAmethystStuff.MOD_ID, "generic.max_health"), ((IEntityDataSaver) entity).getPersistentData().getDouble("am_effect_bonus"), EntityAttributeModifier.Operation.ADD_VALUE);
                entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).removeModifier(modifier);
                entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).addTemporaryModifier(modifier);
            }
        }
        return true;
    }

    @Override
    public void onRemoved(AttributeContainer attributeContainer) {
        attributeContainer.getCustomInstance(EntityAttributes.GENERIC_MAX_HEALTH).removeModifier(Identifier.of(ToaAmethystStuff.MOD_ID, "generic.max_health"));
        super.onRemoved(attributeContainer);
    }
}
