//package dev.fluttercat.appetized.mixin;
//
//import net.minecraft.client.gui.Hud;
//import net.minecraft.resources.Identifier;
//import net.minecraft.server.MinecraftServer;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(Hud.class)
//public class HudMixin {
//    @Inject(at = @At("HEAD"), method = "extractHeart")
//    private void extractHeart(CallbackInfo info) {
//        // This code is injected into the start of MinecraftServer.loadLevel()V
//    }
//}