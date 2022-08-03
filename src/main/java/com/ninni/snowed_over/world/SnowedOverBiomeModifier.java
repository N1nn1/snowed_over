package com.ninni.snowed_over.world;

import com.mojang.serialization.Codec;
import com.ninni.snowed_over.init.SnowedOverBiomeModifiers;
import com.ninni.snowed_over.init.SnowedOverBiomeTags;
import com.ninni.snowed_over.init.SnowedOverEntityTypes;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public class SnowedOverBiomeModifier implements BiomeModifier {

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (biome.is(SnowedOverBiomeTags.PENGUIN_SPAWNS_IN)) {
            builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(SnowedOverEntityTypes.PENGUIN.get(), 5, 3, 8));
        }
        if (biome.is(SnowedOverBiomeTags.REINDEER_SPAWNS_IN)) {
            builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(SnowedOverEntityTypes.REINDEER.get(), 5, 1, 2));
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return SnowedOverBiomeModifiers.SNOWED_OVER_BIOME_MODIFIER.get();
    }
}
