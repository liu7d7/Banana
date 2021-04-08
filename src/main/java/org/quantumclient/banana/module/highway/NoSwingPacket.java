package org.quantumclient.banana.module.highway;

import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import org.lwjgl.glfw.GLFW;
import org.quantumclient.banana.event.EventPacket;
import org.quantumclient.banana.module.Category;
import org.quantumclient.banana.module.Feature;
import org.quantumclient.energy.Subscribe;

public class NoSwingPacket extends Feature {

    public NoSwingPacket() {
        super("no-swing-packet", Category.highway, GLFW.GLFW_KEY_I);
    }

    @Subscribe
    public void onPacket(EventPacket.Send send) {
        if (send.getPacket() instanceof HandSwingC2SPacket) {
            send.setCancelled(true);
        }
    }

}
