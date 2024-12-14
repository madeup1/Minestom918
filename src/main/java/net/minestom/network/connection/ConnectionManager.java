package net.minestom.network.connection;

import net.minestom.Minestom;
import net.minestom.events.impl.SocketConnectionEvent;
import net.minestom.util.Lock;

import java.util.LinkedList;
import java.util.List;

public class ConnectionManager
{
    public Lock lock = new Lock();
    private int length;

    private LinkedList<SocketConnection> connections;

    public ConnectionManager()
    {
        this.connections = new LinkedList<>();
        this.length = 0;
    }

    public void addConnection(SocketConnection connection)
    {
        connections.addLast(connection);
        this.length++;

        Minestom.events().post(new SocketConnectionEvent(connection));
    }

    public void removeConnection(SocketConnection connection)
    {
        connections.remove(connection);
        this.length--;
    }

    public List<SocketConnection> getConnections()
    {
        return connections;
    }

    public int getCount()
    {
        return this.length;
    }

    public void tick()
    {
        lock.lock();

        for (SocketConnection connection : connections)
        {
            connection.tick();
        }

        lock.unlock();
    }
}
