package com.ninni.snowed_over.events;

import com.ninni.snowed_over.SnowedOver;
import com.ninni.snowed_over.init.SnowedOverEnchantments;
import com.ninni.snowed_over.init.SnowedOverItems;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SnowedOver.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MiscEvents {
    private static final float[] PINECONE_DROP_CHANCE = new float[]{0.05F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F};
    private static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    private static final LootItemCondition.Builder HAS_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));

    //TODO: PINECONE TO SPRUCE LEAVES LOOT TABLE

    @SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event) {
        LootTable tableBuilder = event.getTable();
        ResourceLocation id = event.getName();
        if (id.equals(BuiltInLootTables.IGLOO_CHEST)) {
            tableBuilder.addPool(LootPool.lootPool()
                    .add(LootItem.lootTableItem(Items.BOOK)
                            .apply(EnchantRandomlyFunction.randomEnchantment().withEnchantment(SnowedOverEnchantments.CLOUD_JUMPER.get()).withEnchantment(SnowedOverEnchantments.HASTY_HOOVES.get()).withEnchantment(Enchantments.FROST_WALKER)))
                    .add(LootItem.lootTableItem(SnowedOverItems.COMPACTED_SNOW_BRICK.get())
                            .setWeight(3)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 16))))
                    .add(LootItem.lootTableItem(Items.SADDLE)
                            .setWeight(2)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1))))
                    .add(LootItem.lootTableItem(Items.GLOW_LICHEN)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8))))

                    .build()
            );
        }
        if (id.equals(BuiltInLootTables.VILLAGE_SNOWY_HOUSE)) {
            tableBuilder.addPool(LootPool.lootPool()
                    .add(LootItem.lootTableItem(SnowedOverItems.COMPACTED_SNOW_BRICK.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                    .add(LootItem.lootTableItem(SnowedOverItems.PINECONE.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1))))
                    .build()
            );
        }
        if (id.equals(BuiltInLootTables.VILLAGE_TAIGA_HOUSE)) {
            tableBuilder.addPool(LootPool.lootPool()
                    .add(LootItem.lootTableItem(SnowedOverItems.PINECONE.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 3))))
                    .build()
            );
        }
    }

    @SubscribeEvent
    public void onFuelBurn(FurnaceFuelBurnTimeEvent event) {
        if (event.getItemStack().getItem() == SnowedOverItems.PINECONE.get()) {
            event.setBurnTime(600);
        }
    }

}
