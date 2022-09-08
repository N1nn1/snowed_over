package com.ninni.snowed_over.entity;

import com.ninni.snowed_over.SnowedOverTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.levelgen.Heightmap;

import static com.ninni.snowed_over.SnowedOver.*;

public class SnowedOverEntities {
    public static final EntityType<ReindeerEntity> REINDEER = register(
        "reindeer",
        FabricEntityTypeBuilder.createMob()
                               .entityFactory(ReindeerEntity::new)
                               .defaultAttributes(ReindeerEntity::createReindeerAttributes)
                               .spawnGroup(MobCategory.CREATURE)
                               .spawnRestriction(SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE_WG, ReindeerEntity::canSpawn)
                               .dimensions(EntityDimensions.scalable(1.3F, 1.6F))
                               .trackRangeBlocks(8),
        new int[]{ 0x5c392d, 0xdacabc }
    );
    public static final EntityType<PenguinEntity> PENGUIN = register(
        "penguin",
        FabricEntityTypeBuilder.createMob()
                               .entityFactory(PenguinEntity::new)
                               .defaultAttributes(PenguinEntity::createPenguinAttributes)
                               .spawnGroup(MobCategory.CREATURE)
                               .spawnRestriction(SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE_WG, PenguinEntity::canSpawn)
                               .dimensions(EntityDimensions.scalable(0.55F, 0.9F))
                               .trackRangeBlocks(8),
        new int[]{ 0x292929, 0xfff089 }
    );

    static {
        BiomeModifications.addSpawn(BiomeSelectors.tag(SnowedOverTags.PENGUIN_SPAWNS_IN), MobCategory.CREATURE, SnowedOverEntities.PENGUIN, 5, 3, 8);
        BiomeModifications.addSpawn(BiomeSelectors.tag(ConventionalBiomeTags.CLIMATE_COLD), MobCategory.CREATURE, SnowedOverEntities.REINDEER, 5, 1, 2);
    }


    @SuppressWarnings("unchecked")
    private static <T extends Entity> EntityType<T> register(String id, EntityType<T> entityType, int[] spawnEggColors) {
        if (spawnEggColors != null)
            Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, id + "_spawn_egg"), new SpawnEggItem((EntityType<? extends Mob>) entityType, spawnEggColors[0], spawnEggColors[1], new Item.Properties().stacksTo(64).tab(ITEM_GROUP)));

        return Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, id), entityType);
    }

    private static <T extends Entity> EntityType<T> register(String id, FabricEntityTypeBuilder<T> entityType, int[] spawnEggColors) {
        return register(id, entityType.build(), spawnEggColors);
    }

}
