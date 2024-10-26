package net.toavahi.toa_am_stuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.toavahi.toa_am_stuff.block.ModBlocks;
import net.toavahi.toa_am_stuff.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool amCopperPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.AM_COPPER_BLOCK);
        amCopperPool.slab(ModBlocks.AM_COPPER_SLAB);
        amCopperPool.stairs(ModBlocks.AM_COPPER_STAIRS);
        amCopperPool.wall(ModBlocks.AM_COPPER_WALL);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.AM_TRIANGLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.AM_GRENADE, Models.GENERATED);
        itemModelGenerator.register(ModItems.AM_DUST, Models.GENERATED);
        itemModelGenerator.register(ModItems.AM_COPPER_INGOT, Models.GENERATED);

    }


}
