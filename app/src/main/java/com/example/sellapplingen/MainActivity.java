package com.example.sellapplingen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sellapplingen.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private Fragment currentFragment;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        boolean nightMode = sharedPreferences.getBoolean("night", false);
        if (nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

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

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            replaceFragment(new ScannerFragment());
        }

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_scanner:
                    replaceFragment(new ScannerFragment());
                    break;
                case R.id.action_settings:
                    replaceFragment(new SettingFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        currentFragment = fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void showScannerFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new ScannerFragment());
        transaction.commit();
    }
}
