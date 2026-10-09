package org.kurw1k.create_slag_leech.index;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.kurw1k.create_slag_leech.CreateSlagLeech;
import org.kurw1k.create_slag_leech.entity.SlagLeechEntity;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, CreateSlagLeech.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<SlagLeechEntity>> SLAG_LEECH =
            ENTITY_TYPES.register("slag_leech", () ->
                    EntityType.Builder.of(SlagLeechEntity::new, MobCategory.MONSTER)
                            .sized(0.8f, 0.6f) // Размеры хитбокса моба (ширина, высота)
                            .build("slag_leech")
            );

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}