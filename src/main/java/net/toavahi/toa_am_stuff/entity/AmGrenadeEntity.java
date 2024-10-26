package net.toavahi.toa_am_stuff.entity;

import it.unimi.dsi.fastutil.doubles.DoubleDoubleImmutablePair;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.toavahi.toa_am_stuff.effect.ModEffects;
import net.toavahi.toa_am_stuff.item.ModItems;

import java.util.List;

public class AmGrenadeEntity extends ThrownItemEntity {

    public AmGrenadeEntity(EntityType<? extends AmGrenadeEntity> entityType, World world) {
        super(entityType, world);
    }

    public AmGrenadeEntity(World world, LivingEntity owner){
        super(ModEntities.AM_GRENADE_ENTITY, owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.AM_GRENADE;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            List<LivingEntity> list = this.getWorld().getEntitiesByClass(LivingEntity.class,
                    new Box(this.getX() - 5, this.getY() - 5, this.getZ() - 5,
                            this.getX() + 5, this.getY() + 5, this.getZ() + 5),
                    target -> target != this.getOwner());
            for(LivingEntity target : list){
                target.damage(target.getDamageSources().magic(), 4F);
                target.addStatusEffect(new StatusEffectInstance(ModEffects.AM_EFFECT, 1200));
            }
            for (int i = 0; i < 16; i++) {
                double d = this.random.nextFloat() * 2.0F - 1.0F;
                double e = this.random.nextFloat() * 2.0F - 1.0F;
                double f = this.random.nextFloat() * 2.0F - 1.0F;
                if (!(d * d + e * e + f * f > 1.0)) {
                    double g = hitResult.getPos().x + (double)this.getWidth() * d / 4.0;
                    double h = hitResult.getPos().y + (double)this.getHeight() * (0.5 + e / 4.0);
                    double j = hitResult.getPos().z + (double)this.getWidth() * f / 4.0;
                    ((ServerWorld)this.getWorld()).spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK,
                            Blocks.GLASS.getDefaultState()), g, h, j, 1, d, e + 0.2, f, 0);
                }
            }
            this.getWorld().playSound(null, hitResult.getPos().getX(), hitResult.getPos().getY(), hitResult.getPos().getZ(),
                    SoundEvents.ENTITY_SPLASH_POTION_BREAK, SoundCategory.NEUTRAL, 0.5F, 0.4F / (this.getWorld().getRandom().nextFloat() * 0.4F + 0.8F));
            this.discard();
        }
    }

    @Override
    public DoubleDoubleImmutablePair getKnockback(LivingEntity target, DamageSource source) {
        double d = target.getPos().x - this.getPos().x;
        double e = target.getPos().z - this.getPos().z;
        return DoubleDoubleImmutablePair.of(d, e);
    }
}
