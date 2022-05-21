package com.ninni.snowed_over.init;

import com.ninni.snowed_over.SnowedOver;
import com.ninni.snowed_over.enchantments.HastyHoovesEnchantment;
import com.ninni.snowed_over.enchantments.SWEnchantment;
import com.ninni.snowed_over.item.HoofArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = SnowedOver.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SnowedOverEnchantments {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, SnowedOver.MOD_ID);

    public static final EnchantmentCategory HOOF_ARMOR = EnchantmentCategory.create("hoof_armor", item -> item instanceof HoofArmorItem);

    public static final RegistryObject<Enchantment> HASTY_HOOVES = ENCHANTMENTS.register("hasty_hooves", () -> new HastyHoovesEnchantment(Enchantment.Rarity.RARE, HOOF_ARMOR, new EquipmentSlot[]{EquipmentSlot.CHEST}));
    public static final RegistryObject<Enchantment> CLOUD_JUMPER = ENCHANTMENTS.register("cloud_jumper", () -> new SWEnchantment(Enchantment.Rarity.RARE, HOOF_ARMOR, new EquipmentSlot[]{EquipmentSlot.CHEST}));

}
