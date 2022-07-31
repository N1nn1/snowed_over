package com.ninni.snowed_over.entity;

import com.ninni.snowed_over.SnowedOverTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;

import static com.ninni.snowed_over.SnowedOver.*;

public class SnowedOverEntities {
    public static final EntityType<ReindeerEntity> REINDEER = register(
        "reindeer",
        FabricEntityTypeBuilder.createMob()
                               .entityFactory(ReindeerEntity::new)
                               .defaultAttributes(ReindeerEntity::createReindeerAttributes)
                               .spawnGroup(SpawnGroup.CREATURE)
                               .spawnRestriction(SpawnRestriction.Location.ON_GROUND, Heightmap.Type.WORLD_SURFACE_WG, ReindeerEntity::canSpawn)
                               .dimensions(EntityDimensions.changing(1.0F, 1.2F))
                               .trackRangeBlocks(8),
        new int[]{ 0x5c392d, 0xdacabc }
    );
    public static final EntityType<PenguinEntity> PENGUIN = register(
        "penguin",
        FabricEntityTypeBuilder.createMob()
                               .entityFactory(PenguinEntity::new)
                               .defaultAttributes(PenguinEntity::createPenguinAttributes)
                               .spawnGroup(SpawnGroup.CREATURE)
                               .spawnRestriction(SpawnRestriction.Location.ON_GROUND, Heightmap.Type.WORLD_SURFACE_WG, PenguinEntity::canSpawn)
                               .dimensions(EntityDimensions.changing(0.55F, 0.9F))
                               .trackRangeBlocks(8),
        new int[]{ 0x292929, 0xfff089 }
    );

    static {
        BiomeModifications.addSpawn(BiomeSelectors.tag(SnowedOverTags.PENGUIN_SPAWNS_IN), SpawnGroup.CREATURE, SnowedOverEntities.PENGUIN, 5, 3, 8);
        BiomeModifications.addSpawn(BiomeSelectors.tag(ConventionalBiomeTags.CLIMATE_COLD), SpawnGroup.CREATURE, SnowedOverEntities.REINDEER, 5, 1, 2);
    }


    @SuppressWarnings("unchecked")
    private static <T extends Entity> EntityType<T> register(String id, EntityType<T> entityType, int[] spawnEggColors) {
        if (spawnEggColors != null)
            Registry.register(Registry.ITEM, new Identifier(MOD_ID, id + "_spawn_egg"), new SpawnEggItem((EntityType<? extends MobEntity>) entityType, spawnEggColors[0], spawnEggColors[1], new Item.Settings().maxCount(64).group(ITEM_GROUP)));

        return Registry.register(Registry.ENTITY_TYPE, new Identifier(MOD_ID, id), entityType);
    }

    private static <T extends Entity> EntityType<T> register(String id, FabricEntityTypeBuilder<T> entityType, int[] spawnEggColors) {
        return register(id, entityType.build(), spawnEggColors);
    }

}
