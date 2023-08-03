package com.example.sellapplingen;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.example.sellapplingen.databinding.FragmentScannerBinding;

public class ScannerFragment extends Fragment {



    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;

    private FragmentScannerBinding binding;
    private ActivityResultLauncher<Intent> barLauncher;

    public ScannerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentScannerBinding.inflate(inflater, container, false);
        setupViews();
        setupBarcodeScanner();

        return binding.getRoot();
    }

    private void setupViews() {
        binding.btnScan.setOnClickListener(v -> scanCode());
    }

    private void setupBarcodeScanner() {
        barLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        String contents = result.getData().getStringExtra("SCAN_RESULT");
                        showResultDialog(contents);
                        // Speichere die gescannten Informationen in Order
                        saveScanResultToOrder(contents);
                    }
                });
    }

    private void saveScanResultToOrder(String scanResult) {
        String[] scanResultArray = scanResult.split("&");
        if (scanResultArray.length == 6) {
            Order order = ((MainActivity) requireActivity()).getCurrentOrder();
            order.setLastName(scanResultArray[0]);
            order.setFirstName(scanResultArray[1]);
            order.setStreet(scanResultArray[2]);
            order.setHouseNumber(scanResultArray[3]);
            order.setZip(scanResultArray[4]);
            order.setCity(scanResultArray[5]);



            MainActivity mainActivity = (MainActivity) requireActivity();
            mainActivity.onScanSuccess();
        } else {

            showResultDialog("Ungültiger QR-Code-Format.");
        }

    }


    private void scanCode() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {

            Intent intent = new Intent(getActivity(), CaptureActivity.class);
            barLauncher.launch(intent);
        } else {

            requestCameraPermission();
        }
    }

    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.CAMERA)) {

            new AlertDialog.Builder(requireContext())
                    .setTitle("Kameraerlaubnis benötigt")
                    .setMessage("Die App benötigt Zugriff auf die Kamera, um QR-Codes zu scannen.")
                    .setPositiveButton("OK", (dialog, which) -> {
                        // Fordere die Kameraerlaubnis an
                        ActivityCompat.requestPermissions(requireActivity(),
                                new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
                    })
                    .setNegativeButton("Abbrechen", null)
                    .show();
        } else {

            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
        }
    }

    private void showResultDialog(String result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Ergebnis");
        builder.setMessage(result);
        builder.setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss()).show();
    }
}
