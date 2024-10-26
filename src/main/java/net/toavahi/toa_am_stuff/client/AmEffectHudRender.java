package net.toavahi.toa_am_stuff.client;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;
import net.toavahi.toa_am_stuff.ToaAmethystStuff;
import net.toavahi.toa_am_stuff.effect.ModEffects;
import net.toavahi.toa_am_stuff.util.IEntityDataSaver;

public class AmEffectHudRender implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        int[] heartPosX = ((IEntityDataSaver) player).getPersistentData().getIntArray("heartPosX");
        int[] heartPosY = ((IEntityDataSaver) player).getPersistentData().getIntArray("heartPosY");
        if (heartPosX.length == 0) {
            heartPosX = new int[10];
            heartPosY = new int[10];
        }
        double bonus = (20 - player.getMaxHealth()) / 2;
        for (int i = 0; i < 10; ++i) {
            if (player.hasStatusEffect(ModEffects.AM_EFFECT) && !player.isCreative() && !player.isSpectator() && 10 - i <= bonus) {
                drawContext.drawGuiTexture(Identifier.of(ToaAmethystStuff.MOD_ID, "am_heart"),
                        heartPosX[i], heartPosY[i], 9, 9);
            }
        }
    }
}
