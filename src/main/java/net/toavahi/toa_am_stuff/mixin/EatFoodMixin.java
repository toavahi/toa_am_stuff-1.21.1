package net.toavahi.toa_am_stuff.mixin;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.toavahi.toa_am_stuff.effect.ModEffects;
import net.toavahi.toa_am_stuff.util.ModDataComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class EatFoodMixin {
    @Inject(method = "eatFood", at = @At(value = "TAIL"))
    public void eatFood(World world, ItemStack stack, FoodComponent foodComponent, CallbackInfoReturnable<ItemStack> cir){
        LivingEntity obj = (LivingEntity) (Object) this;
        if(stack.get(ModDataComponents.POISON_FOOD) != null && stack.get(ModDataComponents.POISON_FOOD)){
            obj.addStatusEffect(new StatusEffectInstance(ModEffects.AM_EFFECT, 1200));
        }
    }
}
