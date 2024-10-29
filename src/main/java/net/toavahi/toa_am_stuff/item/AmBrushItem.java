package net.toavahi.toa_am_stuff.item;

import net.minecraft.block.*;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.block.enums.BedPart;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.potion.Potions;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.DyeColor;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.toavahi.toa_am_stuff.ToaAmethystStuff;
import net.toavahi.toa_am_stuff.util.ModDataComponents;

import java.util.Arrays;

public class AmBrushItem extends BrushItem {
    Block[] concrete = {Blocks.BLACK_CONCRETE, Blocks.RED_CONCRETE, Blocks.YELLOW_CONCRETE,
    Blocks.BLUE_CONCRETE, Blocks.PURPLE_CONCRETE, Blocks.LIGHT_BLUE_CONCRETE, Blocks.LIGHT_GRAY_CONCRETE,
    Blocks.ORANGE_CONCRETE, Blocks.MAGENTA_CONCRETE, Blocks.BROWN_CONCRETE, Blocks.PINK_CONCRETE,
    Blocks.LIME_CONCRETE, Blocks.CYAN_CONCRETE, Blocks.GRAY_CONCRETE, Blocks.GREEN_CONCRETE};
    public AmBrushItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if(!world.isClient()) {
            DyeColor color = floatToColor(context.getStack().get(ModDataComponents.COLOR));
            BlockPos pos = context.getBlockPos();
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();
            Block result = Blocks.AIR; //so it's filled
            boolean b = true;
            if (context.getStack().get(ModDataComponents.COLOR) != 0) {
                if (context.getStack().get(ModDataComponents.COLOR) != 1F) {
                    if (block == Blocks.GLASS) {
                        switch (color) {
                            case BLACK -> result = Blocks.BLACK_STAINED_GLASS;
                            case BLUE -> result = Blocks.BLUE_STAINED_GLASS;
                            case BROWN -> result = Blocks.BROWN_STAINED_GLASS;
                            case CYAN -> result = Blocks.CYAN_STAINED_GLASS;
                            case GRAY -> result = Blocks.GRAY_STAINED_GLASS;
                            case GREEN -> result = Blocks.GREEN_STAINED_GLASS;
                            case LIGHT_BLUE -> result = Blocks.LIGHT_BLUE_STAINED_GLASS;
                            case LIGHT_GRAY -> result = Blocks.LIGHT_GRAY_STAINED_GLASS;
                            case WHITE -> result = Blocks.WHITE_STAINED_GLASS;
                            case RED -> result = Blocks.RED_STAINED_GLASS;
                            case LIME -> result = Blocks.LIME_STAINED_GLASS;
                            case PINK -> result = Blocks.PINK_STAINED_GLASS;
                            case ORANGE -> result = Blocks.ORANGE_STAINED_GLASS;
                            case MAGENTA -> result = Blocks.MAGENTA_STAINED_GLASS;
                            case PURPLE -> result = Blocks.PURPLE_STAINED_GLASS;
                            case YELLOW -> result = Blocks.YELLOW_STAINED_GLASS;
                        }
                        world.setBlockState(pos, result.getDefaultState());
                        state.updateNeighbors(world, pos, Block.NOTIFY_ALL);
                    } else if (block == Blocks.GLASS_PANE) {
                        switch (color) {
                            case YELLOW -> result = Blocks.YELLOW_STAINED_GLASS_PANE;
                            case BLACK -> result = Blocks.BLACK_STAINED_GLASS_PANE;
                            case BLUE -> result = Blocks.BLUE_STAINED_GLASS_PANE;
                            case BROWN -> result = Blocks.BROWN_STAINED_GLASS_PANE;
                            case CYAN -> result = Blocks.CYAN_STAINED_GLASS_PANE;
                            case GRAY -> result = Blocks.GRAY_STAINED_GLASS_PANE;
                            case GREEN -> result = Blocks.GREEN_STAINED_GLASS_PANE;
                            case LIGHT_BLUE -> result = Blocks.LIGHT_BLUE_STAINED_GLASS_PANE;
                            case LIGHT_GRAY -> result = Blocks.LIGHT_GRAY_STAINED_GLASS_PANE;
                            case WHITE -> result = Blocks.WHITE_STAINED_GLASS_PANE;
                            case RED -> result = Blocks.RED_STAINED_GLASS_PANE;
                            case LIME -> result = Blocks.LIME_STAINED_GLASS_PANE;
                            case PINK -> result = Blocks.PINK_STAINED_GLASS_PANE;
                            case ORANGE -> result = Blocks.ORANGE_STAINED_GLASS_PANE;
                            case MAGENTA -> result = Blocks.MAGENTA_STAINED_GLASS_PANE;
                            case PURPLE -> result = Blocks.PURPLE_STAINED_GLASS_PANE;
                        }
                        world.setBlockState(pos,
                                result.getDefaultState()
                                        .with(ConnectingBlock.NORTH, state.get(ConnectingBlock.NORTH))
                                        .with(ConnectingBlock.EAST, state.get(ConnectingBlock.EAST))
                                        .with(ConnectingBlock.SOUTH, state.get(ConnectingBlock.SOUTH))
                                        .with(ConnectingBlock.WEST, state.get(ConnectingBlock.WEST))
                                        .with(Properties.WATERLOGGED, state.get(Properties.WATERLOGGED)));
                        state.updateNeighbors(world, pos, Block.NOTIFY_ALL);
                    } else if (block == Blocks.TERRACOTTA) {
                        switch (color) {
                            case YELLOW -> result = Blocks.YELLOW_TERRACOTTA;
                            case BLACK -> result = Blocks.BLACK_TERRACOTTA;
                            case BLUE -> result = Blocks.BLUE_TERRACOTTA;
                            case BROWN -> result = Blocks.BROWN_TERRACOTTA;
                            case CYAN -> result = Blocks.CYAN_TERRACOTTA;
                            case GRAY -> result = Blocks.GRAY_TERRACOTTA;
                            case GREEN -> result = Blocks.GREEN_TERRACOTTA;
                            case LIGHT_BLUE -> result = Blocks.LIGHT_BLUE_TERRACOTTA;
                            case LIGHT_GRAY -> result = Blocks.LIGHT_GRAY_TERRACOTTA;
                            case WHITE -> result = Blocks.WHITE_TERRACOTTA;
                            case RED -> result = Blocks.RED_TERRACOTTA;
                            case LIME -> result = Blocks.LIME_TERRACOTTA;
                            case PINK -> result = Blocks.PINK_TERRACOTTA;
                            case ORANGE -> result = Blocks.ORANGE_TERRACOTTA;
                            case MAGENTA -> result = Blocks.MAGENTA_TERRACOTTA;
                            case PURPLE -> result = Blocks.PURPLE_TERRACOTTA;
                        }
                        world.setBlockState(pos, result.getDefaultState());
                        state.updateNeighbors(world, pos, Block.NOTIFY_ALL);
                    } else if (block == Blocks.WHITE_CARPET) {
                        switch (color) {
                            case YELLOW -> result = Blocks.YELLOW_CARPET;
                            case BLACK -> result = Blocks.BLACK_CARPET;
                            case BLUE -> result = Blocks.BLUE_CARPET;
                            case BROWN -> result = Blocks.BROWN_CARPET;
                            case CYAN -> result = Blocks.CYAN_CARPET;
                            case GRAY -> result = Blocks.GRAY_CARPET;
                            case GREEN -> result = Blocks.GREEN_CARPET;
                            case LIGHT_BLUE -> result = Blocks.LIGHT_BLUE_CARPET;
                            case LIGHT_GRAY -> result = Blocks.LIGHT_GRAY_CARPET;
                            case RED -> result = Blocks.RED_CARPET;
                            case LIME -> result = Blocks.LIME_CARPET;
                            case PINK -> result = Blocks.PINK_CARPET;
                            case ORANGE -> result = Blocks.ORANGE_CARPET;
                            case MAGENTA -> result = Blocks.MAGENTA_CARPET;
                            case PURPLE -> result = Blocks.PURPLE_CARPET;
                        }
                        world.setBlockState(pos, result.getDefaultState());
                        state.updateNeighbors(world, pos, Block.NOTIFY_ALL);
                    } else if (block == Blocks.WHITE_WOOL) {
                        switch (color) {
                            case YELLOW -> result = Blocks.YELLOW_WOOL;
                            case BLACK -> result = Blocks.BLACK_WOOL;
                            case BLUE -> result = Blocks.BLUE_WOOL;
                            case BROWN -> result = Blocks.BROWN_WOOL;
                            case CYAN -> result = Blocks.CYAN_WOOL;
                            case GRAY -> result = Blocks.GRAY_WOOL;
                            case GREEN -> result = Blocks.GREEN_WOOL;
                            case LIGHT_BLUE -> result = Blocks.LIGHT_BLUE_WOOL;
                            case LIGHT_GRAY -> result = Blocks.LIGHT_GRAY_WOOL;
                            case RED -> result = Blocks.RED_WOOL;
                            case LIME -> result = Blocks.LIME_WOOL;
                            case PINK -> result = Blocks.PINK_WOOL;
                            case ORANGE -> result = Blocks.ORANGE_WOOL;
                            case MAGENTA -> result = Blocks.MAGENTA_WOOL;
                            case PURPLE -> result = Blocks.PURPLE_WOOL;
                        }
                        world.setBlockState(pos, result.getDefaultState());
                        state.updateNeighbors(world, pos, Block.NOTIFY_ALL);
                    } else if (block == Blocks.CANDLE) {
                        switch (color) {
                            case YELLOW -> result = Blocks.YELLOW_CANDLE;
                            case BLACK -> result = Blocks.BLACK_CANDLE;
                            case BLUE -> result = Blocks.BLUE_CANDLE;
                            case BROWN -> result = Blocks.BROWN_CANDLE;
                            case CYAN -> result = Blocks.CYAN_CANDLE;
                            case GRAY -> result = Blocks.GRAY_CANDLE;
                            case GREEN -> result = Blocks.GREEN_CANDLE;
                            case LIGHT_BLUE -> result = Blocks.LIGHT_BLUE_CANDLE;
                            case LIGHT_GRAY -> result = Blocks.LIGHT_GRAY_CANDLE;
                            case WHITE -> result = Blocks.WHITE_CANDLE;
                            case RED -> result = Blocks.RED_CANDLE;
                            case LIME -> result = Blocks.LIME_CANDLE;
                            case PINK -> result = Blocks.PINK_CANDLE;
                            case ORANGE -> result = Blocks.ORANGE_CANDLE;
                            case MAGENTA -> result = Blocks.MAGENTA_CANDLE;
                            case PURPLE -> result = Blocks.PURPLE_CANDLE;
                        }
                        world.setBlockState(pos, result.getDefaultState()
                                .with(Properties.CANDLES, state.get(Properties.CANDLES))
                                .with(Properties.WATERLOGGED, state.get(Properties.WATERLOGGED)));
                        state.updateNeighbors(world, pos, Block.NOTIFY_ALL);
                    } else if (block == Blocks.WHITE_BED) {
                        switch (color) {
                            case YELLOW -> result = Blocks.YELLOW_BED;
                            case BLACK -> result = Blocks.BLACK_BED;
                            case BLUE -> result = Blocks.BLUE_BED;
                            case BROWN -> result = Blocks.BROWN_BED;
                            case CYAN -> result = Blocks.CYAN_BED;
                            case GRAY -> result = Blocks.GRAY_BED;
                            case GREEN -> result = Blocks.GREEN_BED;
                            case LIGHT_BLUE -> result = Blocks.LIGHT_BLUE_BED;
                            case LIGHT_GRAY -> result = Blocks.LIGHT_GRAY_BED;
                            case RED -> result = Blocks.RED_BED;
                            case LIME -> result = Blocks.LIME_BED;
                            case PINK -> result = Blocks.PINK_BED;
                            case ORANGE -> result = Blocks.ORANGE_BED;
                            case MAGENTA -> result = Blocks.MAGENTA_BED;
                            case PURPLE -> result = Blocks.PURPLE_BED;
                        }
                        placeBed(world, pos, state, result.getDefaultState(), context.getPlayer());
                    } else if (block == Blocks.SHULKER_BOX) {
                        switch (color) {
                            case YELLOW -> result = Blocks.YELLOW_SHULKER_BOX;
                            case BLACK -> result = Blocks.BLACK_SHULKER_BOX;
                            case BLUE -> result = Blocks.BLUE_SHULKER_BOX;
                            case BROWN -> result = Blocks.BROWN_SHULKER_BOX;
                            case CYAN -> result = Blocks.CYAN_SHULKER_BOX;
                            case GRAY -> result = Blocks.GRAY_SHULKER_BOX;
                            case GREEN -> result = Blocks.GREEN_SHULKER_BOX;
                            case LIGHT_BLUE -> result = Blocks.LIGHT_BLUE_SHULKER_BOX;
                            case LIGHT_GRAY -> result = Blocks.LIGHT_GRAY_SHULKER_BOX;
                            case RED -> result = Blocks.RED_SHULKER_BOX;
                            case LIME -> result = Blocks.LIME_SHULKER_BOX;
                            case PINK -> result = Blocks.PINK_SHULKER_BOX;
                            case ORANGE -> result = Blocks.ORANGE_SHULKER_BOX;
                            case MAGENTA -> result = Blocks.MAGENTA_SHULKER_BOX;
                            case PURPLE -> result = Blocks.PURPLE_SHULKER_BOX;
                            case WHITE -> result = Blocks.WHITE_SHULKER_BOX;
                        }
                        placeShulker(world, result, pos, state);
                    } else if (block == Blocks.WHITE_CONCRETE) {
                        switch (color) {
                            case YELLOW -> result = Blocks.YELLOW_CONCRETE;
                            case BLACK -> result = Blocks.BLACK_CONCRETE;
                            case BLUE -> result = Blocks.BLUE_CONCRETE;
                            case BROWN -> result = Blocks.BROWN_CONCRETE;
                            case CYAN -> result = Blocks.CYAN_CONCRETE;
                            case GRAY -> result = Blocks.GRAY_CONCRETE;
                            case GREEN -> result = Blocks.GREEN_CONCRETE;
                            case LIGHT_BLUE -> result = Blocks.LIGHT_BLUE_CONCRETE;
                            case LIGHT_GRAY -> result = Blocks.LIGHT_GRAY_CONCRETE;
                            case WHITE -> result = Blocks.WHITE_CONCRETE;
                            case RED -> result = Blocks.RED_CONCRETE;
                            case LIME -> result = Blocks.LIME_CONCRETE;
                            case PINK -> result = Blocks.PINK_CONCRETE;
                            case ORANGE -> result = Blocks.ORANGE_CONCRETE;
                            case MAGENTA -> result = Blocks.MAGENTA_CONCRETE;
                            case PURPLE -> result = Blocks.PURPLE_CONCRETE;
                        }
                        world.setBlockState(pos, result.getDefaultState());
                        state.updateNeighbors(world, pos, Block.NOTIFY_ALL);
                    } else if (block == Blocks.WATER_CAULDRON && state.get(LeveledCauldronBlock.LEVEL) > 0) {
                        world.setBlockState(pos, state.with(LeveledCauldronBlock.LEVEL, state.get(LeveledCauldronBlock.LEVEL) - 1));
                        context.getStack().set(ModDataComponents.COLOR, 1F);
                    } else {
                        b = false;
                    }
                } else {
                    if (block instanceof StainedGlassBlock && block != Blocks.GLASS) {
                        world.setBlockState(pos, Blocks.GLASS.getDefaultState());
                        world.getBlockState(pos).updateNeighbors(world, pos, Block.NOTIFY_ALL);
                    } else if (block instanceof StainedGlassPaneBlock && block != Blocks.GLASS_PANE) {
                        world.setBlockState(pos, Blocks.GLASS_PANE.getDefaultState());
                        world.getBlockState(pos).updateNeighbors(world, pos, Block.NOTIFY_ALL);
                    } else if (block instanceof ShulkerBoxBlock && block != Blocks.SHULKER_BOX) {
                        placeShulker(world, Blocks.SHULKER_BOX, pos, state);
                    } else if (block instanceof BedBlock && block != Blocks.WHITE_BED) {
                        placeBed(world, pos, state, Blocks.WHITE_BED.getDefaultState(), context.getPlayer());
                    } else if (Arrays.asList(concrete).contains(block)) {
                        world.setBlockState(pos, Blocks.WHITE_CONCRETE.getDefaultState());
                        state.updateNeighbors(world, pos, Block.NOTIFY_ALL);
                    } else if (state.isIn(BlockTags.TERRACOTTA) && block != Blocks.TERRACOTTA) {
                        world.setBlockState(pos, Blocks.TERRACOTTA.getDefaultState());
                        state.updateNeighbors(world, pos, Block.NOTIFY_ALL);
                    } else if (block instanceof DyedCarpetBlock && block != Blocks.WHITE_CARPET) {
                        world.setBlockState(pos, Blocks.WHITE_CARPET.getDefaultState());
                        state.updateNeighbors(world, pos, Block.NOTIFY_ALL);
                    } else if (state.isIn(BlockTags.WOOL) && block != Blocks.WHITE_WOOL) {
                        world.setBlockState(pos, Blocks.WHITE_WOOL.getDefaultState());
                        state.updateNeighbors(world, pos, Block.NOTIFY_ALL);
                    } else if (block instanceof CandleBlock && block != Blocks.CANDLE) {
                        world.setBlockState(pos, Blocks.CANDLE.getDefaultState()
                                .with(Properties.CANDLES, state.get(Properties.CANDLES))
                                .with(Properties.WATERLOGGED, state.get(Properties.WATERLOGGED)));
                        state.updateNeighbors(world, pos, Block.NOTIFY_ALL);
                    } else {
                        b = false;
                    }
                }
                if (b) {
                    context.getStack().damage(1, context.getPlayer(), LivingEntity.getSlotForHand(context.getHand()));
                    context.getPlayer().incrementStat(Stats.USED.getOrCreateStat(this));
                }
            }
        }
        return super.useOnBlock(context);
    }

    private void placeShulker(World world, Block block, BlockPos pos, BlockState state){
        if(world.getBlockEntity(pos) instanceof ShulkerBoxBlockEntity oldShulkerBox) {
            NbtCompound nbt = oldShulkerBox.createNbt(world.getRegistryManager());
            BlockState newState = block.getDefaultState().with(Properties.FACING, state.get(Properties.FACING));
            world.setBlockState(pos, newState);
            ShulkerBoxBlockEntity newShulkerBox = (ShulkerBoxBlockEntity) world.getBlockEntity(pos);
            newShulkerBox.read(nbt, world.getRegistryManager());
            newState.updateNeighbors(world, pos, Block.NOTIFY_ALL);
        }
    }

    private void placeBed(World world, BlockPos pos, BlockState state, BlockState newState, PlayerEntity player) {
        BedPart part = state.get(BedBlock.PART);
        Direction direction = state.get(BedBlock.FACING);
        BlockPos pos2 = pos.offset(part == BedPart.FOOT ? direction : direction.getOpposite());
        BlockPos footPos = part == BedPart.FOOT ? pos : pos2;
        BlockPos headPos = part == BedPart.FOOT ? pos2 : pos;
        BlockState footState = world.getBlockState(footPos);
        BlockState headState = world.getBlockState(headPos);
        world.removeBlock(headPos, false);
        world.removeBlock(footPos, false);
        world.setBlockState(footPos, newState.with(HorizontalFacingBlock.FACING, footState.get(HorizontalFacingBlock.FACING)), 0);
        world.setBlockState(headPos, newState.with(BedBlock.PART, BedPart.HEAD).with(HorizontalFacingBlock.FACING, headState.get(HorizontalFacingBlock.FACING)), 0);
        world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, headPos);
        world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, footPos);
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType == ClickType.LEFT) {
            if (otherStack.getItem() instanceof DyeItem && stack.get(ModDataComponents.COLOR) == 1.0) {
                stack.set(ModDataComponents.COLOR, colorToFloat(((DyeItem) otherStack.getItem()).getColor()));
                return true;
            } else if (otherStack.getItem() instanceof EntityBucketItem || otherStack.getItem() == Items.WATER_BUCKET) {
                stack.set(ModDataComponents.COLOR, 1F);
                return true;
            }
        }
        return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
    }

    private DyeColor floatToColor(float check){
        DyeColor color = null;
        if(check == 0.05F) color = DyeColor.WHITE;
        else if(check == (float) 1 / 16) color = DyeColor.RED;
        else if (check == (float) 1 / 16 * 2) color = DyeColor.BLACK;
        else if (check == (float) 1/ 16 * 3) color = DyeColor.ORANGE;
        else if (check == (float) 1/ 16 * 4) color = DyeColor.GREEN;
        else if (check == (float) 1/ 16 * 5) color = DyeColor.BLUE;
        else if (check == (float) 1/ 16 * 6) color = DyeColor.LIGHT_BLUE;
        else if (check == (float) 1/ 16 * 7) color = DyeColor.LIGHT_GRAY;
        else if (check == (float) 1/ 16 * 8) color = DyeColor.YELLOW;
        else if (check == (float) 1/ 16 * 9) color = DyeColor.PURPLE;
        else if (check == (float) 1/ 16 * 10) color = DyeColor.MAGENTA;
        else if (check == (float) 1/ 16 * 11) color = DyeColor.CYAN;
        else if (check == (float) 1/ 16 * 12) color = DyeColor.LIME;
        else if (check == (float) 1/ 16 * 13) color = DyeColor.BROWN;
        else if (check == (float) 1/ 16 * 14) color = DyeColor.GRAY;
        else if (check == (float) 1/ 16 * 15) color = DyeColor.PINK;
        return color;
    }

    private float colorToFloat(DyeColor color){
        float result = 0;
        if(color == DyeColor.WHITE) result = 0.05F;
        else if(color == DyeColor.RED) result = (float) 1 / 16;
        else if(color == DyeColor.BLACK) result = (float) 1 / 16 * 2;
        else if(color == DyeColor.ORANGE) result = (float) 1 / 16 * 3;
        else if(color == DyeColor.GREEN) result = (float) 1 / 16 * 4;
        else if(color == DyeColor.BLUE) result = (float) 1 / 16 * 5;
        else if(color == DyeColor.LIGHT_BLUE) result = (float) 1 / 16 * 6;
        else if(color == DyeColor.LIGHT_GRAY) result = (float) 1 / 16 * 7;
        else if(color == DyeColor.YELLOW) result = (float) 1 / 16 * 8;
        else if(color == DyeColor.PURPLE) result = (float) 1 / 16 * 9;
        else if(color == DyeColor.MAGENTA) result = (float) 1 / 16 * 10;
        else if(color == DyeColor.CYAN) result = (float) 1 / 16 * 11;
        else if(color == DyeColor.LIME) result = (float) 1 / 16 * 12;
        else if(color == DyeColor.BROWN) result = (float) 1 / 16 * 13;
        else if(color == DyeColor.GRAY) result = (float) 1 / 16 * 14;
        else if(color == DyeColor.PINK) result = (float) 1 / 16 * 15;
        return result;
    }
}
