package net.toavahi.toa_am_stuff.entity.render;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.toavahi.toa_am_stuff.entity.AmGolemEntity;

public class AmGolemEntityModel<T extends AmGolemEntity> extends SinglePartEntityModel<T> {
	private final ModelPart root;
	private final ModelPart head;

	public AmGolemEntityModel(ModelPart root) {
		this.root = root.getChild("root");
		this.head = this.root.getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 20.0F, 0.0F));

		root.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-5.5F, -3.75F, -4.25F, 11.0F, 8.0F, 9.0F, new Dilation(0.0F))
		.uv(30, 38).cuboid(-1.5F, 1.25F, -5.75F, 3.0F, 4.0F, 2.0F, new Dilation(0.0F))
		.uv(14, 34).cuboid(-2.0F, -11.75F, -1.75F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-1.0F, -7.75F, -0.75F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.5F, -9.25F, -0.75F));

		root.addChild("body", ModelPartBuilder.create().uv(0, 17).cuboid(-4.5F, -2.5F, -3.5F, 9.0F, 5.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.5F, -2.5F, -0.5F));

		root.addChild("leg_left", ModelPartBuilder.create().uv(31, 0).cuboid(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 2.0F, -1.0F));

		root.addChild("leg_right", ModelPartBuilder.create().uv(32, 17).cuboid(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 2.0F, -1.0F));

		root.addChild("hand_left", ModelPartBuilder.create().uv(28, 25).cuboid(-1.5F, -4.5F, -1.0F, 3.0F, 9.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(5.5F, -1.0F, -1.0F));

		root.addChild("hand_right", ModelPartBuilder.create().uv(0, 29).cuboid(-1.5F, -4.5F, -2.0F, 3.0F, 9.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.5F, -1.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public ModelPart getPart() {
		return root;
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(headYaw, headPitch);
		this.animateMovement(AmGolemEntityAnimations.WALK, limbAngle, limbDistance, 2.0F, 2.5F);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		root.render(matrices, vertices, light, overlay, color);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
		headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);
		this.head.yaw = headYaw * (float) (Math.PI / 180.0);
		this.head.pitch = headPitch * (float) (Math.PI / 180.0);
	}
}