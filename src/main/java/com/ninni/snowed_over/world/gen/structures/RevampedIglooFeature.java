package com.ninni.snowed_over.world.gen.structures;

import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.PostPlacementProcessor;
import net.minecraft.structure.StructureGeneratorFactory;
import net.minecraft.structure.StructurePiecesGenerator;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.Optional;

public class RevampedIglooFeature extends StructureFeature<StructurePoolFeatureConfig> {

    public RevampedIglooFeature() {
        super(StructurePoolFeatureConfig.CODEC, RevampedIglooFeature::createPiecesGenerator, PostPlacementProcessor.EMPTY);
    }

    public static Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> createPiecesGenerator(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {
        ChunkPos chunkPos = context.chunkPos();
        BlockPos midPos = context.chunkPos().getCenterAtY(0);
        int i = context.chunkGenerator().getHeightOnGround(midPos.getX(), midPos.getZ(), Heightmap.Type.WORLD_SURFACE_WG, context.world());
        BlockPos blockPos = new BlockPos(chunkPos.getStartX(), i, chunkPos.getStartZ());
        Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> structurePiecesGenerator = StructurePoolBasedGenerator.generate(context, PoolStructurePiece::new, blockPos, false, false);
        return structurePiecesGenerator;
    }

}
