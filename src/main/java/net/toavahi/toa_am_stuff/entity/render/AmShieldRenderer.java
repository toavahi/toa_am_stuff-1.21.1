package net.toavahi.toa_am_stuff.entity.render;

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.toavahi.toa_am_stuff.ToaAmethystStuff;

public class AmShieldRenderer implements DynamicItemRenderer {
    private final AmShieldModel modelShield;

    public AmShieldRenderer(ModelPart model) {
        this.modelShield = new AmShieldModel(model);
    }

    @Override
    public void render(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        matrices.scale(1.0f, -1.0f, -1.0f);
        VertexConsumer vertexConsumer = ItemRenderer.getItemGlintConsumer(vertexConsumers, RenderLayer.getItemEntityTranslucentCull(Identifier.of(ToaAmethystStuff.MOD_ID, "textures/item/am_shield.png")), false, stack.hasGlint());
        this.modelShield.render(matrices, vertexConsumer, light, overlay);
        matrices.pop();
    }
}