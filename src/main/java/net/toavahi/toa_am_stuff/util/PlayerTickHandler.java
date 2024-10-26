package net.toavahi.toa_am_stuff.util;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.toavahi.toa_am_stuff.effect.ModEffects;

public class PlayerTickHandler implements ServerTickEvents.StartTick {
    @Override
    public void onStartTick(MinecraftServer server) {
        for(ServerPlayerEntity player : server.getPlayerManager().getPlayerList()){
            if(!player.hasStatusEffect(ModEffects.AM_EFFECT) && ((IEntityDataSaver) player).getPersistentData().getDouble("am_effect_bonus") < 0){
                ((IEntityDataSaver) player).getPersistentData().putDouble("am_effect_bonus", 0);
            }
        }
    }
}
