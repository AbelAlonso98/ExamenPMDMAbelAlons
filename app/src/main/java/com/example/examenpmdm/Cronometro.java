package com.example.examenpmdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cronometro extends AppCompatActivity {
    // Componentes
    TextView cronometro;
    RecyclerView rv;

    int tiempo = 0; // <--- Puedes modificar el tiempo al empezar aqui
    Boolean funcionando = false;  // Variable usada para parar el bucle que suma
    List<String> l; // Lista para el RecyclerView


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);
        cronometro = findViewById(R.id.cronometro);

        // Inicializo el RecyclerView
        rv = findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        // Le asigno el ArrayList
        l = new ArrayList<String>();
        rv.setAdapter(new UserAdapter(l));

        // Seteo el tiempo con formato
        cronometro.setText(format(tiempo));


    }
/*
    Metodo para empezar a contar, usa un hilo que añade 1 al tiempo y setea el texto
    del cronometro.

    El while es el que checkea que esta corriendo con la variable funcionando.
 */
    public void play(View v) {
        funcionando = true;
        new Thread(() -> {
            while (funcionando) {
                runOnUiThread(() -> cronometro.setText(format(tiempo)));
                tiempo++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }

    /*
    Metodo que para, simplemente setea a false la variable del while de arriba.
     */
    public void pause(View v) {
        funcionando = false;
    }

    /*
    Metodo que guarda el tiempo transcurrido. Primero checkea que no haya nada en la lista.
    En caso afirmativo, guarda el tiempo directamente. En caso contrario, recorre las anteriores
    para ver cuanto tiempo ha transcurrido y restarselo al contador.
     */
    public void record(View v) {
        if (l.size() == 0) {
            l.add(Integer.toString(tiempo));
            rv.setAdapter(new UserAdapter(l));
        } else {
            int tiempoAnterior = 0;
            for (String s : l)
                tiempoAnterior += Integer.parseInt(s);
            int tiempoTranscurrido = tiempo - tiempoAnterior;
            l.add(Integer.toString(tiempoTranscurrido));
            rv.setAdapter(new UserAdapter(l));
        }
    }

    /*
    Metodo que formatea los segundos (int) en formato HH:MM:SS.
     */
    private String format(int segundos){
        int min = segundos / 60;
        segundos %= 60;
        int hor = min / 60;
        min %= 60;
        return String.format("%02d:%02d:%02d", hor, min, segundos);
    }

}