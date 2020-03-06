package com.example.edelhome.ui.geraet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.edelhome.R;
import com.example.edelhome.ui.geraeteliste.GeraetelisteViewModel;

public class GeraetFragment extends Fragment {

    private GeraetViewModel geraetViewModel;
    private GeraetFragment geraetFragment;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        geraetViewModel = ViewModelProviders.of(this).get(GeraetViewModel.class);
        View root = inflater.inflate(R.layout.fragment_geraet, container, false);

        /*/ Hier wird das Geräte Frame zu der Geräteliste hinzugefügt
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.nav_host_fragment, geraetFragment);
        transaction.addToBackStack(null);
        transaction.commit();
         */
        String IPAdresse ="";




        return root;
    }



}
