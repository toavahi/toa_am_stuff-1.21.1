package net.toavahi.toa_am_stuff.mixin;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LightningRodBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.toavahi.toa_am_stuff.block.ModBlocks;
import net.toavahi.toa_am_stuff.entity.AmGolemEntity;
import net.toavahi.toa_am_stuff.entity.ModEntities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningRodBlock.class)
public class LightningRodMixin {
    @Inject(method = "onBlockAdded", at = @At(value = "HEAD"))
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify, CallbackInfo ci){
        if(!oldState.isOf(state.getBlock()) && world.getBlockState(pos.down()).getBlock() == ModBlocks.AM_COPPER_BLOCK){
            AmGolemEntity amGolemEntity = ModEntities.AM_GOLEM_ENTITY.create(world);
            if (amGolemEntity != null) {
                world.breakBlock(pos, false);
                world.breakBlock(pos.down(), false);
                amGolemEntity.refreshPositionAndAngles((double)pos.getX() + 0.5, (double)pos.getY() - 0.95, (double)pos.getZ() + 0.5, 0.0F, 0.0F);
                PlayerEntity owner = world.getClosestPlayer(amGolemEntity, 6);
                if(owner != null) amGolemEntity.setOwner(owner);
                world.spawnEntity(amGolemEntity);
                for (ServerPlayerEntity serverPlayerEntity : world.getNonSpectatingEntities(ServerPlayerEntity.class, amGolemEntity.getBoundingBox().expand(5.0))) {
                    Criteria.SUMMONED_ENTITY.trigger(serverPlayerEntity, amGolemEntity);
                }
                world.updateNeighbors(pos, Blocks.AIR);
                world.updateNeighbors(pos.down(), Blocks.AIR);
            }
        }
    }
}
