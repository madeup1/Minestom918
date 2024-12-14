package net.minestom.util.logging;

public class Logger
{
    private final String owner;
    public Logger(String owner)
    {
        this.owner = owner;
    }

    public void info(Object msg)
    {
        System.out.println("[" + owner + "] [INFO] " + msg);
    }

    public void log(Object msg)
    {
        System.out.println("[" + owner + "] " + msg);
    }

    public void warn(Object msg)
    {
        System.out.println("[" + owner + "] [WARN] " + msg);
    }

    public void error(Object msg)
    {
        System.out.println("[" + owner + "] [ERROR] " + msg);
    }
}
