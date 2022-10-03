package com.ninni.snowed_over.block;

import com.ninni.snowed_over.sound.SnowedOverBlockSoundGroups;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import static com.ninni.snowed_over.SnowedOver.MOD_ID;

public class SnowedOverBlocks {

    public static final Block COMPACTED_SNOW_BRICKS = register("compacted_snow_bricks", new Block(BlockBehaviour.Properties.of(Material.SNOW).requiresCorrectToolForDrops().strength(0.4F).sound(SnowedOverBlockSoundGroups.COMPACTED_SNOW)));
    public static final Block COMPACTED_SNOW_BRICK_STAIRS = register("compacted_snow_brick_stairs", new StairBlock(COMPACTED_SNOW_BRICKS.defaultBlockState(), FabricBlockSettings.copyOf(COMPACTED_SNOW_BRICKS)));
    public static final Block COMPACTED_SNOW_BRICK_SLAB = register("compacted_snow_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(COMPACTED_SNOW_BRICKS)));
    public static final Block COMPACTED_SNOW_FOUNDATION = register("compacted_snow_foundation", new Block(FabricBlockSettings.copyOf(COMPACTED_SNOW_BRICKS)));

    private static Block register(String id, Block block) {
        return Registry.register(Registry.BLOCK, new ResourceLocation(MOD_ID, id), block);
    }
}
