package org.kurw1k.create_slag_leech;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import org.kurw1k.create_slag_leech.client.ClientEvents;
import org.kurw1k.create_slag_leech.entity.SlagLeechEntity;
import org.kurw1k.create_slag_leech.index.*;

@Mod(CreateSlagLeech.MOD_ID)
public class CreateSlagLeech {
    public static final String MOD_ID = "create_slag_leech";

    public CreateSlagLeech(IEventBus modEventBus) {
        // Регистрация реестров мода
        ModFluids.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntityTypes.register(modEventBus);
        ModEntityTypes.register(modEventBus); // <--- Регистрация сущностей
        ModCreativeModeTabs.register(modEventBus);

        // Регистрация атрибутов мобов
        modEventBus.addListener(this::registerAttributes);

        // Подписка на клиентские события рендереров
        modEventBus.addListener(ClientEvents::registerRenderers);
    }

    private void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.SLAG_LEECH.get(), SlagLeechEntity.createAttributes().build());
    }
}