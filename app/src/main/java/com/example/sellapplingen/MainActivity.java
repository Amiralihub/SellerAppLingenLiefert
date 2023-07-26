package com.example.sellapplingen;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain);

        // Verwende den ApplicationContext für den LoginManager
        loginManager = LoginManager.getInstance(getApplicationContext());

        if (!loginManager.isLoggedIn()) {
            // Wenn der Benutzer nicht eingeloggt ist, starte die LoginActivity
            startActivity(new Intent(this, LoginActivity.class));
            finish(); // Beende die MainActivity
        }

        // Hier kannst du den Rest des Codes für die MainActivity hinzufügen
    }
}
