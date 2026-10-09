package org.kurw1k.create_slag_leech.client;

import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntityRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import org.kurw1k.create_slag_leech.CreateSlagLeech;
import org.kurw1k.create_slag_leech.client.model.SlagLeechModel;
import org.kurw1k.create_slag_leech.client.renderer.SlagLeechRenderer;
import org.kurw1k.create_slag_leech.index.ModBlockEntityTypes;
import org.kurw1k.create_slag_leech.index.ModEntityTypes;

@EventBusSubscriber(modid = CreateSlagLeech.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {

    // Слой модели для моба Slag Leech
    public static final ModelLayerLocation SLAG_LEECH_LAYER =
            new ModelLayerLocation(
                    ResourceLocation.fromNamespaceAndPath(CreateSlagLeech.MOD_ID, "slag_leech"), "main");

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SLAG_LEECH_LAYER, SlagLeechModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // Регистрация рендерера для зашитого вала
        event.registerBlockEntityRenderer(
                ModBlockEntityTypes.SLAG_ENCASED_SHAFT.get(),
                BracketedKineticBlockEntityRenderer::new
        );

        // Регистрация рендерера для нашего моба
        event.registerEntityRenderer(
                ModEntityTypes.SLAG_LEECH.get(),
                SlagLeechRenderer::new
        );
    }
}