package com.example.sellapplingen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HandlingInfoFragment extends Fragment {

    CheckBox chkOption1, chkOption2, chkOption3, chkOption4;
    Button confirmButton, backToScannerFragmentButton;
    private final StringBuilder selectedInfo = new StringBuilder();
    private Order order;

    public HandlingInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_handling_info, container, false);


        order = ((MainActivity) requireActivity()).getCurrentOrder();

        chkOption1 = view.findViewById(R.id.fluentOption);
        chkOption2 = view.findViewById(R.id.fragileOption);
        chkOption3 = view.findViewById(R.id.glasOption);
        chkOption4 = view.findViewById(R.id.noOption); // Assuming you have this checkbox in your layout
        confirmButton = view.findViewById(R.id.confirmButton);
        backToScannerFragmentButton = view.findViewById(R.id.backToScannerFragmentButton);

        chkOption1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedInfo.append("Flüssig");
                selectedInfo.append(", "); // Füge ein Trennzeichen hinzu, um die verschiedenen Optionen zu trennen
            }
        });

        chkOption2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedInfo.append("Zerbrechlich");
                selectedInfo.append(", "); // Füge ein Trennzeichen hinzu, um die verschiedenen Optionen zu trennen
            }
        });

        chkOption3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedInfo.append("Glas");
                selectedInfo.append(", "); // Füge ein Trennzeichen hinzu, um die verschiedenen Optionen zu trennen
            }
        });

        chkOption4.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedInfo.append("Keine besondere Eigenschaft");
                selectedInfo.append(", "); // Füge ein Trennzeichen hinzu, um die verschiedenen Optionen zu trennen
            }
        });

        confirmButton.setOnClickListener(v -> {
            String info = selectedInfo.toString();
            if (!info.isEmpty()) {
                info = info.substring(0, info.length() - 2); // Entferne das letzte Trennzeichen ", "
                order.setHandlingInfo(info); // Speichere die ausgewählten Informationen in handlingInfo der Order-Instanz
                showToast(info);
 // Wechsle zum HandlingInfo2Fragment
            } else {
                showToast("No option was selected yet.");
            }
        });

        backToScannerFragmentButton.setOnClickListener(v -> {
            showScannerFragment(); // Wechsle zurück zum ScannerFragment
        });



        return view;
    }

    private void showScannerFragment() {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new ScannerFragment());
        transaction.commit();
    }


    public void speichereOrderDaten() {
        Log.d("Test", "Handling Info: " + order.getHandlingInfo());
        // Hier könntest du auch Toast-Nachrichten verwenden, um die Werte anzuzeigen
    }



    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
