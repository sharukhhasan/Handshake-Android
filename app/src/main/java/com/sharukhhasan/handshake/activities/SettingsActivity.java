package com.sharukhhasan.handshake.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rey.material.widget.Switch;
import com.sharukhhasan.handshake.R;
import com.sharukhhasan.handshake.SharedPreference;

public class SettingsActivity extends AppCompatActivity {

    private static final int[] EDITVIEW_IDS = {
            R.id.firstNameEditView,
            R.id.lastNameEditView,
            R.id.emailEditText,
            R.id.phoneNumberEditText,
            R.id.companyEditText,
            R.id.facebookLinkEditText,
            R.id.linkedInEditText
    };

    private static final int[] SWITCH_IDS = {
            R.id.firstNameSwitch,
            R.id.lastNameSwitch,
            R.id.emailSwitch,
            R.id.phoneNumberSwitch,
            R.id.companySwitch,
            R.id.facebookLinkSwitch,
            R.id.linkedInSwitch
    };

    private static final String[] FIELD_KEYS = {
            SharedPreference.FIRST_NAME_KEY,
            SharedPreference.LAST_NAME_KEY,
            SharedPreference.EMAIL_KEY,
            SharedPreference.PHONE_NUMBER_KEY,
            SharedPreference.COMPANY_KEY,
            SharedPreference.FACEBOOK_LINK_KEY,
            SharedPreference.LINKEDIN_NAME_KEY
    };

    private SharedPreference sharedPreference;
    private AppCompatActivity context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreference = new SharedPreference();

        EditText firstName = (EditText) findViewById(R.id.firstNameEditView);
        firstName.setText(sharedPreference.getValue(context, SharedPreference.FIRST_NAME_KEY));
        firstName.setOnFocusChangeListener(focusChangeListener);

        Switch firstNameSwitch = (Switch) findViewById(R.id.firstNameSwitch);
        firstNameSwitch.setChecked(sharedPreference.getBool(context, SharedPreference.FIRST_NAME_KEY));
        firstNameSwitch.setOnClickListener(switchListener);

        EditText lastName = (EditText) findViewById(R.id.lastNameEditView);
        lastName.setText(sharedPreference.getValue(context, SharedPreference.LAST_NAME_KEY));
        lastName.setOnFocusChangeListener(focusChangeListener);

        Switch lastNameSwitch = (Switch) findViewById(R.id.lastNameSwitch);
        lastNameSwitch.setChecked(sharedPreference.getBool(context, SharedPreference.LAST_NAME_KEY));
        lastNameSwitch.setOnClickListener(switchListener);

        EditText email = (EditText) findViewById(R.id.emailEditText);
        email.setText(sharedPreference.getValue(context, SharedPreference.EMAIL_KEY));
        email.setOnFocusChangeListener(focusChangeListener);

        Switch emailSwitch = (Switch) findViewById(R.id.emailSwitch);
        emailSwitch.setChecked(sharedPreference.getBool(context, SharedPreference.EMAIL_KEY));
        emailSwitch.setOnClickListener(switchListener);

        EditText phoneNumber = (EditText) findViewById(R.id.phoneNumberEditText);
        phoneNumber.setText(sharedPreference.getValue(context, SharedPreference.PHONE_NUMBER_KEY));
        phoneNumber.setOnFocusChangeListener(focusChangeListener);

        Switch phoneNumberSwitch = (Switch) findViewById(R.id.phoneNumberSwitch);
        phoneNumberSwitch.setChecked(sharedPreference.getBool(context, SharedPreference.PHONE_NUMBER_KEY));
        phoneNumberSwitch.setOnClickListener(switchListener);

        EditText company = (EditText) findViewById(R.id.companyEditText);
        company.setText(sharedPreference.getValue(context, SharedPreference.COMPANY_KEY));
        company.setOnFocusChangeListener(focusChangeListener);

        Switch companySwitch = (Switch) findViewById(R.id.companySwitch);
        companySwitch.setChecked(sharedPreference.getBool(context, SharedPreference.COMPANY_KEY));
        companySwitch.setOnClickListener(switchListener);

        EditText facebookLink = (EditText) findViewById(R.id.facebookLinkEditText);
        facebookLink.setText(sharedPreference.getValue(context, SharedPreference.FACEBOOK_LINK_KEY));
        facebookLink.setOnFocusChangeListener(focusChangeListener);

        Switch facebookLinkSwitch = (Switch) findViewById(R.id.facebookLinkSwitch);
        facebookLinkSwitch.setChecked(sharedPreference.getBool(context, SharedPreference.FACEBOOK_LINK_KEY));
        facebookLinkSwitch.setOnClickListener(switchListener);

        EditText linkedinName = (EditText) findViewById(R.id.linkedInEditText);
        linkedinName.setText(sharedPreference.getValue(context, SharedPreference.LINKEDIN_NAME_KEY));
        linkedinName.setOnFocusChangeListener(focusChangeListener);

        Switch linkedinNameSwitch = (Switch) findViewById(R.id.linkedInSwitch);
        linkedinNameSwitch.setChecked(sharedPreference.getBool(context, SharedPreference.LINKEDIN_NAME_KEY));
        linkedinNameSwitch.setOnClickListener(switchListener);

        Button doneButton = (Button) findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus)
        {
            int id = v.getId();

            for(int i = 0; i < EDITVIEW_IDS.length; i++)
            {
                if(id == EDITVIEW_IDS[i])
                {
                    String change = ((EditText) v).getText().toString();
                    sharedPreference.saveText(context, FIELD_KEYS[i], change);
                }
            }
        }
    };

    private View.OnClickListener switchListener = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            int id = v.getId();

            for(int i = 0; i < SWITCH_IDS.length; i++)
            {
                if(id == SWITCH_IDS[i])
                {
                    boolean switched = ((Switch) v).isChecked();
                    sharedPreference.saveBool(context, FIELD_KEYS[i], switched);
                }
            }
        }
    };
}
