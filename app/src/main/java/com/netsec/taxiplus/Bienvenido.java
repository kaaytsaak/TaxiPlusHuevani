package com.netsec.taxiplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class Bienvenido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent pantallaso = new Intent(Bienvenido.this,Principal.class);
                startActivity(pantallaso);

            }
        },2000);
    }
}