package net.tazgirl.tutilz.misc_helpers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class RaycastHelpers
{
    public static HitResult playerBlockCheck(Player player)
    {
        return player.pick(player.blockInteractionRange(), 0,false);
    }

    public static boolean isSelectingBlock(Player player)
    {
        return isTypeBlock(playerBlockCheck(player));
    }

    public static BlockState playerSelectedBlock(Player player)
    {
        HitResult hitResult = playerBlockCheck(player);

        if(isTypeBlock(hitResult) )
        {
            Vec3 vec3 = hitResult.getLocation();
            BlockPos blockPos = BlockPos.containing(hitResult.getLocation()).relative(((BlockHitResult) hitResult).getDirection());
            return player.level().getBlockState(BlockPos.containing(hitResult.getLocation()));
        }
        else
        {
            return Blocks.AIR.defaultBlockState();
        }
    }


    public static boolean isTypeBlock(HitResult hitResult)
    {
        return hitResult.getType().equals(HitResult.Type.BLOCK);
    }
}
