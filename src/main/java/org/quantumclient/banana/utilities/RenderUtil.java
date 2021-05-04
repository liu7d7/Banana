package org.quantumclient.banana.utilities;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.util.Identifier;

public class RenderUtil extends DrawableHelper implements MinecraftInterface {

    public static void drawImage(MatrixStack matrices, int x, int y, Identifier image, int width, int height) {
        mc.getTextureManager().bindTexture(image);
        drawTexture(matrices, x, y, 0, 0, width, height, width, height);
    }

    public static final Identifier FONT = new Identifier("banana", "conso");

    private static MutableText formatText(String text){
        return new LiteralText(text).styled(style -> style.withFont(FONT));
    }

    public static void drawStringWithShadow(MatrixStack matrices, String text, int x, int y, int color) {
        mc.textRenderer.drawWithShadow(matrices, formatText(text), x, y, color);
    }

    public static void drawString(MatrixStack matrices, String text, int x, int y, int color) {
        mc.textRenderer.draw(matrices, formatText(text), x, y, color);
    }

}
