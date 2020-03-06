package com.example.edelhome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SecondActivity extends AppCompatActivity {
    EditText vName, nName, password, email, tel;
    Button regis, cancel;
    public static final String URLSTRING = "http://localhost:63343/register.php/?_ijt=joqbhod8s4c17u8h9e5ctg3hef";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registrieren);

        vName = findViewById(R.id.edt_vname);
        nName = findViewById(R.id.edt_nachname);
        password=findViewById(R.id.edt_password_anmelden);
        email = findViewById(R.id.edt_email);
        tel = findViewById(R.id.edta_telnum);

      //  regis = findViewById(R.id.regiSec);
        cancel = findViewById(R.id.btn_registrieren);

        if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                email.setError("Enter E-Mail");
                email.requestFocus();
        }
        if(TextUtils.isEmpty(vName.getText().toString())){
            vName.setError("Enter E-Mail");
            vName.requestFocus();
        }

        if(TextUtils.isEmpty(nName.getText().toString())){
            nName.setError("Enter E-Mail");
            nName.requestFocus();
        }

        if(TextUtils.isEmpty(password.getText().toString())){
            password.setError("Enter E-Mail");
            password.requestFocus();
        }


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reg();
            }
        });
    }

    private void reg(){

        final String name = this.nName.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String password = this.password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLSTRING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String succes = jsonObject.getString("success");

                            if (succes.equals("1")) {
                                Toast.makeText(SecondActivity.this, "Erfolgreich gespeichert", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SecondActivity.this, "Nicht gespeichert " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SecondActivity.this, "Register Error " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("vName", vName.getText().toString());
                params.put("nName", nName.getText().toString());
                params.put("email", email);
                params.put("password", password);
                params.put("tel", tel.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}