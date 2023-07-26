package com.example.sellapplingen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Verwende den ApplicationContext für den LoginManager
        LoginManager loginManager = LoginManager.getInstance(getApplicationContext());

        if (!loginManager.isLoggedIn()) {
            // Wenn der Benutzer nicht eingeloggt ist, starte die LoginActivity
            startActivity(new Intent(this, LoginActivity.class));
            finish(); // Beende die MainActivity
        } else {
            // Der Benutzer ist eingeloggt, zeige den ScannerFragment an
            showScannerFragment();
        }
    }

    private void showScannerFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new ScannerFragment());
        transaction.commit();
    }
}
