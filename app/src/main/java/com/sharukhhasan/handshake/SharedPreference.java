package com.sharukhhasan.handshake;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by Sharukh on 3/26/16.
 */
public class SharedPreference {
    public static final String[] SETTINGS_KEYS = {
            "USER_FIRST_NAME",
            "USER_LAST_NAME",
            "USER_EMAIL",
            "USER_PHONE_NUMBER",
            "USER_COMPANY",
            "USER_FACEBOOK_LINK",
            "USER_LINKEDIN_NAME"
    };

    public static final String[] SWITCH_KEYS = {
            "SHOW_FIRST_NAME",
            "SHOW_LAST_NAME",
            "SHOW_EMAIL",
            "SHOW_PHONE_NUMBER",
            "SHOW_COMPANY",
            "SHOW_FACEBOOK_LINK",
            "SHOW_LINKEDIN_NAME"
    };

    public static final String PREFS_NAME = "USER_PREFS";
    public static final String FULL_NAME_KEY = "USER_FULL_NAME";
    public static final String FIRST_NAME_KEY = "USER_FIRST_NAME";
    public static final String LAST_NAME_KEY = "USER_LAST_NAME";
    public static final String EMAIL_KEY = "USER_EMAIL";
    public static final String FACEBOOK_ID_KEY = "USER_FACEBOOK_ID";
    public static final String FACEBOOK_PIC_URL_KEY = "USER_PICTURE";
    public static final String PHONE_NUMBER_KEY = "USER_PHONE_NUMBER";
    public static final String COMPANY_KEY = "USER_COMPANY";
    public static final String FACEBOOK_LINK_KEY = "USER_FACEBOOK_LINK";
    public static final String LINKEDIN_NAME_KEY = "USER_LINKEDIN_NAME";

    public static boolean firstLogin = true;

    public SharedPreference()
    {
        super();
    }

    public boolean isFirstLogin()
    {
        if(firstLogin)
        {
            firstLogin = false;
            return true;
        }

        return false;
    }

    public void saveText(Context context, String key, String text)
    {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        for(String field : SETTINGS_KEYS)
        {
            if(field.equals(key))
            {
                editor.putString(field, text);
                editor.commit();
            }
        }

    }

    public void saveBool(Context context, String key, Boolean isActive)
    {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        for(String show : SWITCH_KEYS)
        {
            if(show.equals(key))
            {
                editor.putBoolean(show, isActive);
                editor.commit();
            }
        }

    }

    public String getValue(Context context, String key)
    {
        SharedPreferences settings;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        String text = settings.getString(key, null);

        return text;
    }

    public boolean getBool(Context context, String key)
    {
        SharedPreferences settings;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        boolean checked = settings.getBoolean(key, false);

        return checked;
    }

    public void clearSharedPreference(Context context)
    {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.commit();
    }

    public void removeValue(Context context, String key)
    {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.remove(key);
        editor.commit();
    }
}