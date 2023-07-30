package com.example.sellapplingen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HandlingInfo2Fragment extends Fragment {

    Button confirmButton2, backButton1, backToScannerFragmentButton2;
    EditText etPackageCount;
    private Order order; // Die Order-Instanz, in der die ausgewählten Informationen gespeichert werden

    public HandlingInfo2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_handling_info2, container, false);

        // Hole die Order-Instanz aus den Fragment-Argumenten
        Bundle args = getArguments();
        if (args != null && args.containsKey("order")) {
            order = (Order) args.getSerializable("order");
        }

        backToScannerFragmentButton2 = view.findViewById(R.id.backToScannerFragmentButton2);
        confirmButton2 = view.findViewById(R.id.confirmButton2);
        backButton1 = view.findViewById(R.id.backButton1);
        etPackageCount = view.findViewById(R.id.etPackageCount);

        confirmButton2.setOnClickListener(v -> {
            String packageCount = etPackageCount.getText().toString();
            order.setPackageSize(packageCount); // Speichere die Paketanzahl in packageSize der Order-Instanz
            showDeliveryDetailsFragment();
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
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        HandlingInfoFragment handlingInfoFragment = new HandlingInfoFragment();
        transaction.replace(R.id.frame_layout, handlingInfoFragment);
        transaction.commit();
    }

    private void showScannerFragment() {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new ScannerFragment());
        transaction.commit();
    }

    private void showDeliveryDetailsFragment() {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, DeliveryDetailsFragment.newInstance(order));
        transaction.commit();
    }

    }

