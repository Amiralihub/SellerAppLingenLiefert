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
    private Button loginButton;

    private LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);


        // Verwende den ApplicationContext für den LoginManager
        loginManager = LoginManager.getInstance(getApplicationContext());

        loginButton.setOnClickListener(v -> login());
    }




    private void login() {
        String enteredUsername = usernameEditText.getText().toString();
        String enteredPassword = passwordEditText.getText().toString();
        loginManager = new LoginManager(enteredUsername, enteredPassword);

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

    }
}
