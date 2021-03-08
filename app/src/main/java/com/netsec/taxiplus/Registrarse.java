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

public class Registrarse extends AppCompatActivity {

    private Asincrona asincrona;
    private EditText nombre,apellidos,telefono,confirmarTel,pin,confirmarPin,email,casa,oficina;
    private Button registrar;
    private boolean paso1;
    private boolean paso2;
    private boolean paso3;
    private String valnom,valapellidos,valtel,valConfirmar,valpin,valconP,valemail,valcasa,valoficna;
    private static String SERVIDOR_CONTROLADOR;
    private int check=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registrarse);

        SERVIDOR_CONTROLADOR = new Servidor().local;

        nombre = (EditText) findViewById(R.id.nombre);
        apellidos = (EditText) findViewById(R.id.apellidos);
        telefono = (EditText) findViewById(R.id.telefono);
        confirmarTel = (EditText) findViewById(R.id.confirmarTel);
        pin = (EditText) findViewById(R.id.confirmarPin);
        confirmarPin = (EditText) findViewById(R.id.email);
        email = (EditText) findViewById(R.id.email);
        casa = (EditText) findViewById(R.id.casa);
        oficina = (EditText) findViewById(R.id.oficina);
        registrar = (Button) findViewById(R.id.registrar);


        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cuenta = telefono.getText().toString().trim().length();
                if (cuenta == 10) {
                    paso1 = true;
                } else {

                    paso1 = false;
                }

                int conftel = confirmarTel.getText().toString().trim().length();
                if (conftel == 10) {

                    paso2 = true;
                } else {

                    paso2 = false;
                }
                int cpin = pin.getText().toString().trim().length();
                if (cpin == 4) {

                    paso2 = true;
                } else {

                    paso2 = false;
                }
                int confpin = confirmarPin.getText().toString().trim().length();
                if (confpin == 4) {

                    paso2 = true;
                } else {

                    paso2 = false;
                }
                valtel = telefono.getText().toString();
                valConfirmar = confirmarTel.getText().toString();

                if (valtel.equals(valConfirmar)) {

                    paso3 = true;
                } else {
                    paso3 = false;
                }
                valpin = pin.getText().toString();
                valconP = confirmarPin.getText().toString();

                if (valpin.equals(valconP)) {

                    paso3 = true;
                } else {
                    paso3 = false;
                }
                int cnombre = nombre.getText().toString().trim().length();
                int cemail = email.getText().toString().trim().length();
                int capellidos = apellidos.getText().toString().trim().length();
                int coficina = oficina.getText().toString().trim().length();
                int ccasa = casa.getText().toString().trim().length();
                valnom = nombre.getText().toString();
                valemail = email.getText().toString();
                valapellidos = apellidos.getText().toString();
                valcasa = casa.getText().toString();
                valoficna = oficina.getText().toString();

                Log.e("datotel", valtel);
                Log.e("datoConfirmar", valConfirmar);
                Log.e("datopin", valpin);
                Log.e("datoConP", valconP);
                Log.e("datonom", valnom);
                Log.e("datoemail", valemail);
                asincrona = new Registrarse.Asincrona();
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
            Log.e("res3",valtel+valConfirmar+valpin+valconP+valnom+valemail+valapellidos+valcasa+valoficna);
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
        StringRequest request = new StringRequest(Request.Method.POST,  SERVIDOR_CONTROLADOR+"registrarse.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("respuesta4:",response + "sal");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errorVolley", String.valueOf(error));
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("telefono",valtel);
                map.put("password",valpin);
                map.put("nombre",valnom);
                map.put("email",valemail);
                map.put("apellidos",valapellidos);
                map.put("casa",valcasa);
                map.put("oficina",valoficna);
                return map;
            }

        };
        requestQueue.add(request);
    }
}