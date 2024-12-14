package net.minestom.network;

import net.minestom.Flags;
import net.minestom.Minestom;
import net.minestom.network.connection.SocketConnection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;

public class MinecraftSocket implements IServerSocket
{
    private ServerSocket localSocket;
    private boolean running;

    public MinecraftSocket()
    {

    }

    @Override
    public void init(InetSocketAddress addr) throws IOException
    {
        ServerSocket socket = new ServerSocket();
        socket.setReuseAddress(Flags.SOCKET_REUSE_ADDRESS);
        socket.bind(addr);

        this.localSocket = socket;
    }

    @Override
    public void start()
    {
        this.running = true;

        while (this.running)
        {
            Socket socket;

            try
            {
                socket = this.localSocket.accept();

                socket.setSendBufferSize(Flags.SOCKET_BUFFER_SIZE_SEND);
                socket.setReceiveBufferSize(Flags.SOCKET_BUFFER_SIZE_RECEIVE);
                socket.setTcpNoDelay(Flags.SOCKET_NO_DELAY);
                socket.setSoTimeout(Flags.SOCKET_TIMEOUT);
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }

            Minestom.logger().info("Received connection from " + socket.getRemoteSocketAddress());
            SocketConnection connection = new SocketConnection(socket);
            Minestom.connections().addConnection(connection);
        }
    }

    @Override
    public void stop()
    {
        this.running = false;
    }


}
