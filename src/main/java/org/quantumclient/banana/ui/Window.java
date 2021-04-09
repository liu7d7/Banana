package org.quantumclient.banana.ui;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import org.quantumclient.banana.Banana;
import org.quantumclient.banana.module.Category;
import org.quantumclient.banana.module.FeatureManager;
import org.quantumclient.banana.settings.Setting;
import org.quantumclient.banana.utilities.MinecraftInterface;

import java.util.ArrayList;
import java.util.List;

public class Window implements MinecraftInterface {

    public List<Button> buttons = new ArrayList<>();

    public int x, y;
    public Category category;
    public int arrayCount;
    public boolean dragging;
    public ActualScreen parent;
    public int dragX, dragY;

    public Window(int x, int y, Category category, ActualScreen parent) {
        this.x = x;
        this.y = y;
        this.category = category;
        this.parent = parent;
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY) {
        buttons.clear();
        arrayCount = 0;
        ThemeBase.getTheme().drawTitle(matrices, this.category, this);
        arrayCount++;
        for(org.quantumclient.banana.module.Feature feature : Banana.getFeatureManager().getFeaturesInCategory(this.category)) {
            buttons.add(new Button(feature, x, y, arrayCount, ButtonType.mod, this, feature.isToggled()));
            if (feature.isDrawn()) {
                if (feature.hasSettings()) {
                    for (Setting s : feature.getSettings()) {
                        arrayCount++;
                        if(s.isCheck()) {
                            buttons.add(new Button(s, x, y, arrayCount, ButtonType.bool, this, s.getValBoolean()));
                        }
                        if (s.isCombo()) {
                            buttons.add(new Button(s, x, y, arrayCount, ButtonType.string, this, false));
                        }
                        if (s.isSlider()) {
                            buttons.add(new Button(s, x, y, arrayCount, ButtonType.doublePrecision, this, false));
                        }
                    }
                }
                arrayCount++;
                buttons.add(new Button(feature, x, y, arrayCount, ButtonType.bind, this, false));
            }
            arrayCount++;
        }
        for (Button b : buttons) {
            b.render(matrices);
        }
        boolean haiku = false;
        DrawableHelper.fill(matrices, this.x, this.y + 11, this.x + 85 - 40 - 5 + 9, this.y + 12, 0xff7db1fb);
        DrawableHelper.fill(matrices, this.x + 85 - 40 - 5 + 9, this.y + 11, this.x + 85, this.y + 12, 0xff7db1fb);
        if (this.dragging) {
            this.x = this.dragX + mouseX;
            this.y = this.dragY + mouseY;
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        for (Button b : buttons) {
            b.mouseClicked(mouseX, mouseY, button);
        }
        this.dragX = (int) (this.x - mouseX);
        this.dragY = (int) (this.y - mouseY);
    }

    public void mouseScrolled(double mouseX, double mouseY, double button) {
        for (Button b : buttons) {
            b.mouseScrolled(mouseX, mouseY, button);
        }
    }

    public void keyTyped(int key) {
        for (Button b : buttons) {
            b.keyTyped(key);
        }
    }

    public boolean withinTitle(double mouseX, double mouseY) {
        return mouseX > x && mouseX < x + 85 && mouseY > y && mouseY < y + 11;
    }

}
