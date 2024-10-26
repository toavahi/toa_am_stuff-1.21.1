package net.toavahi.toa_am_stuff.entity.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.toavahi.toa_am_stuff.ToaAmethystStuff;
import net.toavahi.toa_am_stuff.entity.AmGrenadeEntity;

@Environment(EnvType.CLIENT)
public class AmGrenadeEntityRenderer extends EntityRenderer<AmGrenadeEntity> {
    private static final Identifier TEXTURE = Identifier.of(ToaAmethystStuff.MOD_ID, "textures/item/am_grenade.png");
    private static final RenderLayer LAYER = RenderLayer.getEntityCutoutNoCull(TEXTURE);

    public AmGrenadeEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(AmGrenadeEntity entity) {
        return TEXTURE;
    }

    @Override
    protected int getBlockLight(AmGrenadeEntity entity, BlockPos pos) {
        return 15;
    }

    @Override
    public void render(AmGrenadeEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        matrices.scale(0.5F, 0.5F, 0.5F);
        matrices.multiply(this.dispatcher.getRotation());
        MatrixStack.Entry entry = matrices.peek();
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(LAYER);
        produceVertex(vertexConsumer, entry, light, 0.0F, 0, 0, 1);
        produceVertex(vertexConsumer, entry, light, 1.0F, 0, 1, 1);
        produceVertex(vertexConsumer, entry, light, 1.0F, 1, 1, 0);
        produceVertex(vertexConsumer, entry, light, 0.0F, 1, 0, 0);
        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    private static void produceVertex(VertexConsumer vertexConsumer, MatrixStack.Entry matrix, int light, float x, int z, int textureU, int textureV) {
        vertexConsumer.vertex(matrix, x - 0.5F, (float)z - 0.25F, 0.0F)
                .color(Colors.WHITE)
                .texture((float)textureU, (float)textureV)
                .overlay(OverlayTexture.DEFAULT_UV)
                .light(light)
                .normal(matrix, 0.0F, 1.0F, 0.0F);
    }
}
