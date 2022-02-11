package com.example.appsqlite_autos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    TextView jtvUsuario;
    Button jbtnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        getSupportActionBar().hide();

        jtvUsuario = findViewById(R.id.tvUsuario);
        jbtnRegresar = findViewById(R.id.btnRegresarV);
        String usuario;
        usuario = getIntent().getStringExtra("datos");
        jtvUsuario.setText(usuario);

    }

    public void Regresar(View b){

        //Cambio de actividad
        Intent intMain = new Intent(this, MainActivity.class);

        //Lanzar la actividad
        startActivity(intMain);

    }

}