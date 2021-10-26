package org.quantumclient.banana.module.render;

import org.lwjgl.glfw.GLFW;
import org.quantumclient.banana.module.Category;
import org.quantumclient.banana.module.Feature;
import org.quantumclient.banana.ui.ActualScreen;

public class ClickGUI extends Feature {

    @Override
    public void onEnable() {
        toggle();
        mc.setScreen(new ActualScreen());
    }

    public ClickGUI() {
        super("click-gui", Category.render, GLFW.GLFW_KEY_UP);
    }

}
