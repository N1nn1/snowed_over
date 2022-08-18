package com.ninni.snowed_over.init;

import com.ninni.snowed_over.block.VerticalSlabBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.ninni.snowed_over.SnowedOver.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SnowedOverBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    public static final RegistryObject<Block> COMPACTED_SNOW_BRICKS = BLOCKS.register("compacted_snow_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.SNOW).requiresCorrectToolForDrops().strength(0.4F).sound(SnowedOverSoundEvents.COMPACTED_SNOW)));
    public static final RegistryObject<Block> COMPACTED_SNOW_BRICK_STAIRS = BLOCKS.register("compacted_snow_brick_stairs", () -> new StairBlock(COMPACTED_SNOW_BRICKS.get().defaultBlockState(), Properties.copy(COMPACTED_SNOW_BRICKS.get())));
    public static final RegistryObject<Block> COMPACTED_SNOW_BRICK_SLAB = BLOCKS.register("compacted_snow_brick_slab", () -> new SlabBlock(Properties.copy(COMPACTED_SNOW_BRICKS.get())));
    public static final RegistryObject<Block> COMPACTED_SNOW_BRICK_VERTICAL_SLAB = BLOCKS.register("compacted_snow_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.copy(COMPACTED_SNOW_BRICKS.get())));
    public static final RegistryObject<Block> COMPACTED_SNOW_FOUNDATION = BLOCKS.register("compacted_snow_foundation", () -> new Block(Properties.copy(COMPACTED_SNOW_BRICKS.get())));

}
