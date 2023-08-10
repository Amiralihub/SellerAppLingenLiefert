
package com.example.sellapplingen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.Objects;

public class SettingFragment extends Fragment {

    private EditText editStoreName, editPassword, editOwner, editStreet, editHouseNumber, editZip, editTelephone, editEmail;
    private Button btnSubmit;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch darkModeSwitch;
    private boolean nightMode;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        /*editStoreName = view.findViewById(R.id.editStoreName);
        editPassword = view.findViewById(R.id.editPassword);
        editOwner = view.findViewById(R.id.editOwner);
        editStreet = view.findViewById(R.id.editStreet);
        editHouseNumber = view.findViewById(R.id.editHouseNumber);
        editZip = view.findViewById(R.id.editZip);
        editTelephone = view.findViewById(R.id.editTelephone);
        editEmail = view.findViewById(R.id.editEmail);


        btnSubmit.setOnClickListener(v -> sendDataToServer());*/

        darkModeSwitch = view.findViewById(R.id.darkModeSwitch);
        sharedPreferences = requireActivity().getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("night", false);
        if (nightMode) {
            darkModeSwitch.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        darkModeSwitch.setOnClickListener(view1 -> {
            nightMode = !nightMode;
            editor = sharedPreferences.edit();
            if (nightMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editor.putBoolean("night", true);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor.putBoolean("night", false);
            }
            editor.apply();
        });

        return view;
    }

    private void sendDataToServer() {
        final String storeName = editStoreName.getText().toString();
        final String password = editPassword.getText().toString();
        final String owner = editOwner.getText().toString();
        final String street = editStreet.getText().toString();
        final String houseNumber = editHouseNumber.getText().toString();
        final String zip = editZip.getText().toString();
        final String telephone = editTelephone.getText().toString();
        final String email = editEmail.getText().toString();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://131.173.65.77:3000/....");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("storeName", storeName);
                    jsonParam.put("password", password);
                    jsonParam.put("owner", owner);
                    jsonParam.put("street", street);
                    jsonParam.put("houseNumber", houseNumber);
                    jsonParam.put("zip", zip);
                    jsonParam.put("telephone", telephone);
                    jsonParam.put("email", email);

                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(jsonParam.toString());
                    os.flush();
                    os.close();

                    int responseCode = conn.getResponseCode();
                    String responseMessage = conn.getResponseMessage();



                    conn.disconnect();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}




























