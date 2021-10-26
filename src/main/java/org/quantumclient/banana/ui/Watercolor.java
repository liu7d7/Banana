package org.quantumclient.banana.ui;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import org.quantumclient.banana.module.Category;

public class Watercolor extends ThemeBase {

    public Watercolor() {
        super("Watercolor");
    }

    @Override
    public void drawTitle(MatrixStack matrices, Category category, Window parent) {
        DrawableHelper.fill(matrices, parent.x, parent.y + 11, parent.x + 85 - 40 - 4 + 9, parent.y + 12, 0xff7db1fb);
        DrawableHelper.fill(matrices, parent.x + 85 - 40 - 7 + 9, parent.y + 11, parent.x + 85, parent.y + 12, 0xff3573d6);
        DrawableHelper.fill(matrices, parent.x, parent.y, parent.x + 85 - 40 - 4 + 9, parent.y + 11, 0xff5297f9);
        DrawableHelper.fill(matrices, parent.x + 85 - 40 - 7 + 9, parent.y, parent.x + 85, parent.y + 11, 0xff3573d6);
        DrawableHelper.fill(matrices, parent.x, parent.y, parent.x + 1, parent.y + 11, 0xff7db1fb);
        DrawableHelper.fill(matrices, parent.x, parent.y, parent.x + 85, parent.y + 1, 0xff7db1fb);
        DrawableHelper.fill(matrices, parent.x + 84, parent.y, parent.x + 85, parent.y + 11, 0xff7db1fb);
        mc.textRenderer.drawWithShadow(matrices, category.toString(), parent.x + 11 + 1, parent.y + 1 + (parent.arrayCount * 11), 0xffffffff);
    }

    @Override
    public void drawButton(MatrixStack matrices, String text, Window parent, Button button, boolean toggled) {
        DrawableHelper.fill(matrices, parent.x, parent.y + (button.arrayCount * 11), parent.x + 85, parent.y + 11 + (button.arrayCount * 11), 0xffebebe4);
        DrawableHelper.fill(matrices, parent.x, parent.y + (button.arrayCount) * 11, parent.x + 1, parent.y + (button.arrayCount + 1) * 11, 0xff7db1fb);
        DrawableHelper.fill(matrices, parent.x + 84, parent.y + (button.arrayCount) * 11, parent.x + 85, parent.y + (button.arrayCount + 1) * 11, 0xff7db1fb);
        if (parent.buttons.indexOf(button) == parent.buttons.size() - 1) {
            DrawableHelper.fill(matrices, parent.x, parent.y + (button.arrayCount) * 11 + 11, parent.x + 85 - 40 - 5 + 9, parent.y + (button.arrayCount) * 11 + 12, 0xff7db1fb);
            DrawableHelper.fill(matrices, parent.x + 85 - 40 - 5 + 9, parent.y + (button.arrayCount) * 11 + 11, parent.x + 85, parent.y + (button.arrayCount) * 11 + 12, 0xff7db1fb);
        }
        mc.textRenderer.draw(matrices, text, parent.x + 2, parent.y + 1 + (button.arrayCount * 11), toggled ? 0xff3573d6 : 0xff2e2e2e);
    }

}
