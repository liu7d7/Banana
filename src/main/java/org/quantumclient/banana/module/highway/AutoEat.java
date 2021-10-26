package org.quantumclient.banana.module.highway;

import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import org.lwjgl.glfw.GLFW;
import org.quantumclient.banana.event.EventTwelvetupleTick;
import org.quantumclient.banana.module.Category;
import org.quantumclient.banana.module.Feature;
import org.quantumclient.banana.settings.Setting;
import org.quantumclient.energy.Subscribe;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AutoEat extends Feature {
    
    final static List<String> modes = Arrays.asList("hunger", "health", "both");

    public static Setting mode = new Setting("mode", "both", modes);
    public static Setting healthToEat = new Setting("health", 10, 1, 36, 1, true);
    public static Setting hungerToEat = new Setting("hunger", 13, 1, 20, 1, true);
    public static Setting gapNoFireRes = new Setting("fire-resistance", false);
    public static Setting crashNoFood = new Setting("quit-no-food", false);
    public static Setting alarm = new Setting("alarm", false);

    public AutoEat() {
        super("auto-eat", Category.highway, GLFW.GLFW_KEY_I);
    }
    private int lastSlot = -1;
    private boolean eating = false;

    @Subscribe
    public void onTick(EventTwelvetupleTick event) {
        if (crashNoFood.getValBoolean()) {
            int food = 0;
            for (int i = 0; i < 9; i++) {
                if (mc.player.getInventory().getStack(i).getItem().isFood()) {
                    food++;
                }
            }
            if (food == 0) {
                mc.close();
            }
        }
        if (((Objects.equals(mode.getValString(), "both") || Objects.equals(mode.getValString(), "hunger")) && mc.player.getHungerManager().getFoodLevel() == 20) && ((Objects.equals(mode.getValString(), "both") || Objects.equals(mode.getValString(), "hunger")) && mc.player.getHealth() + mc.player.getAbsorptionAmount() > healthToEat.getValInt()) && gapNoFireRes.getValBoolean()) {
            if (mc.player.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
                if (lastSlot != -1) {
                    mc.player.getInventory().selectedSlot = lastSlot;
                    lastSlot = -1;
                }
                eating = false;
                mc.options.keyUse.setPressed(false);

                return;
            }
        }
        if (Objects.equals(mode.getValString(), "hunger")) {
            if (eating && (mc.player.getHungerManager().getFoodLevel() == 20) && !(gapNoFireRes.getValBoolean() && !mc.player.hasStatusEffect(StatusEffects.FIRE_RESISTANCE))) {
                if (lastSlot != -1) {
                    mc.player.getInventory().selectedSlot = lastSlot;
                    lastSlot = -1;
                }
                eating = false;
                
                mc.options.keyUse.setPressed(false);

                return;
            }
        }
        if (Objects.equals(mode.getValString(), "hunger")) {
            if (eating && (mc.player.getHealth() + mc.player.getAbsorptionAmount() > healthToEat.getValInt()) && !(gapNoFireRes.getValBoolean() && !mc.player.hasStatusEffect(StatusEffects.FIRE_RESISTANCE))) {
                if (lastSlot != -1) {
                    mc.player.getInventory().selectedSlot = lastSlot;
                    lastSlot = -1;
                }
                eating = false;
                
                mc.options.keyUse.setPressed(false);

                return;
            }
        }
        if (Objects.equals(mode.getValString(), "both")) {
            if (eating && (mc.player.getHealth() + mc.player.getAbsorptionAmount() > healthToEat.getValInt()) && (mc.player.getHungerManager().getFoodLevel() == 20) && !(gapNoFireRes.getValBoolean() && !mc.player.hasStatusEffect(StatusEffects.FIRE_RESISTANCE))) {
                if (lastSlot != -1) {
                    mc.player.getInventory().selectedSlot = lastSlot;
                    lastSlot = -1;
                }
                eating = false;
                
                mc.options.keyUse.setPressed(false);

                return;
            }
        }
        if (eating) {
            return;
        }
        if (gapNoFireRes.getValBoolean()) {
            if (!mc.player.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
                for (int i = 0; i < 9; i++) {
                    if (mc.player.getInventory().getStack(i).getItem() == Items.ENCHANTED_GOLDEN_APPLE) {
                        lastSlot = mc.player.getInventory().selectedSlot;
                        mc.player.getInventory().selectedSlot = i;
                        eating = true;
                        
                        if (mc.currentScreen != null && alarm.getValBoolean()) mc.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ENTITY_ENDERMAN_DEATH, 2.0F));
                        mc.options.keyUse.setPressed(true);
                        mc.interactionManager.interactItem(mc.player, mc.world, Hand.MAIN_HAND);
                        return;
                    }
                }
            }
        }
        if (Objects.equals(mode.getValString(), "hunger")) {
            if (mc.player.getHungerManager().getFoodLevel() < hungerToEat.getValInt()) {
                for (int i = 0; i < 9; i++) {
                    if (mc.player.getInventory().getStack(i).isFood()) {
                        lastSlot = mc.player.getInventory().selectedSlot;
                        mc.player.getInventory().selectedSlot = i;
                        eating = true;
                        
                        if (mc.currentScreen != null && alarm.getValBoolean()) mc.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ENTITY_ENDERMAN_DEATH, 2.0F));
                        mc.options.keyUse.setPressed(true);
                        mc.interactionManager.interactItem(mc.player, mc.world, Hand.MAIN_HAND);
                        return;
                    }
                }
            }
        }
        if (Objects.equals(mode.getValString(), "hunger")) {
            if (mc.player.getHealth() + mc.player.getAbsorptionAmount() <= healthToEat.getValInt()) {
                for (int i = 0; i < 9; i++) {
                    if (mc.player.getInventory().getStack(i).isFood()) {
                        lastSlot = mc.player.getInventory().selectedSlot;
                        mc.player.getInventory().selectedSlot = i;
                        eating = true;
                        
                        if (mc.currentScreen != null && alarm.getValBoolean()) mc.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ENTITY_ENDERMAN_DEATH, 2.0F));
                        mc.options.keyUse.setPressed(true);
                        mc.interactionManager.interactItem(mc.player, mc.world, Hand.MAIN_HAND);
                        return;
                    }
                }
            }
        }
        if (Objects.equals(mode.getValString(), "both")) {
            if (mc.player.getHealth() + mc.player.getAbsorptionAmount() <= healthToEat.getValInt() || mc.player.getHungerManager().getFoodLevel() < hungerToEat.getValInt()) {
                for (int i = 0; i < 9; i++) {
                    if (mc.player.getInventory().getStack(i).isFood()) {
                        lastSlot = mc.player.getInventory().selectedSlot;
                        mc.player.getInventory().selectedSlot = i;
                        eating = true;
                        
                        if (mc.currentScreen != null && alarm.getValBoolean()) mc.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ENTITY_ENDERMAN_DEATH, 2.0F));
                        mc.options.keyUse.setPressed(true);
                        mc.interactionManager.interactItem(mc.player, mc.world, Hand.MAIN_HAND);
                        return;
                    }
                }
            }
        }
    }

    public boolean isEating() {
        return eating;
    }
    
}
