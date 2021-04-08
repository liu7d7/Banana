package org.quantumclient.banana.event;

import net.minecraft.client.gui.screen.Screen;
import org.quantumclient.energy.Event;

public class EventOpenedScreen extends Event {

    Screen screen;

    public EventOpenedScreen(Screen screen) {
        this.screen = screen;
    }

    public Screen getScreen() {
        return screen;
    }
}
