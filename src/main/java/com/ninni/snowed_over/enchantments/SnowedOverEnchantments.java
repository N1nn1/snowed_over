package com.ninni.snowed_over.enchantments;

import com.ninni.snowed_over.SnowedOver;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SnowedOverEnchantments {

    public static final Enchantment HASTY_HOOVES = register("hasty_hooves", new HastyHoovesEnchantment());

    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registry.ENCHANTMENT, new Identifier(SnowedOver.MOD_ID, name), enchantment);
    }

}
