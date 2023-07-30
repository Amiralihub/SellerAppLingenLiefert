package com.example.sellapplingen;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        // Hier den Code zum Speichern der gescannten Informationen in der Order-Klasse einfügen
        // Beispiel: Order.getInstance().setHandlingInfo(scanResult);
        // In diesem Beispiel wird die statische Methode getInstance() der Order-Klasse aufgerufen,
        // um eine Singleton-Instanz der Order-Klasse zu erhalten, und dann die setHandlingInfo-Methode
        // aufgerufen, um das gescannte Ergebnis zu speichern.
        // Stelle sicher, dass die Order-Klasse eine entsprechende setHandlingInfo-Methode hat.
        // Du kannst die Informationen aus dem scanResult entsprechend aufteilen und in die
        // entsprechenden Felder der Order-Klasse setzen.

        // Anschließend, nachdem die Informationen in der Order-Klasse gespeichert wurden, rufe die
        // Methode onScanSuccess() in der MainActivity auf, um zum HandlingInfoFragment zu wechseln.
        MainActivity mainActivity = (MainActivity) requireActivity();
        mainActivity.onScanSuccess();
    }

    private void scanCode() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            // Wenn die Kameraerlaubnis erteilt wurde, starte den QR-Code-Scanner
            Intent intent = new Intent(getActivity(), CaptureActivity.class);
            barLauncher.launch(intent);
        } else {
            // Wenn die Kameraerlaubnis nicht erteilt wurde, fordere sie an
            requestCameraPermission();
        }
    }

    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.CAMERA)) {
            // Zeige eine Erklärung, warum die Kameraerlaubnis benötigt wird (optional)
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
            // Fordere die Kameraerlaubnis an
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
