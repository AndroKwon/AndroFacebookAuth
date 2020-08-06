package com.andro.unityplatformplugin.facebook;

import android.content.Context;
import android.content.Intent;

import com.andro.unityplatformplugin.AndroActivity;
import com.andro.unityplatformplugin.AndroAuthProvider;
import com.andro.unityplatformplugin.AndroAuthProviderCallback;
import com.andro.unityplatformplugin.message.NativeMessage;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class AndroFacebookAuth implements AndroAuthProvider {

    private static CallbackManager FacebookCallbackManager;

    public void initialize(Context context, String jsonData)
    {
        FacebookCallbackManager = CallbackManager.Factory.create();

        try
        {
            JSONObject jsonObj = new JSONObject(jsonData);
            FacebookSdk.setApplicationId(jsonObj.optString("facebookApplicationId"));
            FacebookSdk.sdkInitialize(AndroActivity.activity.getApplicationContext());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        FacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void login(final AndroAuthProviderCallback callback)
    {
        AndroActivity.REQUEST_CODE = AndroActivity.REQUEST_AUTH_FACEBOOK;

        LoginManager loginManager = LoginManager.getInstance();
        loginManager.logInWithReadPermissions(AndroActivity.activity, Arrays.asList("public_profile", "email"));
        LoginManager.getInstance().registerCallback(FacebookCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        callback.OnCallback(true, loginResult.getAccessToken().getToken());
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        callback.OnCallback(false, "cancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        callback.OnCallback(false, exception.getMessage());
                    }
                });
    }

    public void logout(final AndroAuthProviderCallback callback)
    {
        LoginManager.getInstance().logOut();
        callback.OnCallback(true, "");
    }
}
