package com.example.edelhome.ui.geraeteliste;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.edelhome.R;
import com.example.edelhome.ui.geraet.GeraetFragment;

public class GeraetelisteFragment extends Fragment {

    private GeraetelisteViewModel geraetelisteViewModel;
    private GeraetelisteFragment geraetelisteFragment;
    private TextView txt_ueberschrifft;
    private GeraetFragment geraetFragment;
    private Button btnOn;
    private Button btnOff;
    private String ip ;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        geraetelisteViewModel = ViewModelProviders.of(this).get(GeraetelisteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_geraeteliste, container, false);

        txt_ueberschrifft = root.findViewById(R.id.txt_ueberschrift);

        geraetelisteViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txt_ueberschrifft.setText(s);
            }
        });


        ListView geraeteListe = root.findViewById(R.id.lv_list);



        addGeräte();




        return root;
    }





    private void addGeräte(){


        geraetFragment = new GeraetFragment();
        GeraetFragment geraetFragment2 = new GeraetFragment();


        G_ListFragment geraete = new G_ListFragment();


        // Hier wird das unterer Frame mit dem neuen Getauscht
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.nav_host_fragment,geraete );


        transaction.addToBackStack(null);
        transaction.commit();

    }


}