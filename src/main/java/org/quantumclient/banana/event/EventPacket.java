package org.quantumclient.banana.event;

import net.minecraft.network.Packet;
import org.quantumclient.energy.Event;

public class EventPacket extends Event {

    private final Packet packet;

    public EventPacket(Packet packet) {
        super();
        this.packet = packet;
    }

    public Packet getPacket() {
        return packet;
    }

    public static class Receive extends EventPacket {
        public Receive(Packet packet) {
            super(packet);
        }
    }

    public static class Send extends EventPacket {
        public Send(Packet packet) {
            super(packet);
        }
    }

}
