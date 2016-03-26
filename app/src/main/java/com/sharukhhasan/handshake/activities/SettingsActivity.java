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
        firstName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if(!hasFocus)
                {
                    String change = firstName.getText().toString();
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
                    linkedinName.setText(change);
                }
            }
        });


    }
}
