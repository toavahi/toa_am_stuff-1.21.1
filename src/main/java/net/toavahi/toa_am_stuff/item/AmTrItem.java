package net.toavahi.toa_am_stuff.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class AmTrItem extends Item {
    public AmTrItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        float it = 1.2F;
        if(user.getPitch() > 45){
            it = 0.7F;
        } else if(user.getPitch() < -30){
            it = 1.5F;
        }
        if(user.isSneaking()) {
            it -= 02.F;
        }
        world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_AMETHYST_BLOCK_STEP, SoundCategory.PLAYERS, 1.0F, it);
        world.emitGameEvent(GameEvent.INSTRUMENT_PLAY, user.getPos(), GameEvent.Emitter.of(user));
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
