package net.minestom.events;

public class CancellableEvent implements IEvent
{
    private boolean cancelled = false;
    public void setCancelled(boolean value)
    {
        this.cancelled = value;
    }

    public boolean cancelled()
    {
        return this.cancelled;
    }
}
