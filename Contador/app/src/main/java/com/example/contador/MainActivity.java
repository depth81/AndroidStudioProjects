package com.example.contador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contador = 0;
    }

    //Recibe vista (tipo View). Es la vista que desencadena el evento, sobre la que se hizo click (boton)
    public void incrementaContador(View vista){

        contador++;

        mostrarResultado();

    }

    public void decrementaContador(View vista){

        contador--;

        mostrarResultado();

    }

    public void resetContador(View vista){

        contador = 0;

        mostrarResultado();

    }

    public void mostrarResultado(){

        TextView textoResultado = (TextView)findViewById(R.id.contadorTexto);

        textoResultado.setText("" + contador);

    }

}