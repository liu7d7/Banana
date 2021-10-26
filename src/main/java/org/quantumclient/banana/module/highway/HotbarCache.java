package org.quantumclient.banana.module.highway;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;
import org.lwjgl.glfw.GLFW;
import org.quantumclient.banana.event.EventTwelvetupleTick;
import org.quantumclient.banana.module.Category;
import org.quantumclient.banana.module.Feature;
import org.quantumclient.banana.settings.Setting;
import org.quantumclient.banana.utilities.Timer;
import org.quantumclient.energy.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotbarCache extends Feature {

    final static List<String> modes = Arrays.asList("cache", "refill");

    public static final Setting Mode = new Setting("mode", "cache", modes);
    public static final Setting Delay = new Setting("delay", 1.0f, 0.0f, 10.0f, 1.0f, false);

    public HotbarCache()
    {
        super("hotbar-cache", Category.highway, GLFW.GLFW_KEY_I);
    }

    private final ArrayList<Item> Hotbar = new ArrayList<Item>();
    private final Timer timer = new Timer();

    @Override
    public void onEnable()
    {
        super.onEnable();
        Hotbar.clear();
        for (int i = 0; i < 9; ++i) {
            ItemStack l_Stack = mc.player.getInventory().getStack(i);
            if (!l_Stack.isEmpty() && !Hotbar.contains(l_Stack.getItem()))
                Hotbar.add(l_Stack.getItem());
            else
                Hotbar.add(Items.AIR);
        }
    }

    /// Don't activate on startup
    @Override
    public void toggleNoSave() {
    }

    @Subscribe
    public void onTick(EventTwelvetupleTick event) {
        if (!timer.passed(Delay.getValDouble() * 1000))
            return;

        switch (Mode.getValString()) {
            case "cache":
                for (int i = 0; i < 9; ++i) {
                    if (switchSlotIfNeed(i)) {
                        timer.reset();
                        return;
                    }
                }
                break;
            case "refill":
                for (int i = 0; i < 9; ++i) {
                    if (refillSlotIfNeed(i)) {
                        timer.reset();
                        return;
                    }
                }
                break;
            default:
                break;
        }
    }

    private boolean switchSlotIfNeed(int targetSlot) {
        Item targetItem = Hotbar.get(targetSlot);

        if (targetItem == Items.AIR)
            return false;

        if (!mc.player.getInventory().getStack(targetSlot).isEmpty() && mc.player.getInventory().getStack(targetSlot).getItem() == targetItem)
            return false;

        int slotFromCache = getItemSlot(targetItem);

        if (slotFromCache != -1 && slotFromCache != 45) {
            mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, slotFromCache, 0,
                    SlotActionType.PICKUP, mc.player);
            mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, targetSlot+36, 0, SlotActionType.PICKUP,
                    mc.player);
            mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, slotFromCache, 0,
                    SlotActionType.PICKUP, mc.player);
            mc.interactionManager.tick();

            return true;
        }

        return false;
    }

    private boolean refillSlotIfNeed(int targetSlot) {
        ItemStack targetStack = mc.player.getInventory().getStack(targetSlot);

        if (targetStack.isEmpty() || targetStack.getItem() == Items.AIR)
            return false;

        if (!targetStack.isStackable())
            return false;

        if (targetStack.getCount() >= targetStack.getMaxCount())
            return false;

        /// We're going to search the entire getInventory() for the same stack, WITH THE SAME NAME, and use quick move.
        for (int i = 9; i < 36; ++i) {
            final ItemStack currentItem = mc.player.getInventory().getStack(i);

            if (currentItem.isEmpty())
                continue;

            if (canItemBeMergedWith(targetStack, currentItem)) {
                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, i, 0,
                        SlotActionType.QUICK_MOVE, mc.player);
                mc.interactionManager.tick();

                /// Check again for more next available tick
                return true;
            }
        }

        return false;
    }

    private boolean canItemBeMergedWith(ItemStack source, ItemStack target) {
        return source.getItem() == target.getItem() && source.getName().equals(target.getName());
    }

    public static int getItemSlot(Item input) {
        if (mc.player == null)
            return 0;

        for (int i = 0; i < mc.player.getInventory().size(); ++i) {
            if (i == 0 || i == 5 || i == 6 || i == 7 || i == 8)
                continue;

            ItemStack s = mc.player.getInventory().getStack(i);

            if (s.isEmpty())
                continue;

            if (s.getItem() == input) {
                return i;
            }
        }
        return -1;
    }

}
