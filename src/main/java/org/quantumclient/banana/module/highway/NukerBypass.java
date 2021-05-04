package org.quantumclient.banana.module.highway;

import net.minecraft.block.Blocks;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.lwjgl.glfw.GLFW;
import org.quantumclient.banana.Banana;
import org.quantumclient.banana.event.EventDoubleTick;
import org.quantumclient.banana.event.EventTwelvetupleTick;
import org.quantumclient.banana.module.Category;
import org.quantumclient.banana.module.Feature;
import org.quantumclient.banana.settings.Setting;
import org.quantumclient.banana.utilities.PlayerUtils;
import org.quantumclient.energy.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class NukerBypass extends Feature {

    private final List<BlockPos> minedBlockList = new ArrayList<>();

    public static Setting up = new Setting("up", 3, 0, 4, 1, true);
    public static Setting left = new Setting("left", 0, 0, 4, 1, true);
    public static Setting right = new Setting("right", 1, 0, 4, 1, true);
    public static Setting forward = new Setting("forward", 4, 0, 4, 1, true);
    public static Setting backward = new Setting("backward", 4, 0, 4, 1, true);
    public static Setting disable = new Setting("disable", false);

    @Override
    public void toggleNoSave() {

    }

    float prevVol = 0;

    public NukerBypass() {
        super("nuker-bypass", Category.highway, GLFW.GLFW_KEY_I);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        prevVol = mc.options.getSoundVolume(SoundCategory.BLOCKS);
        mc.options.setSoundVolume(SoundCategory.BLOCKS, 0);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.options.setSoundVolume(SoundCategory.BLOCKS, prevVol);
    }

    private List<BlockPos> getBlocks() {
        List<BlockPos> blocks = new ArrayList<>();
        switch (PlayerUtils.GetFacing()) {
            case East:
                for (int i = 0; i < forward.getValInt(); i++) {
                    blocks.add(mc.player.getBlockPos().east(i));
                    for (int i2 = 0; i2 < up.getValInt(); i2++) {
                        blocks.add(mc.player.getBlockPos().east(i).up(i2));
                    }
                    for (int i2 = 0; i2 < left.getValInt(); i2++) {
                        blocks.add(mc.player.getBlockPos().east(i).north(i2));
                        for (int i3 = 0; i3 < up.getValInt(); i3++) {
                            blocks.add(mc.player.getBlockPos().east(i).north(i2).up(i3));
                        }
                    }
                    for (int i2 = 0; i2 < right.getValInt(); i2++) {
                        blocks.add(mc.player.getBlockPos().east(i).south(i2));
                        for (int i3 = 0; i3 < up.getValInt(); i3++) {
                            blocks.add(mc.player.getBlockPos().south(i2).east(i).up(i3));
                        }
                    }
                }
                for (int i = 0; i < backward.getValInt(); i++) {
                    blocks.add(mc.player.getBlockPos().west(i));
                    for (int i2 = 0; i2 < up.getValInt(); i2++) {
                        blocks.add(mc.player.getBlockPos().west(i).up(i2));
                    }
                    for (int i2 = 0; i2 < left.getValInt(); i2++) {
                        blocks.add(mc.player.getBlockPos().west(i).north(i2));
                        for (int i3 = 0; i3 < up.getValInt(); i3++) {
                            blocks.add(mc.player.getBlockPos().west(i).north(i2).up(i3));
                        }
                    }
                    for (int i2 = 0; i2 < right.getValInt(); i2++) {
                        blocks.add(mc.player.getBlockPos().west(i).south(i2));
                        for (int i3 = 0; i3 < up.getValInt(); i3++) {
                            blocks.add(mc.player.getBlockPos().west(i).south(i2).up(i3));
                        }
                    }
                }
                break;
            case South:
                for (int i = 0; i < forward.getValInt(); i++) {
                    blocks.add(mc.player.getBlockPos().south(i));
                    for (int i2 = 0; i2 < up.getValInt(); i2++) {
                        blocks.add(mc.player.getBlockPos().south(i).up(i2));
                    }
                    for (int i2 = 0; i2 < left.getValInt(); i2++) {
                        blocks.add(mc.player.getBlockPos().east(i2));
                        for (int j = 0; j < up.getValInt(); j++) {
                            blocks.add(mc.player.getBlockPos().south(i).east(i2).up(j));
                        }
                    }
                    for (int i2 = 0; i2 < right.getValInt(); i2++) {
                        blocks.add(mc.player.getBlockPos().west(i2));
                        for (int j = 0; j < up.getValInt(); j++) {
                            blocks.add(mc.player.getBlockPos().west(i2).south(i).up(j));
                        }
                    }
                }
                for (int i = 0; i < backward.getValInt(); i++) {
                    blocks.add(mc.player.getBlockPos().north(i));
                    for (int j = 0; j < up.getValInt(); j++) {
                        blocks.add(mc.player.getBlockPos().north(i).up(j));
                    }
                    for (int i2 = 0; i2 < left.getValInt(); i2++) {
                        blocks.add(mc.player.getBlockPos().east(i2));
                        for (int j = 0; j < up.getValInt(); j++) {
                            blocks.add(mc.player.getBlockPos().north(i).east(i2).up(j));
                        }
                    }
                    for (int i2 = 0; i2 < right.getValInt(); i2++) {
                        blocks.add(mc.player.getBlockPos().west(i2));
                        for (int j = 0; j < up.getValInt(); j++) {
                            blocks.add(mc.player.getBlockPos().north(i).west(i2).up(j));
                        }
                    }
                }
                break;
            case West:
                for (int i = 0; i < forward.getValInt(); i++) {
                    blocks.add(mc.player.getBlockPos().west(i));
                    for (int j = 0; j < up.getValInt(); j++) {
                        blocks.add(mc.player.getBlockPos().west(i).up(j));
                    }
                    for (int i2 = 0; i2 < left.getValInt(); i2++) {
                        blocks.add(mc.player.getBlockPos().south(i2));
                        for (int j = 0; j < up.getValInt(); j++) {
                            blocks.add(mc.player.getBlockPos().west(i).south(i2).up(j));
                        }
                    }
                    for (int i2 = 0; i2 < right.getValInt(); i2++) {
                        blocks.add(mc.player.getBlockPos().north(i2));
                        for (int j = 0; j < up.getValInt(); j++) {
                            blocks.add(mc.player.getBlockPos().west(i).north(i2).up(j));
                        }
                    }
                }
                for (int i = 0; i < backward.getValInt(); i++) {
                    blocks.add(mc.player.getBlockPos().east(i));
                    for (int j = 0; j < up.getValInt(); j++) {
                        blocks.add(mc.player.getBlockPos().east(i).up(j));
                    }
                    for (int i2 = 0; i2 < left.getValInt(); i2++) {
                        blocks.add(mc.player.getBlockPos().east(i).south(i2));
                        for (int j = 0; j < up.getValInt(); j++) {
                            blocks.add(mc.player.getBlockPos().south(i2).east(i).up(j));
                        }
                    }
                    for (int i2 = 0; i2 < right.getValInt(); i2++) {
                        blocks.add(mc.player.getBlockPos().east(i).north(i2));
                        for (int j = 0; j < up.getValInt(); j++) {
                            blocks.add(mc.player.getBlockPos().east(i).north(i2).up(j));
                        }
                    }
                }
                break;
            case North:
                for (int i = 0; i < forward.getValInt(); i++) {
                    blocks.add(mc.player.getBlockPos().north(i));
                    for (int j = 0; j < up.getValInt(); j++) {
                        blocks.add(mc.player.getBlockPos().north(i).up(j));
                    }
                    for (int i2 = 0; i2 < left.getValInt(); i2++) {
                        blocks.add(mc.player.getBlockPos().west(i2));
                        for (int j = 0; j < up.getValInt(); j++) {
                            blocks.add(mc.player.getBlockPos().north(i).west(i2).up(j));
                        }
                    }
                    for (int i2 = 0; i2 < right.getValInt(); i2++) {
                        blocks.add(mc.player.getBlockPos().east(i2));
                        for (int j = 0; j < up.getValInt(); j++) {
                            blocks.add(mc.player.getBlockPos().north(i).east(i2).up(j));
                        }
                    }
                }
                for (int i = 0; i < backward.getValInt(); i++) {
                    blocks.add(mc.player.getBlockPos().south(i));
                    for (int j = 0; j < up.getValInt(); j++) {
                        blocks.add(mc.player.getBlockPos().south(i).up(j));
                    }
                    for (int i2 = 0; i2 < left.getValInt(); i2++) {
                        blocks.add(mc.player.getBlockPos().west(i2));
                        for (int j = 0; j < up.getValInt(); j++) {
                            blocks.add(mc.player.getBlockPos().south(i).west(i2).up(j));
                        }
                    }
                    for (int i2 = 0; i2 < right.getValInt(); i2++) {
                        blocks.add(mc.player.getBlockPos().east(i2));
                        for (int j = 0; j < up.getValInt(); j++) {
                            blocks.add(mc.player.getBlockPos().south(i).west(i2).up(j));
                        }
                    }
                }
                break;
        }
        if (disable.getValBoolean()) {
            for (final BlockPos pos : getBlacklistedBlockPoses()) {
                blocks.remove(pos);
            }
        }
        return blocks;
    }

    @Subscribe
    public void onMove(final EventTwelvetupleTick event) {
        if (mc.player.age < 15) return;
        if (minedBlockList.size() > 60) minedBlockList.clear();

        AutoEat autoEat = (AutoEat) Banana.getFeatureManager().getFeature(AutoEat.class);

        if (autoEat.isEating() || !mc.player.isOnGround()) return;

        for (final BlockPos pos : getBlocks()) {
            if (mc.world.getBlockState(pos).isAir() || mc.world.getBlockState(pos).getBlock().equals(Blocks.WATER) || mc.world.getBlockState(pos).getBlock().equals(Blocks.LAVA)) continue;
            if (getBlacklistedBlockPoses().contains(pos) && disable.getValBoolean()) continue;
            mine(pos);
        }
    }

    private void mine(final BlockPos pos) {
        mc.getNetworkHandler().sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.START_DESTROY_BLOCK, pos, Direction.DOWN));
        mc.getNetworkHandler().sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.STOP_DESTROY_BLOCK, pos, Direction.DOWN));
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
                returned.add(mc.player.getBlockPos().north(3).up(2));
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
                returned.add(mc.player.getBlockPos().up(2));
                returned.add(mc.player.getBlockPos().up(2).east());
                returned.add(mc.player.getBlockPos().up(2).east(2));
                returned.add(mc.player.getBlockPos().west().up(2));
                returned.add(mc.player.getBlockPos().west(2).up(2));
                returned.add(mc.player.getBlockPos().west(3).up(2));
        }
        return returned;
    }


}
