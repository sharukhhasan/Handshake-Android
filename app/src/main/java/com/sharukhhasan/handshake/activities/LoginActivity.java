package com.sharukhhasan.handshake.activities;

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
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import com.sharukhhasan.handshake.PreferenceUtils;
import com.sharukhhasan.handshake.R;
import com.sharukhhasan.handshake.User;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (LoginButton) findViewById(R.id.facebook_login_button);
        loginButton.setReadPermissions("public_profile, email");

        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(final JSONObject object, GraphResponse response)
                            {
                                Log.v("LoginActivity", response.toString());

                                final JSONObject jsonObject = response.getJSONObject();

                                try {
                                    user = new User();
                                    user.setUserName(jsonObject.getString("name"));
                                    user.setUserEmail(jsonObject.getString("email"));
                                    user.setUserFacebookId(jsonObject.getString("id"));
                                    user.setUserPictureURL(new URL("https://graph.facebook.com/" + user.userFacebookId + "/picture?type=large"));

                                    URL image_url = user.getUserPictureURL();
                                    user.setUserPicture(BitmapFactory.decodeStream(image_url.openConnection().getInputStream()));

                                    PreferenceUtils.setCurrentUser(user, LoginActivity.this);

                                    Log.d(TAG, user.userName);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(homeIntent);
                                finish();
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
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
