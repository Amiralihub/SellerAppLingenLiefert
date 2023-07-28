package com.example.sellapplingen;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



//TODO: Die Informationen in einem zugehörigen Objekt speichern


public class HandlingInfo3Fragment extends Fragment {

    Button confirmButton3, backButton2, backToScannerFragmentButton3;

    public HandlingInfo3Fragment() {
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

        View view = inflater.inflate(R.layout.fragment_handling_info3, container, false);

        backToScannerFragmentButton3 = view.findViewById(R.id.backToScannerFragmentButton3);
        confirmButton3 = view.findViewById(R.id.confirmButton3);
        backButton2 = view.findViewById(R.id.backButton2);


        confirmButton3.setOnClickListener(v -> {
            showDeliveryDetails();
        });

        backToScannerFragmentButton3.setOnClickListener(v -> {
            showScannerFragment();
        });

        backButton2.setOnClickListener(v -> {
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

    private void showDeliveryDetails() {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new DeliveryDetailsFragment());
        transaction.commit();
    }
}