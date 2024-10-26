package net.toavahi.toa_am_stuff.block;

import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.toavahi.toa_am_stuff.ToaAmethystStuff;
import net.toavahi.toa_am_stuff.sound.ModSounds;

public class ModBlocks {
    public static final Block BEACON_MIRROR = registerBlock("be_mirror", new BeaconMirror(AbstractBlock.Settings.copy(Blocks.GLASS)));
    public static final Block AM_COPPER_BLOCK = registerBlock("am_copper_block", new Block(AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK).sounds(ModSounds.AM_COPPER_BLOCK_SOUNDS)));
    public static final Block AM_COPPER_STAIRS = registerBlock("am_copper_stairs",
            new StairsBlock(AM_COPPER_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(AM_COPPER_BLOCK)));
    public static final Block AM_COPPER_SLAB = registerBlock("am_copper_slab",
            new SlabBlock(AbstractBlock.Settings.copy(AM_COPPER_BLOCK)));
    public static final Block AM_COPPER_WALL = registerBlock("am_copper_wall",
            new WallBlock(AbstractBlock.Settings.copy(AM_COPPER_BLOCK)));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(ToaAmethystStuff.MOD_ID, name), block);
    }
    public static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(ToaAmethystStuff.MOD_ID, name), new BlockItem(block, new Item.Settings()));
    }
    public static void registerBlocks(){
        ToaAmethystStuff.LOGGER.info("register blocks " + ToaAmethystStuff.MOD_ID);
    }
}
