package com.ninni.snowed_over.enchantments;

import com.ninni.snowed_over.item.SnowedOverItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class HastyHoovesEnchantment extends SWEnchantment {

    public HastyHoovesEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public int getMinPower(int level) {
        return 5 + (level - 1) * 8;
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMaxPower(level) + 50;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() == SnowedOverItems.HOOF_ARMOR && super.isAcceptableItem(stack);
    }
}
