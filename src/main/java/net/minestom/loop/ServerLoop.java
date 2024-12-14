package net.minestom.loop;

import net.minestom.Minestom;
import net.minestom.events.impl.TickEvent;

public class ServerLoop
{
    public long lastTick = -1L;
    public void call()
    {
        long localMs = System.currentTimeMillis();

        if (lastTick - localMs <= -50L)
        {
            Minestom.events().post(new TickEvent(localMs));

            lastTick = localMs;

            // tick things

            Minestom.connections().tick();
        }
    }
}
