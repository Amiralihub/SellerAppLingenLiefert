package com.example.sellapplingen;


import android.content.Context;
import android.content.SharedPreferences;

public class LoginManager {

    private static final String PREF_NAME = "LoginPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    private String validUsername;
    private String validPassword;
    private Context context;

    public LoginManager(Context context, String username, String password) {
        this.context = context;
        this.validUsername = username;
        this.validPassword = password;
    }

    public boolean isLoginValid(String enteredUsername, String enteredPassword) {
        return enteredUsername.equals(validUsername) && enteredPassword.equals(validPassword);
    }

    public void saveLoginCredentials() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, validUsername);
        editor.putString(KEY_PASSWORD, validPassword);
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString(KEY_USERNAME, "");
        String savedPassword = sharedPreferences.getString(KEY_PASSWORD, "");
        return validUsername.equals(savedUsername) && validPassword.equals(savedPassword);
    }
}
