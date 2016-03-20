package com.sharukhhasan.handshake.shakeutil;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by Sharukh on 3/19/16.
 */
public class AccelerationQueue {
    private static final long MAX_WINDOW_SIZE = 500000000;
    private static final long MIN_WINDOW_SIZE = MAX_WINDOW_SIZE >> 1;
    private static final int MIN_QUEUE_SIZE = 4;

    private final AccelerationPool pool = new AccelerationPool();
    private AccelerationInfo oldestAccelerationInfo;
    private AccelerationInfo newestAccelerationInfo;
    private int sampleCount;
    private int accelerateCount;

    void addAcceleration(long timestamp, boolean accelerating)
    {
        eliminate(timestamp - MAX_WINDOW_SIZE);

        AccelerationInfo added = pool.acquireAcceleration();
        added.timestamp = timestamp;
        added.accelerating = accelerating;
        added.next = null;

        if(newestAccelerationInfo != null)
        {
            newestAccelerationInfo.next = added;
        }
        newestAccelerationInfo = added;

        if(oldestAccelerationInfo == null)
        {
            oldestAccelerationInfo = added;
        }

        sampleCount++;
        if(accelerating)
        {
            accelerateCount++;
        }
    }

    void removeAll()
    {
        while(oldestAccelerationInfo != null)
        {
            AccelerationInfo removed = oldestAccelerationInfo;
            oldestAccelerationInfo = removed.next;
            pool.releaseAcceleration(removed);
        }
        newestAccelerationInfo = null;
        sampleCount = 0;
        accelerateCount = 0;
    }

    void eliminate(long cutoff)
    {
        while(sampleCount >= MIN_QUEUE_SIZE && oldestAccelerationInfo != null && cutoff - oldestAccelerationInfo.timestamp > 0)
        {
            AccelerationInfo removed = oldestAccelerationInfo;
            if(removed.accelerating)
            {
                accelerateCount--;
            }
            sampleCount--;

            oldestAccelerationInfo = removed.next;
            if(oldestAccelerationInfo == null)
            {
                newestAccelerationInfo = null;
            }
            pool.releaseAcceleration(removed);
        }
    }

    List<AccelerationInfo> asList() {
        List<AccelerationInfo> list = new ArrayList<AccelerationInfo>();
        AccelerationInfo s = oldestAccelerationInfo;

        while(s != null)
        {
            list.add(s);
            s = s.next;
        }
        return list;
    }

    boolean isDeviceShaking()
    {
        return newestAccelerationInfo != null && oldestAccelerationInfo != null && newestAccelerationInfo.timestamp - oldestAccelerationInfo.timestamp >= MIN_WINDOW_SIZE && accelerateCount >= (sampleCount >> 1) + (sampleCount >> 2);
    }
}
