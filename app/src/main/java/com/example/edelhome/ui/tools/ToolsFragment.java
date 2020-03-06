package com.example.edelhome.ui.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.edelhome.MainActivity;
import com.example.edelhome.R;
import com.example.edelhome.ui.geraeteliste.GeraetelisteFragment;
import com.example.edelhome.ui.home.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    private Button btnAn;
    private Button btnAb;
    private HomeFragment mainFragment;
    private FirebaseAuth Auth = FirebaseAuth.getInstance();
    private MainActivity main;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        toolsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        btnAn = root.findViewById(R.id.btn_dableiben);
        btnAb = root.findViewById(R.id.btn_abmelden);

        btnAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GeraetelisteFragment gerateFrag = new GeraetelisteFragment();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, gerateFrag );
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btnAb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Auth.signOut();
                if(Auth.getCurrentUser() == null){
                    mainFragment = new HomeFragment();
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = manager.beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment, mainFragment );
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });



        return root;
    }
}