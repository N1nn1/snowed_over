package com.ninni.snowed_over.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.ninni.snowed_over.SnowedOver;
import com.ninni.snowed_over.entity.ReindeerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class ReindeerScreen extends AbstractContainerScreen<ReindeerScreenHandler> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(SnowedOver.MOD_ID, "textures/gui/container/reindeer.png");
    private final ReindeerEntity entity;
    private float mouseX;
    private float mouseY;

    public ReindeerScreen(ReindeerScreenHandler handler, Inventory inventory, ReindeerEntity entity) {
        super(handler, inventory, entity.getDisplayName());
        this.entity = entity;
        this.passEvents = false;
    }

    @Override
    protected void renderBg(PoseStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(matrices, i, j, 0, 0, this.imageWidth, this.imageHeight);
        if (this.entity.isSaddleable()) {
            this.blit(matrices, i + 7, j + 35 - 18, 18, this.imageHeight + 54, 18, 18);
        }
        if (this.entity.canWearArmor()) {
            this.blit(matrices, i + 7, j + 35, 0, this.imageHeight + 54, 18, 18);
        }
        InventoryScreen.renderEntityInInventory(i + 51, j + 60, 17, (float)(i + 51) - this.mouseX, (float)(j + 75 - 50) - this.mouseY, this.entity);
    }

    @Override
    public void render(PoseStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        super.render(matrices, mouseX, mouseY, delta);
        this.renderTooltip(matrices, mouseX, mouseY);
    }
}
