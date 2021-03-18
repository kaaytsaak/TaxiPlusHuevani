package com.netsec.taxiplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Perfil extends AppCompatActivity {

    private static String SERVIDOR_CONTROLADOR;
    private Asincrona asincrona;
    private SharedPreferences datosUsuario;
    private SharedPreferences.Editor editor_usuario;
    private boolean telExitoso,pinExitoso,telefonoExitoso,conPasswordExitoso,passExitoso,passwordEquals,emailExitoso;
    private LinearLayout cajaImagenTexto,cajaImagenEditor,cajaTelefonoTexto,cajaTelefonoEditar,cajaContraseñaTexto,cajaContraseñaEditar,cajaConfirmarTexto,confirmar_contraseña,cajacorreoTexto,cajaCorreoEditar,cajacasaTexto,cajacasaEditar,cajaOficinaTexto,
            cajaOficinaEditar,cajaFavoritosTexto,cajaFavoritosEditar,cajaSexoTexto,cajaSexoEditar;
    private ImageView fotoEdit,fotoNew,telefonoEdit,telefonoNew,controseñaEdit,contraseñaNew,confirmarContraseñaNew,confirmarContraseñaEdit,emailEdit,emailNew,casaedit,casaNew,oficinaEdit,oficinaNew,favoritosEdit,favoritosNew,sexoEdit,sexoNew,cerrar_sesion,actualizar;
    private TextView nombre,telefono,mensajetelefono,password,confpassword,mensajepassword,mensajepass,mensajeCorreo,id,email,casa,oficina,favoritos,fecha_nacimiento,sexo,fecha_registro,confirmar_si,confirmar_no,cerrar_si,cerrar_no;
    private EditText telefonoEditado,passwordEditado,emailEditado,casaEditar,oficinaEditar,favoritosEditor,sexoEditor,confirmar_contra;
    private String strId,strNombre,strApellido,strTelefono,strpassword,strConfpassword,strEmail,strCasa,strOficina,strFavoritos,strFechaNacimiento,strSexo,strFechaRegistro,strId_sesion,
            nuevoTel,telefonoEditadoFinal,telefonoEditatemp,nuevaContra,passwordFinal,passwordtemp,confirmar_contra_new,confirmar_contraFinal,confirmar_contratemp,nuevoEmail,emailfinal,
                   nuevaCasa,nuevaOficina,nuevoFavoritos,nuevoSexo,valTel,valContra,valconPass,valEmail,valCasa,valOficina,valFavoritos,valSexo;
    private ConstraintLayout confirmar_actualizar,confirmar_cerrar;
    private int check=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_perfil);

        SERVIDOR_CONTROLADOR = new Servidor().servidor;


        nombre = (TextView) findViewById(R.id.nombre);
        telefonoEditado=(EditText)findViewById(R.id.telefonoEditado);
        telefono = (TextView) findViewById(R.id.telefono);
        mensajetelefono = (TextView) findViewById(R.id.mensajetelefono);
        id= (TextView) findViewById(R.id.id);
        password=(TextView)findViewById(R.id.password);
        mensajepass=(TextView)findViewById(R.id.mensajepass);
        passwordEditado =(EditText) findViewById(R.id.passwordEditado);
        confpassword=(TextView)findViewById(R.id.confpassword);
        mensajepassword=(TextView)findViewById(R.id.mensajepassword);
        confirmar_contra =(EditText) findViewById(R.id.confirmar_contra);
        email = (TextView) findViewById(R.id.email);
        mensajeCorreo = (TextView) findViewById(R.id.mensajeCorreo);
        emailEditado=(EditText)findViewById(R.id.emailEditado);
        casa = (TextView) findViewById(R.id.casa);
        casaEditar=(EditText)findViewById(R.id.casaEditar);
        oficina = (TextView) findViewById(R.id.oficina);
        oficinaEditar=(EditText)findViewById(R.id.oficinaEditar);
        favoritos = (TextView) findViewById(R.id.favoritos);
        favoritosEditor=(EditText)findViewById(R.id.favoritosEditor);
        fecha_nacimiento = (TextView) findViewById(R.id.fecha_nacimiento);
        sexo = (TextView) findViewById(R.id.sexo);
        sexoEditor=(EditText)findViewById(R.id.sexoEditor);
        fecha_registro = (TextView) findViewById(R.id.fecha_registro);
        fotoEdit = (ImageView) findViewById(R.id.fotoEdit);
        fotoNew = (ImageView) findViewById(R.id.fotoNew);
        telefonoEdit = (ImageView) findViewById(R.id.telefonoEdit);
        telefonoNew = (ImageView) findViewById(R.id.telefonoNew);
        controseñaEdit= (ImageView) findViewById(R.id.controseñaEdit);
        contraseñaNew= (ImageView) findViewById(R.id.contraseñaNew);
        confirmarContraseñaEdit= (ImageView) findViewById(R.id.confirmarContraseñaEdit);
        confirmarContraseñaNew= (ImageView) findViewById(R.id.confirmarContraseñaNew);
        emailEdit = (ImageView) findViewById(R.id.emailEdit);
        emailNew = (ImageView) findViewById(R.id.emailNew);
        casaedit = (ImageView) findViewById(R.id.casaedit);
        casaNew = (ImageView) findViewById(R.id.casaNew);
        oficinaEdit = (ImageView) findViewById(R.id.oficinaEdit);
        oficinaNew = (ImageView) findViewById(R.id.oficinaNew);
        favoritosEdit = (ImageView) findViewById(R.id.favoritosEdit);
        favoritosNew = (ImageView) findViewById(R.id.favoritosNew);
        sexoEdit = (ImageView) findViewById(R.id.sexoEdit);
        sexoNew = (ImageView) findViewById(R.id.sexoNew);
        cerrar_sesion = (ImageView) findViewById(R.id.cerrar_sesion);
        actualizar=(ImageView)findViewById(R.id.actualizar);
        cajaImagenTexto= (LinearLayout) findViewById(R.id.cajaImagenTexto);
        cajaImagenEditor= (LinearLayout) findViewById(R.id.cajaImagenEditor);
        cajaTelefonoTexto= (LinearLayout) findViewById(R.id.cajaTelefonoTexto);
        cajaTelefonoEditar= (LinearLayout) findViewById(R.id.cajaTelefonoEditar);
        cajaContraseñaTexto= (LinearLayout) findViewById(R.id.cajaContraseñaTexto);
        cajaContraseñaEditar= (LinearLayout) findViewById(R.id.cajaContraseñaEditar);
        confirmar_contraseña= (LinearLayout) findViewById(R.id.confirmar_contraseña);
        cajaConfirmarTexto= (LinearLayout) findViewById(R.id.cajaConfirmarTexto);
        cajacorreoTexto= (LinearLayout) findViewById(R.id.cajacorreoTexto);
        cajaCorreoEditar= (LinearLayout) findViewById(R.id.cajaCorreoEditar);
        cajacasaTexto= (LinearLayout) findViewById(R.id.cajacasaTexto);
        cajacasaEditar= (LinearLayout) findViewById(R.id.cajacasaEditar);
        cajaOficinaTexto= (LinearLayout) findViewById(R.id.cajaOficinaTexto);
        cajaOficinaEditar= (LinearLayout) findViewById(R.id.cajaOficinaEditar);
        cajaFavoritosTexto= (LinearLayout) findViewById(R.id.cajaFavoritosTexto);
        cajaFavoritosEditar= (LinearLayout) findViewById(R.id.cajaFavoritosEditar);
        cajaSexoTexto= (LinearLayout) findViewById(R.id.cajaSexoTexto);
        cajaSexoEditar= (LinearLayout) findViewById(R.id.cajaSexoEditar);
        confirmar_actualizar= (ConstraintLayout) findViewById(R.id.confirmar_actualizar);
        confirmar_si = (TextView) findViewById(R.id.confirmar_si);
        confirmar_no = (TextView) findViewById(R.id.confirmar_no);
        confirmar_cerrar= (ConstraintLayout) findViewById(R.id.confirmar_cerrar);
        cerrar_si = (TextView) findViewById(R.id.cerrar_si);
        cerrar_no = (TextView) findViewById(R.id.cerrar_no);

        datosUsuario = getSharedPreferences("Usuario",this.MODE_PRIVATE);
        strId = datosUsuario.getString("id","1 ");
        strNombre = datosUsuario.getString("nombres"," paque");
        strApellido = datosUsuario.getString("apellidos","Perez");
        strTelefono= datosUsuario.getString("telefono"," 5614753220");
        strEmail = datosUsuario.getString("email"," paquelechat@chatito.com");
        strCasa = datosUsuario.getString("casa"," manzanas");
        strOficina= datosUsuario.getString("oficina"," peras");
        strFavoritos = datosUsuario.getString("favoritos","aurrera ");
        strFechaNacimiento = datosUsuario.getString("fecha_nacimiento","15/05/1993 ");
        strSexo = datosUsuario.getString("sexo"," indefinido");
        strFechaRegistro = datosUsuario.getString("fecha_registro","24/01/2021 ");
        strId_sesion = datosUsuario.getString("id_sesion","123a4bb80df15bec048eb8a680157ac473f6121a");
        strpassword = datosUsuario.getString("password","1509");
        strConfpassword = datosUsuario.getString("password","1509");

        nombre.setText(strNombre+" "+strApellido);
        telefono.setText(strTelefono);
        email.setText(strEmail);
        casa.setText(strCasa);
        oficina.setText(strOficina);
        favoritos.setText(strFavoritos);
        fecha_nacimiento.setText(strFechaNacimiento);
        sexo.setText(strSexo);
        fecha_registro.setText(strFechaRegistro);
        id.setText(strId);
        password.setText(strpassword);
        confpassword.setText(strConfpassword);

        fotoEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento2= new Intent( Perfil.this,Recuperar_Contra.class);
                startActivity(intento2);

            }
        });
        fotoNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento2= new Intent( Perfil.this,Recuperar_Contra.class);
                startActivity(intento2);

            }
        });
        telefonoEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cajaTelefonoTexto.setVisibility(View.GONE);
                cajaTelefonoEditar.setVisibility(View.VISIBLE);
            }
        });
        telefonoNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    //Toast.makeText(getApplicationContext(), "NOMBRE PERDIO FOCO", Toast.LENGTH_LONG).show();
                    telefonoEditadoFinal=telefonoEditado.getText().toString().trim().toLowerCase();
                    if (!telefonoEditadoFinal.equals("")&&telefonoEditadoFinal!=null)
                    {
                        String regexUsuario = "[0-9]+";
                        telefonoEditatemp=telefonoEditadoFinal;
                        String verificarReg= telefonoEditatemp.trim().replaceAll(regexUsuario,"");
                        if (verificarReg.equals(""))
                        {
                            telefonoExitoso=true;
                            mensajetelefono.setVisibility(View.GONE);
                            cajaTelefonoEditar.setVisibility(View.GONE);
                            cajaTelefonoTexto.setVisibility(View.VISIBLE);
                            nuevoTel=telefonoEditado.getText().toString();
                            telefono.setText(nuevoTel);
                        }

                        else
                        {

                            mensajetelefono.setText("El telefono solo pueden tener numeros");
                            mensajetelefono.setVisibility(View.VISIBLE);
                        }
                    }



            }
        });
        controseñaEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cajaContraseñaTexto.setVisibility(View.GONE);
                cajaContraseñaEditar.setVisibility(View.VISIBLE);

            }
        });
        contraseñaNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cajaContraseñaTexto.setVisibility(View.VISIBLE);
                cajaContraseñaEditar.setVisibility(View.GONE);
                nuevaContra=passwordEditado.getText().toString();
                password.setText(nuevaContra);
            }
        });

        confirmarContraseñaNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cajaConfirmarTexto.setVisibility(View.GONE);
                confirmar_contraseña.setVisibility(View.VISIBLE);
            }
        });

        confirmarContraseñaEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmar_contraFinal=confirmar_contra.getText().toString().trim().toLowerCase();
                if (!confirmar_contraFinal.equals("")&&confirmar_contraFinal!=null)
                {
                    String regexUsuario = "[0-9]+";
                    confirmar_contratemp=confirmar_contraFinal;
                    String verificarReg= confirmar_contratemp.trim().replaceAll(regexUsuario,"");
                    if (verificarReg.equals(""))
                    {


                        valContra = passwordEditado.getText().toString().trim();
                        valconPass = confirmar_contra.getText().toString().trim();

                        Log.e("valores",valContra+"   "+valconPass);
                        if(valContra.equals(valconPass)){
                            passwordEquals=true;
                            cajaConfirmarTexto.setVisibility(View.VISIBLE);
                            confirmar_contraseña.setVisibility(View.GONE);
                            confirmar_contra_new=confirmar_contra.getText().toString();
                            confpassword.setText(confirmar_contra_new);

                            mensajepassword.setVisibility(View.GONE);
                            Log.e("paso","paso");
                        }
                        else{
                            mensajepassword.setText("Los PIN no coinciden");
                            mensajepassword.setVisibility(View.VISIBLE);
                        }
                    }

                    else
                    {
                        conPasswordExitoso=false;
                        mensajepassword.setText("El PIN solo pueden tener numeros");
                        mensajepassword.setVisibility(View.VISIBLE);
                    }
                }




            }
        });

        emailEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cajacorreoTexto.setVisibility(View.GONE);
                cajaCorreoEditar.setVisibility(View.VISIBLE);
            }
        });
        emailNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cajaCorreoEditar.setVisibility(View.GONE);
                cajacorreoTexto.setVisibility(View.VISIBLE);
                nuevoEmail=emailEditado.getText().toString();
                email.setText(nuevoEmail);
            }
        });
        casaedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cajacasaTexto.setVisibility(View.GONE);
                cajacasaEditar.setVisibility(View.VISIBLE);
            }
        });
        casaNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cajacasaEditar.setVisibility(View.GONE);
                cajacasaTexto.setVisibility(View.VISIBLE);
                nuevaCasa=casaEditar.getText().toString();
                casa.setText(nuevaCasa);
            }
        });
        oficinaEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cajaOficinaTexto.setVisibility(View.GONE);
                cajaOficinaEditar.setVisibility(View.VISIBLE);
            }
        });
        oficinaNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cajaOficinaEditar.setVisibility(View.GONE);
                cajaOficinaTexto.setVisibility(View.VISIBLE);
                nuevaOficina=oficinaEditar.getText().toString();
                oficina.setText(nuevaOficina);
            }
        });
        favoritosEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cajaFavoritosTexto.setVisibility(View.GONE);
                cajaFavoritosEditar.setVisibility(View.VISIBLE);
            }
        });
        favoritosNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cajaFavoritosEditar.setVisibility(View.GONE);
                cajaFavoritosTexto.setVisibility(View.VISIBLE);
                nuevoFavoritos=favoritosEditor.getText().toString();
                favoritos.setText(nuevoFavoritos);
            }
        });
        sexoEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cajaSexoTexto.setVisibility(View.GONE);
                cajaSexoEditar.setVisibility(View.VISIBLE);
            }
        });
        sexoNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cajaSexoEditar.setVisibility(View.GONE);
                cajaSexoTexto.setVisibility(View.VISIBLE);
                nuevoSexo=sexoEditor.getText().toString();
                sexo.setText(nuevoSexo);
            }
        });
        cerrar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmar_cerrar.setVisibility(View.VISIBLE);

                /*Intent intento2= new Intent( Perfil.this,Principal.class);
                startActivity(intento2);*/
            }
        });
        cerrar_si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intento2= new Intent( Perfil.this,Principal.class);
                startActivity(intento2);
            }
        });
        cerrar_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmar_cerrar.setVisibility(View.GONE);


            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                confirmar_actualizar.setVisibility(View.VISIBLE);
            }

        });
        confirmar_si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cuenta = telefonoEditado.getText().toString().trim().length();
                if (cuenta == 10) {
                    telExitoso = true;
                } else {
                    telExitoso = false;
                }

                int cpin = passwordEditado.getText().toString().trim().length();
                if (cpin == 4) {

                    pinExitoso = true;
                } else {

                    pinExitoso = false;
                }

                valTel = telefonoEditado.getText().toString();
                valEmail = email.getText().toString();
                valCasa=casa.getText().toString();
                valOficina=oficina.getText().toString();
                valFavoritos=favoritos.getText().toString();
                valSexo=sexo.getText().toString();
                valContra=passwordEditado.getText().toString();
                Log.e("datotel", valTel);
                Log.e("datoConfirmar", valEmail);
                Log.e("datopin", valCasa);
                Log.e("datoConP", valOficina);
                Log.e("datonom", valFavoritos);
                Log.e("datoemail", valSexo);

                asincrona= new Asincrona();
                asincrona.execute();
            }
        });
        confirmar_no.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                confirmar_actualizar.setVisibility(View.GONE);
            }

        });

        telefonoEditado.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean tieneFoco) {

               if(!tieneFoco)
                {
                    //Toast.makeText(getApplicationContext(), "NOMBRE PERDIO FOCO", Toast.LENGTH_LONG).show();
                    telefonoEditadoFinal=telefono.getText().toString().trim().toLowerCase();
                    if (!telefonoEditadoFinal.equals("")&&telefonoEditadoFinal!=null)
                    {
                        String regexUsuario = "[0-9]+";
                        telefonoEditatemp=telefonoEditadoFinal;
                        String verificarReg= telefonoEditatemp.trim().replaceAll(regexUsuario,"");
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

            }

        });
        passwordEditado.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean tieneFoco) {

                if(!tieneFoco)
                {
                    //Toast.makeText(getApplicationContext(), "NOMBRE PERDIO FOCO", Toast.LENGTH_LONG).show();
                    passwordFinal=passwordEditado.getText().toString().trim().toLowerCase();
                    if (!passwordFinal.equals("")&&passwordFinal!=null)
                    {
                        String regexUsuario = "[0-9]+";
                        passwordtemp=passwordFinal;
                        String verificarReg= passwordtemp.trim().replaceAll(regexUsuario,"");
                        if (verificarReg.equals(""))
                        {
                            passExitoso=true;
                            mensajepass.setVisibility(View.GONE);
                        }

                        else
                        {

                            mensajepass.setText("El telefono solo pueden tener numeros");
                            mensajepass.setVisibility(View.VISIBLE);
                        }
                    }
                }
                else{
                    //Toast.makeText(getApplicationContext(), "NOMBRE obtuvo FOCO", Toast.LENGTH_LONG).show();
                }
            }
        });
        confirmar_contra.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean tieneFoco) {

                if(!tieneFoco)
                {
                    //Toast.makeText(getApplicationContext(), "NOMBRE PERDIO FOCO", Toast.LENGTH_LONG).show();
                    confirmar_contraFinal=confirmar_contra.getText().toString().trim().toLowerCase();
                    if (!confirmar_contraFinal.equals("")&&confirmar_contraFinal!=null)
                    {
                        String regexUsuario = "[0-9]+";
                        confirmar_contratemp=confirmar_contraFinal;
                        String verificarReg= confirmar_contratemp.trim().replaceAll(regexUsuario,"");
                        if (verificarReg.equals(""))
                        {
                            conPasswordExitoso=true;

                            valContra = passwordEditado.getText().toString().trim();
                            valconPass = confirmar_contra.getText().toString().trim();

                            Log.e("valores",valContra+"   "+valconPass);
                            if(valContra.equals(valconPass)){
                                mensajepassword.setVisibility(View.GONE);
                                Log.e("paso","paso");
                            }
                            else{
                                mensajepassword.setText("Los PIN no coinciden");
                                mensajepassword.setVisibility(View.VISIBLE);
                            }
                        }

                        else
                        {

                            mensajepassword.setText("El PIN solo pueden tener numeros");
                            mensajepassword.setVisibility(View.VISIBLE);
                        }
                    }
                }

            }
        });
        emailEditado.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean tieneFoco) {

                if(!tieneFoco)
                {
                    emailfinal=emailEditado.getText().toString().trim().toLowerCase();
                    if (!emailfinal.equals("")&&emailfinal!=null)
                    {
                        // String regex = "^(.+)@(.+)$";

                        String regexUsuario = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
                        Pattern pattern = Pattern.compile(regexUsuario);
                        Matcher matcher = pattern.matcher(emailfinal);
                        if(matcher.matches()==true){

                            emailExitoso=true;
                            mensajeCorreo.setVisibility(View.GONE);
                        }
                    }
                }
                else{
                    mensajeCorreo.setText("Ingrese una direccion de correo valido");
                    mensajeCorreo.setVisibility(View.VISIBLE);
                }
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
            Log.e("res3",valTel+valEmail+valCasa+valOficina+valFavoritos+valSexo+valContra);
            actualizar_usuario();
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
    public void actualizar_usuario()
    {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST,  SERVIDOR_CONTROLADOR+"actualizar_usuario.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("respuesta4:",response + "sal");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", String.valueOf(error));
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("telefono",valTel);
                map.put("email",valEmail);
                map.put("casa",valCasa);
                map.put("oficina",valOficina);
                map.put("favoritos",valFavoritos);
                map.put("sexo",valSexo);
                map.put("password",valContra);
                map.put("id",strId);
                map.put("id_sesion",strId_sesion);
                return map;
            }

        };
        requestQueue.add(request);
    }
}
