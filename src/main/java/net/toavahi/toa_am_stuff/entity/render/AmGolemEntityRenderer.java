package net.toavahi.toa_am_stuff.entity.render;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.toavahi.toa_am_stuff.ToaAmethystStuff;
import net.toavahi.toa_am_stuff.entity.AmGolemEntity;

public class AmGolemEntityRenderer extends MobEntityRenderer<AmGolemEntity, AmGolemEntityModel<AmGolemEntity>> {
    public static final EntityModelLayer AM_GOLEM = new EntityModelLayer(Identifier.of(ToaAmethystStuff.MOD_ID, "am_golem_entity"), "main");
    public AmGolemEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new AmGolemEntityModel<>(context.getPart(AM_GOLEM)), 0.6F);
    }

    @Override
    public Identifier getTexture(AmGolemEntity entity) {
        return Identifier.of(ToaAmethystStuff.MOD_ID, "textures/entity/am_golem_entity.png");
    }

    @Override
    public void render(AmGolemEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
