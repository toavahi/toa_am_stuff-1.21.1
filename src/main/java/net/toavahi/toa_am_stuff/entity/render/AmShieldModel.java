package net.toavahi.toa_am_stuff.entity.render;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class AmShieldModel extends Model {
	private final ModelPart root;
	public AmShieldModel(ModelPart root) {
        super(RenderLayer::getEntitySolid);
        this.root = root;
    }
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild("plate", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -11.0F, -2.0F, 12.0F, 22.0F, 1.0F, new Dilation(0.0F)), ModelTransform.NONE);//ModelTransform.pivot(0.0F, 13.0F, -1.0F));

		modelPartData.addChild("handle", ModelPartBuilder.create().uv(26, 0).cuboid(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.NONE);//ModelTransform.pivot(0.0F, 13.0F, -1.0F));

		ModelPartData shards = modelPartData.addChild("shards", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		shards.addChild("cube_r1", ModelPartBuilder.create().uv(26, 12).cuboid(-2.0F, -6.0F, -1.0F, 3.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, -27.0F, -2.0F, 0.2618F, 0.0F, 0.0F));

		shards.addChild("cube_r2", ModelPartBuilder.create().uv(26, 12).cuboid(-1.0F, -6.0F, -1.0F, 3.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -26.0F, -2.0F, 0.2618F, 0.0F, 0.0F));

		shards.addChild("cube_r3", ModelPartBuilder.create().uv(26, 12).cuboid(-2.0F, -6.0F, -1.0F, 3.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -17.0F, -2.0F, 0.2618F, 0.0F, 0.0F));

		shards.addChild("cube_r4", ModelPartBuilder.create().uv(26, 12).cuboid(-1.0F, -6.0F, 0.0F, 3.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -15.0F, -2.0F, 0.2618F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
		this.root.render(matrices, vertexConsumer, light, overlay, color);
	}
}