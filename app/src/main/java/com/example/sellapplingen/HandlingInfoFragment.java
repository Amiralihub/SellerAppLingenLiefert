package com.example.sellapplingen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class HandlingInfoFragment extends Fragment {

    CheckBox chkOption1, chkOption2, chkOption3, chkOption4;
    Button confirmButton, backToScannerFragmentButton;
    private StringBuilder selectedInfo = new StringBuilder();

    public HandlingInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_handling_info, container, false);

        chkOption1 = view.findViewById(R.id.chkOption1);
        chkOption2 = view.findViewById(R.id.chkOption2);
        chkOption3 = view.findViewById(R.id.chkOption3);
        chkOption4 = view.findViewById(R.id.chkOption4); // Assuming you have this checkbox in your layout
        confirmButton = view.findViewById(R.id.confirmButton);
        backToScannerFragmentButton = view.findViewById(R.id.backToScannerFragmentButton);

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

        confirmButton.setOnClickListener(v -> {
            String info = selectedInfo.toString();
            if (!info.isEmpty()) {
                info = info.substring(0, info.length() - 4); // Remove the last " && "
                showHandlingInfo2();
                showToast(info);
            } else {
                showToast("No option was selected yet.");
            }
        });

        backToScannerFragmentButton.setOnClickListener(v -> {
            showScannerFragment();
        });

        return view;
    }

    private void showScannerFragment() {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new ScannerFragment());
        transaction.commit();
    }

    private void showHandlingInfo2() {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new HandlingInfo2Fragment());
        transaction.commit();
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
