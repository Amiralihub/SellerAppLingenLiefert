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

        loginButton.setOnClickListener(v -> startMainActivity());
    }
    private void startMainActivity() {
        // Starte die MainActivity
        startActivity(new Intent(this, MainActivity.class));
        finish(); // Beende die LoginActivity
    }





}
