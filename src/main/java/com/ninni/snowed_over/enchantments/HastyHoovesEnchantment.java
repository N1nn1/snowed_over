package com.ninni.snowed_over.enchantments;

import com.ninni.snowed_over.init.SnowedOverItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
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
    public boolean canEnchant(ItemStack pStack) {
        return pStack.getItem() == SnowedOverItems.HOOF_ARMOR.get() && super.canEnchant(pStack);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

}
