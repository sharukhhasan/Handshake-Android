package com.sharukhhasan.handshake.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rey.material.widget.Switch;
import com.sharukhhasan.handshake.R;
import com.sharukhhasan.handshake.SharedPreference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sharukh on 3/26/16.
 */
public class ProfileActivity extends AppCompatActivity {
    private static final int[] TEXTVIEW_IDS = {
            R.id.firstNameTextView,
            R.id.lastNameTextView,
            R.id.emailTextView,
            R.id.phoneNumberTextView,
            R.id.companyTextView,
            R.id.facebookLinkTextView,
            R.id.linkedInTextView
    };

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

    private List<TextView> settingsTitles;
    private List<EditText> settingsInput;
    private List<Switch> settingsSwitches;
    private TextView settingsTitle;
    private Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        settingsTitles = new ArrayList<TextView>();
        for(int id : TEXTVIEW_IDS)
        {
            TextView textView = (TextView) findViewById(id);
            settingsTitles.add(textView);
        }

        settingsInput = new ArrayList<EditText>();
        int keyCounter = 0;
        for(int id : EDITVIEW_IDS)
        {
            EditText editText = (EditText) findViewById(id);
            editText.setTag(FIELD_KEYS[keyCounter]);
            editText.setOnFocusChangeListener(focusChangeListener);
            keyCounter++;
            settingsInput.add(editText);
        }

        settingsSwitches = new ArrayList<Switch>();
        keyCounter = 0;
        for(int id : SWITCH_IDS)
        {
            Switch _switch = (Switch) findViewById(id);
            _switch.setTag(FIELD_KEYS[keyCounter]);
            _switch.setOnClickListener(switchListener);
            keyCounter++;
            settingsSwitches.add(_switch);
        }



    }

    private View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
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
    };

    private View.OnClickListener switchListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            v.get
        }
    };
}
