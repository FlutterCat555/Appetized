package dev.fluttercat.appetized.item;

import dev.fluttercat.appetized.Appetized;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.phys.Vec3;
import org.apache.logging.log4j.core.jmx.Server;

public class ScytheItem extends Item {

    public ScytheItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        if (level.getBlockState(pos).getBlock() instanceof CropBlock cropBlock && cropBlock.isMaxAge(level.getBlockState(pos))) {
            for (int x = -1; x < 2; x++) {
                for (int z = -1; z < 2; z++) {
                    BlockPos pos1 = pos.offset(new Vec3i(x, 0, z));
                    if (level.getBlockState(pos1).getBlock() instanceof CropBlock cropBlockInstance && cropBlockInstance.isMaxAge(level.getBlockState(pos1))) {
                        Block block = level.getBlockState(pos1).getBlock();
                        level.destroyBlock(pos1, true, context.getPlayer());
                        level.setBlockAndUpdate(pos1, block.defaultBlockState());
                    }
                }
            }
            if(!level.isClientSide()){
                ((ServerLevel)level).sendParticles(ParticleTypes.SWEEP_ATTACK, pos.getX(), pos.getY()+0.5, pos.getZ(), 1, 0, 0, 0, 1);
            }
            return InteractionResult.SUCCESS;
        }
        else{
            return Items.IRON_HOE.useOn(context); //hacky fix
        }
    }

    @Override
    public void hurtEnemy(ItemStack itemStack, LivingEntity mob, LivingEntity attacker) {
        super.hurtEnemy(itemStack, mob, attacker);
        Vec3 vel = new Vec3(
                (attacker.getX()-mob.getX())/4-mob.getDeltaMovement().x,
                -attacker.getDeltaMovement().y/1.5,
                (attacker.getZ()-mob.getZ())/4-mob.getDeltaMovement().z);
        mob.push(vel.x, vel.y, vel.z);
    }
}
