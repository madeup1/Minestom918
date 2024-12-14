package net.minestom;

import net.minestom.events.EventManager;
import net.minestom.events.impl.SocketConnectionEvent;
import net.minestom.events.impl.TickEvent;
import net.minestom.loop.ServerLoop;
import net.minestom.network.IServerSocket;
import net.minestom.network.MinecraftSocket;
import net.minestom.network.connection.ConnectionManager;
import net.minestom.util.logging.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Minestom
{
    private static byte[] reserve = new byte[1024 * 1024 * 512];
    private static Logger logger;
    private static IServerSocket socket;
    private static ConnectionManager connections;
    private static EventManager events;
    private static ServerLoop serverLoop;

    private static boolean running = false;

    static
    {
        logger = new Logger("MINESTOM");
        socket = new MinecraftSocket();
        connections = new ConnectionManager();
        events = new EventManager("minestom");
        serverLoop = new ServerLoop();
    }

    public static void init()
    {

    }

    public static void start(String address, int port) throws IOException
    {
        // socket
        socket.init(new InetSocketAddress(address, port));
        Thread.startVirtualThread(socket::start);

        Minestom.running = true;

        events.register(SocketConnectionEvent.class, c -> {
            logger.info("Connection received! There are now " + connections.getCount());
        });

        while (Minestom.running)
        {
            serverLoop.call();
        }
    }

    public static Logger logger()
    {
        return logger;
    }

    public static IServerSocket socket()
    {
        return socket;
    }

    public static ConnectionManager connections()
    {
        return connections;
    }

    public static ServerLoop loop()
    {
        return serverLoop;
    }

    public static EventManager events()
    {
        return events;
    }

    public static boolean isRunning()
    {
        return running;
    }

    public static void stop()
    {
        running = false;
    }
}