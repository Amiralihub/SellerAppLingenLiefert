package com.example.sellapplingen;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HandlingInfoFragment extends Fragment {

    CheckBox chkOption1, chkOption2, chkOption3, chkOption4, chkOption5, small, medium, large, xlarge;
    Button confirmButton, backToScannerFragmentButton;
    final Calendar myCalendar = Calendar.getInstance();

    EditText reciptname;
    private final StringBuilder selectedInfo = new StringBuilder();
    private final StringBuilder packageSizeInfo = new StringBuilder();
    private Order order;
    Button date,time;
    String setDate = "";
    String getTime = "";

    Order clientInfo;

    public HandlingInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_handling_info, container, false);


//        order = ((MainActivity) requireActivity()).getCurrentOrder();

        small = view.findViewById(R.id.small);
        reciptname = view.findViewById(R.id.recipientNameEditText);
        date = view.findViewById(R.id.calendarView);
        time = view.findViewById(R.id.timeview);
        medium = view.findViewById(R.id.medium);
        large = view.findViewById(R.id.large);
        xlarge = view.findViewById(R.id.xlarge);
//        clientInfo = (Order) requireActivity().getIntent().getSerializableExtra("order");

        chkOption1 = view.findViewById(R.id.fluentOption);
        chkOption2 = view.findViewById(R.id.fragileOption);
        chkOption3 = view.findViewById(R.id.glasOption);
        chkOption4 = view.findViewById(R.id.noOption); // Assuming you have this checkbox in your layout
        chkOption5 = view.findViewById(R.id.heavy);
        confirmButton = view.findViewById(R.id.confirmButton);

        time = view.findViewById(R.id.timeview);

// Aktuelle Uhrzeit festlegen
        String myFormat = "hh:mm";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        getTime = dateFormat.format(Calendar.getInstance().getTime());
        time.setText(getTime);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(requireActivity(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {

                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, day);

                        if (myCalendar.get(Calendar.HOUR_OF_DAY) >= 13) {
                            // Wenn die ausgewählte Uhrzeit nach 13:00 Uhr ist, erhöhe den Tag um 1
                            myCalendar.add(Calendar.DAY_OF_MONTH, 1);
                        }
                        String myFormat = "d-MMM-yyyy";
                        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
                        setDate = dateFormat.format(myCalendar.getTime());
                        date.setText(setDate);

                    }
                }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });


        //backToScannerFragmentButton = view.findViewById(R.id.backToScannerFragmentButton);
        small.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                packageSizeInfo.append("small");
                packageSizeInfo.append(", "); // Füge ein Trennzeichen hinzu, um die verschiedenen Optionen zu trennen
            }
        });
        medium.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                packageSizeInfo.append("medium");
                packageSizeInfo.append(", "); // Füge ein Trennzeichen hinzu, um die verschiedenen Optionen zu trennen
            }
        });
        large.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                packageSizeInfo.append("large");
                packageSizeInfo.append(", "); // Füge ein Trennzeichen hinzu, um die verschiedenen Optionen zu trennen
            }
        });
        xlarge.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                packageSizeInfo.append("Extra large");
                packageSizeInfo.append(", "); // Füge ein Trennzeichen hinzu, um die verschiedenen Optionen zu trennen
            }
        });


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
            Order order1 = new Order();
            order1.setLastName(clientInfo.getLastName());
            order1.setFirstName(clientInfo.getFirstName());
            order1.setStreet(clientInfo.getStreet());
            order1.setHouseNumber(clientInfo.getHouseNumber());
            order1.setZip(clientInfo.getZip());
            order1.setCity(clientInfo.getCity());

            order1.setPackageSize(packageSizeInfo.toString());
            order1.setHandlingInfo(selectedInfo.toString());
            order1.setDeliveryDate(setDate);
            order1.setNumberPackage(reciptname.getText().toString());
            order1.setTimestamp(getTime);
            String info = selectedInfo.toString();
            Log.i("tariq", "onCreateView: " + info + "\n" + packageSizeInfo + "\n" + setDate);
            DeliveryDetailsFragment fragment = new DeliveryDetailsFragment();
            Bundle args = new Bundle();
            args.putSerializable("order", order1);
            fragment.setArguments(args);
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame_layout, fragment);
            transaction.commit();
/*            if (!info.isEmpty()) {
                info = info.substring(0, info.length() - 2); // Entferne das letzte Trennzeichen ", "
                order.setHandlingInfo(info); // Speichere die ausgewählten Informationen in handlingInfo der Order-Instanz
                showToast(info);
                // Wechsle zum HandlingInfo2Fragment
            } else {
                showToast("No option was selected yet.");
            }*/
        });

/*        backToScannerFragmentButton.setOnClickListener(v -> {
            showScannerFragment(); // Wechsle zurück zum ScannerFragment
        });*/

        if(getArguments()!=null){
            clientInfo= (Order) getArguments().getSerializable("order");
        }
        Log.i("tariq", "onCreateView: check "+clientInfo);

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
