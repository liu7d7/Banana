package org.quantumclient.banana.ui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.quantumclient.banana.Banana;
import org.quantumclient.banana.module.Category;
import org.quantumclient.banana.module.render.ClickGUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActualScreen extends Screen {

    public ActualScreen() {
        super(Text.of("ActualScreen"));
    }

    List<Window> windows = new ArrayList<>();

    public void init() {
        windows.add(new Window(50, 50, Category.render, this));
        windows.add(new Window(150, 75, Category.highway, this));
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        for (Window window : windows) {
            window.mouseClicked(mouseX, mouseY, mouseButton);
            if (window.withinTitle(mouseX, mouseY)) {
                window.dragging = true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double mouseButton) {
        for (Window window : windows) {
            window.mouseScrolled(mouseX, mouseY, mouseButton);
        }
        return super.mouseScrolled(mouseX, mouseY, mouseButton);
    }


    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float partialTicks) {
        for (Window window : windows) {
            window.render(matrices, mouseX, mouseY);
        }
    }

    @Override
    public void onClose() {
        super.onClose();
        Banana.getFeatureManager().getFeature(ClickGUI.class).setToggled(false);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int mouseButton) {
        for (Window window : windows) {
            window.dragging = false;
        }
        return super.mouseReleased(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean charTyped(char typed, int keyCode) {
        System.out.println(keyCode);
        for (Window w : windows) {
            w.keyTyped(keyCode);
        }
        return super.charTyped(typed, keyCode);
    }

}
