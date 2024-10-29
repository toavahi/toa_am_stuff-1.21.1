package net.toavahi.toa_am_stuff.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.toavahi.toa_am_stuff.ToaAmethystStuff;

public class ModEntities {
    public static final EntityType<AmGolemEntity> AM_GOLEM_ENTITY = register("am_golem_entity",
        EntityType.Builder.<AmGolemEntity>create(AmGolemEntity::new, SpawnGroup.MISC)
                .dimensions(0.8F, 0.8F));
    public static final EntityType<AmGrenadeEntity> AM_GRENADE_ENTITY = register("am_grenade",
            EntityType.Builder.<AmGrenadeEntity>create(AmGrenadeEntity::new, SpawnGroup.MISC)
                    .dimensions(0.25F, 0.25F));

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, Identifier.of(ToaAmethystStuff.MOD_ID, id), type.build(id));
    }
}
