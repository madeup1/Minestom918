package net.minestom.util;

import java.util.LinkedList;

public class Lock
{
    private boolean locked;
    private LinkedList<Runnable> runnables = new LinkedList<>();
    public Lock()
    {
        this.locked = false;
    }

    public boolean locked()
    {
        return this.locked;
    }

    public void lock()
    {
        this.locked = true;
    }

    public void unlock()
    {
        this.locked = false;

        for (Runnable runnable : runnables)
        {
            runnable.run();
        }

        runnables.clear();
    }

    public void forceWait()
    {
        while (this.locked);
    }

    public void waitAsync(Runnable runnable)
    {
        if (locked)
            this.runnables.addLast(runnable);
        else
            runnable.run();
    }
}
