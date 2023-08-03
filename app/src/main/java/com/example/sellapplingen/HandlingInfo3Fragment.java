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
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HandlingInfo3Fragment extends Fragment {

    Button confirmButton3, backButton2, backToScannerFragmentButton3;
    CheckBox chkOptionS, chkOptionM, chkOptionL, chkOptionXL;
    CalendarView calendarView;
    Date selectedDate;

    public HandlingInfo3Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_handling_info3, container, false);

        // Zugriff auf die bereits existierende Order-Instanz in der MainActivity
        Order order = ((MainActivity) requireActivity()).getCurrentOrder();

        backToScannerFragmentButton3 = view.findViewById(R.id.backToScannerFragmentButton3);
        confirmButton3 = view.findViewById(R.id.confirmButton3);
        backButton2 = view.findViewById(R.id.backButton2);

        chkOptionS = view.findViewById(R.id.heavyOption);
        chkOptionM = view.findViewById(R.id.fluentOption);
        chkOptionL = view.findViewById(R.id.fragileOption);
        chkOptionXL = view.findViewById(R.id.glasOption);
        calendarView = view.findViewById(R.id.calendarView);

        chkOptionS.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                order.setPackageSize("S");
            }
        });

        chkOptionM.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                order.setPackageSize("M");
            }
        });

        chkOptionL.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                order.setPackageSize("L");
            }
        });

        chkOptionXL.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                order.setPackageSize("XL");
            }
        });

        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            Calendar selectedCalendar = Calendar.getInstance();
            selectedCalendar.set(year, month, dayOfMonth);
            selectedDate = selectedCalendar.getTime();
        });

        confirmButton3.setOnClickListener(v -> {
            if (selectedDate != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                String formattedDate = dateFormat.format(selectedDate);
                order.setDeliveryDate(formattedDate);
                showToast("Package Size: " + order.getPackageSize() + ", Delivery Date: " + formattedDate);
                showDeliveryDetails();
            } else {
                showToast("Please select a delivery date.");
            }
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

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
