package com.ninni.snowed_over.enchantments;

import com.ninni.snowed_over.SnowedOver;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class SnowedOverEnchantments {

    public static final Enchantment HASTY_HOOVES = register("hasty_hooves", new HastyHoovesEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.WEARABLE, new EquipmentSlot[]{EquipmentSlot.CHEST}));
    public static final Enchantment CLOUD_JUMPER = register("cloud_jumper", new SWEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.WEARABLE, new EquipmentSlot[]{EquipmentSlot.CHEST}));

    private static Enchantment register(String id, Enchantment enchantment) {
        return Registry.register(Registry.ENCHANTMENT, new ResourceLocation(SnowedOver.MOD_ID, id), enchantment);
    }

}
