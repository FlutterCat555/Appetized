package dev.fluttercat.appetized.mixin;

import dev.fluttercat.appetized.block.ModBlocks;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class PlaceableFoodItemsMixin {
    @Inject(at = @At("TAIL"), method = "useOn", cancellable = true)
    private void useOn(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        if (context.getPlayer() == null) {
            return;
        }
        else if(context.getPlayer().isSecondaryUseActive()) {
            if (context.getPlayer().getItemInHand(context.getHand()).getItem().equals(Items.BREAD)) {
                cir.setReturnValue(ModBlocks.PLACED_BREAD.asItem().useOn(context));
            }
        }
    }
}