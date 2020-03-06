package com.example.edelhome;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.edelhome.data.GeraeteManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class ExampleDialog extends AppCompatDialogFragment {
    private EditText editTextUsername;
    private EditText editTextip;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference reference = database.getReference();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        final FirebaseFirestore DB = FirebaseFirestore.getInstance();

        builder.setView(view)
                .setTitle("Geräte Hinzufügen")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = firebaseAuth.getCurrentUser().getEmail();
                        GeraeteManager geraeteManager = new GeraeteManager(editTextUsername.getText().toString(), editTextip.getText().toString(), name);

                        DB.collection("Geräte").add(geraeteManager);
                        Toast.makeText(getActivity(), "Gerät wurde in der Datenbank gespeichert", Toast.LENGTH_LONG).show();

                    }
                });

        editTextUsername = view.findViewById(R.id.edit_gName);
        editTextip = view.findViewById(R.id.edit_ip);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
              } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public EditText getEditTextUsername() {
        return editTextUsername;
    }

    public void setEditTextUsername(EditText editTextUsername) {
        this.editTextUsername = editTextUsername;
    }
}