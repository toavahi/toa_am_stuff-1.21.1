package net.toavahi.toa_am_stuff.block.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.toavahi.toa_am_stuff.ToaAmethystStuff;
import net.toavahi.toa_am_stuff.block.ModBlocks;

public class ModBlockEntities {
    /*public static final BlockEntityType<AmBeamBlockEntity> AM_BEAM = create(
                "am_beam", BlockEntityType.Builder.create(AmBeamBlockEntity::new, ModBlocks.AM_BEAM).build(null));*/

    public static <T extends BlockEntity> BlockEntityType<T> create(String id, BlockEntityType<T> builder) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(ToaAmethystStuff.MOD_ID, id), builder);
    }
    public static void registerModEntities(){}
}
