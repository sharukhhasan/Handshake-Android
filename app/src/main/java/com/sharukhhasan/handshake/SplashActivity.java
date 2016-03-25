package com.sharukhhasan.handshake;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.sharukhhasan.handshake.activities.LoginActivity;
import com.sharukhhasan.handshake.activities.MainActivity;

public class SplashActivity extends AppCompatActivity {
    private TextView appTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_splash);

        appTitle = (TextView) findViewById(R.id.handshakeTitle);

        Thread timerThread = new Thread(){
            public void run()
            {
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    boolean loggedIn = isUserLoggedIn();
                    if(!loggedIn)
                    {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        };

        timerThread.start();
    }

    public boolean isUserLoggedIn()
    {
        return AccessToken.getCurrentAccessToken() != null;
    }
}
