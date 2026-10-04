package org.kurw1k.create_slag_leech.index;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.kurw1k.create_slag_leech.CreateSlagLeech;
import org.kurw1k.create_slag_leech.base.SlagBlock;
import org.kurw1k.create_slag_leech.base.SlagEncasedShaftBlock;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(net.minecraft.core.registries.Registries.BLOCK, CreateSlagLeech.MOD_ID);

    // Регистрация только блоков (предметы теперь регистрируются отдельно в ModItems)
    public static final DeferredHolder<Block, Block> SLAG_CASING = BLOCKS.register("slag_casing",
            () -> new Block(BlockBehaviour.Properties.of().destroyTime(3.0F)));

    public static final DeferredHolder<Block, Block> SLAG_BLOCK = BLOCKS.register("slag_block",
            () -> new SlagBlock(BlockBehaviour.Properties.of().destroyTime(3.0F)));

    public static final DeferredHolder<Block, SlagEncasedShaftBlock> SLAG_ENCASED_SHAFT = BLOCKS.register("slag_encased_shaft",
            () -> new SlagEncasedShaftBlock(BlockBehaviour.Properties.of().destroyTime(3.0F).explosionResistance(6.0F)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}