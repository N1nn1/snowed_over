package com.ninni.snowed_over.util;

import com.ninni.snowed_over.init.SnowedOverItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class SnowedOverCreativeModeTab extends CreativeModeTab {

    public SnowedOverCreativeModeTab(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(SnowedOverItems.SNOWED_OVER.get());
    }
}
