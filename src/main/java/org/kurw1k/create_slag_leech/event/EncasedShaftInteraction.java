package org.kurw1k.create_slag_leech.event;

import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.kurw1k.create_slag_leech.CreateSlagLeech;
import org.kurw1k.create_slag_leech.base.SlagEncasedShaftBlock;
import org.kurw1k.create_slag_leech.index.ModBlocks;
import org.kurw1k.create_slag_leech.index.ModItems;

@EventBusSubscriber(modid = CreateSlagLeech.MOD_ID)
public class EncasedShaftInteraction {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        ItemStack heldItem = event.getItemStack();

        // Проверяем, держит ли игрок предмет корпуса шлака
        if (heldItem.is(ModItems.SLAG_CASING_ITEM.get())) {
            ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(state.getBlock());

            // Проверяем через ID блока, что это вал из мода Create ("create:shaft")
            if ("create".equals(blockId.getNamespace()) && "shaft".equals(blockId.getPath())) {
                if (!level.isClientSide) {
                    Direction.Axis axis = state.getValue(RotatedPillarKineticBlock.AXIS);
                    BlockState encasedState = ModBlocks.SLAG_ENCASED_SHAFT.get().defaultBlockState()
                            .setValue(SlagEncasedShaftBlock.AXIS, axis);

                    level.setBlockAndUpdate(pos, encasedState);
                    level.playSound(null, pos, SoundEvents.COPPER_PLACE, SoundSource.BLOCKS, 1.0f, 1.0f);

                    if (!event.getEntity().isCreative()) {
                        heldItem.shrink(1);
                    }
                }
                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.SUCCESS);
            }
        }
    }
}