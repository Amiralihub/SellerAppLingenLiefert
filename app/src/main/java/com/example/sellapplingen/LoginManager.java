package com.example.sellapplingen;

import static android.icu.text.ListFormatter.Type.OR;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginManager {

    // Die URL des Servers, auf dem die Benutzerdaten überprüft werden sollen
    private static final String SERVER_URL = "http://131.173.65.77:5000/auth/login";

    public static final String PREF_NAME = "LoginPrefs";
    public String KEY_USERNAME = "awd";
    public String KEY_PASSWORD = "awd";

    private static LoginManager instance;
    private final Context context;

    // Privater Konstruktor, um die Instanz nur einmal zu erstellen
    private LoginManager(Context context) {
        this.context = context;
    }

    // Statische Methode zum Erhalten der einzigen Instanz des LoginManagers
    public static LoginManager getInstance(Context context) {
        if (instance == null) {
            instance = new LoginManager(context.getApplicationContext());
        }
        return instance;
    }

    public boolean isLoginValid(String enteredUsername, String enteredPassword) {
        try {

            URL url = new URL("http://131.173.65.77:5000/auth/login");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            JSONObject requestData = new JSONObject();
            requestData.put("username", enteredUsername); // Verwende die eingegebenen Benutzerdaten
            requestData.put("password", enteredPassword); // Verwende die eingegebenen Benutzerdaten

            DataOutputStream os = new DataOutputStream(connection.getOutputStream());
            os.writeBytes(requestData.toString());
            os.flush();
            os.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Erfolgreiche Anmeldung
                Log.d("Wiebitte", "LOGIN ERFOLGREICH!");
                return true;
            } else {
                // Falsche Anmeldedaten oder anderer Fehler
                System.out.println("Nicht erfolgreich du penner");
                Log.d("Wiekacke", "LOGIN KACKE!");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    //jsonwebtoken muss immer mit geschickt werden, z.b bei settings usw

    public void saveLoginCredentials(String enteredUsername, String enteredPassword) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, enteredUsername);
        editor.putString(KEY_PASSWORD, enteredPassword);
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString(KEY_USERNAME, "");
        String savedPassword = sharedPreferences.getString(KEY_PASSWORD, "");
        return isLoginValid(savedUsername, savedPassword);
    }

    // Beispiel-Implementierung für die Überprüfung der Login-Daten mit dem Server
    private boolean serverLoginCheck(String username, String password) {
        // Hier einen HTTP-GET-Request an den Server senden und die Antwort überprüfen
        try {
            URL url = new URL(SERVER_URL + "?username=" + username + "&password=" + password);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(20000);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String response = bufferedReader.readLine();
                bufferedReader.close();
                inputStream.close();

                return "success".equals(response); // Hier sollte die Antwort des Servers überprüft werden
            } else {
                // Fehler bei der Verbindung oder Antwort des Servers
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


    }

    public void sendPost() {
        Log.d("LoginManager", "sendPost() Methode aufgerufen");

        if (KEY_USERNAME == null || KEY_PASSWORD == null )  {
            Log.d("LoginManager", "Name oder Passwort ist null");
            throw new NullPointerException("Name or password is null");
        } else if (KEY_USERNAME.trim().isEmpty() || KEY_PASSWORD.trim().isEmpty()) {
            Log.d("LoginManager", "Name oder Passwort ist leer");
            throw new IllegalArgumentException("Name or password is empty");
        }

        Log.d("LoginManager", "Geht in Methdawdwdode");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://131.173.65.77:5000/auth/login");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    Log.d("LoginManager", "Serververbindung wird hergestellt...");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject requestData = new JSONObject();
                    requestData.put("username", KEY_USERNAME.trim());
                    requestData.put("password", KEY_PASSWORD.trim());

                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(requestData.toString());
                    os.flush();
                    os.close();

                    int responseCode = conn.getResponseCode();
                    Log.d("LoginManager", "Serverantwortcode: " + responseCode);

                    String responseMessage = conn.getResponseMessage(); // Hier die getResponseMessage() aufrufen
                    Log.d("LoginManager", "Serverantwortnachricht: " + responseMessage);

                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = conn.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        String response = bufferedReader.readLine();
                        bufferedReader.close();
                        inputStream.close();

                        if ("success".equals(response)) {
                            // Login successful, you can perform further actions here
                            Log.d("LoginManager", "Login erfolgreich!");
                        } else {
                            // Login failed
                            Log.d("LoginManager", "Login fehlgeschlagen!");
                        }
                    } else {
                        // Connection or server response error
                        Log.d("LoginManager", "Fehler bei der Verbindung oder Antwort des Servers!");
                    }

                    conn.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.start();
    }

}

