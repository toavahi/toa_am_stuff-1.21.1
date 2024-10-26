package net.toavahi.toa_am_stuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;
import net.toavahi.toa_am_stuff.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.AM_COPPER_BLOCK);
        addDrop(ModBlocks.AM_COPPER_STAIRS);
        addDrop(ModBlocks.AM_COPPER_SLAB);
        addDrop(ModBlocks.AM_COPPER_WALL);
    }
}
