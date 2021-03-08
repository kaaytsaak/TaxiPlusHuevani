package com.netsec.taxiplus;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class Login extends AppCompatActivity {

    private Asincrona asincrona;
    private EditText telefono,contrasena;
    private TextView ingresar,recuperarContra,registrarse,mensaje;
    private boolean paso1;
    private boolean paso2;
    private String valtel,valContra;
    private static String SERVIDOR_CONTROLADOR;
    private int check=0;
    private SharedPreferences datosUsuario;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        SERVIDOR_CONTROLADOR = new Servidor().servidor;
        datosUsuario = getSharedPreferences("Usuario",this.MODE_PRIVATE);
        editor=datosUsuario.edit();


        telefono=findViewById(R.id.telefono);
        contrasena =findViewById(R.id.contrasena);
        ingresar= findViewById(R.id.ingresar);
        recuperarContra =findViewById(R.id.recuperarContra);
        registrarse =findViewById(R.id.registrarse);
        mensaje =findViewById(R.id.mensaje);

        ingresar.setOnClickListener(new View.OnClickListener() {
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

                if (paso1==true){
                    if (paso1==true){
                        recuperarContra.setVisibility(View.GONE);
                        registrarse.setVisibility(View.GONE);
                        ingresar.setVisibility(View.GONE);
                        mensaje.setText("Iniciando sesión ...");
                        mensaje.setVisibility(View.VISIBLE);

                        asincrona= new Asincrona();
                        asincrona.execute();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"PIN debe ser de 4 dígitos..",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"El teléfono debe de ser de 10 dígitos.",Toast.LENGTH_LONG).show();
                }
            }
        });
        recuperarContra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intento2= new Intent( Login.this,Recuperar_Contra.class);
                //startActivity(intento2);
            }
        });
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento2= new Intent( Login.this,Registro.class);
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
        StringRequest request = new StringRequest(Request.Method.POST,  SERVIDOR_CONTROLADOR+"iniciar_sesion.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("respuesta:",response);
                        if (response.equals("no_existe")) {
                            recuperarContra.setVisibility(View.VISIBLE);
                            registrarse.setVisibility(View.VISIBLE);
                            ingresar.setVisibility(View.VISIBLE);
                            mensaje.setText("El teléfono o PIN es incorrecto.");
                        }
                        else
                        {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String strId = jsonObject.getString("id");
                                String strNombre = jsonObject.getString("nombres");
                                String strApellido = jsonObject.getString("apellidos");
                                String strTelefono = jsonObject.getString("telefono");
                                String strEmail = jsonObject.getString("email");
                                String strPassword= jsonObject.getString("password");
                                String strActivo = jsonObject.getString("activo");
                                String strCasa = jsonObject.getString("casa");
                                String strOficina = jsonObject.getString("oficina");
                                String strFavoritos = jsonObject.getString("favoritos");
                                String strFechaNacimiento = jsonObject.getString("fecha_nacimiento");
                                String strSexo = jsonObject.getString("sexo");
                                String strSesion = jsonObject.getString("id_sesion");
                                String strFechaRegistro = jsonObject.getString("fecha_registro");

                                editor.putString("id",strId);
                                editor.putString("nombres",strNombre);
                                editor.putString("apellidos",strApellido);
                                editor.putString("telefono",strTelefono);
                                editor.putString("email",strEmail);
                                editor.putString("password",strPassword);
                                editor.putString("activo",strActivo);
                                editor.putString("casa",strCasa);
                                editor.putString("oficina",strOficina);
                                editor.putString("favoritos",strFavoritos);
                                editor.putString("fecha_nacimiento",strFechaNacimiento);
                                editor.putString("sexo",strSexo);
                                editor.putString("id_sesion",strSesion);
                                editor.putString("fecha_registro",strFechaRegistro);
                                editor.apply();

                                Intent intent = new Intent(Login.this, Mapa.class);
                                startActivity(intent);

                            } catch (JSONException e) {
                                Log.e("errorRespuesta", String.valueOf(e));
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e( "error", "error: " +error);
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