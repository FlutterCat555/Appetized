package dev.fluttercat.appetized.item;

import dev.fluttercat.appetized.Appetized;
import dev.fluttercat.appetized.effect.ModMobEffects;
import dev.fluttercat.appetized.entity.ThrownCleaver;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.ThrownTrident;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.SnowballItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.world.entity.projectile.Projectile.spawnProjectile;

public class CleaverItem extends Item {
    public CleaverItem(Properties properties) {
        super(properties);
    }

//    public static final int ACTIVATION_TICKS = 4;

    @Override
    public void hurtEnemy(ItemStack itemStack, LivingEntity mob, LivingEntity attacker) {
        super.hurtEnemy(itemStack, mob, attacker);
        mob.addEffect(new MobEffectInstance(ModMobEffects.SLASHED, 4 * 20, 3, false, false));
    }

//    @Override
//    public boolean releaseUsing(ItemStack itemStack, Level level, LivingEntity entity, int remainingTime) {
//        if (entity instanceof Player player) {
//            if (level instanceof ServerLevel serverLevel) {
//                player.awardStat(Stats.ITEM_USED.get(this));
//                itemStack.consume(1, player);
//                spawnProjectileFromRotationXZSpread(ThrownCleaver::new, serverLevel, itemStack, player, player.getYRot() + (0 - (float) 1 / 2 + 0.5f) * 8, 1F, 2.0F, 0.25F);
//            }
//        }
//        return false;
//    }
//
//    public static <T extends Projectile> T spawnProjectileFromRotationXZSpread(final Projectile.ProjectileFactory<T> creator, final ServerLevel serverLevel, final ItemStack itemStack, final LivingEntity source, final float yRot, final float yOffset, final float pow, final float uncertainty) {
//        return (T) spawnProjectile(creator.create(serverLevel, source, itemStack), serverLevel, itemStack, (projectile) -> projectile.shootFromRotation(source, source.getXRot(), yRot, yOffset, pow, uncertainty));
//    }
//
//    @Override
//    public int getUseDuration(final ItemStack itemStack, final LivingEntity user) {
//        return 72000;
//    }
//
//    @Override
//    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack itemStack, int ticksRemaining) {
//        int timeHeld = this.getUseDuration(itemStack, livingEntity) - ticksRemaining;
//        if (timeHeld == ACTIVATION_TICKS) {
//            level.playSound(null, BlockPos.containing(livingEntity.position()), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1.0F, 1F);
//            super.onUseTick(level, livingEntity, itemStack, ticksRemaining);
//        }
//    }
//
//    @Override
//    public InteractionResult use(Level level, Player player, InteractionHand hand) {
//        if (!player.isShiftKeyDown()) {
//            player.startUsingItem(hand);
//        }
//        return InteractionResult.PASS;
//    }
//
//    @Override
//    public ItemUseAnimation getUseAnimation(ItemStack itemStack) {
//        return ItemUseAnimation.BOW;
//    }
}
