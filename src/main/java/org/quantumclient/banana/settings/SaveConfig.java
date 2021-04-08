package org.quantumclient.banana.settings;

import com.google.gson.*;
import org.quantumclient.banana.Banana;
import org.quantumclient.banana.module.Feature;
import org.quantumclient.banana.utilities.Timer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * @author  ChiquitaV2
 */
public class SaveConfig {

    public static Timer saveTimer;

    public SaveConfig() {
        try {
            saveConfig();
            saveModules();
            saveTimer = new Timer();
            timedSave();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String folderName = "Banana/";
    public static String moduleName = "Modifications/";

    public void saveConfig() throws IOException {
        if (!Files.exists(Paths.get(folderName))) {
            Files.createDirectories(Paths.get(folderName));
        }
        if (!Files.exists(Paths.get(folderName + moduleName))) {
            Files.createDirectories(Paths.get(folderName + moduleName));
        }
    }

    public void saveModules() {
        for (Feature feature : Banana.getFeatureManager().getFeatures()) {
            saveIndividualFeature(feature);
        }
    }

    public void saveIndividualFeature(Feature feature) {
        try {
            makeFile(moduleName, feature.getName());

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            OutputStreamWriter fileOutputStreamWriter = new OutputStreamWriter(new FileOutputStream(folderName + moduleName + feature.getName() + ".json"), StandardCharsets.UTF_8);
            JsonObject moduleObject = new JsonObject();
            JsonObject settingObject = new JsonObject();
            JsonObject toggledObject = new JsonObject();
            JsonObject hiddenObject = new JsonObject();
            JsonObject bindObject = new JsonObject();
            moduleObject.add("Module", new JsonPrimitive(feature.getName()));
            toggledObject.add("Toggled", new JsonPrimitive(feature.isToggled()));
            bindObject.add("Bind", new JsonPrimitive(feature.getKeyBind()));

            if (feature.hasSettings()) {
                for (Setting settingsList : feature.getSettings()) {
                    if (settingsList.isCheck()) {
                        settingObject.add(settingsList.getName(), new JsonPrimitive(((Boolean) settingsList.getValBoolean())));
                    }  else if (settingsList.isSlider()) {
                        settingObject.add(settingsList.getName(), new JsonPrimitive((((Double) settingsList.getValDouble()))));
                    }  else if (settingsList.isCombo()) {
                        settingObject.add(settingsList.getName(), new JsonPrimitive(settingsList.getValString().toString()));
                    }
                }

            }

            moduleObject.add("Toggled", toggledObject);
            moduleObject.add("Hidden", hiddenObject);
            moduleObject.add("Bind", bindObject);
            moduleObject.add("Settings", settingObject);
            String jsonString = gson.toJson(new JsonParser().parse(moduleObject.toString()));
            fileOutputStreamWriter.write(jsonString);
            fileOutputStreamWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makeFile(String location, String name) throws IOException {
        if (location != null) {
            if (!Files.exists(Paths.get(folderName + location + name + ".json"))) {
                Files.createFile(Paths.get(folderName + location + name + ".json"));
            }
            else {
                File file = new File(folderName + location + name + ".json");

                if (file.delete()) {
                    Files.createFile(Paths.get(folderName + location + name + ".json"));
                }
            }
        } else {
            if (!Files.exists(Paths.get(folderName + name + ".json"))) {
                Files.createFile(Paths.get(folderName + name + ".json"));
            }
            else {
                File file = new File(folderName + name + ".json");

                file.delete();

                Files.createFile(Paths.get(folderName +name + ".json"));
            }
        }

    }

    public void timedSave(){
        if (saveTimer.passed(5000))
        {
            saveModules();
            saveTimer.reset();
        }
    }
}