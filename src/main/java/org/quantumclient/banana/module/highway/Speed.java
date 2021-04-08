package org.quantumclient.banana.module.highway;

import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;
import org.quantumclient.banana.event.EventPacket;
import org.quantumclient.banana.event.EventTwelvetupleTick;
import org.quantumclient.banana.module.Category;
import org.quantumclient.banana.module.Feature;
import org.quantumclient.banana.settings.Setting;
import org.quantumclient.energy.Subscribe;

import java.util.Random;

public class Speed extends Feature {

    Random random = new Random();

    boolean toggledDueToPacket = false;

    Vec3d speedVec = Vec3d.ZERO;

    public static final Setting bypass = new Setting("Bypass", false);
    public static final Setting tickDelay = new Setting("TickDelay", 10, 0, 100, 1, true);
    public static final Setting randomize = new Setting("TickRandomizer", 5, 1, 100, 1, true);
    public static final Setting addition = new Setting("TickConstant", 8, 0, 100, 1, true);
    public static final Setting up2 = new Setting("StopUp2", true);
    public static final Setting Speed = new Setting("Speed", 1.56f, 0.3f, 3f, 0.01f, false);
    public static final Setting disableOnLagBack = new Setting("Disable", false);
    public static final Setting Sneak = new Setting("Sneak", false);

    public Speed() {
        super("speed", Category.highway, GLFW.GLFW_KEY_I);
    }

    @Subscribe
    public void onTick(EventTwelvetupleTick event) {
        if (bypass.getValBoolean() && (((mc.player.age % tickDelay.getValDouble()) + addition.getValDouble()) > (tickDelay.getValDouble() - random.nextInt(randomize.getValInt())) / 2) || (mc.world.getBlockState(mc.player.getBlockPos().up(2)).isAir() && up2.getValBoolean()) || (!Sneak.getValBoolean() && mc.player.isSneaking()) || (mc.player.isFallFlying())) {
            return;
        }
        double speedStrafe = Speed.getValDouble() / 3;
        double forward = mc.player.forwardSpeed;
        double strafe = mc.player.sidewaysSpeed;
        float yaw = mc.player.yaw;
        if ((forward == 0.0D) && (strafe == 0.0D)) {
            speedVec = new Vec3d(0, mc.player.getVelocity().y, 0);
        }
        else {
            if (forward != 0.0D) {
                if (strafe > 0.0D) {
                    yaw += (forward > 0.0D ? -45 : 45);
                } else if (strafe < 0.0D) yaw += (forward > 0.0D ? 45 : -45);
                strafe = 0.0D;
                if (forward > 0.0D) {
                    forward = 1.0D;
                } else if (forward < 0.0D) forward = -1.0D;
            }
            speedVec = new Vec3d((forward * speedStrafe * Math.cos(Math.toRadians(yaw + 90.0F)) + strafe * speedStrafe * Math.sin(Math.toRadians(yaw + 90.0F))), mc.player.getVelocity().y,
                    forward * speedStrafe * Math.sin(Math.toRadians(yaw + 90.0F)) - strafe * speedStrafe * Math.cos(Math.toRadians(yaw + 90.0F)));
        }
        mc.player.setVelocity(speedVec);
    }

    @Subscribe
    public void onLagBack(EventPacket.Receive event) {
        if (disableOnLagBack.getValBoolean()) {
            if (event.getPacket() instanceof PlayerPositionLookS2CPacket) {
                toggledDueToPacket = true;
                toggle();
                toggledDueToPacket = false;
            }
        }
    }

}
