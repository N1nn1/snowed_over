package com.ninni.snowed_over.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class HastyHoovesEnchantment extends SWEnchantment {

    public HastyHoovesEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public int getMinCost(int pLevel) {
        return 5 + (pLevel - 1) * 8;
    }

    @Override
    public int getMaxCost(int pLevel) {
        return super.getMaxCost(pLevel) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

}
