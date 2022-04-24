package com.ninni.snowed_over.client.model.entity;

import com.google.common.collect.ImmutableList;
import com.ninni.snowed_over.entity.PenguinEntity;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.util.math.MathHelper;

@SuppressWarnings("FieldCanBeLocal, unused")
public class PenguinEntityModel<T extends PenguinEntity> extends AnimalModel<PenguinEntity> {
    private final ModelPart root;

    private final ModelPart body;
    private final ModelPart leftWing;
    private final ModelPart rightWing;
    private final ModelPart tail;
    private final ModelPart head;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;


    public PenguinEntityModel(ModelPart root) {
        super(false, 5.0F, 0.0F);
        this.root = root;

        this.body         = root.getChild("body");
        this.head         = root.getChild("head");
        this.leftLeg      = root.getChild("leftLeg");
        this.rightLeg     = root.getChild("rightLeg");

        this.leftWing     = body.getChild("leftWing");
        this.rightWing    = body.getChild("rightWing");
        this.tail         = body.getChild("tail");
    }

    public static TexturedModelData getTexturedModelData() {

        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        ModelPartData body = root.addChild(
            "body",
            ModelPartBuilder.create()
                            .uv(0, 0)
                            .mirrored(false)
                            .cuboid(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, 19.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData leftWing = body.addChild(
            "leftWing",
            ModelPartBuilder.create()
                            .uv(26, 10)
                            .mirrored(false)
                            .cuboid(0.0F, 0.0F, -3.0F, 1.0F, 7.0F, 6.0F, new Dilation(0.0F)),
            ModelTransform.of(4.0F, -4.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData rightWing = body.addChild(
            "rightWing",
            ModelPartBuilder.create()
                            .uv(26, 10)
                            .mirrored(true)
                            .cuboid(-1.0F, 0.0F, -3.0F, 1.0F, 7.0F, 6.0F, new Dilation(0.0F)),
            ModelTransform.of(-4.0F, -4.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData tail = body.addChild(
            "tail",
            ModelPartBuilder.create()
                            .uv(13, 30)
                            .mirrored(false)
                            .cuboid(-2.0F, -0.5F, 0.0F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, 2.5F, 4.0F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData head = root.addChild(
            "head",
            ModelPartBuilder.create()
                            .uv(0, 16)
                            .mirrored(false)
                            .cuboid(-4.0F, -6.0F, -4.0F, 8.0F, 6.0F, 8.0F, new Dilation(0.0F))
                            .uv(24, 4)
                            .mirrored(false)
                            .cuboid(-2.0F, -2.0F, -6.0F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, 15.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData leftLeg = root.addChild(
            "leftLeg",
            ModelPartBuilder.create()
                            .uv(0, 30)
                            .mirrored(false)
                            .cuboid(-2.0F, 0.0F, -3.5F, 4.0F, 1.0F, 5.0F, new Dilation(0.0F)),
            ModelTransform.of(2.5F, 23.0F, 0.5F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData rightLeg = root.addChild(
            "rightLeg",
            ModelPartBuilder.create()
                            .uv(0, 30)
                            .mirrored(true)
                            .cuboid(-2.0F, 0.0F, -3.5F, 4.0F, 1.0F, 5.0F, new Dilation(0.0F)),
            ModelTransform.of(-2.5F, 23.0F, 0.5F, 0.0F, 0.0F, 0.0F)
        );

        return TexturedModelData.of(data, 48, 48);
    }

    @Override
    public void animateModel(PenguinEntity entity, float limbAngle, float limbDistance, float tickDelta) {
        super.animateModel(entity, limbAngle, limbDistance, tickDelta);

        if (entity.WingsFlapTicks != 0) {
            this.leftWing.roll = MathHelper.cos((float)entity.age + tickDelta * 1.5F) * 0.2F - 0.2F;
            this.rightWing.roll = MathHelper.cos((float)entity.age + tickDelta * 1.5F) * -0.2F + 0.2F;
        }
        else { this.leftWing.roll = 0.0F; this.rightWing.roll = 0.0F; }
    }

    @Override
    public void setAngles(PenguinEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        limbDistance = MathHelper.clamp(limbDistance, -0.45F, 0.45F);
        float speed = 1.0f;
        float degree = 1.0f;
        head.pitch = headPitch * ((float) Math.PI / 180f);
        head.yaw = headYaw * ((float) Math.PI / 180f);
    }


    @Override
    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.body, this.leftLeg, this.rightLeg);
    }
}
