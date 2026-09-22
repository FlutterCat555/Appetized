package dev.fluttercat.appetized.entity;


import dev.fluttercat.appetized.item.ModItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

public class ThrownCleaver extends ThrowableItemProjectile {
    public ThrownCleaver(final EntityType<ThrownCleaver> type, final Level level) {
        super(type, level);
    }

    public ThrownCleaver(final Level level, final LivingEntity mob, final ItemStack itemStack) {
        super(ModEntityTypes.CLEAVER, mob, level, itemStack);
    }

    public ThrownCleaver(final Level level, final double x, final double y, final double z, final ItemStack itemStack) {
        super(ModEntityTypes.CLEAVER, x, y, z, level, itemStack);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.CLEAVER;
    }

    private ParticleOptions getParticle() {
        ItemStack item = this.getItem();//TODO: not be snowball
        return (ParticleOptions) (item.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, ItemStackTemplate.fromNonEmptyStack(item)));
    }

    public void handleEntityEvent(final byte id) {
        if (id == 3) {
            ParticleOptions particle = this.getParticle();

            for (int i = 0; i < 8; ++i) {
                this.level().addParticle(particle, this.getX(), this.getY(), this.getZ(), (double) 0.0F, (double) 0.0F, (double) 0.0F);
            }
        }

    }


    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        this.level().broadcastEntityEvent(this, (byte) 3);
        this.level().playSound(null, this.blockPosition(), SoundEvents.AMETHYST_CLUSTER_BREAK, SoundSource.PLAYERS, 1, 1);
        this.discard();//TODO: make it not do infinite damage

    }

    @Override
    protected void onHitEntity(final EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity entity = hitResult.getEntity();
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 10f);
    }

    @Override
    public boolean canBeCollidedWith(@Nullable Entity other) {
        return false;
    }
}