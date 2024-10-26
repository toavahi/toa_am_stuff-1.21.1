package net.toavahi.toa_am_stuff;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import net.toavahi.toa_am_stuff.client.AmEffectHudRender;
import net.toavahi.toa_am_stuff.entity.ModEntities;
import net.toavahi.toa_am_stuff.entity.render.*;
import net.toavahi.toa_am_stuff.item.ModItems;
import net.toavahi.toa_am_stuff.util.ModDataComponents;

public class ToaAmethystStuffClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelPredicateProviderRegistry.register(
                ModItems.AM_SHIELD,
                Identifier.ofVanilla("blocking"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F
        );
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.AM_SHIELD, new AmShieldRenderer(AmShieldModel.getTexturedModelData().createModel()));
        ModelPredicateProviderRegistry.register(ModItems.AM_CHISEL, Identifier.of(ToaAmethystStuff.MOD_ID, "am_chisel_am"), (itemStack, clientWorld, livingEntity, i) -> itemStack.get(ModDataComponents.AM_CHISEL) ? 1.0F : 0.0F);
        EntityRendererRegistry.register(ModEntities.AM_GRENADE_ENTITY, AmGrenadeEntityRenderer::new);
        HudRenderCallback.EVENT.register(new AmEffectHudRender());
        ModelPredicateProviderRegistry.register(
                ModItems.AM_BRUSH,
                Identifier.of(ToaAmethystStuff.MOD_ID, "color"),
                (stack, world, entity, seed) -> entity != null ? stack.get(ModDataComponents.COLOR) : 0.0F
        );
        EntityModelLayerRegistry.registerModelLayer(AmGolemEntityRenderer.AM_GOLEM, AmGolemEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.AM_GOLEM_ENTITY, AmGolemEntityRenderer::new);
    }
}
