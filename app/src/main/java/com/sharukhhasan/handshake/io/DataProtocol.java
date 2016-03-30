package com.sharukhhasan.handshake.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Sharukh on 3/29/16.
 */
public class DataProtocol {

    public byte[] convertArrayToBytes(String[] input) throws IOException
    {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(byteOut);

        for(String element : input)
        {
            dataOut.writeUTF(element);
        }
        dataOut.flush();

        byte[] bytes = byteOut.toByteArray();

        return bytes;
    }

    public byte[] convertSingleToBytes(String input) throws IOException
    {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(byteOut);

        dataOut.writeUTF(input);
        dataOut.flush();

        return byteOut.toByteArray();
    }

    public static String convertToString(byte[] input) throws IOException
    {
        return new String(input, "US-ASCII");
    }

    public static String[] splitString(String input)
    {
        String[] stringArray = input.split("\\s+");

        return stringArray;
    }
}
