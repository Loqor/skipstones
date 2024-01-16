package loqor.skipstones.core;

import loqor.skipstones.Skipstones;
import loqor.skipstones.core.entities.SkippingStoneEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SkipEntityTypes {

    public static final EntityType<SkippingStoneEntity> SKIPPING_STONE_ENTITY_TYPE = FabricEntityTypeBuilder.<SkippingStoneEntity>create(SpawnGroup.MISC, SkippingStoneEntity::new)
            .dimensions(EntityDimensions.fixed(0.1f, 0.1f)).build();

    public static void initialize() {
        Registry.register(Registries.ENTITY_TYPE, new Identifier(Skipstones.MOD_ID, "skipping_stone"), SKIPPING_STONE_ENTITY_TYPE);
    }
}
