package net.minestom.events.impl;

import net.minestom.events.IEvent;

public class TickEvent implements IEvent
{
    private long milli;

    public TickEvent(long milli)
    {
        this.milli = milli;
    }

    public long getTime()
    {
        return milli;
    }
}
