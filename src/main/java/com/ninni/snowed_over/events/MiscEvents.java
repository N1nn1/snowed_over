package com.ninni.snowed_over.events;

import com.ninni.snowed_over.SnowedOver;
import com.ninni.snowed_over.init.SnowedOverEnchantments;
import com.ninni.snowed_over.init.SnowedOverItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SnowedOver.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MiscEvents {

    //TODO: PINECONE TO SPRUCE LEAVES LOOT TABLE

    @SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event) {
        LootTable table = event.getTable();
        ResourceLocation name = event.getName();
        if (name.equals(BuiltInLootTables.IGLOO_CHEST)) {
            table.addPool(LootPool.lootPool().add(LootItem.lootTableItem(Items.BOOK).setWeight(5).apply((new EnchantRandomlyFunction.Builder()).withEnchantment(SnowedOverEnchantments.CLOUD_JUMPER.get()).withEnchantment(SnowedOverEnchantments.HASTY_HOOVES.get()))).add(LootItem.lootTableItem(SnowedOverItems.COMPACTED_SNOW_BRICK.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 16.0F)))).setRolls(UniformGenerator.between(0.0F, 2.0F)).add(LootItem.lootTableItem(SnowedOverItems.COMPACTED_SNOW_BRICK.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))).add(LootItem.lootTableItem(Items.SADDLE).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))).build());
        }
        if (name.equals(BuiltInLootTables.VILLAGE_SNOWY_HOUSE)) {
            table.addPool(LootPool.lootPool().setRolls(UniformGenerator.between(0.0F, 2.0F)).add(LootItem.lootTableItem(SnowedOverItems.COMPACTED_SNOW_BRICK.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))).add(LootItem.lootTableItem(SnowedOverItems.PINECONE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))).build());
        }
        if (name.equals(BuiltInLootTables.VILLAGE_TAIGA_HOUSE)) {
            table.addPool(LootPool.lootPool().add(LootItem.lootTableItem(SnowedOverItems.PINECONE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))).build());
        }
    }

    @SubscribeEvent
    public void onFuelBurn(FurnaceFuelBurnTimeEvent event) {
        if (event.getItemStack().getItem() == SnowedOverItems.PINECONE.get()) {
            event.setBurnTime(600);
        }
    }

}
