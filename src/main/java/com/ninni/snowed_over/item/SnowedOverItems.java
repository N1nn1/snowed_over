package com.ninni.snowed_over.item;

import com.ninni.snowed_over.SnowedOver;
import com.ninni.snowed_over.block.SnowedOverBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unused")
public class SnowedOverItems {
    public static final Item PINECONE = register("pinecone", new PineconeItem(new FabricItemSettings().group(ItemGroup.MISC).food(new FoodComponent.Builder().hunger(2).saturationModifier(0.0F).build())));
    public static final Item COMPACTED_SNOW_BRICK = register("compacted_snow_brick", new Item(new FabricItemSettings().group(ItemGroup.MISC)));
    public static final Item COMPACTED_SNOW_BRICKS = register("compacted_snow_bricks", new BlockItem(SnowedOverBlocks.COMPACTED_SNOW_BRICKS, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item COMPACTED_SNOW_BRICK_STAIRS = register("compacted_snow_brick_stairs", new BlockItem(SnowedOverBlocks.COMPACTED_SNOW_BRICK_STAIRS, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item COMPACTED_SNOW_BRICK_SLAB = register("compacted_snow_brick_slab", new BlockItem(SnowedOverBlocks.COMPACTED_SNOW_BRICK_SLAB, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

    private static Item register(String id, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(SnowedOver.MOD_ID, id), item);
    }

    static {
        CompostingChanceRegistry composting = CompostingChanceRegistry.INSTANCE;
        composting.add(PINECONE, 0.5F);
        FuelRegistry.INSTANCE.add(PINECONE, 600);
    }
}


