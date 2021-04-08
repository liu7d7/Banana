package org.quantumclient.banana.settings;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.quantumclient.banana.Banana;
import org.quantumclient.banana.module.Feature;
import org.quantumclient.banana.module.FeatureManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author  ChiquitaV2
 */

public class LoadConfig {

    public static String folderName = SaveConfig.folderName;
    public static String moduleName = SaveConfig.moduleName;

    public LoadConfig() {
        loadModules();
    }


    public void loadModules() {
        String moduleLocation = folderName + moduleName;

        for (Feature feature : Banana.getFeatureManager().getFeatures()) {
            try {
                if (!Files.exists(Paths.get(moduleLocation + feature.getName() + ".json"))) {
                    return;
                }

                InputStream inputStream = Files.newInputStream(Paths.get(moduleLocation + feature.getName() + ".json"));
                JsonObject moduleObject = new JsonParser().parse(new InputStreamReader(inputStream)).getAsJsonObject();

                if (moduleObject.get("Module") == null) {
                    return;
                }

                JsonObject toggleObject = moduleObject.get("Toggled").getAsJsonObject();
                JsonElement toggleElement = toggleObject.get("Toggled");
                // feature.setToggled(toggleElement.getAsBoolean());
                if (toggleElement.getAsBoolean()) {
                    if(!feature.isToggled())
                        feature.toggleNoSave();
                }
                else if (feature.isToggled())
                    feature.toggle();

                JsonObject bindObject = moduleObject.get("Bind").getAsJsonObject();
                JsonElement bindElement = bindObject.get("Bind");
                feature.setKeyBind(Integer.parseInt(bindElement.toString()));

                JsonObject settingObject = moduleObject.get("Settings").getAsJsonObject();

                if (feature.hasSettings()) {
                    for (Setting settingsList : feature.getSettings()) {
                        JsonElement valueElement = settingObject.get(settingsList.getName());
                        if (settingsList.isCheck() && valueElement != null) {
                            settingsList.setValBoolean(valueElement.getAsBoolean());
                        } else if (settingsList.isSlider() && valueElement != null) {
                            settingsList.setValDouble(valueElement.getAsDouble());
                        }  else if (settingsList.isCombo() && valueElement != null) {
                            if (valueElement.getAsString() != null) {
                                settingsList.setValString(valueElement.getAsString());
                            }
                        }
                    }
                }


                inputStream.close();
            } catch (IOException e) {
                System.out.println(feature.getName());
                e.printStackTrace();
            }
        }
    }


}