package com.ninni.snowed_over.init;

import com.ninni.snowed_over.SnowedOver;
import com.ninni.snowed_over.item.HoofArmorItem;
import com.ninni.snowed_over.item.PineconeItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.ninni.snowed_over.SnowedOver.MOD_ID;

@Mod.EventBusSubscriber(modid = SnowedOver.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SnowedOverItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Item> PINECONE = ITEMS.register("pinecone", () -> new PineconeItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.0F).build())));
    public static final RegistryObject<Item> COMPACTED_SNOW_BRICK = ITEMS.register("compacted_snow_brick", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> HOOF_ARMOR = ITEMS.register("hoof_armor", () -> new HoofArmorItem(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION)));
    public static final RegistryObject<Item> PENGUIN_SPAWN_EGG = ITEMS.register("penguin_spawn_egg", () -> new ForgeSpawnEggItem(SnowedOverEntityTypes.PENGUIN, 0x292929, 0xfff089, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> REINDEER_SPAWN_EGG = ITEMS.register("reindeer_spawn_egg", () -> new ForgeSpawnEggItem(SnowedOverEntityTypes.REINDEER, 0x5c392d, 0xdacabc, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

}


