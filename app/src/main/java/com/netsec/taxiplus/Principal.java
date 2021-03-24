package com.netsec.taxiplus;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Principal extends AppCompatActivity {
    private Asincrona asincrona;
    private EditText telefono,contrasena;
    private Button iniciarSesion,recuperarContra,registrarse;
    private boolean paso1;
    private boolean paso2;
    private String valtel,valContra;
    private static String SERVIDOR_CONTROLADOR;
    private int check=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_principal);

        SERVIDOR_CONTROLADOR = new Servidor().local;

        telefono= (EditText) findViewById(R.id.telefono);
        contrasena =(EditText) findViewById(R.id.contrasena);
        iniciarSesion= (Button) findViewById(R.id.iniciarSesion);
        recuperarContra =(Button)findViewById(R.id.recuperarContra);
        registrarse =(Button)findViewById(R.id.registrarse);

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cuenta=telefono.getText().toString().trim().length();
                if (cuenta == 10) {
                    paso1 = true;
                } else {

                    paso1 = false;
                }

                int cpin=contrasena.getText().toString().trim().length();
                if (cpin==4) {

                    paso2 = true;
                } else {

                    paso2 = false;
                }
                valtel=telefono.getText().toString();
                valContra=contrasena.getText().toString();
                Log.e("datotel",valtel );
                Log.e("datocontra",valContra );
                asincrona= new Asincrona();
                asincrona.execute();

            }
        });
        recuperarContra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento2= new Intent( Principal.this,Recuperar_Contra.class);
                startActivity(intento2);
            }
        });
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento2= new Intent( Principal.this,Registro.class);
                startActivity(intento2);
            }
        });




    }
    private class Asincrona extends AsyncTask<Void, Integer,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            check=1;
            Log.e("res2",valtel+valContra );
            hacerPeticion();
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
        @Override

        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
    public void hacerPeticion()
    {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST,  SERVIDOR_CONTROLADOR+"login.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("respuesta:",response + "uijnui");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e( "error", "error: " );
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("telefono",valtel);
                map.put("password",valContra);
                return map;
            }

        };
        requestQueue.add(request);
    }



}