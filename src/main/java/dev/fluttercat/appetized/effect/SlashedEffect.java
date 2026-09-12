package dev.fluttercat.appetized.effect;

import dev.fluttercat.appetized.Appetized;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class SlashedEffect extends MobEffect {

    protected SlashedEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {
        if(mob.getLastHurtByPlayer()!=null) {
            mob.hurtServer(serverLevel, serverLevel.damageSources().playerAttack(mob.getLastHurtByPlayer()), amplification/2+0.5f);
        }else{
            mob.hurtServer(serverLevel, serverLevel.damageSources().magic(), amplification/2+0.5f);
        }
//        mob.removeEffect(ModMobEffects.SLASHED);
        return super.applyEffectTick(serverLevel, mob, amplification);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        Appetized.LOGGER.info(String.valueOf(tickCount));
        return tickCount%20==0;
    }
}
