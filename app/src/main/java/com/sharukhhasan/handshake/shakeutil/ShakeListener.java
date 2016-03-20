package com.sharukhhasan.handshake.shakeutil;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by Sharukh on 3/19/16.
 */
public class ShakeListener implements SensorEventListener {
    public static final int SHAKE_LIGHT_SENSITIVITY = 11;
    public static final int SHAKE_MEDIUM_SENSITIVITY = 13;
    public static final int SHAKE_HARD_SENSITIVITY = 15;
    private static final int DEFAULT_ACCELERATION_THRESHOLD = SHAKE_MEDIUM_SENSITIVITY;
    private int accelerationThreshold = DEFAULT_ACCELERATION_THRESHOLD;

    private final AccelerationQueue queue = new AccelerationQueue();
    private final Listener listener;
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;

    public interface Listener
    {
        void hearShake();
    }

    public ShakeListener(Listener listener)
    {
        this.listener = listener;
    }

    public boolean start(SensorManager sensorManager)
    {
        if(accelerometerSensor != null)
        {
            return true;
        }

        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if(accelerometerSensor != null)
        {
            this.sensorManager = sensorManager;
            sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_FASTEST);
        }
        return accelerometerSensor != null;
    }

    public void stop()
    {
        if(accelerometerSensor != null)
        {
            sensorManager.unregisterListener(this, accelerometerSensor);
            sensorManager = null;
            accelerometerSensor = null;
        }
    }

    private boolean isAccelerating(SensorEvent event)
    {
        float x_axis = event.values[0];
        float y_axis = event.values[1];
        float z_axis = event.values[2];

        final double squaredMagnitude = x_axis * x_axis + y_axis * y_axis + z_axis * z_axis;
        return squaredMagnitude > accelerationThreshold * accelerationThreshold;
    }

    public void setSensitivity(int accelerationThreshold)
    {
        this.accelerationThreshold = accelerationThreshold;
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        boolean is_Accelerating = isAccelerating(event);
        long timestamp = event.timestamp;
        queue.addAcceleration(timestamp, is_Accelerating);

        if(queue.isDeviceShaking())
        {
            queue.removeAll();
            listener.hearShake();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}