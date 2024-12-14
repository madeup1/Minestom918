package net.minestom.network.connection;

import net.minestom.Minestom;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketConnection
{
    private final Socket socket;
    private final InputStream input;
    private final OutputStream output;
    public SocketConnection(Socket socket)
    {
        this.socket = socket;

        try
        {
            this.input = socket.getInputStream();
            this.output = socket.getOutputStream();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public Socket socket()
    {
        return this.socket;
    }

    public void tick()
    {
        if (!socket.isConnected())
            this.close();

        try
        {
            this.read();
        }
        catch (IOException e)
        {
            this.close();
        }
    }

    public void read() throws IOException
    {
        int length = this.input.read();

        if (length == 0)
        {
            this.close();
        }

        Minestom.logger().info("Found " + length + " bytes");
    }

    public void close()
    {
        try
        {
            socket.close();
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        Minestom.connections().lock.waitAsync(() -> {
            Minestom.connections().removeConnection(this);

            Minestom.logger().info("Disconnected!");
        });
    }
}
