package com.ninni.snowed_over.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class HoofArmorItem extends Item {

    public HoofArmorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return true;
    }

    @Override
    public int getEnchantmentValue() {
        return 1;
    }

}
