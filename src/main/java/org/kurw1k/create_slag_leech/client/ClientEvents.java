package org.kurw1k.create_slag_leech.client;

import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntityRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import org.kurw1k.create_slag_leech.index.ModBlockEntityTypes;

@EventBusSubscriber(modid = "create_slag_leech", value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(
                ModBlockEntityTypes.SLAG_ENCASED_SHAFT.get(),
                BracketedKineticBlockEntityRenderer::new
        );
    }
}