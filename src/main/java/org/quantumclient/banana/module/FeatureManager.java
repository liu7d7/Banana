package org.quantumclient.banana.module;

import net.minecraft.client.gui.screen.ChatScreen;
import org.lwjgl.glfw.GLFW;
import org.quantumclient.banana.event.KeyPressEvent;
import org.quantumclient.banana.module.highway.*;
import org.quantumclient.banana.module.render.ClickGUI;
import org.quantumclient.banana.settings.Setting;
import org.quantumclient.energy.EventBus;
import org.quantumclient.energy.Subscribe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.quantumclient.banana.module.Feature.mc;

public class FeatureManager {

    private static List<Feature> features = new ArrayList<>();

    public void init() {
        EventBus.register(this);
        add(new AutoEat());
        add(new AutoWalk());
        add(new HotbarCache());
        add(new NukerBypass());
        add(new Speed());
        add(new Yaw());
        add(new ClickGUI());
    }

    public Feature getFeature(Class clazz) {
        for (Feature feature : features) {
            if (feature.getClass() == clazz) {
                return feature;
            }
        }
        return null;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void add(Feature feature) {
        try
        {
            for (Field field : feature.getClass().getDeclaredFields())
            {
                if (Setting.class.isAssignableFrom(field.getType()))
                {
                    if (!field.isAccessible())
                    {
                        field.setAccessible(true);
                    }
                    final Setting setting = (Setting) field.get(feature);
                    if (setting != null) {
                        System.out.println("hi");
                        setting.initializeFeature(feature);
                        feature.getSettings().add(setting);
                    }
                }
            }
            features.add(feature);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public List<Feature> getFeaturesInCategory(Category category) {
        List<Feature> fortnite = new ArrayList<>();
        for (Feature feature : features) {
            if (feature.getCategory() == category) {
                fortnite.add(feature);
            }
        }
        return fortnite;
    }

    @Subscribe
    public void onKey(KeyPressEvent event) {
        if (event.action != GLFW.GLFW_PRESS) return;
        if (mc.currentScreen != null) return;
        if (event.keyCode == GLFW.GLFW_KEY_COMMA) {
            mc.openScreen(new ChatScreen(""));
        }
        features.stream().filter(f -> f.getKeyBind() == event.keyCode).forEach(Feature::toggle);
    }


}
