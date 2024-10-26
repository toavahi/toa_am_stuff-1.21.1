package net.toavahi.toa_am_stuff.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.toavahi.toa_am_stuff.ToaAmethystStuff;

import java.util.List;

public class BeaconMirror extends Block {

    public BeaconMirror(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FacingBlock.FACING);
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
        if(!world.isClient()) {
            for (Direction dir : DIRECTIONS) {
                if(dir == state.get(FacingBlock.FACING)){
                    int i = 1;
                    BlockState newState = world.getBlockState(pos.offset(state.get(FacingBlock.FACING), 1));
                    while(newState.isAir()){
                        i++;
                        newState = world.getBlockState(pos.offset(state.get(FacingBlock.FACING), i));
                        if(newState.getBlock() == ModBlocks.BEACON_MIRROR){

                        }
                    }
                } else {

                }
            }
        }
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public static class BeamSegment {
        public final int color;
        public int length;

        public BeamSegment(int color) {
            this.color = color;
            this.length = 1;
        }
    }
}
