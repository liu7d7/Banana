package org.quantumclient.banana.module.highway;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.network.packet.s2c.play.PlaySoundIdS2CPacket;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.lwjgl.glfw.GLFW;
import org.quantumclient.banana.Banana;
import org.quantumclient.banana.event.EventPacket;
import org.quantumclient.banana.event.EventTwelvetupleTick;
import org.quantumclient.banana.module.Category;
import org.quantumclient.banana.module.Feature;
import org.quantumclient.banana.settings.Setting;
import org.quantumclient.banana.utilities.PlayerUtils;
import org.quantumclient.energy.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NukerBypass extends Feature {

    private final List<Block> blockList = new ArrayList<>();

    final static List<String> modes = Arrays.asList("1x3", "2x3", "3x3", "highway", "thin-highway");

    public static Setting blocks = new Setting("blocks", "2x3", modes);

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

    private final BlockPos lastPlayerPos = null;

    private List<BlockPos> getBlocks() {
        List<BlockPos> blocks = new ArrayList<>();
        if (this.isToggled()) {
            switch (this.blocks.getValString()) {
                case "1x3":
                    blocks = get1x3();
                    break;
                case "2x3":
                    blocks = get2x3();
                    break;
                case "3x3":
                    blocks = getCube();
                    break;
                case "highway":
                    blocks = getHighway4();
                    break;
                case "thin-highway":
                    blocks = getHighway2();
                    break;
            }
        }
        return blocks;
    }

    @Subscribe
    public void onMove(EventTwelvetupleTick event) {
        List<BlockPos> blocks = getBlocks();
        if (mc.player.age < 15) return;

        AutoEat autoEat = (AutoEat) Banana.getFeatureManager().getFeature(AutoEat.class);

        if (autoEat.isEating()) return;

        if (blocks.isEmpty()) return;

        for (BlockPos pos : blocks) {
            if (mc.world.getBlockState(pos).isAir()) continue;
            mine(pos);
        }
    }

    public List<BlockPos> getCube() {
        List<BlockPos> cubeBlocks = new ArrayList<>();
        BlockPos playerPos = new BlockPos(Math.floor(mc.player.getX()), Math.floor(mc.player.getY()), Math.floor(mc.player.getZ()));
        if (lastPlayerPos == null || !lastPlayerPos.equals(playerPos)) {
            switch (PlayerUtils.GetFacing()) {
                case East:
                    for (int i = 0; i < 7; ++i) {
                        cubeBlocks.add(playerPos.east());
                        cubeBlocks.add(playerPos.east().up());
                        cubeBlocks.add(playerPos.east().up().up());
                        cubeBlocks.add(playerPos.east().north());
                        cubeBlocks.add(playerPos.east().north().up());
                        cubeBlocks.add(playerPos.east().north().up().up());
                        cubeBlocks.add(playerPos.east().south());
                        cubeBlocks.add(playerPos.east().south().up());
                        cubeBlocks.add(playerPos.east().south().up().up());

                        playerPos = new BlockPos(playerPos).east();
                    }
                    break;
                case North:
                    for (int i = 0; i < 7; ++i) {
                        cubeBlocks.add(playerPos.north());
                        cubeBlocks.add(playerPos.north().up());
                        cubeBlocks.add(playerPos.north().up().up());
                        cubeBlocks.add(playerPos.north().east());
                        cubeBlocks.add(playerPos.north().east().up());
                        cubeBlocks.add(playerPos.north().east().up().up());
                        cubeBlocks.add(playerPos.north().west());
                        cubeBlocks.add(playerPos.north().west().up());
                        cubeBlocks.add(playerPos.north().west().up().up());

                        playerPos = new BlockPos(playerPos).north();
                    }
                    break;
                case South:
                    for (int i = 0; i < 7; ++i) {
                        cubeBlocks.add(playerPos.south());
                        cubeBlocks.add(playerPos.south().up());
                        cubeBlocks.add(playerPos.south().up().up());
                        cubeBlocks.add(playerPos.south().west());
                        cubeBlocks.add(playerPos.south().west().up());
                        cubeBlocks.add(playerPos.south().west().up().up());
                        cubeBlocks.add(playerPos.south().east());
                        cubeBlocks.add(playerPos.south().east().up());
                        cubeBlocks.add(playerPos.south().east().up().up());

                        playerPos = new BlockPos(playerPos).south();
                    }
                    break;
                case West:
                    for (int i = 0; i < 7; ++i) {
                        cubeBlocks.add(playerPos.west());
                        cubeBlocks.add(playerPos.west().up());
                        cubeBlocks.add(playerPos.west().up().up());
                        cubeBlocks.add(playerPos.west().south());
                        cubeBlocks.add(playerPos.west().south().up());
                        cubeBlocks.add(playerPos.west().south().up().up());
                        cubeBlocks.add(playerPos.west().north());
                        cubeBlocks.add(playerPos.west().north().up());
                        cubeBlocks.add(playerPos.west().north().up().up());


                        playerPos = new BlockPos(playerPos).west();
                    }
                    break;
            }
        }
        return cubeBlocks;
    }

    public List<BlockPos> get1x3() {
        List<BlockPos> cubeBlocks = new ArrayList<>();
        BlockPos playerPos = new BlockPos(Math.floor(mc.player.getX()), Math.floor(mc.player.getY()), Math.floor(mc.player.getZ()));
        if (lastPlayerPos == null || !lastPlayerPos.equals(playerPos)) {
            switch (PlayerUtils.GetFacing()) {
                case East:
                    for (int i = 0; i < 7; ++i) {
                        cubeBlocks.add(playerPos.east());
                        cubeBlocks.add(playerPos.east().up());
                        cubeBlocks.add(playerPos.east().up().up());
                        playerPos = new BlockPos(playerPos).east();
                    }
                    break;
                case North:
                    for (int i = 0; i < 7; ++i) {
                        cubeBlocks.add(playerPos.north());
                        cubeBlocks.add(playerPos.north().up());
                        cubeBlocks.add(playerPos.north().up().up());

                        playerPos = new BlockPos(playerPos).north();
                    }
                    break;
                case South:
                    for (int i = 0; i < 7; ++i) {
                        cubeBlocks.add(playerPos.south());
                        cubeBlocks.add(playerPos.south().up());
                        cubeBlocks.add(playerPos.south().up().up());

                        playerPos = new BlockPos(playerPos).south();
                    }
                    break;
                case West:
                    for (int i = 0; i < 7; ++i) {
                        cubeBlocks.add(playerPos.west());
                        cubeBlocks.add(playerPos.west().up());
                        cubeBlocks.add(playerPos.west().up().up());


                        playerPos = new BlockPos(playerPos).west();
                    }
                    break;
            }
        }
        return cubeBlocks;
    }

    public List<BlockPos> get2x3() {
        List<BlockPos> cubeBlocks = new ArrayList<>();
        BlockPos playerPos = new BlockPos(Math.floor(mc.player.getX()), Math.floor(mc.player.getY()), Math.floor(mc.player.getZ()));
        if (lastPlayerPos == null || !lastPlayerPos.equals(playerPos)) {
            switch (PlayerUtils.GetFacing()) {
                case East:
                    for (int i = 0; i < 4; ++i) {
                        cubeBlocks.add(playerPos.east());
                        cubeBlocks.add(playerPos.east().up());
                        cubeBlocks.add(playerPos.east().up(2));
                        cubeBlocks.add(playerPos.east().north());
                        cubeBlocks.add(playerPos.east().north().up());
                        cubeBlocks.add(playerPos.east().north().up(2));
                        playerPos = new BlockPos(playerPos).east();
                    }
                    break;
                case North:
                    for (int i = 0; i < 4; ++i) {
                        cubeBlocks.add(playerPos.north());
                        cubeBlocks.add(playerPos.north().up());
                        cubeBlocks.add(playerPos.north().up().up());
                        cubeBlocks.add(playerPos.north().east());
                        cubeBlocks.add(playerPos.north().east().up());
                        cubeBlocks.add(playerPos.north().east().up().up());
                        playerPos = new BlockPos(playerPos).north();
                    }
                    break;
                case South:
                    for (int i = 0; i < 4; ++i) {
                        cubeBlocks.add(playerPos.south());
                        cubeBlocks.add(playerPos.south().up());
                        cubeBlocks.add(playerPos.south().up().up());
                        cubeBlocks.add(playerPos.south().west());
                        cubeBlocks.add(playerPos.south().west().up());
                        cubeBlocks.add(playerPos.south().west().up().up());
                        playerPos = new BlockPos(playerPos).south(i);
                    }
                    break;
                case West:
                    for (int i = 0; i < 4; ++i) {
                        cubeBlocks.add(playerPos.west());
                        cubeBlocks.add(playerPos.west().up());
                        cubeBlocks.add(playerPos.west().up().up());
                        cubeBlocks.add(playerPos.west().south());
                        cubeBlocks.add(playerPos.west().south().up());
                        cubeBlocks.add(playerPos.west().south().up().up());
                        playerPos = new BlockPos(playerPos).west();
                    }
                    break;
            }
        }
        return cubeBlocks;
    }
    public List<BlockPos> getHighway4() {
        List<BlockPos> cubeBlocks = new ArrayList<>();
        BlockPos playerPos = new BlockPos(Math.floor(mc.player.getX()), Math.floor(mc.player.getY()), Math.floor(mc.player.getZ()));
        switch (PlayerUtils.GetFacing()) {
            case East:
                for (int i = 0; i < 4; ++i) {
                    cubeBlocks.add(playerPos.east());
                    cubeBlocks.add(playerPos.east().up());
                    cubeBlocks.add(playerPos.east().up(2));
                    cubeBlocks.add(playerPos.east().up(3));
                    cubeBlocks.add(playerPos.east().south());
                    cubeBlocks.add(playerPos.east().south().up());
                    cubeBlocks.add(playerPos.east().south().up(2));
                    cubeBlocks.add(playerPos.east().south().up(3));
                    cubeBlocks.add(playerPos.east().south(2).up());
                    cubeBlocks.add(playerPos.east().south(2).up(2));
                    cubeBlocks.add(playerPos.east().south(2).up(3));
                    cubeBlocks.add(playerPos.east().north());
                    cubeBlocks.add(playerPos.east().north().up());
                    cubeBlocks.add(playerPos.east().north().up(2));
                    cubeBlocks.add(playerPos.east().north().up(3));
                    cubeBlocks.add(playerPos.east().north(2));
                    cubeBlocks.add(playerPos.east().north(2).up());
                    cubeBlocks.add(playerPos.east().north(2).up(2));
                    cubeBlocks.add(playerPos.east().north(2).up(3));
                    cubeBlocks.add(playerPos.east().north(3).up());
                    cubeBlocks.add(playerPos.east().north(3).up(2));
                    cubeBlocks.add(playerPos.east().north(3).up(3));
                    playerPos = new BlockPos(playerPos).east();
                }
                break;
            case North:
                for (int i = 0; i < 4; ++i) {
                    cubeBlocks.add(playerPos.north());
                    cubeBlocks.add(playerPos.north().up());
                    cubeBlocks.add(playerPos.north().up(2));
                    cubeBlocks.add(playerPos.north().up(3));
                    cubeBlocks.add(playerPos.north().east());
                    cubeBlocks.add(playerPos.north().east().up());
                    cubeBlocks.add(playerPos.north().east().up(2));
                    cubeBlocks.add(playerPos.north().east().up(3));
                    cubeBlocks.add(playerPos.north().east(2).up());
                    cubeBlocks.add(playerPos.north().east(2).up(2));
                    cubeBlocks.add(playerPos.north().east(2).up(3));
                    cubeBlocks.add(playerPos.north().west());
                    cubeBlocks.add(playerPos.north().west().up());
                    cubeBlocks.add(playerPos.north().west().up(2));
                    cubeBlocks.add(playerPos.north().west().up(3));
                    cubeBlocks.add(playerPos.north().west(2));
                    cubeBlocks.add(playerPos.north().west(2).up());
                    cubeBlocks.add(playerPos.north().west(2).up(2));
                    cubeBlocks.add(playerPos.north().west(2).up(3));
                    cubeBlocks.add(playerPos.north().west(3).up());
                    cubeBlocks.add(playerPos.north().west(3).up(2));
                    cubeBlocks.add(playerPos.north().west(3).up(3));
                    playerPos = new BlockPos(playerPos).north();
                }
                break;
            case South:
                for (int i = 0; i < 4; ++i) {
                    cubeBlocks.add(playerPos.south());
                    cubeBlocks.add(playerPos.south().up());
                    cubeBlocks.add(playerPos.south().up(2));
                    cubeBlocks.add(playerPos.south().up(3));
                    cubeBlocks.add(playerPos.south().west());
                    cubeBlocks.add(playerPos.south().west().up());
                    cubeBlocks.add(playerPos.south().west().up(2));
                    cubeBlocks.add(playerPos.south().west().up(3));
                    cubeBlocks.add(playerPos.south().west(2).up());
                    cubeBlocks.add(playerPos.south().west(2).up(2));
                    cubeBlocks.add(playerPos.south().west(2).up(3));
                    cubeBlocks.add(playerPos.south().east());
                    cubeBlocks.add(playerPos.south().east().up());
                    cubeBlocks.add(playerPos.south().east().up(2));
                    cubeBlocks.add(playerPos.south().east().up(3));
                    cubeBlocks.add(playerPos.south().east(2));
                    cubeBlocks.add(playerPos.south().east(2).up());
                    cubeBlocks.add(playerPos.south().east(2).up(2));
                    cubeBlocks.add(playerPos.south().east(2).up(3));
                    cubeBlocks.add(playerPos.south().east(3).up());
                    cubeBlocks.add(playerPos.south().east(3).up(2));
                    cubeBlocks.add(playerPos.south().east(3).up(3));
                    playerPos = new BlockPos(playerPos).south();
                }
                break;
            case West:
                for (int i = 0; i < 4; ++i) {
                    cubeBlocks.add(playerPos.west());
                    cubeBlocks.add(playerPos.west().up());
                    cubeBlocks.add(playerPos.west().up(2));
                    cubeBlocks.add(playerPos.west().up(3));
                    cubeBlocks.add(playerPos.west().north());
                    cubeBlocks.add(playerPos.west().north().up());
                    cubeBlocks.add(playerPos.west().north().up(2));
                    cubeBlocks.add(playerPos.west().north().up(3));
                    cubeBlocks.add(playerPos.west().north(2).up());
                    cubeBlocks.add(playerPos.west().north(2).up(2));
                    cubeBlocks.add(playerPos.west().north(2).up(3));
                    cubeBlocks.add(playerPos.west().south());
                    cubeBlocks.add(playerPos.west().south().up());
                    cubeBlocks.add(playerPos.west().south().up(2));
                    cubeBlocks.add(playerPos.west().south().up(3));
                    cubeBlocks.add(playerPos.west().south(2));
                    cubeBlocks.add(playerPos.west().south(2).up());
                    cubeBlocks.add(playerPos.west().south(2).up(2));
                    cubeBlocks.add(playerPos.west().south(2).up(3));
                    cubeBlocks.add(playerPos.west().south(3).up());
                    cubeBlocks.add(playerPos.west().south(3).up(2));
                    cubeBlocks.add(playerPos.west().south(3).up(3));
                    playerPos = new BlockPos(playerPos).west();
                }
                break;
        }
        return cubeBlocks;
    }

    public List<BlockPos> getHighway2() {
        List<BlockPos> cubeBlocks = new ArrayList<>();
        BlockPos playerPos = new BlockPos(Math.floor(mc.player.getX()), Math.floor(mc.player.getY()), Math.floor(mc.player.getZ()));
        switch (PlayerUtils.GetFacing()) {
            case East:
                for (int i = 0; i < 7; ++i) {
                    cubeBlocks.add(playerPos.east());
                    cubeBlocks.add(playerPos.east().up());
                    cubeBlocks.add(playerPos.east().up().up());
                    cubeBlocks.add(playerPos.east().up().up().up());
                    cubeBlocks.add(playerPos.east().south().up());
                    cubeBlocks.add(playerPos.east().south().up().up());
                    cubeBlocks.add(playerPos.east().south().up().up().up());
                    cubeBlocks.add(playerPos.east().north());
                    cubeBlocks.add(playerPos.east().north().up());
                    cubeBlocks.add(playerPos.east().north().up().up());
                    cubeBlocks.add(playerPos.east().north().up().up().up());
                    cubeBlocks.add(playerPos.east().north().north().up());
                    cubeBlocks.add(playerPos.east().north().north().up().up());
                    cubeBlocks.add(playerPos.east().north().north().up().up().up());
                    playerPos = new BlockPos(playerPos).east();
                }
                break;
            case North:
                for (int i = 0; i < 7; ++i) {
                    cubeBlocks.add(playerPos.north());
                    cubeBlocks.add(playerPos.north().up());
                    cubeBlocks.add(playerPos.north().up().up());
                    cubeBlocks.add(playerPos.north().up().up().up());
                    cubeBlocks.add(playerPos.north().west().up());
                    cubeBlocks.add(playerPos.north().west().up().up());
                    cubeBlocks.add(playerPos.north().west().up().up().up());
                    cubeBlocks.add(playerPos.north().east());
                    cubeBlocks.add(playerPos.north().east().up());
                    cubeBlocks.add(playerPos.north().east().up().up());
                    cubeBlocks.add(playerPos.north().east().up().up().up());
                    cubeBlocks.add(playerPos.north().east().north().up());
                    cubeBlocks.add(playerPos.north().east().north().up().up());
                    cubeBlocks.add(playerPos.north().east().north().up().up().up());
                    playerPos = new BlockPos(playerPos).north();
                }
                break;
            case South:
                for (int i = 0; i < 7; ++i) {
                    cubeBlocks.add(playerPos.south());
                    cubeBlocks.add(playerPos.south().up());
                    cubeBlocks.add(playerPos.south().up().up());
                    cubeBlocks.add(playerPos.south().up().up().up());
                    cubeBlocks.add(playerPos.south().east().up());
                    cubeBlocks.add(playerPos.south().east().up().up());
                    cubeBlocks.add(playerPos.south().east().up().up().up());
                    cubeBlocks.add(playerPos.south().west());
                    cubeBlocks.add(playerPos.south().west().up());
                    cubeBlocks.add(playerPos.south().west().up().up());
                    cubeBlocks.add(playerPos.south().west().up().up().up());
                    cubeBlocks.add(playerPos.south().west().west().up());
                    cubeBlocks.add(playerPos.south().west().west().up().up());
                    cubeBlocks.add(playerPos.south().west().west().up().up().up());
                    playerPos = new BlockPos(playerPos).south();
                }
                break;
            case West:
                for (int i = 0; i < 7; ++i) {
                    cubeBlocks.add(playerPos.west());
                    cubeBlocks.add(playerPos.west().up());
                    cubeBlocks.add(playerPos.west().up().up());
                    cubeBlocks.add(playerPos.west().up().up().up());
                    cubeBlocks.add(playerPos.west().north().up());
                    cubeBlocks.add(playerPos.west().north().up().up());
                    cubeBlocks.add(playerPos.west().north().up().up().up());
                    cubeBlocks.add(playerPos.west().south());
                    cubeBlocks.add(playerPos.west().south().up());
                    cubeBlocks.add(playerPos.west().south().up().up());
                    cubeBlocks.add(playerPos.west().south().up().up().up());
                    cubeBlocks.add(playerPos.west().south().south().up());
                    cubeBlocks.add(playerPos.west().south().south().up().up());
                    cubeBlocks.add(playerPos.west().south().south().up().up().up());
                    playerPos = new BlockPos(playerPos).west();
                }
                break;
        }
        return cubeBlocks;
    }

    private void mine(BlockPos pos) {
        mc.interactionManager.updateBlockBreakingProgress(pos, Direction.DOWN);
        mc.interactionManager.updateBlockBreakingProgress(pos, Direction.DOWN);
        mc.player.swingHand(Hand.MAIN_HAND);
    }

}
