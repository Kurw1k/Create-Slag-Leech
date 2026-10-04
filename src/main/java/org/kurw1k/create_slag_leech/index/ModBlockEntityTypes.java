package org.kurw1k.create_slag_leech.index;

import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.kurw1k.create_slag_leech.CreateSlagLeech;

public class ModBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, CreateSlagLeech.MOD_ID);

    @SuppressWarnings("DataFlowIssue")
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BracketedKineticBlockEntity>> SLAG_ENCASED_SHAFT =
            BLOCK_ENTITIES.register("slag_encased_shaft",
                    () -> BlockEntityType.Builder.<BracketedKineticBlockEntity>of(
                            (pos, state) -> new BracketedKineticBlockEntity(ModBlockEntityTypes.SLAG_ENCASED_SHAFT.get(), pos, state),
                            ModBlocks.SLAG_ENCASED_SHAFT.get()
                    ).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}