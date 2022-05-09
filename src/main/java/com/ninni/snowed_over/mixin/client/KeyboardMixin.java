package com.ninni.snowed_over.mixin.client;

import com.ninni.snowed_over.enchantments.SnowedOverEnchantments;
import com.ninni.snowed_over.entity.ReindeerEntity;
import com.ninni.snowed_over.item.SnowedOverItems;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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
        if (player.getVehicle() instanceof ReindeerEntity reindeerEntity) {
            ItemStack stack = reindeerEntity.getEquippedStack(EquipmentSlot.CHEST);
            if (!reindeerEntity.isOnGround() && stack.isOf(SnowedOverItems.HOOF_ARMOR) && EnchantmentHelper.getLevel(SnowedOverEnchantments.CLOUD_JUMPER, stack) > 0) {
                if (key == 32) {
                    Vec3d velocity = reindeerEntity.getVelocity();
                    reindeerEntity.setVelocityClient(velocity.x, velocity.y + 1.0D, velocity.z);
                }
            }
        }
    }

}
