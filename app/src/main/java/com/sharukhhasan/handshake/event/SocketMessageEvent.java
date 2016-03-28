package com.sharukhhasan.handshake.event;

/**
 * Created by Sharukh on 3/28/16.
 */
public class SocketMessageEvent {

    private String mMessage;

    public SocketMessageEvent(String message)
    {
        mMessage = message;
    }

    public String getMessage()
    {
        return mMessage;
    }
}
