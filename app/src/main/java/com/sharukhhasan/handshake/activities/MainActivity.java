package com.sharukhhasan.handshake.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sharukhhasan.handshake.R;
import com.sharukhhasan.handshake.SharedPreference;

import com.squareup.seismic.ShakeDetector;

public class MainActivity extends AppCompatActivity implements ShakeDetector.Listener{
    private static final String TAG = "***MainActivity***";
    private ImageButton pastShakesBtn;
    private ImageButton settingsBtn;
    private ImageView appIcon;
    private TextView welcomeTextView;
    private TextView waitingShakeTextView;
    private SharedPreference sharedPreference;
    private AppCompatActivity context = this;

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreference = new SharedPreference(context);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        pastShakesBtn = (ImageButton) findViewById(R.id.pastShakesButton);
        pastShakesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, PastshakesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        settingsBtn = (ImageButton) findViewById(R.id.settingsButton);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        appIcon = (ImageView) findViewById(R.id.handshakeIcon);
        waitingShakeTextView = (TextView) findViewById(R.id.waitingShakeTextView);

        welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
        //String welcomeName = sharedPreference.getValue(context, SharedPreference.FIRST_NAME_KEY);
        String welcomeName = prefs.getString(SharedPreference.FIRST_NAME_KEY, "none");
        welcomeTextView.setText("Welcome, " + welcomeName);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ShakeDetector sd = new ShakeDetector(this);
        sd.setSensitivity(11);
        sd.start(sensorManager);
    }

    public void hearShake()
    {
        Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        waitingShakeTextView.setText("Searching for people...");
        waitingShakeTextView.startAnimation(shake);
        welcomeTextView.startAnimation(shake);
        pastShakesBtn.startAnimation(shake);
        settingsBtn.startAnimation(shake);
        appIcon.startAnimation(shake);

        Log.d(TAG, prefs.getString(SharedPreference.FULL_NAME_KEY, "no full name"));
        Log.d(TAG, prefs.getString(SharedPreference.EMAIL_KEY, "no email"));
        Log.d(TAG, prefs.getString(SharedPreference.FACEBOOK_ID_KEY, "no facebook id"));
        Log.d(TAG, prefs.getString(SharedPreference.FACEBOOK_LINK_KEY, "no facebook link"));
        Log.d(TAG, prefs.getString(SharedPreference.FACEBOOK_PIC_URL_KEY, "no picture url"));

        Thread txtChangeThread = new Thread(){
            @Override
            public void run()
            {
                try{
                    Thread.sleep(5000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run()
                        {
                            waitingShakeTextView.setText("Waiting for handshake...");
                        }
                    });
                } catch(InterruptedException e) {

                }
            }
        };
        txtChangeThread.start();
    }
}
