package org.kurw1k.create_slag_leech.index;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.kurw1k.create_slag_leech.CreateSlagLeech;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateSlagLeech.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SLAG_TAB = CREATIVE_MODE_TABS.register("slag_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.create_slag_leech.slag_tab"))
                    .icon(() -> new ItemStack(ModItems.SLAG_INGOT.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.SLAG_INGOT.get());
                        output.accept(ModItems.LIQUID_SLAG_BUCKET.get());
                        output.accept(ModItems.SLAG_BLOCK_ITEM.get());
                        output.accept(ModItems.SLAG_CASING_ITEM.get());
                        output.accept(ModItems.SLAG_ENCASED_SHAFT_ITEM.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}