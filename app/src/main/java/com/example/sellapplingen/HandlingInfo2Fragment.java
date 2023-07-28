package com.example.sellapplingen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


//TODO: backTofragmentButton leitet den Nutzer auf dem QRCOdeScanner
//TODO: confirmButton leitet den Nutzer auf HandlingInfo3 weiter
//TODO: backButton leitet den Nutzer auf HandlingInfo weiter
//TODO: Funktion zum erhöhen und zum reduzieren von den Anzahl der Paketen
//TODO: Die Informationen in einem zugehörigen Objekt speichern

public class HandlingInfo2Fragment extends Fragment {

    Button confirmButton2, backButton1, backToScannerFragmentButton2;

    public HandlingInfo2Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_handling_info2, container, false);

        backToScannerFragmentButton2 = view.findViewById(R.id.backToScannerFragmentButton2);
        confirmButton2 = view.findViewById(R.id.confirmButton2);
        backButton1 = view.findViewById(R.id.backButton1);


        confirmButton2.setOnClickListener(v -> {
            showHandlingInfo3Fragment();
        });

        backToScannerFragmentButton2.setOnClickListener(v -> {
            showScannerFragment();
        });

        backButton1.setOnClickListener(v -> {
            goBackToPreviousFragment();
        });

        return view;
    }

    private void goBackToPreviousFragment() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.popBackStack();
    }

    private void showScannerFragment() {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new ScannerFragment());
        transaction.commit();
    }

    private void showHandlingInfo3Fragment() {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new HandlingInfo3Fragment());
        transaction.commit();
    }

}