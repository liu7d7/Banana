package org.quantumclient.banana.module.render;

import org.quantumclient.banana.module.Category;
import org.quantumclient.banana.module.Feature;
import org.quantumclient.banana.settings.Setting;

import java.util.Arrays;
import java.util.List;

public class Fun extends Feature {

    final static List<String> animationModes = Arrays.asList("itemangle", "swirl", "flux");

    public static final Setting fortnite = new Setting("animation", "swirl", animationModes);
    public static final Setting fortnite2 = new Setting("animate-picks", false);

    public Fun() {
        super("fun-features", Category.render);
    }

}
