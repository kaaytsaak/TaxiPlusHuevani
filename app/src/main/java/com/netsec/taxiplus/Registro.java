 package com.netsec.taxiplus;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Asincrona asincrona;
    private AsincronaTel asincrona_tel;
    private TextView fecha, registrar,mensaje,mensajeNombre,mensajeApellido,mensajetelefono,mensajeconfirmartel,telefonosRegistrado,mensajepin,mensajeEmail,mensajeteligual,mensajeConPin;
    private EditText nombre,apellidos,telefono,confirmarTel,pin,confirmarPin,email;
    private String valnom,valapellidos,valtel,valConfirmar,valpin,valconP,valemail,valfecha, nombreFinal,apellidosFinal,telefonoFinal,confirmartelFinal,conPinFinal,pinFinal,emailfinal,nombreTmp,apellidostemp,confirmarteltemp,telefonotemp,pintemp,conPintemp;
    private boolean tel10,pin4,nombreExitoso,apellidoExitoso,telefonoExitoso,confirmartelExitoso,telefonoExistente,pinExitoso,comPinExitoso,emailExitoso;
    private static String SERVIDOR_CONTROLADOR;
    private String sexo = "hombre";
    private String fechaElegida = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registro);

        SERVIDOR_CONTROLADOR = new Servidor().servidor;

        nombre = findViewById(R.id.nombre);
        apellidos = findViewById(R.id.apellidos);
        telefono = findViewById(R.id.telefono);
        confirmarTel = findViewById(R.id.confirmarTel);
        pin = findViewById(R.id.pin);
        confirmarPin = findViewById(R.id.confirmarPin);
        email =findViewById(R.id.email);
        registrar = findViewById(R.id.registrar);
        mensaje = findViewById(R.id.mensaje);
        fecha = findViewById(R.id.fecha);
        mensajeNombre=findViewById(R.id.mensajeNombre);
        mensajeApellido=findViewById(R.id.mensajeApellido);
        mensajetelefono=findViewById(R.id.mensajetelefono);
        mensajepin=findViewById(R.id.mensajepin);
        mensajeconfirmartel=findViewById(R.id.mensajeconfirmartel);
        telefonosRegistrado=findViewById(R.id.telefonosRegistrado);
        mensajeConPin=findViewById(R.id.mensajeConPin);
        mensajeteligual=findViewById(R.id.mensajeteligual);
        mensajeEmail=findViewById(R.id.mensajeEmail);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cuenta = telefono.getText().toString().trim().length();
                if (cuenta == 10) {
                    tel10 = true;
                } else {
                    tel10 = false;
                }

                int cpin = pin.getText().toString().trim().length();
                if (cpin == 4) {

                    pin4 = true;
                } else {

                    pin4 = false;
                }
                valtel = telefono.getText().toString();
                valConfirmar = confirmarTel.getText().toString();


                valpin = pin.getText().toString();
                valconP = confirmarPin.getText().toString();


                valnom = nombre.getText().toString();
                valemail = email.getText().toString();
                valapellidos = apellidos.getText().toString();
                valfecha=fecha.getText().toString();
                if (!valapellidos.trim().equals("")){
                    if (!valnom.trim().equals("")){
                        if (tel10==true){
                            if (pin4==true){
                                if (valtel.equals(valConfirmar)){
                                    if (valpin.equals(valconP)){
                                        if(!valemail.trim().equals("")){
                                            if(!valfecha.trim().equals("")){
                                                if(nombreExitoso=true){
                                                    if(apellidoExitoso=true){
                                                        if(telefonoExitoso=true){
                                                            if(confirmartelExitoso=true){
                                                                if(pinExitoso=true){
                                                                    if(emailExitoso=true){
                                                                        if(telefonoExistente==true){
                                                                            registrar.setVisibility(View.VISIBLE);
                                                                            telefonosRegistrado.setVisibility(View.VISIBLE);
                                                                            asincrona = new Asincrona();
                                                                            asincrona.execute();
                                                                        }
                                                                        else{
                                                                            mensaje.setVisibility(View.VISIBLE);
                                                                            telefonosRegistrado.setVisibility(View.GONE);
                                                                            registrar.setVisibility(View.GONE);
                                                                        }


                                                                    }
                                                                    else{
                                                                        Toast.makeText(getApplicationContext(),"Ingrese una direccion de correo valido",Toast.LENGTH_LONG).show();
                                                                    }

                                                                }
                                                                else{
                                                                    Toast.makeText(getApplicationContext(),"El pin solo puede tener numeros",Toast.LENGTH_LONG).show();
                                                                }
                                                            }
                                                            else{
                                                                Toast.makeText(getApplicationContext(),"El telefono solo puede tener numeros",Toast.LENGTH_LONG).show();
                                                            }
                                                        }
                                                        else{
                                                            Toast.makeText(getApplicationContext(),"El telefono solo puede tener numeros",Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                    else{
                                                        Toast.makeText(getApplicationContext(),"El apellido solo puede tener letras",Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                                else{
                                                    Toast.makeText(getApplicationContext(),"El nombre solo puede tener letras",Toast.LENGTH_LONG).show();
                                                }
                                            }
                                            else{
                                                Toast.makeText(getApplicationContext(),"La fecha es necesaria.",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(),"El correo electronico es necesario.",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),"Los PIN no coinciden.",Toast.LENGTH_LONG).show();
                                    }
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"Los teléfonos no coinciden.",Toast.LENGTH_LONG).show();
                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"El PIN debe ser de 4 dígitos.",Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"El teléfono debe ser de 10 dígitos .",Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"El nombre es necesario.",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"El apellido es necesario.",Toast.LENGTH_LONG).show();
                }


            }
        });

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DialogoFecha();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        nombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean tieneFoco) {

                if(!tieneFoco)
                {
                    //Toast.makeText(getApplicationContext(), "NOMBRE PERDIO FOCO", Toast.LENGTH_LONG).show();
                    nombreFinal=nombre.getText().toString().trim().toLowerCase();
                    if (!nombreFinal.equals("")&&nombreFinal!=null)
                    {
                        String regexUsuario = "[a-z]+";
                        nombreTmp=nombreFinal;
                        String verificarReg= nombreTmp.trim().replaceAll(regexUsuario,"");
                        if (verificarReg.equals(""))
                        {
                            nombreExitoso=true;
                            mensajeNombre.setVisibility(View.GONE);
                        }

                        else
                        {

                            mensajeNombre.setText("El nombre solo puede  letras");
                            mensajeNombre.setVisibility(View.VISIBLE);
                        }
                    }
                }
                else{
                    //Toast.makeText(getApplicationContext(), "NOMBRE obtuvo FOCO", Toast.LENGTH_LONG).show();
                }
            }
        });
        apellidos.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean tieneFoco) {

                if(!tieneFoco)
                {
                    //Toast.makeText(getApplicationContext(), "NOMBRE PERDIO FOCO", Toast.LENGTH_LONG).show();
                    apellidosFinal=apellidos.getText().toString().trim().toLowerCase();
                    if (!apellidosFinal.equals("")&&apellidosFinal!=null)
                    {
                        String regexUsuario = "[a-z]+";
                        apellidostemp=apellidosFinal;
                        String verificarReg= apellidostemp.trim().replaceAll(regexUsuario,"");
                        if (verificarReg.equals(""))
                        {
                            apellidoExitoso=true;
                            mensajeApellido.setVisibility(View.GONE);
                        }

                        else
                        {

                            mensajeApellido.setText("Los apellidos solo pueden tener   letras");
                            mensajeApellido.setVisibility(View.VISIBLE);
                        }
                    }
                }
                else{
                    //Toast.makeText(getApplicationContext(), "NOMBRE obtuvo FOCO", Toast.LENGTH_LONG).show();
                }
            }
        });
        telefono.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean tieneFoco) {

                if(!tieneFoco)
                {
                    //Toast.makeText(getApplicationContext(), "NOMBRE PERDIO FOCO", Toast.LENGTH_LONG).show();
                    telefonoFinal=telefono.getText().toString().trim().toLowerCase();
                    if (!telefonoFinal.equals("")&&telefonoFinal!=null)
                    {
                        String regexUsuario = "[0-9]+";
                        telefonotemp=telefonoFinal;
                        String verificarReg= telefonotemp.trim().replaceAll(regexUsuario,"");
                        if (verificarReg.equals(""))
                        {
                            telefonoExitoso=true;
                            mensajetelefono.setVisibility(View.GONE);
                        }

                        else
                        {

                            mensajetelefono.setText("El telefono solo pueden tener numeros");
                            mensajetelefono.setVisibility(View.VISIBLE);
                        }
                    }
                }
                else{
                    //Toast.makeText(getApplicationContext(), "NOMBRE obtuvo FOCO", Toast.LENGTH_LONG).show();
                }
            }
        });
        confirmarTel.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean tieneFoco) {

                if(!tieneFoco)
                {
                    //Toast.makeText(getApplicationContext(), "NOMBRE PERDIO FOCO", Toast.LENGTH_LONG).show();
                    confirmartelFinal=confirmarTel.getText().toString().trim().toLowerCase();
                    if (!confirmartelFinal.equals("")&&confirmartelFinal!=null)
                    {
                        String regexUsuario = "[0-9]+";
                        confirmarteltemp=confirmartelFinal;
                        String verificarReg= confirmarteltemp.trim().replaceAll(regexUsuario,"");
                        if (verificarReg.equals(""))
                        {
                            confirmartelExitoso=true;

                            valtel = telefono.getText().toString().trim();
                            valConfirmar = confirmarTel.getText().toString().trim();

                            Log.e("valores",valtel+"   "+valConfirmar);
                            if(valtel.equals(valConfirmar)){
                                mensajeteligual.setVisibility(View.GONE);
                                Log.e("paso","paso");
                                asincrona_tel = new AsincronaTel();
                                asincrona_tel.execute();


                            }
                            else{
                                mensajeteligual.setText("Los telefonos no coinciden");
                                mensajeteligual.setVisibility(View.VISIBLE);

                            }
                        }

                        else
                        {

                            mensajeconfirmartel.setText("El telefono solo pueden tener numeros");
                            mensajeconfirmartel.setVisibility(View.VISIBLE);
                        }
                    }
                }
                else{
                    //Toast.makeText(getApplicationContext(), "NOMBRE obtuvo FOCO", Toast.LENGTH_LONG).show();
                }
            }
        });
        pin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean tieneFoco) {

                if(!tieneFoco)
                {
                    //Toast.makeText(getApplicationContext(), "NOMBRE PERDIO FOCO", Toast.LENGTH_LONG).show();
                    pinFinal=pin.getText().toString().trim().toLowerCase();
                    if (!pinFinal.equals("")&&pinFinal!=null)
                    {
                        String regexUsuario = "[0-9]+";
                        pintemp=pinFinal;
                        String verificarReg= pintemp.trim().replaceAll(regexUsuario,"");
                        if (verificarReg.equals(""))
                        {
                            pinExitoso=true;
                            mensajepin.setVisibility(View.GONE);
                        }

                        else
                        {

                            mensajepin.setText("El telefono solo pueden tener numeros");
                            mensajepin.setVisibility(View.VISIBLE);
                        }
                    }
                }
                else{
                    //Toast.makeText(getApplicationContext(), "NOMBRE obtuvo FOCO", Toast.LENGTH_LONG).show();
                }
            }
        });
        confirmarPin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean tieneFoco) {

                if(!tieneFoco)
                {
                    //Toast.makeText(getApplicationContext(), "NOMBRE PERDIO FOCO", Toast.LENGTH_LONG).show();
                    conPinFinal=confirmarPin.getText().toString().trim().toLowerCase();
                    if (!conPinFinal.equals("")&&conPinFinal!=null)
                    {
                        String regexUsuario = "[0-9]+";
                        conPintemp=conPinFinal;
                        String verificarReg= conPintemp.trim().replaceAll(regexUsuario,"");
                        if (verificarReg.equals(""))
                        {
                            comPinExitoso=true;

                            valpin = pin.getText().toString().trim();
                            valconP = confirmarPin.getText().toString().trim();

                            Log.e("valores",valpin+"   "+valconP);
                            if(valpin.equals(valconP)){
                                mensajeConPin.setVisibility(View.GONE);
                                Log.e("paso","paso");
                            }
                            else{
                                mensajeConPin.setText("Los PIN no coinciden");
                                mensajeConPin.setVisibility(View.VISIBLE);
                            }
                        }

                        else
                        {

                            mensajeConPin.setText("El PIN solo pueden tener numeros");
                            mensajeConPin.setVisibility(View.VISIBLE);
                        }
                    }
                }
                else{
                    //Toast.makeText(getApplicationContext(), "NOMBRE obtuvo FOCO", Toast.LENGTH_LONG).show();
                }
            }
        });
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean tieneFoco) {

                if(!tieneFoco)
                {
                    emailfinal=email.getText().toString().trim().toLowerCase();
                    if (!emailfinal.equals("")&&emailfinal!=null)
                    {
                        // String regex = "^(.+)@(.+)$";

                        String regexUsuario = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
                        Pattern pattern = Pattern.compile(regexUsuario);
                        Matcher matcher = pattern.matcher(emailfinal);
                        if(matcher.matches()==true){

                            emailExitoso=true;
                            mensajeEmail.setVisibility(View.GONE);
                        }
                    }
                }
                else{
                    mensajeEmail.setText("Ingrese una direccion de correo valido");
                    mensajeEmail.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_hombre:
                if (checked)
                    sexo = "hombre";
                break;
            case R.id.radio_mujer:
                if (checked)
                    sexo="mujer";
                break;
        }
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DATE,dayOfMonth);
        fechaElegida = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        fecha.setText(fechaElegida);
    }

    private class Asincrona extends AsyncTask<Void, Integer,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            Log.e("res3",valtel+valConfirmar+valpin+valconP+valnom+valemail+valapellidos+valemail+valfecha);
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
        StringRequest request = new StringRequest(Request.Method.POST,  SERVIDOR_CONTROLADOR+"registro.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("respuesta4:",response + "sal");
                        if(response.equals("success")){
                            Intent intent = new Intent(Registro.this,RegistroExitoso.class);
                            startActivity(intent);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("respuesta4Error:",error + "error");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();

                map.put("nombres",valnom);
                map.put("apellidos",valapellidos);
                map.put("telefono",valtel);
                map.put("email",valemail);
                map.put("password",valpin);
                map.put("fecha",fechaElegida);
                map.put("sexo",sexo);

                return map;
            }
        };
        requestQueue.add(request);
    }

    private class AsincronaTel extends AsyncTask<Void, Integer,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            Log.e("res6",valtel);
            buscar_tel();
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
    public void buscar_tel()
    {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST,  SERVIDOR_CONTROLADOR+"verificar_telefono.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("respuesta5:",response + "EL TELEFONO YA EXISTE");
                        if(response.equals("existe")){
                            telefonoExistente=true;
                            telefonosRegistrado.setVisibility(View.VISIBLE);
                            telefonosRegistrado.setText("El telefono ya existe");


                        }
                        else{

                            telefonoExistente=false;
                            telefonosRegistrado.setVisibility(View.GONE);

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("respuesta5Error:",error + "error");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();


                map.put("telefono",valtel);
                return map;
            }
        };
        requestQueue.add(request);
    }
}