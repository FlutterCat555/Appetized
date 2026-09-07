package dev.fluttercat.appetized.block;

import dev.fluttercat.appetized.item.CleaverItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlacedBreadBlock extends PlaceableVanillaFoodBlock {
    //public static final IntegerProperty SLICES = ModBlockStateProperties.;  //TODO: rosey can you do this plz im stupid and dont know how

    public static final int MAX_BITES = 6;


    static final VoxelShape[] SHAPE = Block.boxes(6, (slices) ->
            Block.box(
                    (double) 4,//side push x+
                    (double) 0,//bottom push y+
                    (double) 1,//side push z+
                    (double) 12,//side extrude x+
                    (double) 5,//top extrude y+
                    (double) 15//side extrude z+
            ));


    public PlacedBreadBlock(Item dropItem, Properties properties) {
        super(dropItem,properties);
    }



    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE[0];
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(itemStack.getItem() instanceof CleaverItem){

            return InteractionResult.SUCCESS;
        }else{
            return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
        }
    }
}
