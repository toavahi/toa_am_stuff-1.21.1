package net.toavahi.toa_am_stuff.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.toavahi.toa_am_stuff.util.ModDataComponents;

import java.util.Random;

public class AmChiselItem extends Item {
    public AmChiselItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        ItemStack stack = context.getStack();
        if(world.isClient()){
            return ActionResult.PASS;
        } else {
            BlockState state = world.getBlockState(pos);
            if (state.isOf(Blocks.BUDDING_AMETHYST) && stack.get(ModDataComponents.AM_CHISEL)) {
                world.playSound(null, pos, SoundEvents.BLOCK_AMETHYST_BLOCK_STEP,
                        SoundCategory.BLOCKS, 0.25F, 1F);
                for (float i = 0; i < 1.1; i += 0.5F) {
                    for (float j = 0; j < 1.1; j += 0.5F) {
                        for (float l = 0; l < 1.1; l += 0.5F) {
                            ((ServerWorld) world).spawnParticles(ParticleTypes.REVERSE_PORTAL,
                                    pos.getX() + i,
                                    pos.getY() + j,
                                    pos.getZ() + l,
                                    1, 0, 0, 0, 0);
                        }
                    }
                }
                world.breakBlock(context.getBlockPos(), false);
                context.getPlayer().giveItemStack(new ItemStack(Blocks.BUDDING_AMETHYST));
                stack.set(ModDataComponents.AM_CHISEL, false);
                context.getPlayer().incrementStat(Stats.USED.getOrCreateStat(this));
            } else {
                Random random = new Random();
                int rand = random.nextInt(9);
                boolean check = false;
                for (int i = 0; i < 9; ++i) {
                    if (context.getPlayer().getInventory().getStack(i).getItem() instanceof BlockItem) {
                        check = true;
                    }
                }
                if (check) {
                    while (!(context.getPlayer().getInventory().getStack(rand).getItem() instanceof BlockItem item)) {
                        rand = random.nextInt(9);
                    }
                    if(item.useOnBlock(new ItemUsageContext(context.getPlayer(), context.getHand(),
                            new BlockHitResult(context.getHitPos().offset(context.getSide(), 1),
                                    context.getSide(), context.getBlockPos().offset(context.getSide()), false))).isAccepted()) {
                        BlockSoundGroup soundGroup = item.getBlock().getDefaultState().getSoundGroup();
                        world.playSound(null, context.getBlockPos(),
                                soundGroup.getPlaceSound(), SoundCategory.BLOCKS,
                                (soundGroup.getVolume() + 1.0F) / 2.0F,
                                soundGroup.getPitch() * 0.8F);
                        world.emitGameEvent(GameEvent.BLOCK_PLACE, context.getBlockPos(),
                                GameEvent.Emitter.of(context.getPlayer(), item.getBlock().getDefaultState()));
                        context.getPlayer().incrementStat(Stats.USED.getOrCreateStat(this));
                    }
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if(otherStack.getItem() == Items.AMETHYST_SHARD && !stack.get(ModDataComponents.AM_CHISEL)){
            otherStack.decrementUnlessCreative(1, player);
            stack.set(ModDataComponents.AM_CHISEL, true);
            return true;
        }
        return false;
    }

    public boolean canPlace(ItemUsageContext context, BlockState state) {
        PlayerEntity playerEntity = context.getPlayer();
        ShapeContext shapeContext = playerEntity == null ? ShapeContext.absent() : ShapeContext.of(playerEntity);
        return state.canPlaceAt(context.getWorld(), context.getBlockPos())
                && context.getWorld().canPlace(state, context.getBlockPos(), shapeContext);
    }
}
