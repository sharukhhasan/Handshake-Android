package com.sharukhhasan.handshake.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sharukhhasan.handshake.R;
import com.sharukhhasan.handshake.User;


public class PastshakesActivity extends AppCompatActivity {
    ImageView userPicture;
    TextView userName;
    ImageButton emailBtn;
    ImageButton linkedinBtn;
    ImageButton facebookBtn;
    private boolean viewBusy = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pastshakes);

        emailBtn = (ImageButton) findViewById(R.id.emailBtn);

        linkedinBtn = (ImageButton) findViewById(R.id.linkedinBtn);

        facebookBtn = (ImageButton) findViewById(R.id.fbBtn);

    }

    public boolean isViewBusy()
    {
        return viewBusy;
    }
}
