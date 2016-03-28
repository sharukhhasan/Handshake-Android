package com.sharukhhasan.handshake;

import android.app.Application;
import android.util.Log;

import com.sharukhhasan.handshake.event.SocketMessageEvent;
import com.sharukhhasan.handshake.socket.MySocketServer;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import de.greenrobot.event.EventBus;

/**
 * Created by Sharukh on 3/28/16.
 */
public class MyApplication extends Application{
    private static final String TAG = "MyApplication";
    private static final int SERVER_PORT = 12345;

    private MySocketServer mServer;

    @Override
    public void onCreate()
    {
        super.onCreate();

        startServer();
        EventBus.getDefault().register(this);
    }

    private void startServer()
    {
        InetAddress inetAddress = getInetAddress();
        if(inetAddress == null)
        {
            Log.e(TAG, "Unable to lookup IP address");
            return;
        }

        mServer = new MySocketServer(new InetSocketAddress(inetAddress.getHostAddress(), SERVER_PORT));
        mServer.start();
    }

    private static InetAddress getInetAddress()
    {
        try {
            for(Enumeration en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
            {
                NetworkInterface networkInterface = (NetworkInterface) en.nextElement();

                for(Enumeration enumIpAddr = networkInterface.getInetAddresses(); enumIpAddr.hasMoreElements();)
                {
                    InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();

                    if(!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address)
                    {
                        return inetAddress;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
            Log.e(TAG, "Error getting the network interface information");
        }

        return null;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEvent(SocketMessageEvent event)
    {
        String message = event.getMessage();
        mServer.sendMessage("echo: " + message);
    }
}
