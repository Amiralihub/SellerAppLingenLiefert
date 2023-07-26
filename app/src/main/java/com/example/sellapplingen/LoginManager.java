package com.example.sellapplingen;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginManager {

    private static final String PREF_NAME = "LoginPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    private static LoginManager instance;
    private String validUsername;
    private String validPassword;
    private Context context;

    // Privater Konstruktor, um die Instanz nur einmal zu erstellen
    private LoginManager(Context context) {
        this.context = context;
        // Die "richtigen" Login-Daten festlegen
        validUsername = "test";
        validPassword = "test";
    }

    // Statische Methode zum Erhalten der einzigen Instanz des LoginManagers
    public static LoginManager getInstance(Context context) {
        if (instance == null) {
            instance = new LoginManager(context.getApplicationContext());
        }
        return instance;
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
