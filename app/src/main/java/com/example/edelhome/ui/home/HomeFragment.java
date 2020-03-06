package com.example.edelhome.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.edelhome.R;
import com.example.edelhome.ui.Registrieren.RegistrierenFragment;
import com.example.edelhome.ui.anmelden.AnmeldenFragment;
import com.example.edelhome.ui.anmelden.AnmeldenViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TextView textView;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        // final TextView textView = root.findViewById(R.id.text_home);


        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //   textView.setText(s);

            }
        });


        //Hier wird die Funktion des Anmeldebutton Definiert
        final Button btn_anmelden = root.findViewById(R.id.btn_anmelden_fragmentAnmelden);
        final Button btn_registrieren = root.findViewById(R.id.btn_registrieren);
        final Button btn_anmelden_fragmentAnmelden = root.findViewById(R.id.btn_anmelden_fragmentAnmelden);

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView = root.findViewById(R.id.textView_ein);
               textView.setText(
                       "Wir sind Ihr Dienstleister im Bereich SmartHome."
                        +"EdelHome kümmert sich um die Steuerung der Smarten geräte in Ihrem Haus."
               );



            }
        });


        return root;
    }
}