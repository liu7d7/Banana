package org.quantumclient.banana;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;

import java.io.InputStream;

public class Banana implements ModInitializer {

    protected static MinecraftClient mc = MinecraftClient.getInstance();
    public static final String NAME = "Banana";
    public static final String VERSION = "1.0-beta";

    @Override
    public void onInitialize() {
        mc.execute(this::updateTitle);
        mc.execute(this::updateIcon);
    }

    public void updateTitle() {
        final Window window = mc.getWindow();
        window.setTitle(String.format("%s-%s", NAME, VERSION));
    }

    public void updateIcon() {
        final Window window = mc.getWindow();
        try {
            InputStream icon16 = getClass().getResourceAsStream("/assets/banana/imgs/banana.png");
            InputStream icon32 = getClass().getResourceAsStream("/assets/banana/imgs/banana32.png");

            window.setIcon(icon16, icon32);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
