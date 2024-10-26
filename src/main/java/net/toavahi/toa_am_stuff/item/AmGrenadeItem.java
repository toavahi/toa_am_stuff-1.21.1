package net.toavahi.toa_am_stuff.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.toavahi.toa_am_stuff.entity.AmGrenadeEntity;
import net.toavahi.toa_am_stuff.entity.ModEntities;

public class AmGrenadeItem extends Item implements ProjectileItem {
    public AmGrenadeItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        return new AmGrenadeEntity(ModEntities.AM_GRENADE_ENTITY, world);
    }

    @Override
    public ProjectileItem.Settings getProjectileSettings() {
        return ProjectileItem.Settings.builder()
                .uncertainty(ProjectileItem.Settings.DEFAULT.uncertainty() * 0.5F)
                .power(ProjectileItem.Settings.DEFAULT.power() * 1.25F)
                .build();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient()) {
            AmGrenadeEntity entity = new AmGrenadeEntity(world, user);
            entity.setVelocity(user, user.getPitch(), user.getYaw(), -20.0F, 0.5F, 1.0F);
            entity.setPosition(user.getX(), user.getEyeY() - 0.1F, user.getZ());
            world.spawnEntity(entity);
            world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_SPLASH_POTION_THROW,
                    SoundCategory.PLAYERS, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
            );
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        itemStack.decrementUnlessCreative(1, user);
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
