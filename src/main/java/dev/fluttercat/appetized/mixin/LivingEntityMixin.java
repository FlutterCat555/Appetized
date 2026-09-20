//package dev.fluttercat.appetized.mixin;
//
//import com.mojang.datafixers.kinds.App;
//import dev.fluttercat.appetized.Appetized;
//import dev.fluttercat.appetized.item.CleaverItem;
//import net.minecraft.server.level.ServerLevel;
//import net.minecraft.world.damagesource.DamageSource;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.player.Player;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.Unique;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//@Mixin(LivingEntity.class)
//public abstract class LivingEntityMixin {
//
//    @Shadow
//    public abstract void setHealth(float health);
//
//    @Shadow
//    public abstract float getHealth();
//
//    @Shadow
//    public abstract float getMaxHealth();
//
//    @Unique
//    float healthOffset = 0;
//
//    @Inject(at = @At("HEAD"), method = "hurtServer")
//    private void hurtServer(ServerLevel level, DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
//        if (source.getEntity() != null) {
//            if (source.getEntity().getWeaponItem().getItem() instanceof CleaverItem) {
//                this.healthOffset += damage;
//            }
//        }
//    }
//
//    @Inject(at = @At("HEAD"), method = "tick")
//    private void tick(CallbackInfo ci) {
//        if ((LivingEntity) (Object) this instanceof Player) {
//        }
////		if(getHealth()>getMaxHealth()-this.healthOffset){
////			setHealth(getMaxHealth()-this.healthOffset);
////		}
//    }
//
//    @Inject(at = @At("HEAD"), method = "setHealth", cancellable = true)
//    private void setHealth(float health, CallbackInfo ci) {
//        if (health > getMaxHealth() - this.healthOffset && health > 0) {
//            ci.cancel();
//        }
//        if (health < 0) {
//            this.healthOffset -= -this.healthOffset / 3;
//        }
//    }
//
//    @Inject(at = @At("HEAD"), method = "die")
//    private void die(DamageSource source, CallbackInfo ci) {
//        this.healthOffset = 0;
//    }
//}