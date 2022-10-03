package com.ninni.snowed_over;

import com.ninni.snowed_over.enchantments.SnowedOverEnchantments;
import com.ninni.snowed_over.item.SnowedOverItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.AlternativeLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class SnowedOverLootTableAdditions {
    private static final float[] PINECONE_DROP_CHANCE = new float[]{0.05F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F};
    private static final LootItemCondition.Builder WITH_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    private static final LootItemCondition.Builder WITH_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));

    static {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (id.equals(Blocks.SPRUCE_LEAVES.getLootTable())) {
                tableBuilder.withPool(LootPool.lootPool()
                                          .add(LootItem.lootTableItem(SnowedOverItems.PINECONE)
                                                         .when(ExplosionCondition.survivesExplosion())
                                                         .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, PINECONE_DROP_CHANCE)))
                                          .when(InvertedLootItemCondition.invert(AlternativeLootItemCondition.alternative(WITH_SHEARS, WITH_SILK_TOUCH))));
            }
            if (id.equals(BuiltInLootTables.IGLOO_CHEST)) {
                tableBuilder.withPool(LootPool.lootPool()
                                          .add(LootItem.lootTableItem(Items.BOOK)
                                                         .setWeight(10)
                                                         .apply(EnchantRandomlyFunction.randomEnchantment().withEnchantment(SnowedOverEnchantments.CLOUD_JUMPER).withEnchantment(SnowedOverEnchantments.HASTY_HOOVES)))
                                          .add(LootItem.lootTableItem(SnowedOverItems.COMPACTED_SNOW_BRICK)
                                                         .setWeight(3)
                                                         .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 16))))
                                          .add(LootItem.lootTableItem(Items.SADDLE)
                                                         .setWeight(2)
                                                         .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1))))
                                          .add(LootItem.lootTableItem(Items.GLOW_LICHEN)
                                                         .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8))))

                );
            }
            if (id.equals(BuiltInLootTables.VILLAGE_SNOWY_HOUSE)) {
                tableBuilder.withPool(LootPool.lootPool()
                                          .add(LootItem.lootTableItem(SnowedOverItems.COMPACTED_SNOW_BRICK)
                                                         .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                                          .add(LootItem.lootTableItem(SnowedOverItems.PINECONE)
                                                         .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1))))

                );
            }
            if (id.equals(BuiltInLootTables.VILLAGE_TAIGA_HOUSE)) {
                tableBuilder.withPool(LootPool.lootPool()
                                          .add(LootItem.lootTableItem(SnowedOverItems.PINECONE)
                                                         .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 3))))

                );
            }
        });
    }

}
