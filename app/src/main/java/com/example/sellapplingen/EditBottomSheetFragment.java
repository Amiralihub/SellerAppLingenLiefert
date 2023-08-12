package com.example.sellapplingen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class EditBottomSheetFragment extends BottomSheetDialogFragment {

    private EditFieldData editFieldData;
    private String fieldName;

    public static EditBottomSheetFragment newInstance(EditFieldData editFieldData, String fieldName) {
        EditBottomSheetFragment fragment = new EditBottomSheetFragment();
        fragment.editFieldData = editFieldData;
        fragment.fieldName = fieldName;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_edit_field, container, false);

        TextView oldFieldTextView = view.findViewById(R.id.oldFieldTextView);
        EditText newFieldEditText = view.findViewById(R.id.newFieldEditText);
        Button saveButton = view.findViewById(R.id.saveButton);

        oldFieldTextView.setText(editFieldData.getOldValue());

        saveButton.setOnClickListener(v -> {
            String newValue = newFieldEditText.getText().toString();
            editFieldData.setNewValue(newValue);
            // TODO: Perform any necessary actions with the new value, like sending to the server
            dismiss();
        });

        return view;
    }
}

