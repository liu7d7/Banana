package org.quantumclient.banana.module.highway;

import org.lwjgl.glfw.GLFW;
import org.quantumclient.banana.event.EventTwelvetupleTick;
import org.quantumclient.banana.module.Category;
import org.quantumclient.banana.module.Feature;
import org.quantumclient.banana.settings.Setting;
import org.quantumclient.banana.utilities.PlayerUtils;
import org.quantumclient.energy.Subscribe;

public class Yaw extends Feature {

    public static final Setting autoAlign = new Setting("AutoAlign", true);

    public Yaw() {
        super("yaw", Category.highway, GLFW.GLFW_KEY_I);
    }

    @Subscribe
    public void onTick(EventTwelvetupleTick event) {
        if (autoAlign.getValBoolean()) {
            switch (PlayerUtils.determineHighway()) {
                case XP: mc.player.setYaw(-90); break;
                case XPZP: mc.player.setYaw(-45); break;
                case XPZN: mc.player.setYaw(-135); break;
                case XN: mc.player.setYaw(90); break;
                case XNZP: mc.player.setYaw(45); break;
                case XNZN: mc.player.setYaw(135); break;
                case ZP: mc.player.setYaw(0); break;
                case ZN: mc.player.setYaw(180); break;
            }
        }

    }


}
