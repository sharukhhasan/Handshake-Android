package com.sharukhhasan.handshake.shakeutil;

/**
 * Created by Sharukh on 3/19/16.
 */
public class AccelerationPool {
    private AccelerationInfo headAcceleration;

    AccelerationInfo acquireAcceleration() {
        AccelerationInfo acquired_acceleration = headAcceleration;
        if(acquired_acceleration == null)
        {
            acquired_acceleration = new AccelerationInfo();
        }
        else
        {
            headAcceleration = acquired_acceleration.next;
        }
        return acquired_acceleration;
    }

    void releaseAcceleration(AccelerationInfo sample)
    {
        sample.next = headAcceleration;
        headAcceleration = sample;
    }
}
