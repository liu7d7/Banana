package org.quantumclient.banana.utilities;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BananaHelper implements MinecraftInterface {

    public int floor2Ints(int floor, int input) {
        if (input < floor) {
            return floor;
        }
        return input;
    }

    public int ceil2Ints(int ceil, int input) {
        if (input > ceil) {
            return ceil;
        }
        return input;
    }


    public static float round(float value, int places) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.floatValue();
    }

    public static double round(final double value, final int places) {
        if (places < 0) {
            return value;
        }
        return new BigDecimal(value).setScale(places, RoundingMode.HALF_UP).doubleValue();
    }

}
