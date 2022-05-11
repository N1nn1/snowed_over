package com.ninni.snowed_over.enchantments;

import com.ninni.snowed_over.SnowedOver;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SnowedOverEnchantments {

    public static final Enchantment HASTY_HOOVES = register("hasty_hooves", new HastyHoovesEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.WEARABLE, new EquipmentSlot[]{EquipmentSlot.CHEST}));
    public static final Enchantment CLOUD_JUMPER = register("cloud_jumper", new SWEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.WEARABLE, new EquipmentSlot[]{EquipmentSlot.CHEST}));

    private static Enchantment register(String id, Enchantment enchantment) {
        return Registry.register(Registry.ENCHANTMENT, new Identifier(SnowedOver.MOD_ID, id), enchantment);
    }

}
