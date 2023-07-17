import android.Manifest;
import android.content.DialogInterface;
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

public class ScannerFragment extends Fragment {

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;

    private Button btnScan;
    private ActivityResultLauncher<Intent> barLauncher;

    public ScannerFragment() {
        // Required empty public constructor
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
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Fordere die Kameraerlaubnis an
                            ActivityCompat.requestPermissions(requireActivity(),
                                    new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
                        }
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
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scanner, container, false);

        btnScan = view.findViewById(R.id.btnScan);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanCode();
            }
        });

        barLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == getActivity().RESULT_OK && result.getData() != null) {
                        String contents = result.getData().getStringExtra("SCAN_RESULT");
                        showResultDialog(contents);
                    }
                });

        return view;
    }
}
