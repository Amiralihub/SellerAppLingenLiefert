// LoginActivity.java
package com.example.sellapplingen;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;

    private LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Verwende den ApplicationContext für den LoginManager
        loginManager = LoginManager.getInstance(getApplicationContext());

        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        Button loginButton = findViewById(R.id.buttonLogin);

        loginButton.setOnClickListener(v -> login());
    }




    private void login() {
        String enteredUsername = usernameEditText.getText().toString();
        String enteredPassword = passwordEditText.getText().toString();

        try {
            // Sende die Benutzerdaten an den Server
            loginManager.sendPost();
        } catch (NullPointerException e) {
            // Handle NullPointerException
            Toast.makeText(this, "Name or password is null", Toast.LENGTH_SHORT).show();
            return;
        } catch (IllegalArgumentException e) {
            // Handle IllegalArgumentException
            Toast.makeText(this, "Name or password is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Überprüfe die Login-Daten mit dem LoginManager
        if (loginManager.isLoginValid(enteredUsername, enteredPassword)) {
            // Erfolgreich eingeloggt - hier kannst du die Hauptactivity starten oder andere Aktionen ausführen
            Toast.makeText(this, "Login erfolgreich!", Toast.LENGTH_SHORT).show();

            // Speichere die Login-Daten im LoginManager
            loginManager.saveLoginCredentials(enteredUsername, enteredPassword);

            // Starte die MainActivity
            startActivity(new Intent(this, MainActivity.class));
            finish(); // Beende die LoginActivity
        } else {
            // Falsche Anmeldedaten
            Toast.makeText(this, "Ungültiger Benutzername oder Passwort.", Toast.LENGTH_SHORT).show();
        }
    }
}
