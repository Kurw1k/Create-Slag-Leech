package org.kurw1k.create_slag_leech.index;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.kurw1k.create_slag_leech.CreateSlagLeech;

public class ModFluids {
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, CreateSlagLeech.MOD_ID);

    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(Registries.FLUID, CreateSlagLeech.MOD_ID);

    public static final DeferredHolder<FluidType, FluidType> LIQUID_SLAG_TYPE = FLUID_TYPES.register("liquid_slag",
            () -> new FluidType(FluidType.Properties.create()
                    .descriptionId("block.create_slag_leech.liquid_slag")
                    .fallDistanceModifier(0.0F)
                    .canExtinguish(false)
                    .density(1500)
                    .viscosity(2000)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)));

    public static final DeferredHolder<Fluid, BaseFlowingFluid.Source> LIQUID_SLAG_SOURCE = FLUIDS.register("liquid_slag",
            () -> new BaseFlowingFluid.Source(ModFluids.SLAG_FLUID_PROPERTIES));

    public static final DeferredHolder<Fluid, BaseFlowingFluid.Flowing> LIQUID_SLAG_FLOWING = FLUIDS.register("liquid_slag_flowing",
            () -> new BaseFlowingFluid.Flowing(ModFluids.SLAG_FLUID_PROPERTIES));

    public static final DeferredHolder<Block, LiquidBlock> LIQUID_SLAG_BLOCK = ModBlocks.BLOCKS.register("liquid_slag",
            () -> new LiquidBlock(LIQUID_SLAG_SOURCE.get(), BlockBehaviour.Properties.ofFullCopy(net.minecraft.world.level.block.Blocks.WATER).noCollission().noLootTable()));

    public static final BaseFlowingFluid.Properties SLAG_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(
            LIQUID_SLAG_TYPE, LIQUID_SLAG_SOURCE, LIQUID_SLAG_FLOWING)
            .bucket(ModItems.LIQUID_SLAG_BUCKET)
            .block(LIQUID_SLAG_BLOCK);

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
        FLUIDS.register(eventBus);
        eventBus.addListener(ModFluids::registerClientExtensions);
    }

    private static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerFluidType(new IClientFluidTypeExtensions() {
            private static final ResourceLocation STILL =
                    ResourceLocation.fromNamespaceAndPath(CreateSlagLeech.MOD_ID, "block/liquid_slag_still");
            private static final ResourceLocation FLOW =
                    ResourceLocation.fromNamespaceAndPath(CreateSlagLeech.MOD_ID, "block/liquid_slag_flow");

            @Override
            public @NotNull ResourceLocation getStillTexture() { return STILL; }
            @Override
            public @NotNull ResourceLocation getFlowingTexture() { return FLOW; }
            @Override
            public int getTintColor() { return 0xFFFFFFFF; }
        }, LIQUID_SLAG_TYPE.get());
    }
}