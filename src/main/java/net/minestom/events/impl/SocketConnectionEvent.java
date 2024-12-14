package net.minestom.events.impl;

import net.minestom.events.CancellableEvent;
import net.minestom.network.connection.SocketConnection;

public class SocketConnectionEvent extends CancellableEvent
{
    private final SocketConnection connection;
    public SocketConnectionEvent(SocketConnection connection)
    {
        this.connection = connection;
    }

    public SocketConnection getConnection()
    {
        return this.connection;
    }
}
