package org.kurw1k.create_slag_leech.index;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.kurw1k.create_slag_leech.CreateSlagLeech;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(CreateSlagLeech.MOD_ID);

    // Слиток шлака
    public static final DeferredItem<Item> SLAG_INGOT = ITEMS.register("slag_ingot",
            () -> new Item(new Item.Properties()));

    // Ведро жидкого шлака
    public static final DeferredItem<Item> LIQUID_SLAG_BUCKET = ITEMS.register("liquid_slag_bucket",
            () -> new BucketItem(ModFluids.LIQUID_SLAG_SOURCE.get(),
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    // Предметы блоков
    public static final DeferredItem<BlockItem> SLAG_BLOCK_ITEM = ITEMS.register("slag_block",
            () -> new BlockItem(ModBlocks.SLAG_BLOCK.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> SLAG_CASING_ITEM = ITEMS.register("slag_casing",
            () -> new BlockItem(ModBlocks.SLAG_CASING.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> SLAG_ENCASED_SHAFT_ITEM = ITEMS.register("slag_encased_shaft",
            () -> new BlockItem(ModBlocks.SLAG_ENCASED_SHAFT.get(), new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}