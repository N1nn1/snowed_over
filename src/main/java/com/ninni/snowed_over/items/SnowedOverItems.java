package com.ninni.snowed_over.items;

import com.ninni.snowed_over.SnowedOver;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unused")
public class SnowedOverItems {
    public static final Item PINECONE = register("pinecone", new PineconeItem(new FabricItemSettings().group(ItemGroup.MISC).food(new FoodComponent.Builder().hunger(2).saturationModifier(0.0F).build())));

    private static Item register(String id, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(SnowedOver.MOD_ID, id), item);
    }

    static {
        CompostingChanceRegistry composting = CompostingChanceRegistry.INSTANCE;
        composting.add(PINECONE, 0.5F);
        FuelRegistry.INSTANCE.add(PINECONE, 600);
    }
}


