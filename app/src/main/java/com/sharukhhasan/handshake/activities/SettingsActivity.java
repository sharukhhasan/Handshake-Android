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

    private static final String[] EDIT_FIELD_KEYS = SharedPreference.SETTINGS_KEYS;
    private static final String[] SWITCH_KEYS = SharedPreference.SWITCH_KEYS;

    private EditText[] fields;
    private Switch[] switches;

    private SharedPreference sharedPreference;
    private AppCompatActivity context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreference = new SharedPreference();

        fields = new EditText[EDIT_FIELD_KEYS.length];
        switches = new Switch[SWITCH_KEYS.length];

        for(int i = 0; i < fields.length; i++)
        {
            fields[i] = (EditText) findViewById(EDITVIEW_IDS[i]);
            fields[i].setText(sharedPreference.getValue(context, EDIT_FIELD_KEYS[i]));
            fields[i].setOnFocusChangeListener(focusChangeListener);

            switches[i] = (Switch) findViewById(SWITCH_IDS[i]);
            switches[i].setChecked(sharedPreference.getBool(context, SWITCH_KEYS[i]));
            switches[i].setOnClickListener(switchListener);
        }

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
                    sharedPreference.saveText(context, EDIT_FIELD_KEYS[i], change);
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
                    sharedPreference.saveBool(context, SWITCH_KEYS[i], switched);
                }
            }
        }
    };
}
