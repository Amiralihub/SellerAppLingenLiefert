package com.example.sellapplingen;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanOptions;

public class QRCodeScanner extends AppCompatActivity {

    Button btnScan;
    ActivityResultLauncher<Intent> barLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_scanner);

        btnScan = findViewById(R.id.btnScan);
        btnScan.setOnClickListener(v -> {
            ScanCode();
        });

        barLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        String contents = result.getData().getStringExtra("SCAN_RESULT");
                        showResultDialog(contents);
                    }
                });
    }

    private void ScanCode() {
        Intent intent = new Intent(this, CaptureActivity.class);
        barLauncher.launch(intent);
    }

    private void showResultDialog(String result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Result");
        builder.setMessage(result);
        builder.setPositiveButton("OK", (dialogInterface, i) -> {
            dialogInterface.dismiss();
        }).show();
    }
}