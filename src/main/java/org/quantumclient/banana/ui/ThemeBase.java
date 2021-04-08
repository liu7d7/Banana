package org.quantumclient.banana.ui;

import net.minecraft.client.util.math.MatrixStack;
import org.quantumclient.banana.module.Category;
import org.quantumclient.banana.utilities.MinecraftInterface;

import java.util.ArrayList;
import java.util.List;

public class ThemeBase implements MinecraftInterface {

    public static List<ThemeBase> themes = new ArrayList<ThemeBase>();

    private final String name;

    public ThemeBase(String name) {
        this.name = name;
    }

    public static void initThemes() {
        themes.add(new Watercolor());
    }

    public static ThemeBase getTheme() {
        return themes.get(0);
    }

    public void drawTitle(MatrixStack matrices, Category category, Window parent) {

    }

    public void drawButton(MatrixStack matrices, String text, Window window, Button button, boolean toggled) {

    }


}
