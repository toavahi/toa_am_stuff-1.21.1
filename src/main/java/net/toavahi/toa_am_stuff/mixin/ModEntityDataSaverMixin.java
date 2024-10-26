package net.toavahi.toa_am_stuff.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.toavahi.toa_am_stuff.util.IEntityDataSaver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class ModEntityDataSaverMixin implements IEntityDataSaver {
    private NbtCompound persistentData;
    @Override
    public NbtCompound getPersistentData(){
        if(this.persistentData == null){
            this.persistentData = new NbtCompound();
        }
        return persistentData;
    }
    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfoReturnable info){
        if(persistentData != null){
            nbt.put("toa_am_stuff.toavahi_data", persistentData);
        }
    }
    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void injectReadMethod(NbtCompound nbt, CallbackInfo info){
        if(nbt.contains("toa_am_stuff.toavahi_data", 10)){
            persistentData = nbt.getCompound("toa_am_stuff.toavahi_data");
        }
    }
}
