package com.example.sellapplingen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class SettingFragment extends Fragment {

    private EditText editStoreName, editOwner, editStreet, editHouseNumber, editZip, editTelephone, editEmail;
    private Button btnSubmit;
    private String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        editStoreName = view.findViewById(R.id.editStoreName);
        editOwner = view.findViewById(R.id.editOwner);
        editStreet = view.findViewById(R.id.editStreet);
        editHouseNumber = view.findViewById(R.id.editHouseNumber);
        editZip = view.findViewById(R.id.editZip);
        editTelephone = view.findViewById(R.id.editTelephone);
        editEmail = view.findViewById(R.id.editEmail);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        // Lese den Token aus den SharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(LoginManager.PREF_NAME, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", null);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToServer();
            }
        });

        return view;
    }

    private void sendDataToServer() {
        final String storeName = editStoreName.getText().toString();
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

                    URL url = new URL("http://131.173.65.77:8080/api/setSettings");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);

                    if (token != null) {
                        conn.setRequestProperty("Authorization", "Bearer " + token);
                    }

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("storeName", storeName);
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
