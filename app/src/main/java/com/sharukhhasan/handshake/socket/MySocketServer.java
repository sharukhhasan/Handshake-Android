package com.sharukhhasan.handshake.socket;

import com.sharukhhasan.handshake.event.SocketMessageEvent;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

import de.greenrobot.event.EventBus;

/**
 * Created by Sharukh on 3/28/16.
 */
public class MySocketServer extends WebSocketServer {
    private WebSocket mSocket;

    public MySocketServer(InetSocketAddress address)
    {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake)
    {
        mSocket = conn;
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote)
    {

    }

    @Override
    public void onMessage(WebSocket conn, String message)
    {
        EventBus.getDefault().post(new SocketMessageEvent(message));
    }

    @Override
    public void onError(WebSocket conn, Exception ex)
    {

    }

    public void sendMessage(String message)
    {
        mSocket.send(message);
    }
}
