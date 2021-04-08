package org.quantumclient.banana.ui;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.lwjgl.opengl.GL11;
import org.quantumclient.banana.Banana;
import org.quantumclient.banana.module.Category;
import org.quantumclient.banana.utilities.RenderUtil;

public class Watercolor extends ThemeBase {

    final Identifier WATERCOLOR_SQUARES = new Identifier("banana:imgs/watercolor.png");
    final Identifier WATERCOLOR_ICONS = new Identifier("banana:imgs/watercolorbuttonsshort.png");
    final Identifier FOLDER_ICON = new Identifier("banana:imgs/foldericon.png");

    public Watercolor() {
        super("Watercolor");
    }

    @Override
    public void drawTitle(MatrixStack matrices, Category category, Window parent) {
        GL11.glPushMatrix();
        DrawableHelper.fill(matrices, parent.x, parent.y + 11, parent.x + 85 - 40 - 4 + 9, parent.y + 12, 0xff7db1fb);
        DrawableHelper.fill(matrices, parent.x + 85 - 40 - 7 + 9, parent.y + 11, parent.x + 85, parent.y + 12, 0xff3573d6);
        DrawableHelper.fill(matrices, parent.x, parent.y, parent.x + 85 - 40 - 4 + 9, parent.y + 11, 0xff5297f9);
        DrawableHelper.fill(matrices, parent.x + 85 - 40 - 7 + 9, parent.y, parent.x + 85, parent.y + 11, 0xff3573d6);
        RenderUtil.drawImage(matrices, (int) (parent.x + 85 - 40 - 4), parent.y, WATERCOLOR_SQUARES, 13, 11);
        RenderUtil.drawImage(matrices, parent.x + 85 - 19, parent.y + 2, WATERCOLOR_ICONS, 17, 8);
        RenderUtil.drawImage(matrices, parent.x + 2, parent.y + 3, FOLDER_ICON, 9, 8);
        DrawableHelper.fill(matrices, parent.x, parent.y, parent.x + 1, parent.y + 11, 0xff7db1fb);
        DrawableHelper.fill(matrices, parent.x, parent.y, parent.x + 85, parent.y + 1, 0xff7db1fb);
        DrawableHelper.fill(matrices, parent.x + 84, parent.y, parent.x + 85, parent.y + 11, 0xff7db1fb);
        RenderUtil.drawStringWithShadow(matrices, category.toString(), parent.x + 11 + 1, parent.y + 1 + (parent.arrayCount * 11), 0xffffffff);
        GL11.glPopMatrix();
    }

    @Override
    public void drawButton(MatrixStack matrices, String text, Window parent, Button button, boolean toggled) {
        GL11.glPushMatrix();
        DrawableHelper.fill(matrices, parent.x, parent.y + (button.arrayCount * 11), parent.x + 85, parent.y + 11 + (button.arrayCount * 11), 0xffebebe4);
        DrawableHelper.fill(matrices, parent.x, parent.y + (button.arrayCount) * 11, parent.x + 1, parent.y + (button.arrayCount + 1) * 11, 0xff7db1fb);
        DrawableHelper.fill(matrices, parent.x + 84, parent.y + (button.arrayCount) * 11, parent.x + 85, parent.y + (button.arrayCount + 1) * 11, 0xff7db1fb);
        if (parent.buttons.indexOf(button) == parent.buttons.size() - 1) {
            DrawableHelper.fill(matrices, parent.x, parent.y + (button.arrayCount) * 11 + 11, parent.x + 85 - 40 - 5 + 9, parent.y + (button.arrayCount) * 11 + 12, 0xff7db1fb);
            DrawableHelper.fill(matrices, parent.x + 85 - 40 - 5 + 9, parent.y + (button.arrayCount) * 11 + 11, parent.x + 85, parent.y + (button.arrayCount) * 11 + 12, 0xff7db1fb);
        }
        RenderUtil.drawString(matrices, text, parent.x + 2, parent.y + 1 + (button.arrayCount * 11), toggled ? 0xff3573d6 : 0xff2e2e2e);
        GL11.glPopMatrix();
    }

}
