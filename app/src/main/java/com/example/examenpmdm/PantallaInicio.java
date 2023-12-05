package com.example.examenpmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PantallaInicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

    }

    /*
    Metodo para ir al cronometro
     */
    public void irACronometro(View v){
        Intent i = new Intent(this, Cronometro.class);
        startActivity(i);
    }

    /*
    Metodo para salir de la aplicacion.
     */
    public void salir(View v){
        finishAffinity();
    }


}