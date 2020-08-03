package com.andro.unityplatformplugin.facebook;

import com.andro.unityplatformplugin.AndroAuthProvider;

public class AuthProviderFactory {

    private static AndroFacebookAuth facebookAuth = null;

    public static AndroAuthProvider create() {
        if (facebookAuth != null)
            return facebookAuth;

        facebookAuth = new AndroFacebookAuth();
        return facebookAuth;
    }
}
