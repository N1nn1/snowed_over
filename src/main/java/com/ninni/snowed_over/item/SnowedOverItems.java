package com.ninni.snowed_over.item;

import com.ninni.snowed_over.block.SnowedOverBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import static com.ninni.snowed_over.SnowedOver.*;

@SuppressWarnings("unused")
public class SnowedOverItems {
    public static final Item SNOWED_OVER = register("snowed_over", new Item(new FabricItemSettings().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));

    public static final Item PINECONE = register("pinecone", new PineconeItem(new FabricItemSettings().tab(ITEM_GROUP).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.0F).build())));

    public static final Item COMPACTED_SNOW_BRICK = register("compacted_snow_brick", new Item(new FabricItemSettings().tab(ITEM_GROUP)));
    public static final Item COMPACTED_SNOW_BRICKS = register("compacted_snow_bricks", new BlockItem(SnowedOverBlocks.COMPACTED_SNOW_BRICKS, new FabricItemSettings().tab(ITEM_GROUP)));
    public static final Item COMPACTED_SNOW_BRICK_STAIRS = register("compacted_snow_brick_stairs", new BlockItem(SnowedOverBlocks.COMPACTED_SNOW_BRICK_STAIRS, new FabricItemSettings().tab(ITEM_GROUP)));
    public static final Item COMPACTED_SNOW_BRICK_SLAB = register("compacted_snow_brick_slab", new BlockItem(SnowedOverBlocks.COMPACTED_SNOW_BRICK_SLAB, new FabricItemSettings().tab(ITEM_GROUP)));
    public static final Item COMPACTED_SNOW_FOUNDATION = register("compacted_snow_foundation", new BlockItem(SnowedOverBlocks.COMPACTED_SNOW_FOUNDATION, new FabricItemSettings().tab(ITEM_GROUP)));

    public static final Item HOOF_ARMOR = register("hoof_armor", new HoofArmorItem(new FabricItemSettings().stacksTo(1).tab(ITEM_GROUP)));

    private static Item register(String id, Item item) {
        return Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, id), item);
    }

    static {
        CompostingChanceRegistry composting = CompostingChanceRegistry.INSTANCE;
        composting.add(PINECONE, 0.5F);
        FuelRegistry.INSTANCE.add(PINECONE, 600);
    }
}


