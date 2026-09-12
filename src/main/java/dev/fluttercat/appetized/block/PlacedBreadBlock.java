package dev.fluttercat.appetized.block;

import dev.fluttercat.appetized.Appetized;
import dev.fluttercat.appetized.item.CleaverItem;
import dev.fluttercat.appetized.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlacedBreadBlock extends PlaceableVanillaFoodBlock {
    public static final IntegerProperty BITES;
    public static final int MAX_BITES = 3;


    static final VoxelShape[] SHAPE;

    public PlacedBreadBlock(Item dropItem, Properties properties) {
        super(dropItem, properties);
        this.registerDefaultState((BlockState) ((BlockState) this.stateDefinition.any()).setValue(BITES, 0));
    }


    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{BITES});
    }


    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE[state.getValue(BITES)];
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        Appetized.LOGGER.info(String.valueOf(state.getValue(BITES)));
        if (itemStack.getItem() instanceof CleaverItem) {
            int bites = state.getValue(BITES);
            level.setBlock(pos, (BlockState) state.setValue(BITES, bites + 1), 3);
            level.addFreshEntity(new ItemEntity(level, pos.getX()+0.5, pos.getY()+0.25, pos.getZ()+0.5, new ItemStack(ModItems.SLICED_BREAD,4), 0, 0, 0));
            //this could cause a crash if you setblock with an unreasonable value, but it should be fine in gameplay I HOPE??
            if (bites > MAX_BITES-1) {//my dumbass forgot to account that bites wont change, so instead we subtract 1 here
                level.removeBlock(pos, false);
                level.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
            }
            return InteractionResult.SUCCESS;
        } else {
            return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
        }
    }

    static {
        BITES = BlockStateProperties.BITES;
        SHAPE = Block.boxes(6, (bite) ->
                Block.box(
                        (double) 4,//side push x+
                        (double) 0,//bottom push y+
                        (double) 1+Math.min(bite*3,14),//side push z+.  also it MUST be less than 1 or else it dies a very painful death
                        (double) 12,//side extrude x+
                        (double) 5,//top extrude y+
                        (double) 15//side extrude z+
                ));

    }
}
