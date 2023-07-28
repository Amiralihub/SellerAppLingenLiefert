package com.example.sellapplingen;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


//TODO: Handling Info muss als Fragment implementiert werden
//TODO: Ausgewählte Optionen müssen in einem dazugehörigen Obkjekt als Strings gespeichert werden
//TODO: backTofragmentButton leitet den Nutzer auf dem QRCOdeScanner weiter
//TODO: confirmButton leitet den Nutzer auf HandlingInfo2 weiter



public class HandlingInfo extends AppCompatActivity {
    private Fragment currentFragment;
    CheckBox chkOption1, chkOption2, chkOption3, chkOption4;
    Button confirmButton, backToScannerFragmentButton;
    private StringBuilder selectedInfo = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handling_info);

        chkOption1 = findViewById(R.id.chkOption1);
        chkOption2 = findViewById(R.id.chkOption2);
        chkOption3 = findViewById(R.id.chkOption3);
        confirmButton = findViewById(R.id.confirmButton);
        backToScannerFragmentButton = findViewById(R.id.backToScannerFragmentButton);

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

        chkOption4.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedInfo.append("Keine besondere Eigenschaft");
            }
        });

        //Anpassung notwendig, weil es jetzt eine vierte Option gibt
        confirmButton.setOnClickListener(v -> {
            String info = selectedInfo.toString();
            if (!info.isEmpty()) {
                info = info.substring(0, info.length() - 4); // Remove the last " && "
                showToast(info);
            } else {
                showToast("No option was selected yet.");
            }
        });

        backToScannerFragmentButton.setOnClickListener(v -> {
            showScannerFragment();
        });
    }

    private void showScannerFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new ScannerFragment());
        transaction.commit();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
