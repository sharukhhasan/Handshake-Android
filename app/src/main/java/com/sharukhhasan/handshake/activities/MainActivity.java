package com.sharukhhasan.handshake.activities;

import android.content.Intent;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.Profile;
import com.sharukhhasan.handshake.PreferenceUtils;
import com.sharukhhasan.handshake.R;

import com.sharukhhasan.handshake.User;
import com.squareup.seismic.ShakeDetector;

public class MainActivity extends AppCompatActivity implements ShakeDetector.Listener{
    private ImageButton pastShakesBtn;
    private ImageButton settingsBtn;
    private ImageView appIcon;
    private TextView welcomeTextView;
    private TextView waitingShakeTextView;
    private User currentUser;
    private Profile profile = Profile.getCurrentProfile();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentUser = PreferenceUtils.getCurrentUser(getApplicationContext());

        currentUser.setUserFacebookLink("fb://profile/" + currentUser.getUserFacebookId());

        //String usrname = profile.getFirstName();

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
        String[] splitName = currentUser.userName.split("\\s+");
        String welcome = "Welcome, " + splitName[0] + "!";
        welcomeTextView.setText(welcome);

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
