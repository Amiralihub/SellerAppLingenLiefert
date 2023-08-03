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

    //vergleich mit createAndSaveRecipient aus CodeFragment
    //enteredUsername & enteredPassword als parameter
    //LogInManager objekt
    //set userame auf enteredUsername
    private void login() {
        String enteredUsername = usernameEditText.getText().toString();
        String enteredPassword = passwordEditText.getText().toString();

        if (!loginManager.isLoginValid(enteredUsername, enteredPassword)) {
            // Erfolgreich eingeloggt - hier kannst du die Hauptactivity starten oder andere Aktionen ausführen
            Toast.makeText(this, "Login erfolgreich!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish(); // Beende die LoginActivity
        } else {
            // Falsche Anmeldedaten
            Toast.makeText(this, "Ungültiger Benutzername oder Passwort.", Toast.LENGTH_SHORT).show();
        }
    }
}
