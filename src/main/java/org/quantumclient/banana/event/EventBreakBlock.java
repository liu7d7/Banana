package org.quantumclient.banana.event;

import net.minecraft.util.math.BlockPos;
import org.quantumclient.energy.Event;

public class EventBreakBlock extends Event {

    public BlockPos blockPos;

    public EventBreakBlock(BlockPos blockPos) {
        this.blockPos = blockPos;
    }

}
