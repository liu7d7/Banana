package org.quantumclient.banana.ui;

import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;
import org.quantumclient.banana.module.Feature;
import org.quantumclient.banana.settings.Setting;
import org.quantumclient.banana.utilities.BananaHelper;

public class Button {

    public Feature feature;
    public int baseX, baseY, arrayCount;
    public ButtonType type;
    public Setting setting;
    public Window parent;
    public boolean toggled;

    public Button(Feature module, int baseX, int baseY, int arrayCount, ButtonType type, Window parent, boolean moduleToggled) {
        this.feature = module;
        this.baseX = baseX;
        this.baseY = baseY;
        this.type = type;
        this.arrayCount = arrayCount;
        this.parent = parent;
        this.toggled = moduleToggled;
    }

    public Button(Setting setting, int baseX, int baseY, int arrayCount, ButtonType type, Window parent, boolean moduleToggled) {
        this.setting = setting;
        this.baseX = baseX;
        this.baseY = baseY;
        this.type = type;
        this.arrayCount = arrayCount;
        this.parent = parent;
        this.toggled = moduleToggled;
    }

    public void render(MatrixStack matrices) {
        if (this.type == ButtonType.bind) {
            ThemeBase.getTheme().drawButton(matrices, "bind: " + ((feature.getKeyBind() < 0) ? "none" : InputUtil.fromKeyCode(feature.getKeyBind(), -1).toString().substring(13).replace('.', ' ')), parent, this, this.toggled);
        }
        if (this.type == ButtonType.mod) {
            ThemeBase.getTheme().drawButton(matrices, feature.getName(), parent, this, this.toggled);
        }
        if (this.type == ButtonType.bool) {
            ThemeBase.getTheme().drawButton(matrices, setting.getName(), parent, this, this.toggled);
        }
        if (this.type == ButtonType.doublePrecision) {
            ThemeBase.getTheme().drawButton(matrices, setting.getName() + ": " + BananaHelper.round(setting.getValDouble(), 2), parent, this, this.toggled);
        }
        if (this.type == ButtonType.string) {
            ThemeBase.getTheme().drawButton(matrices, setting.getName() + ": " + setting.getValString(), parent, this, this.toggled);
        }
    }

    public boolean isWithin(double mouseX, double mouseY) {
        return mouseX > this.baseX && mouseX < this.baseX + 85 && mouseY > this.baseY + this.arrayCount * 11 && mouseY < this.baseY + (this.arrayCount + 1) * 11;
    }

    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (isWithin(mouseX, mouseY)) {
            if (mouseButton == 0) {
                this.handleLMB(mouseX, mouseY);
            }
            if (mouseButton == 1) {
                this.handleRMB();
            }
            if (mouseButton == 2) {
                this.handleMMB();
            }
        }
    }

    public void handleLMB(double mouseX, double mouseY) {
        if (this.type == ButtonType.bind) {
            this.feature.setBinding(!this.feature.isBinding());
        }
        if (this.type == ButtonType.mod) {
            this.feature.toggle();
        }
        if (this.type == ButtonType.string) {
            this.setting.setValString(setting.getNextInList(false));
        }
        if (this.type == ButtonType.doublePrecision) {
            this.setting.setValDouble((((double) (mouseX - baseX)) / 85) * (setting.getMax() - setting.getMin()) + setting.getMin());
        }
        if (this.type == ButtonType.bool) {
            this.setting.setValBoolean(!this.setting.getValBoolean());
        }
        if (this.type == ButtonType.doublePrecision) {
            this.setting.setValDouble(this.setting.getValDouble() - this.setting.getInc());
        }
    }

    public void handleRMB() {
        if (this.type == ButtonType.string) {
            this.setting.setValString(setting.getNextInList(true));
        }
        if (this.type == ButtonType.mod) {
            this.feature.setDrawn(!this.feature.isDrawn());
        }
        if (this.type == ButtonType.doublePrecision) {
            this.setting.setValDouble(this.setting.getValDouble() + this.setting.getInc());
        }
    }

    public void handleMMB() {
        if (this.type == ButtonType.bind) {
            this.feature.setKeyBind(-2);
        }
    }


    public void keyTyped(int key) {
        if (this.type == ButtonType.bind) {
            if (this.feature.isBinding()) {
                this.feature.setKeyBind(key);
                this.feature.setBinding(false);
            }
        }
    }


}
