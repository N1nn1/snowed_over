package com.ninni.snowed_over.mixin;

import com.google.common.collect.Lists;
import com.ninni.snowed_over.enchantments.SnowedOverEnchantments;
import com.ninni.snowed_over.item.HoofArmorItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    @Inject(at = @At("HEAD"), method = "getPossibleEntries", cancellable = true)
    private static void SO$getPossibleEntries(int power, ItemStack stack, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
        ArrayList<EnchantmentLevelEntry> list = Lists.newArrayList();
        Enchantment[] hoofEnchantments = new Enchantment[]{ SnowedOverEnchantments.CLOUD_JUMPER, SnowedOverEnchantments.HASTY_HOOVES, Enchantments.FROST_WALKER };
        if (stack.getItem() instanceof HoofArmorItem) {
            for (Enchantment enchantment : hoofEnchantments) {
                for (int i = enchantment.getMaxLevel(); i > enchantment.getMinLevel(); i--) {
                    if (power < enchantment.getMinPower(i) || power > enchantment.getMaxPower(i)) continue;
                    list.add(new EnchantmentLevelEntry(enchantment, i));
                    cir.setReturnValue(list);
                }
            }
        }
    }

}
