package com.sharukhhasan.handshake.activities;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sharukhhasan.handshake.PreferenceUtils;
import com.sharukhhasan.handshake.R;
import com.sharukhhasan.handshake.User;


public class PastshakesActivity extends AppCompatActivity {
    ImageView userPicture;
    TextView userName;
    ImageButton emailBtn;
    ImageButton linkedinBtn;
    ImageButton facebookBtn;
    User currentUser;
    private boolean viewBusy = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pastshakes);
        
        currentUser = PreferenceUtils.getCurrentUser(getApplicationContext());

        emailBtn = (ImageButton) findViewById(R.id.emailBtn);
        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{currentUser.getUserEmail()});
                startActivity(emailIntent);
            }
        });

        linkedinBtn = (ImageButton) findViewById(R.id.linkedinBtn);
        linkedinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent getintent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.android-examples.com"));
                startActivity(getintent);
            }
        });

        ImageButton facebookButton = (ImageButton) findViewById(R.id.fbBtn);
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try {
                    // Check if FB app is even installed
                    getPackageManager().getPackageInfo("com.facebook.katana", 0);

                    String facebookScheme = "fb://profile/" + currentUser.getUserFacebookId();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookScheme));
                    startActivity(intent);
                } catch(Exception e) {

                    // Cache and Open a url in browser
                    String facebookProfileUri = "https://www.facebook.com/" + currentUser.getUserFacebookId();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookProfileUri));
                    startActivity(intent);
                }
            }
        });

    }

    public boolean isViewBusy()
    {
        return viewBusy;
    }
}
