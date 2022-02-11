package com.example.me;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText jetUsuario, jetClave;
    Button jbtnIngresar, jbtnRegistrar;
    ClassPersona oPersona;
    ArrayList<ClassPersona> alPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        jetUsuario = findViewById(R.id.etUsuario);
        jetClave = findViewById(R.id.etClave);
        jbtnIngresar = findViewById(R.id.btnIngresar);
        jbtnRegistrar = findViewById(R.id.btnRegistrar);
        alPersonas = (ArrayList<ClassPersona>) getIntent().getSerializableExtra("listas");

    }

    public void ingresar(View v){
        String usuario, clave;
        usuario = jetUsuario.getText().toString();
        clave = jetClave.getText().toString();

        if(usuario.isEmpty() || clave.isEmpty()){
            Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
            jetUsuario.requestFocus();
        }else{
            if(getIntent().getSerializableExtra("listas") == null){
                Toast.makeText(this, "La lista esta vacia. Registrese primero por favor", Toast.LENGTH_SHORT).show();
            }else{
                
                int sw=0, pos=0;

                while (pos < alPersonas.size() && sw == 0){
                    oPersona = alPersonas.get(pos);
                    if (usuario.equals(oPersona.getIdentificacion()) && clave.equals(oPersona.getClave()))
                        sw = 1;
                    else
                        pos ++;
                }//END WHILE

                if(sw == 0){
                    Toast.makeText(this,"Usuario o clave invalidos",Toast.LENGTH_SHORT ).show();
                    jetClave.setText("");
                    jetUsuario.requestFocus();
                }else{
                    Intent intMostrar = new Intent(this, MostrarActivity.class);
                    startActivity(intMostrar);
                }
            }
        }
    }


    public void registrar(View v){
        Intent intentRegistrar = new Intent(this,RegistrarActivity.class);
        startActivity(intentRegistrar);
    }

}