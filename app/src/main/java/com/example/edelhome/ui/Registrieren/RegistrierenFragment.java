package com.example.edelhome.ui.Registrieren;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.edelhome.R;
import com.example.edelhome.data.Benutzer;
import com.example.edelhome.ui.anmelden.AnmeldenFragment;
import com.example.edelhome.ui.anmelden.AnmeldenViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class RegistrierenFragment extends Fragment {

    private AnmeldenFragment anmeldenFragment;
    private AnmeldenViewModel anmeldenViewModel;
    private RegistrierenViewModel registrierenViewModel;
    private RegistrierenFragment registrierenFragment;
    private Button btn_abbrechen;

    private EditText vName;
    private EditText nachname;
    private EditText tel;
    private EditText email;
    private EditText password;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = firebaseDatabase.getReference();
    private FirebaseAuth mAuth;



    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        registrierenViewModel =
                ViewModelProviders.of(this).get(RegistrierenViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_registrieren, container, false);
        //final TextView textView = root.findViewById(R.id.text_gallery);
        registrierenViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
              //  textView.setText(s);
            }
        });

        btn_abbrechen = root.findViewById(R.id.btn_registrieren);

        registrierenViewModel = new RegistrierenViewModel();
        anmeldenViewModel = new AnmeldenViewModel();

        vName = root.findViewById(R.id.edt_vname);
        nachname = root.findViewById(R.id.edt_nachname);
        tel = root.findViewById(R.id.edta_telnum);
        email = root.findViewById(R.id.edt_email);
        password = root.findViewById(R.id.edt_password_anmelden);


        // Ist für die benutzer authentifikation
        mAuth = FirebaseAuth.getInstance();




        registrierenViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                btn_abbrechen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final FirebaseFirestore db = FirebaseFirestore.getInstance();
                       //  db.collection("Benutzerkonten").add(user);
                       // db.collection("Benutzerkonten").document("ZxvPJmhmJYUBF38p2PL3").set("Daniel,Zimmermann,email@mail.de,12345");
                        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    final FirebaseUser user = mAuth.getCurrentUser();
                                    Benutzer benutzer =   new Benutzer(vName.getText().toString(),nachname.getText().toString(),
                                            tel.getText().toString(),password.getText().toString(), user);
                                    db.collection("Benutzer").add(benutzer);

                                    // hier wird die UI zu anmelden getauscht
                                    anmeldenFragment = new AnmeldenFragment();
                                    FragmentManager manager = getFragmentManager();
                                    FragmentTransaction fragmentTransaction = manager.beginTransaction();
                                    fragmentTransaction.replace(R.id.nav_host_fragment, anmeldenFragment );
                                    fragmentTransaction.addToBackStack(null);
                                    fragmentTransaction.commit();



                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(getActivity(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });



                    }
                });
            }
        });



        return root;
    }




    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // createAccount();
}


}




