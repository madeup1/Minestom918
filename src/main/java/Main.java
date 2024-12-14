import net.minestom.Flags;
import net.minestom.Minestom;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        String[] testArgs = {"--socket-buffer-size-receive", "4096"};
        Flags.parse(testArgs);

        Minestom.init();
        Minestom.start("0.0.0.0", 25565);
    }
}
