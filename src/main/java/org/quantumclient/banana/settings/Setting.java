package org.quantumclient.banana.settings;

import net.minecraft.util.math.MathHelper;
import org.quantumclient.banana.Banana;
import org.quantumclient.banana.module.Feature;

import java.util.List;

public class Setting {
    private String name;
    private String mode;
    private Feature feature;

    private String sval;
    private List<String> options;

    private boolean bval;

    private double inc;
    private double dval;
    private double min;
    private double max;
    private boolean onlyint = false;


    public Setting(String name, String sval, List<String> options) {
        this.name = name;
        this.sval = sval;
        this.options = options;
        this.mode = "Combo";
    }

    public Setting(String name, boolean bval) {
        this.name = name;
        this.bval = bval;
        this.mode = "Check";
    }

    public Setting(String name, double dval, double min, double max, boolean onlyint) {
        this.name = name;
        this.dval = dval;
        this.min = min;
        this.max = max;
        this.onlyint = onlyint;
        this.mode = "Slider";
    }

    public Setting(String name, double dval, double min, double max, double inc, boolean onlyint) {
        this.name = name;
        this.dval = dval;
        this.min = min;
        this.max = max;
        this.onlyint = onlyint;
        this.inc = inc;
        this.mode = "Slider";
    }

    public double getInc() {
        return inc;
    }

    public String getName() {
        return name;
    }

    public String getValString() {
        return this.sval;
    }

    public void setValString(String in) {
        this.sval = in;
        if (Banana.getSaveConfig() != null) Banana.getSaveConfig().saveIndividualFeature(feature);
    }

    public List<String> getOptions() {
        return this.options;
    }

    public boolean getValBoolean() {
        return this.bval;
    }

    public void setValBoolean(boolean in) {
        this.bval = in;
        if (Banana.getSaveConfig() != null) Banana.getSaveConfig().saveIndividualFeature(feature);
    }

    public double getValDouble() {
        return this.dval;
    }

    public int getValInt() {
        int fortnite = 0;
        if(this.onlyint) {
            fortnite = (int) dval;
        }
        return fortnite;
    }

    public void setValDouble(double in) {
        this.dval = MathHelper.clamp(in, min, max);
        if (Banana.getSaveConfig() != null) Banana.getSaveConfig().saveIndividualFeature(feature);
    }

    public double getMin() {
        return this.min;
    }

    public double getMax() {
        return this.max;
    }

    public boolean isCombo() {
        return this.mode.equalsIgnoreCase("Combo") ? true : false;
    }

    public boolean isCheck() {
        return this.mode.equalsIgnoreCase("Check") ? true : false;
    }

    public boolean isSlider() {
        return this.mode.equalsIgnoreCase("Slider") ? true : false;
    }

    public boolean onlyInt() {
        return this.onlyint;
    }

    public String getNextInList(boolean p_Reverse)
    {
        final String l_CurrEnum = (String) this.getValString();

        int i = 0;

        for (; i < this.options.size(); i++)
        {
            final String e = (String) this.options.get(i);
            if (e.equalsIgnoreCase(l_CurrEnum))
            {
                break;
            }
        }

        return this.options.get((p_Reverse ? (i != 0 ? i - 1 : this.options.size() - 1)
                : i + 1) % this.options.size());
    }

    public void initializeFeature(Feature feature) {
        this.feature = feature;
    }

}
