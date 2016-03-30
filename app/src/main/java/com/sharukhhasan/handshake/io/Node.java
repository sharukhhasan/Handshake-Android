package com.sharukhhasan.handshake.io;

import android.util.Log;

import com.sharukhhasan.handshake.activities.SocketActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

import io.underdark.Underdark;
import io.underdark.transport.Link;
import io.underdark.transport.Transport;
import io.underdark.transport.TransportKind;
import io.underdark.transport.TransportListener;
import io.underdark.util.nslogger.NSLogger;
import io.underdark.util.nslogger.NSLoggerAdapter;

public class Node implements TransportListener {
    private static final String TAG = "TRANSPORT_LINK";
    private boolean running;
    private SocketActivity activity;
    private long nodeId;
    private Transport transport;
    public String fromApple;
    public String[] fromAppleArray;
    public String normalized;

    public String name;
    public String email;
    public String id;
    public String url;

    private ArrayList<Link> links = new ArrayList<>();
    private int framesCount = 0;

    public Node(SocketActivity activity)
    {
        this.activity = activity;

        do
        {
            nodeId = new Random().nextLong();
        } while (nodeId == 0);

        if(nodeId < 0)
            nodeId = -nodeId;

        configureLogging();

        EnumSet<TransportKind> kinds = EnumSet.of(TransportKind.BLUETOOTH, TransportKind.WIFI);
        //kinds = EnumSet.of(TransportKind.WIFI);
        //kinds = EnumSet.of(TransportKind.BLUETOOTH);

        this.transport = Underdark.configureTransport(
                234235,
                nodeId,
                this,
                null,
                activity.getApplicationContext(),
                kinds
        );
    }

    private void configureLogging()
    {
        NSLoggerAdapter adapter = (NSLoggerAdapter)
                StaticLoggerBinder.getSingleton().getLoggerFactory().getLogger(Node.class.getName());
        adapter.logger = new NSLogger(activity.getApplicationContext());
        adapter.logger.connect("192.168.5.203", 50000);

        Underdark.configureLogging(true);
    }

    public void start()
    {
        if(running)
            return;

        running = true;
        transport.start();
    }

    public void stop()
    {
        if(!running)
            return;

        running = false;
        transport.stop();
    }

    public ArrayList<Link> getLinks()
    {
        return links;
    }

    public int getFramesCount()
    {
        return framesCount;
    }

    public String getAppleOutput()
    {
        return fromApple;
    }

    public String getAppleArraySingle(int pos)
    {
        return fromAppleArray[pos];
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getId()
    {
        return id;
    }

    public String getUrl()
    {
        return url;
    }

    public void broadcastFrame(byte[] frameData)
    {
        if(links.isEmpty())
            return;

        ++framesCount;
        activity.refreshFrames();

        for(Link link : links)
        {
            link.sendFrame(frameData);
        }
    }

    //region TransportListener
    @Override
    public void transportNeedsActivity(Transport transport, ActivityCallback callback)
    {
        callback.accept(activity);
    }

    @Override
    public void transportLinkConnected(Transport transport, Link link)
    {
        links.add(link);
        activity.refreshPeers();
    }

    @Override
    public void transportLinkDisconnected(Transport transport, Link link)
    {
        links.remove(link);
        activity.refreshPeers();

        if(links.isEmpty())
        {
            framesCount = 0;
            activity.refreshFrames();
        }
    }

    @Override
    public void transportLinkDidReceiveFrame(Transport transport, Link link, byte[] frameData)
    {
        ++framesCount;
        activity.refreshFrames();
        activity.refreshApple();

        try {
            fromApple = DataProtocol.convertToString(frameData);
            normalized = java.text.Normalizer.normalize(fromApple, java.text.Normalizer.Form.NFD);
            name = normalized.substring(0, 13);
            email = normalized.substring(14, 35);
            id = normalized.substring(40, 56);
            url = normalized.substring(59, 121);
            Log.d(TAG, name);
            Log.d(TAG, email);
            Log.d(TAG, id);
            Log.d(TAG, url);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    //endregion
}
