package com.ninni.snowed_over.util;

import com.ninni.snowed_over.init.SnowedOverItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;

public class SnowedOverVanillaIntegration {

    public static void init() {
        registerCompostable(SnowedOverItems.PINECONE.get(), 0.5F);
    }

    public static void registerCompostable(ItemLike itemLike, float chance) {
        ComposterBlock.COMPOSTABLES.put(itemLike, chance);
    }

}
