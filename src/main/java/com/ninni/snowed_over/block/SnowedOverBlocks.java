package com.ninni.snowed_over.block;

import com.ninni.snowed_over.sound.SnowedOverBlockSoundGroups;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.ninni.snowed_over.SnowedOver.MOD_ID;

public class SnowedOverBlocks {

    public static final Block COMPACTED_SNOW_BRICKS = register("compacted_snow_bricks", new Block(AbstractBlock.Settings.of(Material.SNOW_BLOCK).requiresTool().strength(0.4F).sounds(SnowedOverBlockSoundGroups.COMPACTED_SNOW)));
    public static final Block COMPACTED_SNOW_BRICK_STAIRS = register("compacted_snow_brick_stairs", new StairsBlock(COMPACTED_SNOW_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(COMPACTED_SNOW_BRICKS)));
    public static final Block COMPACTED_SNOW_BRICK_SLAB = register("compacted_snow_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(COMPACTED_SNOW_BRICKS)));
    public static final Block COMPACTED_SNOW_FOUNDATION = register("compacted_snow_foundation", new Block(FabricBlockSettings.copyOf(COMPACTED_SNOW_BRICKS)));

    private static Block register(String id, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(MOD_ID, id), block);
    }
}
