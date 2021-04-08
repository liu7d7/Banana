package org.quantumclient.banana.module;

import net.minecraft.client.MinecraftClient;
import org.quantumclient.banana.Banana;
import org.quantumclient.banana.settings.Setting;
import org.quantumclient.energy.EventBus;

import java.util.ArrayList;
import java.util.List;

public class Feature {

    private boolean binding = false, drawn = false;
    public List<Setting> settings = new ArrayList<>();
    private boolean toggled = false;
    private int keyBind = -2;
    private Category category;
    private String name;
    protected static MinecraftClient mc = MinecraftClient.getInstance();

    public Feature(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public Feature(String name, Category category, int keyBind) {
        this.name = name;
        this.category = category;
        this.keyBind = keyBind;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public boolean isToggled() {
        return toggled;
    }

    public void toggle() {
        toggled = !toggled;
        if (toggled) {
            onEnable();
        } else {
            onDisable();
        }
        Banana.getSaveConfig().saveIndividualFeature(this);
    }

    public void onEnable() {
        EventBus.register(this);
    }

    public void onDisable() {
        try {
            EventBus.unregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toggleNoSave() {
        toggled = !toggled;
        if (toggled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void setToggled(boolean b) {
        this.toggled = b;
        if (toggled) {
            onEnable();
        } else {
            onDisable();
        }
        Banana.getSaveConfig().saveIndividualFeature(this);
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public int getKeyBind() {
        return keyBind;
    }

    public String getName() {
        return name;
    }

    public void setKeyBind(int keyBind) {
        this.keyBind = keyBind;
    }

    public Category getCategory() {
        return category;
    }

    public boolean hasSettings() {
        return !this.settings.isEmpty();
    }

    public boolean isBinding() {
        return binding;
    }

    public void setBinding(boolean b) {
        this.binding = b;
    }

    public boolean isDrawn() {
        return drawn;
    }

    public void setDrawn(boolean drawn) {
        this.drawn = drawn;
    }
}
