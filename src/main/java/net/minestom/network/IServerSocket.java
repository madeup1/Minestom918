package net.minestom.network;

import java.io.IOException;
import java.net.InetSocketAddress;

public interface IServerSocket
{
    void init(InetSocketAddress addr) throws IOException;
    void start();
    void stop();
}
