package com.ninni.snowed_over.client.model.entity;

import com.google.common.collect.ImmutableList;
import com.ninni.snowed_over.entity.ReindeerEntity;
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
public class ReindeerEntityModel<T extends ReindeerEntity> extends AnimalModel<ReindeerEntity> {
    private final ModelPart root;

    private final ModelPart head;
    private final ModelPart leftRein;
    private final ModelPart rightRein;
    private final ModelPart leftAntler;
    private final ModelPart rightAntler;
    private final ModelPart leftEar;
    private final ModelPart rightEar;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart rightArm;
    private final ModelPart leftArm;

    public ReindeerEntityModel(ModelPart root) {
        this.root = root;

        this.body         = root.getChild("body");
        this.rightLeg     = root.getChild("rightLeg");
        this.leftLeg      = root.getChild("leftLeg");
        this.leftArm      = root.getChild("leftArm");
        this.rightArm     = root.getChild("rightArm");
        this.head         = root.getChild("head");

        this.tail         = body.getChild("tail");

        this.rightAntler  = head.getChild("rightAntler");
        this.leftAntler   = head.getChild("leftAntler");
        this.leftRein     = head.getChild("leftRein");
        this.rightRein    = head.getChild("rightRein");
        this.leftEar      = head.getChild("leftEar");
        this.rightEar     = head.getChild("rightEar");

    }

    public static TexturedModelData getTexturedModelData() {

        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        ModelPartData head = root.addChild(
            "head",
            ModelPartBuilder.create()
                            .uv(0, 28)
                            .mirrored(false)
                            .cuboid(-3.5F, -16.0F, -5.0F, 7.0F, 19.0F, 6.0F, new Dilation(0.0F))
                            .uv(44, 28)
                            .mirrored(false)
                            .cuboid(-3.5F, -16.0F, -5.0F, 7.0F, 19.0F, 6.0F, new Dilation(0.25F))
                            .uv(0, 0)
                            .mirrored(false)
                            .cuboid(-2.5F, -14.0F, -9.0F, 5.0F, 4.0F, 4.0F, new Dilation(0.0F))
                            .uv(0, 53)
                            .mirrored(false)
                            .cuboid(-2.5F, -14.0F, -9.0F, 5.0F, 4.0F, 4.0F, new Dilation(0.25F)),
            ModelTransform.of(0.0F, 7.0F, -6.0F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData leftRein = head.addChild(
            "leftRein",
            ModelPartBuilder.create()
                            .uv(0, 72)
                            .mirrored(false)
                            .cuboid(0.0F, -0.5F, 0.0F, 0.0F, 2.0F, 6.0F, new Dilation(0.0F)),
            ModelTransform.of(3.5F, -12.5F, 1.0F, -0.3927F, 0.0F, 0.0F)
        );

        ModelPartData rightRein = head.addChild(
            "rightRein",
            ModelPartBuilder.create()
                            .uv(0, 72)
                            .mirrored(true)
                            .cuboid(0.0F, -0.5F, 0.0F, 0.0F, 2.0F, 6.0F, new Dilation(0.0F)),
            ModelTransform.of(-3.5F, -12.5F, 1.0F, -0.3927F, 0.0F, 0.0F)
        );

        ModelPartData leftAntler = head.addChild(
            "leftAntler",
            ModelPartBuilder.create()
                            .uv(0, 8)
                            .mirrored(false)
                            .cuboid(-1.0F, -10.0F, 0.0F, 6.0F, 10.0F, 0.0F, new Dilation(0.0F)),
            ModelTransform.of(2.5F, -16.0F, -2.0F, 0.0F, -0.3927F, 0.0F)
        );

        ModelPartData rightAntler = head.addChild(
            "rightAntler",
            ModelPartBuilder.create()
                            .uv(0, 8)
                            .mirrored(true)
                            .cuboid(-5.0F, -10.0F, 0.0F, 6.0F, 10.0F, 0.0F, new Dilation(0.0F)),
            ModelTransform.of(-2.5F, -16.0F, -2.0F, 0.0F, 0.3927F, 0.0F)
        );

        ModelPartData leftEar = head.addChild(
            "leftEar",
            ModelPartBuilder.create()
                            .uv(38, 7)
                            .mirrored(false)
                            .cuboid(0.0F, -0.5F, -0.5F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)),
            ModelTransform.of(3.5F, -14.5F, -1.5F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData rightEar = head.addChild(
            "rightEar",
            ModelPartBuilder.create()
                            .uv(38, 7)
                            .mirrored(true)
                            .cuboid(-3.0F, -0.5F, -0.5F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)),
            ModelTransform.of(-3.5F, -14.5F, -1.5F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData body = root.addChild(
            "body",
            ModelPartBuilder.create()
                            .uv(0, 0)
                            .mirrored(false)
                            .cuboid(-5.0F, -3.0F, -10.0F, 10.0F, 10.0F, 18.0F, new Dilation(0.0F))
                            .uv(24, 52)
                            .mirrored(false)
                            .cuboid(-5.0F, -3.0F, -10.0F, 10.0F, 10.0F, 18.0F, new Dilation(0.5F)),
            ModelTransform.of(0.0F, 5.0F, 2.0F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData tail = body.addChild(
            "tail",
            ModelPartBuilder.create()
                            .uv(38, 10)
                            .mirrored(false)
                            .cuboid(-2.0F, -1.0F, 0.0F, 4.0F, 5.0F, 2.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, -1.0F, 8.0F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData rightLeg = root.addChild(
            "rightLeg",
            ModelPartBuilder.create()
                            .uv(26, 28)
                            .mirrored(true)
                            .cuboid(-2.0F, 0.0F, -2.5F, 4.0F, 12.0F, 5.0F, new Dilation(0.0F)),
            ModelTransform.of(-3.0F, 12.0F, 5.5F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData leftLeg = root.addChild(
            "leftLeg",
            ModelPartBuilder.create()
                            .uv(26, 28)
                            .mirrored(false)
                            .cuboid(-2.0F, 0.0F, -2.5F, 4.0F, 12.0F, 5.0F, new Dilation(0.0F)),
            ModelTransform.of(3.0F, 12.0F, 5.5F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData rightArm = root.addChild(
            "rightArm",
            ModelPartBuilder.create()
                            .uv(26, 28)
                            .mirrored(true)
                            .cuboid(-2.0F, 0.0F, -2.5F, 4.0F, 12.0F, 5.0F, new Dilation(0.0F)),
            ModelTransform.of(-3.0F, 12.0F, -4.5F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData leftArm = root.addChild(
            "leftArm",
            ModelPartBuilder.create()
                            .uv(26, 28)
                            .mirrored(false)
                            .cuboid(-2.0F, 0.0F, -2.5F, 4.0F, 12.0F, 5.0F, new Dilation(0.0F)),
            ModelTransform.of(2.5F, 12.0F, -4.5F, 0.0F, 0.0F, 0.0F)
        );

        return TexturedModelData.of(data, 80, 80);
    }

    @Override
    public void animateModel(ReindeerEntity entity, float limbAngle, float limbDistance, float tickDelta) {
        super.animateModel(entity, limbAngle, limbDistance, tickDelta);
        float angryAnim = entity.getAngryAnimationProgress(tickDelta);

        this.head.pivotY = 7.0F;
        this.head.pivotZ = -6.0F;
        this.head.pitch = 0.0F;
        this.body.pitch = 0.0F;
        this.leftArm.pivotY = 12.0F;
        this.rightArm.pivotY = 12.0F;
        this.leftArm.pivotZ = -4.5F;
        this.rightArm.pivotZ = -4.5F;
        if (!entity.canCloudJump()) this.head.pitch = angryAnim + (1.0F - angryAnim) * this.head.pitch;
        else this.head.pitch = angryAnim * 0.5F + (1.0F - angryAnim) * this.head.pitch;
        this.body.pitch = angryAnim * -0.8F + (1.0F - angryAnim) * this.body.pitch;
        this.head.pivotY = angryAnim * -1.0F + (1.0F - angryAnim) * this.head.pivotY;
        this.head.pivotZ = angryAnim * -3F + (1.0F - angryAnim) * this.head.pivotZ;
        this.leftArm.pivotY = angryAnim * 4F + (1.0F - angryAnim) * this.leftArm.pivotY;
        this.rightArm.pivotY = angryAnim * 4F + (1.0F - angryAnim) * this.rightArm.pivotY;
        this.leftArm.pivotZ = angryAnim * -8F + (1.0F - angryAnim) * this.leftArm.pivotZ;
        this.rightArm.pivotZ = angryAnim * -8F + (1.0F - angryAnim) * this.rightArm.pivotZ;
        if (entity.tailWagTicks != 0 && !entity.hasPassengers()) {
            this.head.roll = MathHelper.cos((float)entity.age + tickDelta * 0.8F) * 0.2F;
        } else {
            this.head.roll = 0.0F;
        }
    }

    @Override
    public void setAngles(ReindeerEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        limbDistance = MathHelper.clamp(limbDistance, -0.65F, 0.65F);
        float speed = 1.0f;
        float degree = 1.0f;

        leftRein.visible = false;
        rightRein.visible = false;
        head.pitch += headPitch * ((float) Math.PI / 180f);
        head.yaw = headYaw * ((float) Math.PI / 180f);
        rightLeg.pitch = MathHelper.cos(limbAngle * speed * 0.6F) * 1.4F * limbDistance;
        leftLeg.pitch = MathHelper.cos(limbAngle * speed * 0.6F + (float)Math.PI) * 1.4F * limbDistance;
        rightArm.pitch = MathHelper.cos(limbAngle * speed * 0.6F + (float)Math.PI) * 1.4F * limbDistance;
        leftArm.pitch = MathHelper.cos(limbAngle * speed * 0.6F) * 1.4F * limbDistance;

        if (entity.canCloudJump()) {
            rightLeg.pitch = MathHelper.cos(limbAngle * speed * 0.3F) * 0.35F * limbDistance + 0.75F;
            leftLeg.pitch = MathHelper.sin(limbAngle * speed * 0.3F) * 0.35F * limbDistance + 0.75F;
            rightArm.pitch = MathHelper.sin(limbAngle * speed * 0.3F) * 0.35F * limbDistance + 0.5F;
            leftArm.pitch = MathHelper.cos(limbAngle * speed * 0.3F) * 0.35F * limbDistance + 0.5F;
            if (entity.hasPassengers()) head.pitch += MathHelper.cos(limbAngle * speed * 0.2F) * 0.1 * limbDistance + 0.5F;
        }

        if (entity.hasPassengers()) {
            leftRein.visible = true;
            rightRein.visible = true;
            head.pitch += MathHelper.cos(limbAngle * speed * 0.2F) * 0.1 * limbDistance;
        }
    }


    @Override
    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.body, this.leftArm, this.leftLeg, this.rightArm, this.rightLeg);
    }
}
