package com.ninni.snowed_over.enchantments;

import com.ninni.snowed_over.SnowedOver;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SnowedOverEnchantments {

    public static final HastyHoovesEnchantment HASTY_HOOVES = new HastyHoovesEnchantment();

    public static void init() {
        Registry.register(Registry.ENCHANTMENT, new Identifier(SnowedOver.MOD_ID, "hasty_hooves"), HASTY_HOOVES);
    }

}
