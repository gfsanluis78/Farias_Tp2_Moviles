package com.farias.farias_tp2_moviles;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pedirPermisos();

        //ServicioMensajes servicioMensajes = new ServicioMensajes();

        i = new Intent(this,ServicioMensajes.class);

        startService(i);

    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(i);
    }

    private void pedirPermisos(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkCallingOrSelfPermission(Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_SMS}, 1000);
        }

    }
}