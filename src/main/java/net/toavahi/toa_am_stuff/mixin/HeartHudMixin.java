package net.toavahi.toa_am_stuff.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.toavahi.toa_am_stuff.ToaAmethystStuff;
import net.toavahi.toa_am_stuff.util.IEntityDataSaver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


import java.util.Random;

@Mixin(InGameHud.class)
public abstract class HeartHudMixin {

    @Inject(method = "renderHealthBar",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/hud/InGameHud;drawHeart(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/gui/hud/InGameHud$HeartType;IIZZZ)V",
                    ordinal = 0,
                    shift = At.Shift.AFTER
            )
    )
    private void captureHeartPositions(
            DrawContext context,
            PlayerEntity player,
            int x, int y,
            int lines,
            int regeneratingHeartIndex,
            float maxHealth,
            int lastHealth,
            int health,
            int absorption,
            boolean blinking,
            CallbackInfo ci) {
        int i = MathHelper.ceil((double)maxHealth / 2.0);
        int j = MathHelper.ceil((double)absorption / 2.0);
        int[] heartPosX = new int[10];
        int[] heartPosY = new int[10];
        int tick = 0;
        for (int l = /*i + j - 1*/ 9; l >= 0; l--) {
            int m = l / 10;
            int n = l % 10;
            int o = x + n * 8;
            int p = y - m * lines;
            if (lastHealth + absorption <= 4) {
                Random random = new Random();
                p += random.nextInt(2);
            }
            if (l < 9 && l == regeneratingHeartIndex) {
                p -= 2;
            }
            if (heartPosX[l] != o && heartPosY[l] != p) {
                heartPosX[l] = o;
                heartPosY[l] = p;
            }
        }
        ((IEntityDataSaver) player).getPersistentData().putIntArray("heartPosX", heartPosX);
        ((IEntityDataSaver) player).getPersistentData().putIntArray("heartPosY", heartPosY);
    }
}
