package com.example.sellapplingen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;


//TODO: Handling Info muss als Fragment implementiert werden
//TODO: Ausgewählte Optionen müssen in einem dazugehörigen Obkjekt als Strings gespeichert werden
//TODO XML (Alaa): Es muss eine weitere Option "Keine besondere Eigenschaft" geben, falls keine Option zutrifft
//TODO XML (Alaa): Ein weiterer backTofragmentButton (mein ImageButton kann verwendet werden), der oben links platziert ist
//TODO: backTofragmentButton leitet den Nutzer auf dem QRCOdeScanner weiter
//TODO: confirmButton leitet den Nutzer auf HandlingInfo2 weiter



public class HandlingInfo extends AppCompatActivity {


    private StringBuilder selectedInfo = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handling_info);

        CheckBox chkOption1 = findViewById(R.id.chkOption1);
        CheckBox chkOption2 = findViewById(R.id.chkOption2);
        CheckBox chkOption3 = findViewById(R.id.chkOption3);
        Button confirmButton = findViewById(R.id.confirmButton);

        chkOption1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedInfo.append("Flüssig");
            }
        });

        chkOption2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedInfo.append("Zerbrechlich");
            }
        });

        chkOption3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedInfo.append("Glas");
            }
        });

        confirmButton.setOnClickListener(v -> {
            String info = selectedInfo.toString();
            if (!info.isEmpty()) {
                info = info.substring(0, info.length() - 4); // Remove the last " && "
                showToast(info);
            } else {
                showToast("No option was selected yet.");
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
