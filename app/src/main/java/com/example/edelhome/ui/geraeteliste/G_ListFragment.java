package com.example.edelhome.ui.geraeteliste;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.edelhome.R;
import com.example.edelhome.ui.geraet.GeraetFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class G_ListFragment extends ListFragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private  CollectionReference gName = db.collection("Geräte");
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private ArrayList<String> geraete = new ArrayList<>();
    private ArrayList<String> ipAdress = new ArrayList();
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        getName();



    }






    private void getName(){
        if(auth.getCurrentUser()!=null) {
            Query q = gName.whereEqualTo("name", auth.getCurrentUser().getEmail());
            db.collection("Geräte").whereEqualTo("name", auth.getCurrentUser().getEmail())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());

                                    geraete.add(document.getString("geraeteName"));
                                    ipAdress.add(document.getString("ipAdress"));

                                }
                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                                Toast.makeText(getActivity(), "gescheitert", Toast.LENGTH_LONG).show();
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                    android.R.layout.simple_expandable_list_item_1, geraete);
                            setListAdapter(adapter);

                        }

                    });


        }else {
            Toast.makeText(getActivity(), "Bitte Anmelden!", Toast.LENGTH_LONG).show();
        }






    }



    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        boolean an = true;
        if(an){
            setGeraetOn(ipAdress.get(position));
            an = false;
       }else {
            setGeraetOff(ipAdress.get(position));
            an = true;
        }
    }
     private void setGeraetOn(String ip){
        OkHttpClient client = new OkHttpClient();
        String url  = "http://"+ ip +"/cm?cmnd=Power%20On";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

                Log.e(TAG, e.toString());
            }
            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    final String message = jsonObject.toString(5);
                    Log.i(TAG, message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
        }
        });
    }






   public void setGeraetOff (String ip){
        OkHttpClient client = new OkHttpClient();
        String url  = "http://"+ ip +"/cm?cmnd=Power%20Off";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

                Log.e(TAG, e.toString());
            }
            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    final String message = jsonObject.toString(5);
                    Log.i(TAG, message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

   /* Switch swt_geraet = root.findViewById(R.id.swt_geraet);

        swt_geraet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked==true){

                setGeraetOff(ip);
            }else{
                setGeraetOn(ip);
            }
        }
    }); */




}