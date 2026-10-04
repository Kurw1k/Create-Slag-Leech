package org.kurw1k.create_slag_leech;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.kurw1k.create_slag_leech.client.ClientEvents;
import org.kurw1k.create_slag_leech.index.*;

@Mod(CreateSlagLeech.MOD_ID)
public class CreateSlagLeech {
    public static final String MOD_ID = "create_slag_leech";

    public CreateSlagLeech(IEventBus modEventBus) {
        ModFluids.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntityTypes.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);

        // Подписка на клиентские события регистрации рендереров
        modEventBus.addListener(ClientEvents::registerRenderers);
    }
}