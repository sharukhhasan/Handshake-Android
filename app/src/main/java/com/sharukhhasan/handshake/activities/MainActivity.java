package com.sharukhhasan.handshake.activities;

import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.GraphRequest;
import com.sharukhhasan.handshake.R;

import com.squareup.seismic.ShakeDetector;

public class MainActivity extends AppCompatActivity implements ShakeDetector.Listener{
    private ImageButton pastShakesBtn;
    private ImageButton settingsBtn;
    private TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pastShakesBtn = (ImageButton) findViewById(R.id.pastShakesButton);
        settingsBtn = (ImageButton) findViewById(R.id.settingsButton);
        welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
        welcomeTextView.setText();

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ShakeDetector sd = new ShakeDetector(this);
        sd.setSensitivity(11);
        sd.start(sensorManager);
    }

    public void hearShake()
    {
        Toast.makeText(this, "Don't shake me, bro!", Toast.LENGTH_SHORT).show();
    }
}
