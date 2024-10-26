package net.toavahi.toa_am_stuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.toavahi.toa_am_stuff.ToaAmethystStuff;
import net.toavahi.toa_am_stuff.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.AM_COPPER_BLOCK)
                .add(ModBlocks.AM_COPPER_WALL)
                .add(ModBlocks.AM_COPPER_SLAB)
                .add(ModBlocks.AM_COPPER_STAIRS);
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.AM_COPPER_BLOCK)
                .add(ModBlocks.AM_COPPER_STAIRS)
                .add(ModBlocks.AM_COPPER_SLAB)
                .add(ModBlocks.AM_COPPER_WALL);
        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.AM_COPPER_WALL);
        getOrCreateTagBuilder(BlockTags.STAIRS)
                .add(ModBlocks.AM_COPPER_STAIRS);
        getOrCreateTagBuilder(BlockTags.SLABS)
                .add(ModBlocks.AM_COPPER_SLAB);
    }
}
