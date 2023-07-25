package com.example.sellapplingen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


//TODO XML (Alaa): Ein weiterer backTofragmentButton (mein ImageButton kann verwendet werden), der oben links platziert ist #ERLEDIGT
//TODO: backTofragmentButton leitet den Nutzer auf dem QRCOdeScanner
//TODO: confirmButton leitet den Nutzer auf HandlingInfo3 weiter
//TODO: backButton leitet den Nutzer auf HandlingInfo weiter
//TODO: Funktion zum erhöhen und zum reduzieren von den Anzahl der Paketen
//TODO: Die Informationen in einem zugehörigen Objekt speichern

public class HandlingInfo2Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HandlingInfo2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HandlingInfo2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HandlingInfo2Fragment newInstance(String param1, String param2) {
        HandlingInfo2Fragment fragment = new HandlingInfo2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_handling_info2, container, false);
    }
}