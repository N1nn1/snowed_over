package com.ninni.snowed_over;

import com.ninni.snowed_over.item.SnowedOverItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.EnchantmentPredicate;

public class SnowedOverLootTableAdditions {
    private static final float[] PINECONE_DROP_CHANCE = new float[]{0.05F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F};
    private static final LootCondition.Builder WITH_SILK_TOUCH = MatchToolLootCondition.builder(net.minecraft.predicate.item.ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, NumberRange.IntRange.atLeast(1))));
    private static final LootCondition.Builder WITHOUT_SILK_TOUCH = WITH_SILK_TOUCH.invert();
    private static final LootCondition.Builder WITH_SHEARS = MatchToolLootCondition.builder(net.minecraft.predicate.item.ItemPredicate.Builder.create().items(Items.SHEARS));
    private static final LootCondition.Builder WITH_SILK_TOUCH_OR_SHEARS = WITH_SHEARS.or(WITH_SILK_TOUCH);
    private static final LootCondition.Builder WITHOUT_SILK_TOUCH_NOR_SHEARS = WITH_SILK_TOUCH_OR_SHEARS.invert();

    static {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (id.equals(Blocks.SPRUCE_LEAVES.getLootTableId())) {
                tableBuilder.pool(LootPool.builder()
                                          .with(ItemEntry.builder(SnowedOverItems.PINECONE)
                                                         .conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS)
                                                         .alternatively(BlockLootTableGenerator.addSurvivesExplosionCondition(Blocks.SPRUCE_LEAVES, ItemEntry.builder(SnowedOverItems.PINECONE)))
                                                         .conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, PINECONE_DROP_CHANCE))));
            }
        });
    }

}
