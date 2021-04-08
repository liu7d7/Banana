package org.quantumclient.banana.module.highway;

import net.minecraft.util.math.BlockPos;
import org.lwjgl.glfw.GLFW;
import org.quantumclient.banana.event.EventBlockBreakingProgress;
import org.quantumclient.banana.module.Category;
import org.quantumclient.banana.module.Feature;
import org.quantumclient.banana.utilities.PlayerUtils;
import org.quantumclient.energy.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class Disabler extends Feature {

    public Disabler() {
        super("disabler", Category.highway, GLFW.GLFW_KEY_I);
    }

    @Subscribe
    public void onBreak(EventBlockBreakingProgress event) {
        for (BlockPos blockPos : getBlacklistedBlockPoses()) {
            if (event.getBlockPos().equals(blockPos)) {
                event.setCancelled(true);
            }
        }
    }

    public List<BlockPos> getBlacklistedBlockPoses() {
        List<BlockPos> returned = new ArrayList<>();
        switch (PlayerUtils.GetFacing()) {
            case South:
                returned.add(mc.player.getBlockPos().west());
                returned.add(mc.player.getBlockPos().west().up());
                returned.add(mc.player.getBlockPos().west().up(2));
                returned.add(mc.player.getBlockPos().up(2));
                returned.add(mc.player.getBlockPos().up(2).north());
                returned.add(mc.player.getBlockPos().up(2).north(2));
                returned.add(mc.player.getBlockPos().west().south());
                returned.add(mc.player.getBlockPos().west().south().up());
                returned.add(mc.player.getBlockPos().west().south().up(2));
                returned.add(mc.player.getBlockPos().south().up(2));
                returned.add(mc.player.getBlockPos().west().south(2));
                returned.add(mc.player.getBlockPos().west().south(2).up());
                returned.add(mc.player.getBlockPos().west().south(2).up(2));
                returned.add(mc.player.getBlockPos().south(2).up(2));
                returned.add(mc.player.getBlockPos().west().south(3));
                returned.add(mc.player.getBlockPos().west().south(3).up());
                returned.add(mc.player.getBlockPos().west().south(3).up(2));
                returned.add(mc.player.getBlockPos().south(3).up(2));
                returned.add(mc.player.getBlockPos().west().south(4));
                returned.add(mc.player.getBlockPos().west().south(4).up());
                returned.add(mc.player.getBlockPos().west().south(4).up(2));
                returned.add(mc.player.getBlockPos().south(4).up(2));
                break;
            case North:
                returned.add(mc.player.getBlockPos().east());
                returned.add(mc.player.getBlockPos().east().up());
                returned.add(mc.player.getBlockPos().east().up(2));
                returned.add(mc.player.getBlockPos().up(2));
                returned.add(mc.player.getBlockPos().up(2).south());
                returned.add(mc.player.getBlockPos().up(2).south(2));
                returned.add(mc.player.getBlockPos().east().north());
                returned.add(mc.player.getBlockPos().east().north().up());
                returned.add(mc.player.getBlockPos().east().north().up(2));
                returned.add(mc.player.getBlockPos().north().up(2));
                returned.add(mc.player.getBlockPos().east().north(2));
                returned.add(mc.player.getBlockPos().east().north(2).up());
                returned.add(mc.player.getBlockPos().east().north(2).up(2));
                returned.add(mc.player.getBlockPos().north(2).up(2));
                returned.add(mc.player.getBlockPos().east().north(3));
                returned.add(mc.player.getBlockPos().east().north(3).up());
                returned.add(mc.player.getBlockPos().east().north(3).up(2));
                returned.add(mc.player.getBlockPos().south(3).up(2));
                returned.add(mc.player.getBlockPos().east().north(4));
                returned.add(mc.player.getBlockPos().east().north(4).up());
                returned.add(mc.player.getBlockPos().east().north(4).up(2));
                returned.add(mc.player.getBlockPos().north(4).up(2));
                break;
            case East:
                returned.add(mc.player.getBlockPos().south());
                returned.add(mc.player.getBlockPos().south().up());
                returned.add(mc.player.getBlockPos().south().up(2));
                returned.add(mc.player.getBlockPos().up(2));
                returned.add(mc.player.getBlockPos().up(2).west());
                returned.add(mc.player.getBlockPos().up(2).west(2));
                returned.add(mc.player.getBlockPos().south().east());
                returned.add(mc.player.getBlockPos().south().east().up());
                returned.add(mc.player.getBlockPos().south().east().up(2));
                returned.add(mc.player.getBlockPos().east().up(2));
                returned.add(mc.player.getBlockPos().south().east(2));
                returned.add(mc.player.getBlockPos().south().east(2).up());
                returned.add(mc.player.getBlockPos().south().east(2).up(2));
                returned.add(mc.player.getBlockPos().east(2).up(2));
                returned.add(mc.player.getBlockPos().south().east(3));
                returned.add(mc.player.getBlockPos().south().east(3).up());
                returned.add(mc.player.getBlockPos().south().east(3).up(2));
                returned.add(mc.player.getBlockPos().east(3).up(2));
                returned.add(mc.player.getBlockPos().south().east(4));
                returned.add(mc.player.getBlockPos().south().east(4).up());
                returned.add(mc.player.getBlockPos().south().east(4).up(2));
                returned.add(mc.player.getBlockPos().east(4).up(2));
                break;
            case West:
                returned.add(mc.player.getBlockPos().north());
                returned.add(mc.player.getBlockPos().north().up());
                returned.add(mc.player.getBlockPos().north().up(2));
                returned.add(mc.player.getBlockPos().up(2));
                returned.add(mc.player.getBlockPos().up(2).east());
                returned.add(mc.player.getBlockPos().up(2).east(2));
                returned.add(mc.player.getBlockPos().north().west());
                returned.add(mc.player.getBlockPos().north().west().up());
                returned.add(mc.player.getBlockPos().north().west().up(2));
                returned.add(mc.player.getBlockPos().west().up(2));
                returned.add(mc.player.getBlockPos().north().west(2));
                returned.add(mc.player.getBlockPos().north().west(2).up());
                returned.add(mc.player.getBlockPos().north().west(2).up(2));
                returned.add(mc.player.getBlockPos().west(2).up(2));
                returned.add(mc.player.getBlockPos().north().west(3));
                returned.add(mc.player.getBlockPos().north().west(3).up());
                returned.add(mc.player.getBlockPos().north().west(3).up(2));
                returned.add(mc.player.getBlockPos().west(3).up(2));
                returned.add(mc.player.getBlockPos().north().west(4));
                returned.add(mc.player.getBlockPos().north().west(4).up());
                returned.add(mc.player.getBlockPos().north().west(4).up(2));
                returned.add(mc.player.getBlockPos().west(4).up(2));
        }
        return returned;
    }

}
