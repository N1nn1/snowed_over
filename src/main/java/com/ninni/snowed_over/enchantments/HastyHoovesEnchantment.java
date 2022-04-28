package com.ninni.snowed_over.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class HastyHoovesEnchantment extends Enchantment {

    public HastyHoovesEnchantment() {
        super(Rarity.COMMON, EnchantmentTarget.WEARABLE, new EquipmentSlot[]{EquipmentSlot.CHEST});
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

}
