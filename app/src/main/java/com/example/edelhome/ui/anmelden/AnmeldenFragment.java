package com.example.edelhome.ui.anmelden;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.edelhome.MainActivity;
import com.example.edelhome.R;
import com.example.edelhome.ui.home.HomeFragment;
import com.example.edelhome.ui.send.SendViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class AnmeldenFragment extends Fragment {




    private AnmeldenViewModel anmeldenViewModel;
    private FirebaseAuth auth;
    private EditText password;
    private EditText email;
    private HomeFragment homeFragment;
    private Button btn_sigin;
    private TextView textName;
    private TextView textEmail;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        anmeldenViewModel =
                ViewModelProviders.of(this).get(AnmeldenViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_anmelden, container, false);
      //  final TextView textView = root.findViewById(R.id.text_anmelden);

        textEmail = root.findViewById(R.id.textView_name);
        textName = root.findViewById(R.id.textView_email);


        auth = FirebaseAuth.getInstance();
        password = root.findViewById(R.id.edt_password_anmelden);
        email = root.findViewById(R.id.edt_mail_anmelden);
        btn_sigin = root.findViewById(R.id.btn_anmelden_fragmentAnmelden);

        anmeldenViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });




        btn_sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    auth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(getActivity(), "Successfully Logged In", Toast.LENGTH_LONG).show();
                                String name = auth.getCurrentUser().getEmail();
//                                tex.setText(name);
                                // hier wird die UI zu  home getauscht
                                homeFragment = new HomeFragment();
                                FragmentManager manager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                                fragmentTransaction.replace(R.id.nav_host_fragment, homeFragment );
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();

                                FirebaseUser currentUser = auth.getCurrentUser();
                                String displayName = currentUser.getEmail();
                                Toast.makeText(getActivity(), "Sie sind angemeldet als " + displayName,
                                        Toast.LENGTH_LONG).show();

                            }
                            else{
                                Toast.makeText(getActivity(), "Nicht Erfolgreich", Toast.LENGTH_LONG).show();
                            }

                        }
                    });

                }



            });








        return root;
    }







}
