package org.quantumclient.banana.module.highway;

import com.google.common.collect.Streams;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import org.lwjgl.glfw.GLFW;
import org.quantumclient.banana.Banana;
import org.quantumclient.banana.event.EventTwelvetupleTick;
import org.quantumclient.banana.module.Category;
import org.quantumclient.banana.module.Feature;
import org.quantumclient.banana.settings.Setting;
import org.quantumclient.banana.utilities.PlayerUtils;
import org.quantumclient.energy.Subscribe;

import java.util.List;
import java.util.stream.Collectors;

public class AutoWalk extends Feature {

    public static final Setting autoAlign = new Setting("AutoAlign", false);
    public static final Setting pauseNoPickaxe = new Setting("NoPickaxePause", false);
    public static final Setting player = new Setting("player", false);
    public static final Setting stop = new Setting("StopY121", false);

    @Override
    public void toggleNoSave() {

    }

    public AutoWalk() {
        super("auto-walk", Category.highway, GLFW.GLFW_KEY_I);
    }

    @Subscribe
    public void onTick(EventTwelvetupleTick event) {
        System.out.println("hi");
        AutoEat autoEat = (AutoEat) Banana.getFeatureManager().getFeature(AutoEat.class);
        boolean pause = (mc.player.getY() >= 121 && stop.getValBoolean()) || getClosestPlayer() || autoEat.isEating() || (pauseNoPickaxe.getValBoolean() && (mc.player.inventory.getMainHandStack().getItem() != Items.DIAMOND_PICKAXE && mc.player.inventory.getMainHandStack().getItem() != Items.NETHERITE_PICKAXE));
        if (true) {
            if (!pause) {
                if (autoAlign.getValBoolean()) {
                    Yaw.autoAlign.setValBoolean(false);

                    switch (PlayerUtils.determineHighway()) {
                        case XPZP:
                            mc.player.yaw = 0;
                            mc.player.headYaw = -45;
                            mc.options.keyLeft.setPressed(true);
                            mc.options.keyForward.setPressed(true);
                            break;
                        case XNZP:
                            mc.player.yaw = 90;
                            mc.player.headYaw = -135;
                            mc.options.keyForward.setPressed(true);
                            mc.options.keyLeft.setPressed(true);
                            break;
                        case XPZN:
                            mc.player.yaw = 180;
                            mc.player.yaw = 135;
                            break;
                    }
                } else {
                    mc.options.keyForward.setPressed(true);
                }
            }
            if (pause) {
                mc.options.keyForward.setPressed(false);
                mc.options.keyLeft.setPressed(false);
                mc.options.keyRight.setPressed(false);
                mc.options.keyBack.setPressed(false);
            }
        }
    }

    public void onDisable() {
        mc.options.keyForward.setPressed(false);
        mc.options.keyLeft.setPressed(false);
        mc.options.keyRight.setPressed(false);
        mc.options.keyBack.setPressed(false);
        super.onDisable();
    }

    public boolean getClosestPlayer() {
        if (player.getValBoolean()) {
            List<Entity> players =
                    Streams.stream(mc.world.getEntities()).filter(e -> e instanceof PlayerEntity).filter(e -> e != mc.player).sorted((a, b) -> Float.compare(a.distanceTo(mc.player), b.distanceTo(mc.player))).collect(Collectors.toList());
            return !players.isEmpty() && mc.player.distanceTo(players.get(0)) < 15;
        }
        return false;
    }
    
}
