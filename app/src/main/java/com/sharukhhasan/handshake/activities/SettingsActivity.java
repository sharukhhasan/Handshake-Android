package com.sharukhhasan.handshake.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.facebook.Profile;
import com.sharukhhasan.handshake.PreferenceUtils;
import com.sharukhhasan.handshake.R;
import com.sharukhhasan.handshake.User;

public class SettingsActivity extends AppCompatActivity {
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText phoneNumber;
    private EditText company;
    private EditText facebookLink;
    private EditText linkedinName;

    /*private Switch firstNameSwitch;
    private Switch lastNameSwitch;
    private Switch emailSwitch;
    private Switch phoneNumberSwitch;
    private Switch companySwitch;
    private Switch facebookLinkSwitch;
    private Switch linkedinNameSwitch;*/


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Profile currentUser = Profile.getCurrentProfile();
        User user = PreferenceUtils.getCurrentUser(getApplicationContext());

        firstName = (EditText) findViewById(R.id.firstNameEditView);
        firstName.setText(currentUser.getFirstName());

        lastName = (EditText) findViewById(R.id.lastNameEditView);
        lastName.setText(currentUser.getLastName());

        email = (EditText) findViewById(R.id.emailEditText);

        phoneNumber = (EditText) findViewById(R.id.phoneNumberEditText);

        company = (EditText) findViewById(R.id.companyEditText);

        facebookLink = (EditText) findViewById(R.id.facebookLinkEditText);
        facebookLink.setText(currentUser.getLinkUri().toString());

        linkedinName = (EditText) findViewById(R.id.linkedInEditText);


    }
}
