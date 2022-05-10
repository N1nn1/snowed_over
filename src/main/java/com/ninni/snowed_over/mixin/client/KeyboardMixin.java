package com.ninni.snowed_over.mixin.client;

import com.ninni.snowed_over.entity.ReindeerEntity;
import net.minecraft.block.Blocks;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Inject(at = @At("HEAD"), method = "onKey")
    private void SO$onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        MinecraftClient instance = MinecraftClient.getInstance();
        PlayerEntity player = instance.player;
        if (instance.currentScreen != null) return;
        if (player == null) return;
        if (player.getVehicle() instanceof ReindeerEntity reindeer) {
            if (!reindeer.isOnGround() && reindeer.hasCloudJumper(reindeer.getEquippedStack(EquipmentSlot.CHEST))) {
                if (key == 32 && !reindeer.world.getBlockState(reindeer.getBlockPos().down(64)).isOf(Blocks.AIR) && reindeer.world.getBlockState(reindeer.getBlockPos().down(3)).isOf(Blocks.AIR)) {
                    Vec3d velocity = reindeer.getVelocity();
                    reindeer.setVelocityClient(velocity.x, velocity.y + 0.35D, velocity.z);
                }
            }
        }
    }

}
