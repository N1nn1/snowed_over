package com.ninni.snowed_over.mixin;

import com.google.common.collect.Lists;
import com.ninni.snowed_over.init.SnowedOverItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    @Inject(at = @At("HEAD"), method = "getAvailableEnchantmentResults", cancellable = true)
    private static void SO$getPossibleEntries(int power, ItemStack stack, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentInstance>> cir) {
        ArrayList<EnchantmentInstance> list = Lists.newArrayList();
        Enchantment enchantment = Enchantments.FROST_WALKER;
        if (stack.getItem() == SnowedOverItems.HOOF_ARMOR.get()) {
            for (int i = enchantment.getMaxLevel(); i > enchantment.getMinLevel(); i--) {
                if (power < enchantment.getMinCost(i) || power > enchantment.getMaxCost(i)) continue;
                list.add(new EnchantmentInstance(enchantment, i));
                cir.setReturnValue(list);
            }
        }
    }

}
