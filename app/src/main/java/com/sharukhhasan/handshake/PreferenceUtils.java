package com.sharukhhasan.handshake;

import android.content.Context;

/**
 * Created by Sharukh on 3/22/16.
 */
public class PreferenceUtils {

    public static void setCurrentUser(User currentUser, Context ctx)
    {
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "user_prefs", 0);
        complexPreferences.putObject("current_user_value", currentUser);
        complexPreferences.commit();
    }

    public static User getCurrentUser(Context ctx)
    {
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "user_prefs", 0);
        User currentUser = complexPreferences.getObject("current_user_value", User.class);
        return currentUser;
    }

}
