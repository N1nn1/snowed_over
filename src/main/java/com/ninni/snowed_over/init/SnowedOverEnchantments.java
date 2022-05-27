package com.ninni.snowed_over.init;

import com.ninni.snowed_over.SnowedOver;
import com.ninni.snowed_over.enchantments.HastyHoovesEnchantment;
import com.ninni.snowed_over.enchantments.SWEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = SnowedOver.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SnowedOverEnchantments {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, SnowedOver.MOD_ID);

    public static final RegistryObject<Enchantment> HASTY_HOOVES = ENCHANTMENTS.register("hasty_hooves", HastyHoovesEnchantment::new);
    public static final RegistryObject<Enchantment> CLOUD_JUMPER = ENCHANTMENTS.register("cloud_jumper", SWEnchantment::new);

}
