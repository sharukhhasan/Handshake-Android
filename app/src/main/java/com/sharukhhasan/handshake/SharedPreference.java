package com.sharukhhasan.handshake;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by Sharukh on 3/26/16.
 */
public class SharedPreference {

    public static final String PREFS_NAME = "USER_PREFS";
    public static final String FULL_NAME_KEY = "USER_FULL_NAME";
    public static final String FIRST_NAME_KEY = "USER_FIRST_NAME";
    public static final String LAST_NAME_KEY = "USER_LAST_NAME";
    public static final String EMAIL_KEY = "USER_EMAIL";
    public static final String FACEBOOK_ID_KEY = "USER_FACEBOOK_ID_KEY";
    public static final String FACEBOOK_PIC_URL_KEY = "USER_PICTURE_KEY";
    public static final String PHONE_NUMBER_KEY = "USER_PHONE_NUMBER";
    public static final String COMPANY_KEY = "USER_COMPANY";
    public static final String FACEBOOK_LINK_KEY = "USER_FACEBOOK_LINK";
    public static final String LINKEDIN_NAME_KEY = "USER_LINKEDIN_NAME";

    public SharedPreference()
    {
        super();
    }

    public void saveText(Context context, String key, String text)
    {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        switch(key){
            case FULL_NAME_KEY:
                editor.putString(FULL_NAME_KEY, text);
                break;
            case FIRST_NAME_KEY:
                editor.putString(FIRST_NAME_KEY, text);
                break;
            case LAST_NAME_KEY:
                editor.putString(LAST_NAME_KEY, text);
                break;
            case EMAIL_KEY:
                editor.putString(EMAIL_KEY, text);
                break;
            case FACEBOOK_ID_KEY:
                editor.putString(FACEBOOK_ID_KEY, text);
                break;
            case FACEBOOK_PIC_URL_KEY:
                editor.putString(FACEBOOK_PIC_URL_KEY, text);
                break;
            case PHONE_NUMBER_KEY:
                editor.putString(PHONE_NUMBER_KEY, text);
                break;
            case COMPANY_KEY:
                editor.putString(COMPANY_KEY, text);
                break;
            case FACEBOOK_LINK_KEY:
                editor.putString(FACEBOOK_LINK_KEY, text);
                break;
            case LINKEDIN_NAME_KEY:
                editor.putString(LINKEDIN_NAME_KEY, text);
                break;
        }

        editor.commit();
    }

    public void saveBool(Context context, String key, Boolean active)
    {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        switch(key){
            case FULL_NAME_KEY:
                editor.putBoolean(FULL_NAME_KEY, active);
                break;
            case FIRST_NAME_KEY:
                editor.putBoolean(FIRST_NAME_KEY, active);
                break;
            case LAST_NAME_KEY:
                editor.putBoolean(LAST_NAME_KEY, active);
                break;
            case EMAIL_KEY:
                editor.putBoolean(EMAIL_KEY, active);
                break;
            case FACEBOOK_ID_KEY:
                editor.putBoolean(FACEBOOK_ID_KEY, active);
                break;
            case FACEBOOK_PIC_URL_KEY:
                editor.putBoolean(FACEBOOK_PIC_URL_KEY, active);
                break;
            case PHONE_NUMBER_KEY:
                editor.putBoolean(PHONE_NUMBER_KEY, active);
                break;
            case COMPANY_KEY:
                editor.putBoolean(COMPANY_KEY, active);
                break;
            case FACEBOOK_LINK_KEY:
                editor.putBoolean(FACEBOOK_LINK_KEY, active);
                break;
            case LINKEDIN_NAME_KEY:
                editor.putBoolean(LINKEDIN_NAME_KEY, active);
                break;
        }

        editor.commit();
    }

    public String getValue(Context context, String key)
    {
        SharedPreferences settings;
        String text = null;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        switch(key){
            case FULL_NAME_KEY:
                text = settings.getString(FULL_NAME_KEY, null);
                break;
            case FIRST_NAME_KEY:
                text = settings.getString(FIRST_NAME_KEY, null);
                break;
            case LAST_NAME_KEY:
                text = settings.getString(LAST_NAME_KEY, null);
                break;
            case EMAIL_KEY:
                text = settings.getString(EMAIL_KEY, null);
                break;
            case FACEBOOK_ID_KEY:
                text = settings.getString(FACEBOOK_ID_KEY, null);
                break;
            case FACEBOOK_PIC_URL_KEY:
                text = settings.getString(FACEBOOK_PIC_URL_KEY, null);
                break;
            case PHONE_NUMBER_KEY:
                text = settings.getString(PHONE_NUMBER_KEY, null);
                break;
            case COMPANY_KEY:
                text = settings.getString(COMPANY_KEY, null);
                break;
            case FACEBOOK_LINK_KEY:
                text = settings.getString(FACEBOOK_LINK_KEY, null);
                break;
            case LINKEDIN_NAME_KEY:
                text = settings.getString(LINKEDIN_NAME_KEY, null);
                break;
        }

        return text;
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

        switch(key){
            case FULL_NAME_KEY:
                editor.remove(FULL_NAME_KEY);
                break;
            case FIRST_NAME_KEY:
                editor.remove(FIRST_NAME_KEY);
                break;
            case LAST_NAME_KEY:
                editor.remove(LAST_NAME_KEY);
                break;
            case EMAIL_KEY:
                editor.remove(EMAIL_KEY);
                break;
            case FACEBOOK_ID_KEY:
                editor.remove(FACEBOOK_ID_KEY);
                break;
            case FACEBOOK_PIC_URL_KEY:
                editor.remove(FACEBOOK_PIC_URL_KEY);
                break;
            case PHONE_NUMBER_KEY:
                editor.remove(PHONE_NUMBER_KEY);
                break;
            case COMPANY_KEY:
                editor.remove(COMPANY_KEY);
                break;
            case FACEBOOK_LINK_KEY:
                editor.remove(FACEBOOK_LINK_KEY);
                break;
            case LINKEDIN_NAME_KEY:
                editor.remove(LINKEDIN_NAME_KEY);
                break;
        }

        editor.commit();
    }
}