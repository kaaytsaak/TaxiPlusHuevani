package com.netsec.taxiplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Recuperar_Contra extends AppCompatActivity {


    private Asincrona asincrona;
    private EditText telefono,confirmarTel;
    private Button enviar;
    private boolean paso1;
    private boolean paso2;
    private boolean paso3;
    private String valtel,valConfirmar;
    private static String SERVIDOR_CONTROLADOR;
    private int check=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_recuperar__contra);

        SERVIDOR_CONTROLADOR = new Servidor().local;

        telefono= (EditText) findViewById(R.id.telefono);
        confirmarTel =(EditText) findViewById(R.id.confirmarTel);
        enviar= (Button) findViewById(R.id.enviar);


        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cuenta=telefono.getText().toString().trim().length();
                if (cuenta == 10) {
                    paso1 = true;
                } else {

                    paso1 = false;
                }

                int cpin=confirmarTel.getText().toString().trim().length();
                if (cpin==10) {

                    paso2 = true;
                } else {

                    paso2 = false;
                }

                valtel=telefono.getText().toString();
                valConfirmar=confirmarTel.getText().toString();

                if(valtel.equals(valConfirmar)){

                    paso3=true;
                }
                else{
                    paso3=false;
                }

                Log.e("datotel",valtel );
                Log.e("datoConfirmar",valConfirmar );
                asincrona= new Recuperar_Contra.Asincrona();
                asincrona.execute();

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
            Log.e("res3",valtel+valConfirmar );
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
        StringRequest request = new StringRequest(Request.Method.POST,  SERVIDOR_CONTROLADOR+"recuperar_contra.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("respuesta2:",response + "sal");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("telefono",valtel);
                map.put("confirmarTel",valConfirmar);
                return map;
            }

        };
        requestQueue.add(request);
    }



}