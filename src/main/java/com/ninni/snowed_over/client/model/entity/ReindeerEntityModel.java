package com.ninni.snowed_over.client.model.entity;

import com.google.common.collect.ImmutableList;
import com.ninni.snowed_over.entity.ReindeerEntity;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

@SuppressWarnings("FieldCanBeLocal, unused")
public class ReindeerEntityModel<T extends ReindeerEntity> extends AgeableListModel<ReindeerEntity> {
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

    public static LayerDefinition getTexturedModelData() {

        MeshDefinition data = new MeshDefinition();
        PartDefinition root = data.getRoot();

        PartDefinition head = root.addOrReplaceChild(
            "head",
            CubeListBuilder.create()
                            .texOffs(0, 28)
                            .mirror(false)
                            .addBox(-3.5F, -16.0F, -5.0F, 7.0F, 19.0F, 6.0F, new CubeDeformation(0.0F))
                            .texOffs(44, 28)
                            .mirror(false)
                            .addBox(-3.5F, -16.0F, -5.0F, 7.0F, 19.0F, 6.0F, new CubeDeformation(0.25F))
                            .texOffs(0, 0)
                            .mirror(false)
                            .addBox(-2.5F, -14.0F, -9.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 53)
                            .mirror(false)
                            .addBox(-2.5F, -14.0F, -9.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.25F)),
            PartPose.offsetAndRotation(0.0F, 7.0F, -6.0F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition leftRein = head.addOrReplaceChild(
            "leftRein",
            CubeListBuilder.create()
                            .texOffs(0, 72)
                            .mirror(false)
                            .addBox(0.0F, -0.5F, 0.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(3.5F, -12.5F, 1.0F, -0.3927F, 0.0F, 0.0F)
        );

        PartDefinition rightRein = head.addOrReplaceChild(
            "rightRein",
            CubeListBuilder.create()
                            .texOffs(0, 72)
                            .mirror(true)
                            .addBox(0.0F, -0.5F, 0.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(-3.5F, -12.5F, 1.0F, -0.3927F, 0.0F, 0.0F)
        );

        PartDefinition leftAntler = head.addOrReplaceChild(
            "leftAntler",
            CubeListBuilder.create()
                            .texOffs(0, 8)
                            .mirror(false)
                            .addBox(-1.0F, -10.0F, 0.0F, 6.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(2.5F, -16.0F, -2.0F, 0.0F, -0.3927F, 0.0F)
        );

        PartDefinition rightAntler = head.addOrReplaceChild(
            "rightAntler",
            CubeListBuilder.create()
                            .texOffs(0, 8)
                            .mirror(true)
                            .addBox(-5.0F, -10.0F, 0.0F, 6.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(-2.5F, -16.0F, -2.0F, 0.0F, 0.3927F, 0.0F)
        );

        PartDefinition leftEar = head.addOrReplaceChild(
            "leftEar",
            CubeListBuilder.create()
                            .texOffs(38, 7)
                            .mirror(false)
                            .addBox(0.0F, -0.5F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(3.5F, -14.5F, -1.5F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition rightEar = head.addOrReplaceChild(
            "rightEar",
            CubeListBuilder.create()
                            .texOffs(38, 7)
                            .mirror(true)
                            .addBox(-3.0F, -0.5F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(-3.5F, -14.5F, -1.5F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition body = root.addOrReplaceChild(
            "body",
            CubeListBuilder.create()
                            .texOffs(0, 0)
                            .mirror(false)
                            .addBox(-5.0F, -3.0F, -10.0F, 10.0F, 10.0F, 18.0F, new CubeDeformation(0.0F))
                            .texOffs(24, 52)
                            .mirror(false)
                            .addBox(-5.0F, -3.0F, -10.0F, 10.0F, 10.0F, 18.0F, new CubeDeformation(0.5F)),
            PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition tail = body.addOrReplaceChild(
            "tail",
            CubeListBuilder.create()
                            .texOffs(38, 10)
                            .mirror(false)
                            .addBox(-2.0F, -1.0F, 0.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(0.0F, -1.0F, 8.0F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition rightLeg = root.addOrReplaceChild(
            "rightLeg",
            CubeListBuilder.create()
                            .texOffs(26, 28)
                            .mirror(true)
                            .addBox(-2.0F, 0.0F, -2.5F, 4.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(-3.0F, 12.0F, 5.5F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition leftLeg = root.addOrReplaceChild(
            "leftLeg",
            CubeListBuilder.create()
                            .texOffs(26, 28)
                            .mirror(false)
                            .addBox(-2.0F, 0.0F, -2.5F, 4.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(3.0F, 12.0F, 5.5F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition rightArm = root.addOrReplaceChild(
            "rightArm",
            CubeListBuilder.create()
                            .texOffs(26, 28)
                            .mirror(true)
                            .addBox(-2.0F, 0.0F, -2.5F, 4.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(-3.0F, 12.0F, -4.5F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition leftArm = root.addOrReplaceChild(
            "leftArm",
            CubeListBuilder.create()
                            .texOffs(26, 28)
                            .mirror(false)
                            .addBox(-2.0F, 0.0F, -2.5F, 4.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(2.5F, 12.0F, -4.5F, 0.0F, 0.0F, 0.0F)
        );

        return LayerDefinition.create(data, 80, 80);
    }

    @Override
    public void prepareMobModel(ReindeerEntity entity, float limbAngle, float limbDistance, float tickDelta) {
        super.prepareMobModel(entity, limbAngle, limbDistance, tickDelta);
        float angryAnim = entity.getStandAnim(tickDelta);

        this.head.y = 7.0F;
        this.head.z = -6.0F;
        this.head.xRot = 0.0F;
        this.body.xRot = 0.0F;
        this.leftArm.y = 12.0F;
        this.rightArm.y = 12.0F;
        this.leftArm.z = -4.5F;
        this.rightArm.z = -4.5F;
        if (!entity.canCloudJump()) this.head.xRot = angryAnim + (1.0F - angryAnim) * this.head.xRot;
        else this.head.xRot = angryAnim * 0.5F + (1.0F - angryAnim) * this.head.xRot;
        this.body.xRot = angryAnim * -0.8F + (1.0F - angryAnim) * this.body.xRot;
        this.head.y = angryAnim * -1.0F + (1.0F - angryAnim) * this.head.y;
        this.head.z = angryAnim * -3F + (1.0F - angryAnim) * this.head.z;
        this.leftArm.y = angryAnim * 4F + (1.0F - angryAnim) * this.leftArm.y;
        this.rightArm.y = angryAnim * 4F + (1.0F - angryAnim) * this.rightArm.y;
        this.leftArm.z = angryAnim * -8F + (1.0F - angryAnim) * this.leftArm.z;
        this.rightArm.z = angryAnim * -8F + (1.0F - angryAnim) * this.rightArm.z;
        if (entity.tailCounter != 0 && !entity.isVehicle()) {
            this.head.zRot = Mth.cos((float)entity.tickCount + tickDelta * 0.8F) * 0.2F;
        } else {
            this.head.zRot = 0.0F;
        }
    }

    @Override
    public void setupAnim(ReindeerEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        limbDistance = Mth.clamp(limbDistance, -0.65F, 0.65F);
        float speed = 1.0f;
        float degree = 1.0f;

        leftRein.visible = false;
        rightRein.visible = false;
        head.xRot += headPitch * ((float) Math.PI / 180f);
        head.yRot = headYaw * ((float) Math.PI / 180f);
        rightLeg.xRot = Mth.cos(limbAngle * speed * 0.6F) * 1.4F * limbDistance;
        leftLeg.xRot = Mth.cos(limbAngle * speed * 0.6F + (float)Math.PI) * 1.4F * limbDistance;
        rightArm.xRot = Mth.cos(limbAngle * speed * 0.6F + (float)Math.PI) * 1.4F * limbDistance;
        leftArm.xRot = Mth.cos(limbAngle * speed * 0.6F) * 1.4F * limbDistance;

        if (entity.canCloudJump()) {
            rightLeg.xRot = Mth.cos(limbAngle * speed * 0.3F) * 0.35F * limbDistance + 0.75F;
            leftLeg.xRot = Mth.sin(limbAngle * speed * 0.3F) * 0.35F * limbDistance + 0.75F;
            rightArm.xRot = Mth.sin(limbAngle * speed * 0.3F) * 0.35F * limbDistance + 0.5F;
            leftArm.xRot = Mth.cos(limbAngle * speed * 0.3F) * 0.35F * limbDistance + 0.5F;
            if (entity.isVehicle()) head.xRot += Mth.cos(limbAngle * speed * 0.2F) * 0.1 * limbDistance + 0.5F;
        }

        if (entity.isVehicle()) {
            leftRein.visible = true;
            rightRein.visible = true;
            head.xRot += Mth.cos(limbAngle * speed * 0.2F) * 0.1 * limbDistance;
        }
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body, this.leftArm, this.leftLeg, this.rightArm, this.rightLeg);
    }

}
