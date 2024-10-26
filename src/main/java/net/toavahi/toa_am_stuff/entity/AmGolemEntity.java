package net.toavahi.toa_am_stuff.entity;

import net.minecraft.block.*;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import net.toavahi.toa_am_stuff.ToaAmethystStuff;
import net.toavahi.toa_am_stuff.item.ModItems;
import net.toavahi.toa_am_stuff.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AmGolemEntity extends TameableEntity {
    private boolean fire_upgrade;
    private boolean ice_upgrade;
    private int shield_totem;
    private int heal_totem;

    public AmGolemEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    public AmGolemEntity(World world, PlayerEntity owner){
        super(ModEntities.AM_GOLEM_ENTITY, world);
        this.setOwner(owner);
    }

    public static DefaultAttributeContainer.Builder createAmGolemEntityAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.GENERIC_FALL_DAMAGE_MULTIPLIER, 0);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new PressButtonGoal(this));
        this.goalSelector.add(2, new FollowOwnerGoal(this, 1.0, 2.0F, 10.0F));
        this.goalSelector.add(3, new WanderAroundGoal(this, 1.0));
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        World world = player.getWorld();
        if (!world.isClient()) {
            ItemStack stack = player.getStackInHand(hand);
            if (stack.getItem() == Items.FIRE_CHARGE) {
                stat(stack, player);
                this.fire_upgrade = true;
            } else if(stack.getItem() == Items.GOLD_NUGGET){
                this.heal_totem += 20 * 60;
                stat(stack, player);
            } else if(stack.getItem() == Items.IRON_NUGGET){
                this.shield_totem += 20 * 60;
                stat(stack, player);
            } else if(stack.getItem() == Items.SNOWBALL){
                stat(stack, player);
                this.ice_upgrade = true;
            } else if(stack.getItem() == ModItems.AM_COPPER_INGOT){
                this.heal(5F);
            } else if(stack.getItem() == Items.COPPER_BLOCK){
                this.eatFood(world, new ItemStack(Items.COPPER_BLOCK), new FoodComponent.Builder().nutrition(0).saturationModifier(0).build());
                ItemEntity entity = new ItemEntity(world, this.getX(), this.getY(), this.getZ(), new ItemStack(Items.COPPER_INGOT, 10));
                world.spawnEntity(entity);
                this.damage(this.getDamageSources().dryOut(), 1);
            }
        }
        return ActionResult.SUCCESS;
    }

    private void stat(ItemStack stack, PlayerEntity player){
        stack.decrementUnlessCreative(1, player);
        player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
    }

    @Override
    public boolean canSpawn(WorldView world) {
        BlockPos blockPos = this.getBlockPos();
        BlockPos blockPos2 = blockPos.down();
        BlockState blockState2 = world.getBlockState(blockPos2);
        if (!blockState2.hasSolidTopSurface(world, blockPos2, this)) return false;
        else {
            BlockState blockState = world.getBlockState(blockPos);
            if(!SpawnHelper.isClearForSpawn(world, blockPos, blockState, blockState.getFluidState(), ModEntities.AM_GOLEM_ENTITY)
            && !SpawnHelper.isClearForSpawn(world, blockPos2, blockState2, blockState2.getFluidState(), ModEntities.AM_GOLEM_ENTITY)) return false;
            return SpawnHelper.isClearForSpawn(world, blockPos, world.getBlockState(blockPos), Fluids.EMPTY.getDefaultState(), ModEntities.AM_GOLEM_ENTITY)
                    && world.doesNotIntersectEntities(this);
        }
    }

    protected void produceParticles(ParticleEffect parameters) {
        for (int i = 0; i < 5; i++) {
            double d = this.random.nextGaussian() * 0.02;
            double e = this.random.nextGaussian() * 0.02;
            double f = this.random.nextGaussian() * 0.02;
            ((ServerWorld) this.getWorld()).spawnParticles(parameters,
                    this.getParticleX(1.0),
                    this.getRandomBodyY() + 1.0,
                    this.getParticleZ(1.0),
                    1, d, e, f, 0.0);
        }
    }

    @Override
    public void tick() {
        super.tick();
        World world = this.getWorld();
        if(!world.isClient()) {
            if (world.getTime() % 20 == 0) {
                if(this.shield_totem > 0) {
                    this.shield_totem -= 20;
                    if(this.shield_totem < 0) this.shield_totem = 0;
                    this.totem(world, true);
                } else if(this.heal_totem > 0){
                    this.heal_totem -= 20;
                    if(this.heal_totem < 0) this.heal_totem = 0;
                    this.totem(world, false);
                }
            }
            if(world.getTime() % 100 == 0) {
                List<HostileEntity> list = world.getEntitiesByClass(HostileEntity.class,
                        new Box(this.getX() + 20, this.getY() + 60, this.getZ() + 20,
                                this.getX() - 20,this.getY() - 60, this.getZ() - 20),
                        this::canSee);
                if(list != null && !list.isEmpty()){
                    produceParticles(ParticleTypes.ELECTRIC_SPARK);
                }
            }
        }
    }

    private void totem(World world, boolean check){
        BlockPos pos = this.getBlockPos();
        this.produceParticles(check ? ParticleTypes.WAX_OFF : ParticleTypes.WAX_ON);
        List<LivingEntity> list = world.getEntitiesByClass(LivingEntity.class, new Box(pos.getX() - 5, pos.getY() - 5,
                pos.getZ() - 5, pos.getX() + 5, pos.getY() + 5, pos.getZ() + 10), target -> target != this);
        for (LivingEntity target : list) {
            if (target.distanceTo(this) < 5) {
                if(!(target instanceof HostileEntity)){
                    if(check && (!target.hasStatusEffect(StatusEffects.RESISTANCE) || target.getStatusEffect(StatusEffects.RESISTANCE).getAmplifier() < 5)) {
                        target.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, this.shield_totem, 5));
                    } else {
                        target.heal(1);
                    }
                }
            }
        }
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("fire_upgrade", this.fire_upgrade);
        nbt.putBoolean("ice_upgrade", this.ice_upgrade);
        nbt.putInt("shield_totem", this.shield_totem);
        nbt.putInt("heal_totem", this.heal_totem);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.fire_upgrade = nbt.getBoolean("fire_upgrade");
        this.ice_upgrade = nbt.getBoolean("ice_upgrade");
        this.shield_totem = nbt.getInt("shield_totem");
        this.heal_totem = nbt.getInt("heal_totem");
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.AM_COPPER_BLOCK_BREAK;
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return ModSounds.AM_COPPER_BLOCK_FALL;
    }

    private static class PressButtonGoal extends WanderAroundGoal {
        public PressButtonGoal(AmGolemEntity mob){
            super(mob, 1.0);
        }

        @Override
        protected @Nullable Vec3d getWanderTarget() {
            ArrayList<BlockPos> posList = new ArrayList<>();
            for(int i = -20; i < 20; ++i){
                for(int k = -5; k < 5; ++k){
                    for(int j = -20; j < 20; ++j){
                        if(this.mob.getWorld().getBlockState(new BlockPos((int) (this.mob.getX() + i),
                                (int) (this.mob.getY() + k), (int) (this.mob.getZ() + j))).getBlock()
                                instanceof ButtonBlock) {
                            posList.add(new BlockPos((int) (this.mob.getX() + i),
                                    (int) (this.mob.getY() + k), (int) (this.mob.getZ() + j)));
                        }
                    }
                }
            }
            if(!posList.isEmpty()) {
                Random random = new Random();
                BlockPos pos = posList.get(random.nextInt(posList.size()));
                int i = 0;
                Path path = this.mob.getNavigation().findPathTo(pos, 20);
                while (path == null && i < 100) {
                    i++;
                    pos = posList.get(random.nextInt(posList.size()));
                    path = this.mob.getNavigation().findPathTo(pos, 20);
                }
                return i < 100 ? Vec3d.of(pos) : null;
            }
            return null;
        }

        @Override
        public void stop() {
            World world = this.mob.getWorld();
            BlockPos pos = new BlockPos((int) this.targetX, (int) this.targetY, (int) this.targetZ);
            if(this.mob.getBlockPos().isWithinDistance(pos, 2)){
                BlockState state = world.getBlockState(pos);
                ((ButtonBlock) state.getBlock()).powerOn(state, world, pos, null);
            }
            ToaAmethystStuff.LOGGER.info(String.valueOf(pos));
            super.stop();
        }
    }
}
