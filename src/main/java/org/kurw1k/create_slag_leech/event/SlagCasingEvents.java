package org.kurw1k.create_slag_leech.event;

import net.minecraft.core.BlockPos;
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
import org.kurw1k.create_slag_leech.index.ModBlocks;
import org.kurw1k.create_slag_leech.index.ModItems;

@EventBusSubscriber(modid = CreateSlagLeech.MOD_ID)
public class SlagCasingEvents {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        ItemStack heldItem = event.getItemStack();

        if (heldItem.is(ModItems.SLAG_INGOT.get())) {
            String blockPath = state.getBlock().builtInRegistryHolder().key().location().getPath();

            if (blockPath.contains("stripped_") && (blockPath.contains("_log") || blockPath.contains("_wood"))) {
                if (!level.isClientSide) {
                    level.setBlockAndUpdate(pos, ModBlocks.SLAG_CASING.get().defaultBlockState());
                    level.playSound(null, pos, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0f, 1.0f);

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