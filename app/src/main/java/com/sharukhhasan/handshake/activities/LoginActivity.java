package com.sharukhhasan.handshake.activities;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import com.sharukhhasan.handshake.PreferenceUtils;
import com.sharukhhasan.handshake.R;
import com.sharukhhasan.handshake.SharedPreference;
import com.sharukhhasan.handshake.User;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private SharedPreference sharedPreference;
    AppCompatActivity context = this;
    ProfileTracker profileTracker;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreference = new SharedPreference();

        loginButton = (LoginButton) findViewById(R.id.facebook_login_button);
        loginButton.setReadPermissions("public_profile, email");

        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(final JSONObject object, GraphResponse response)
                    {
                        final JSONObject jsonObject = response.getJSONObject();

                        try {
                            sharedPreference.saveText(context, SharedPreference.FULL_NAME_KEY, jsonObject.getString("name"));
                            sharedPreference.saveText(context, SharedPreference.EMAIL_KEY, jsonObject.getString("email"));
                            sharedPreference.saveText(context, SharedPreference.FACEBOOK_ID_KEY, jsonObject.getString("id"));
                            sharedPreference.saveText(context, SharedPreference.FACEBOOK_LINK_KEY, "fb://profile/" + jsonObject.getString("id"));
                            sharedPreference.saveText(context, SharedPreference.FACEBOOK_PIC_URL_KEY, "https://graph.facebook.com/" + user.userFacebookId + "/picture?type=large");

                            String name = jsonObject.getString("name");
                            String[] splitName = name.split("\\s+");
                            sharedPreference.saveText(context, SharedPreference.FIRST_NAME_KEY, splitName[0]);
                            sharedPreference.saveText(context, SharedPreference.LAST_NAME_KEY, splitName[1]);

                            //URL image_url = user.getUserPictureURL();

                            //user.setUserPicture(BitmapFactory.decodeStream(image_url.openConnection().getInputStream()));

                            PreferenceUtils.setCurrentUser(user, LoginActivity.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if(sharedPreference.isFirstLogin())
                        {
                            Intent settingIntent = new Intent(LoginActivity.this, SettingsActivity.class);
                            startActivity(settingIntent);
                            finish();
                        }
                        else
                        {
                            Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(homeIntent);
                            finish();
                        }
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel()
            {
                Log.v("LoginActivity", "cancel");
            }

            @Override
            public void onError(FacebookException exception)
            {
                Log.v("LoginActivity", exception.getCause().toString());
            }
        });


        if(PreferenceUtils.getCurrentUser(LoginActivity.this) != null)
        {
            Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(homeIntent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
