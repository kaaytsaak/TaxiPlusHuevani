package com.netsec.taxiplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Taxi_Plus_Lista extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Taxi_Plus_Recycler> listarank;
    private Context context;

    private SegundoPlanoHistorial segundoPlanoHistorial;


    private String SERVIDOR_CONTROLADOR=new Servidor().servidor;
    private String id_usuario, id_sesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_taxi__plus__lista);

        context = this;
        recyclerView =(RecyclerView) findViewById(R.id.TaxiplusRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        listarank = new ArrayList<>();
        id_usuario="1";
        id_sesion="123a4bb80df15bec048eb8a680157ac473f6121a";
        segundoPlanoHistorial=new SegundoPlanoHistorial();
        segundoPlanoHistorial.execute();
    }

    private class SegundoPlanoHistorial extends AsyncTask<Void, Integer,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e("primero" , "onPreExecute: " );
        }
        @Override
        protected Void doInBackground(Void... voids) {
            Log.e("segundo" , "onPreExecute: " );
            pedir_historial_viajes();
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.e("tercero" , "onPreExecute: " );
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.e("cuarto" , "onPreExecute: " );
        }
        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.e("quinto" , "onPreExecute: " );
        }
    }
    public void pedir_historial_viajes()
    {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST,  SERVIDOR_CONTROLADOR+"pedir_historial_viajes_usuario.php",
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {

                        String limpio=response.replace("\\","");
                        Log.e("jsonObject:",""+limpio);

                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(limpio);

                            for (int i =0;i<jsonArray.length();i++){
                                Log.e("jsonarrlimpio:", String.valueOf(jsonArray.get(i)));
                                JSONObject datos = jsonArray.getJSONObject(i);
                                Log.e("jsondatlimpio", String.valueOf(datos.get("id")));
                                listarank.add(new Taxi_Plus_Recycler(String.valueOf(datos.get("id")),String.valueOf(datos.get("id")),String.valueOf(datos.get("fecha_inicio")),String.valueOf(datos.get("fecha_termino")),String.valueOf(datos.get("estado")),String.valueOf(datos.get("costo")),String.valueOf(datos.get("id_conductor"))));


                            }
                            AdapterLista adapterLista = new AdapterLista(listarank);
                            recyclerView.setAdapter(adapterLista);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("jsonaraa:",""+jsonArray);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }



                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("id_usuario",id_usuario);
                //map.put("usuario",strUsuario);0
                map.put("id_sesion",id_sesion);
                //map.put("ubicacion", strUbicacion);
                //map.put("contacto", strContacto);
                //map.put("ayuda", strAyuda);
                return map;
            }

        };
        requestQueue.add(request);
    }
    public static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] textBytes = text.getBytes("iso-8859-1");
        md.update(textBytes, 0, textBytes.length);
        byte[] sha1hash = md.digest();
        return convertToHex(sha1hash);
    }
    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

}
