package com.sharukhhasan.handshake.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.facebook.Profile;
import com.sharukhhasan.handshake.PreferenceUtils;
import com.sharukhhasan.handshake.R;
import com.sharukhhasan.handshake.SharedPreference;
import com.sharukhhasan.handshake.User;

public class SettingsActivity extends AppCompatActivity {
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText phoneNumber;
    private EditText company;
    private EditText facebookLink;
    private EditText linkedinName;
    private SharedPreference sharedPreference;
    AppCompatActivity context = this;

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
        sharedPreference = new SharedPreference();

        firstName = (EditText) findViewById(R.id.firstNameEditView);
        firstName.setText(currentUser.getFirstName());
        firstName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if(!hasFocus)
                {
                    String change = firstName.getText().toString();
                    sharedPreference.save(context, SharedPreference.FIRST_NAME_KEY, change);
                    firstName.setText(change);
                }
            }
        });

        lastName = (EditText) findViewById(R.id.lastNameEditView);
        lastName.setText(currentUser.getLastName());
        lastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if(!hasFocus)
                {
                    String change = lastName.getText().toString();
                    sharedPreference.save(context, SharedPreference.LAST_NAME_KEY, change);
                    lastName.setText(change);
                }
            }
        });

        email = (EditText) findViewById(R.id.emailEditText);
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if(!hasFocus)
                {
                    String change = email.getText().toString();
                    sharedPreference.save(context, SharedPreference.EMAIL_KEY, change);
                    email.setText(change);
                }
            }
        });

        phoneNumber = (EditText) findViewById(R.id.phoneNumberEditText);
        phoneNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if(!hasFocus)
                {
                    String change = phoneNumber.getText().toString();
                    sharedPreference.save(context, SharedPreference.PHONE_NUMBER_KEY, change);
                    phoneNumber.setText(change);
                }
            }
        });

        company = (EditText) findViewById(R.id.companyEditText);
        company.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if(!hasFocus)
                {
                    String change = company.getText().toString();
                    sharedPreference.save(context, SharedPreference.COMPANY_KEY, change);
                    company.setText(change);
                }
            }
        });

        facebookLink = (EditText) findViewById(R.id.facebookLinkEditText);
        facebookLink.setText(currentUser.getLinkUri().toString());
        facebookLink.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if(!hasFocus)
                {
                    String change = facebookLink.getText().toString();
                    sharedPreference.save(context, SharedPreference.FACEBOOK_LINK_KEY, change);
                    facebookLink.setText(change);
                }
            }
        });

        linkedinName = (EditText) findViewById(R.id.linkedInEditText);
        linkedinName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if(!hasFocus)
                {
                    String change = linkedinName.getText().toString();
                    sharedPreference.save(context, SharedPreference.LINKEDIN_NAME_KEY, change);
                    linkedinName.setText(change);
                }
            }
        });


    }
}
