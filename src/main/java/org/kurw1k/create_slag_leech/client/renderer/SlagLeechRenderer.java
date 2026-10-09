package org.kurw1k.create_slag_leech.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.kurw1k.create_slag_leech.CreateSlagLeech;
import org.kurw1k.create_slag_leech.client.ClientEvents;
import org.kurw1k.create_slag_leech.client.model.SlagLeechModel;
import org.kurw1k.create_slag_leech.entity.SlagLeechEntity;

public class SlagLeechRenderer extends MobRenderer<SlagLeechEntity, SlagLeechModel<SlagLeechEntity>> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(CreateSlagLeech.MOD_ID, "textures/entity/slag_leech.png");

    public SlagLeechRenderer(EntityRendererProvider.Context context) {
        super(context, new SlagLeechModel<>(context.bakeLayer(ClientEvents.SLAG_LEECH_LAYER)), 0.4F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull SlagLeechEntity entity) {
        return TEXTURE;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void render(@NotNull SlagLeechEntity entity, float entityYaw, float partialTicks,
                       @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);

        poseStack.pushPose();
        // Поднимаем чуть выше основания лужи
        poseStack.translate(0.0D, 0.05D, 0.0D);

        long time = entity.level().getGameTime();
        float angle = (time + partialTicks) * 4.0F;

        // Вращающийся вал Create внутри моба (по оси Y)
        poseStack.pushPose();
        poseStack.translate(-0.15D, 0.0D, -0.15D);
        poseStack.mulPose(Axis.YP.rotationDegrees(angle));
        poseStack.scale(0.4F, 0.4F, 0.4F);

        BlockState shaftState = BuiltInRegistries.BLOCK
                .get(ResourceLocation.fromNamespaceAndPath("create", "shaft"))
                .defaultBlockState();

        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(
                shaftState, poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();

        // Железный самородок внутри (тоже вращается по оси Y)
        poseStack.pushPose();
        poseStack.translate(0.15D, 0.02D, 0.15D);
        poseStack.mulPose(Axis.YP.rotationDegrees(-angle * 1.5F));
        poseStack.scale(0.35F, 0.35F, 0.35F);

        Minecraft.getInstance().getItemRenderer().renderStatic(
                Items.IRON_NUGGET.getDefaultInstance(),
                ItemDisplayContext.FIXED,
                packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, entity.level(), 0);
        poseStack.popPose();

        poseStack.popPose();
    }
}