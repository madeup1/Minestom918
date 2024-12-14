package net.minestom.util.network;

import java.util.ArrayList;
import java.util.List;

public class VarInt
{
    public static byte[] getBytes(int value)
    {
        List<Byte> byteList = new ArrayList<>();

        while ((value & -128) != 0)
        {
            byteList.add((byte)(value & 127 | 128));
            value = (value >> 7);
        }

        byteList.add((byte) value);

        return null;
    }
}
