package dev.fluttercat.appetized.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.world.level.block.ComposterBlock.COMPOSTABLES;

@Mixin(ComposterBlock.class)
public class ComposterBlockMixin {

    @Shadow
    static IntegerProperty LEVEL;

    /**
     * @author fluttercat555
     * @reason more stable than injecting for same result
     */
    @Overwrite
    private static BlockState addItem(final @Nullable Entity sourceEntity, final BlockState state, final LevelAccessor level, final BlockPos pos, final ItemStack itemStack) {
        int fillLevel = (Integer) state.getValue(LEVEL);
        float chance = COMPOSTABLES.getFloat(itemStack.getItem());
        if (chance >= 0.5) {
            int newLevel = fillLevel + 2;
            BlockState newState = (BlockState) state.setValue(LEVEL, newLevel);
            level.setBlock(pos, newState, 3);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(sourceEntity, newState));
            if (newLevel == 7) {
                level.scheduleTick(pos, state.getBlock(), 20);
            }
            return state;
        } else {
            int newLevel = fillLevel + 1;
            BlockState newState = (BlockState) state.setValue(LEVEL, newLevel);
            level.setBlock(pos, newState, 3);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(sourceEntity, newState));
            if (newLevel == 7) {
                level.scheduleTick(pos, state.getBlock(), 20);
            }
            return state;
        }
    }

}