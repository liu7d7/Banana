package org.quantumclient.banana;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quantumclient.banana.module.Feature;
import org.quantumclient.banana.module.FeatureManager;
import org.quantumclient.banana.settings.LoadConfig;
import org.quantumclient.banana.settings.SaveConfig;
import org.quantumclient.banana.ui.ThemeBase;

import java.io.InputStream;

public class Banana implements ModInitializer {

    protected static MinecraftClient mc = MinecraftClient.getInstance();
    public static final String NAME = "Banana";
    protected static SaveConfig saveConfig;
    protected static LoadConfig loadConfig;
    public static final String VERSION = "1.3";
    protected static final FeatureManager featureManager = new FeatureManager();

    @Override
    public void onInitialize() {
        featureManager.init();
        ThemeBase.initThemes();
        mc.execute(this::updateTitle);
        mc.execute(this::updateIcon);
        loadConfig = new LoadConfig();
        saveConfig = new SaveConfig();
        Runtime.getRuntime().addShutdownHook(new Thread(saveConfig::saveModules));
    }

    public static FeatureManager getFeatureManager() {
        return featureManager;
    }

    public static SaveConfig getSaveConfig() {
        return saveConfig;
    }

    public static LoadConfig getLoadConfig() {
        return loadConfig;
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
