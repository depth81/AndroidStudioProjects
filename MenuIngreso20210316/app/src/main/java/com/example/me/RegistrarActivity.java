package com.example.me;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class RegistrarActivity extends AppCompatActivity {

    EditText jetUsuario2, jetNombre, jetClave1, jetClave2;
    Button jbtnConsultar, jbtnAdicionar, jbtnModificar, jbtnEliminar, jbtnLimpiar, jbtnRegresar;
    ClassPersona oPersona;
    ArrayList<ClassPersona> alPersonas;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        getSupportActionBar().hide();

        jetUsuario2 = findViewById(R.id.etUsuario2);
        jetNombre = findViewById(R.id.etNombre);
        jetClave1 = findViewById(R.id.etClave1);
        jetClave2 = findViewById(R.id.etClave2);
        jbtnConsultar = findViewById(R.id.btnConsultar);
        jbtnAdicionar = findViewById(R.id.btnAdicionar);
        jbtnModificar = findViewById(R.id.btnModificar);
        jbtnEliminar = findViewById(R.id.btnEliminar);
        jbtnLimpiar = findViewById(R.id.btnLimpiar);
        jbtnRegresar = findViewById(R.id.btnRegresar);
        alPersonas = new ArrayList<ClassPersona>();

    }

    public void adicionarPersona(View v) {
        String usuario2, nombre, clave1, clave2;
        usuario2 = jetUsuario2.getText().toString();
        nombre = jetNombre.getText().toString();
        clave1 = jetClave1.getText().toString();
        clave2 = jetClave2.getText().toString();

        if (usuario2.isEmpty() || nombre.isEmpty() || clave1.isEmpty() || clave2.isEmpty()) {
            Toast.makeText(this, "todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            jetUsuario2.requestFocus();
        } else {
            if (!clave1.equals(clave2)) {
                Toast.makeText(this, "Las claves son diferentes", Toast.LENGTH_SHORT).show();
                jetClave1.setText("");
                jetClave2.setText("");
                jetClave1.requestFocus();
            } else {
                oPersona = new ClassPersona(usuario2, nombre, clave1);
                alPersonas.add(oPersona);
                Toast.makeText(this, "Registro guardado con exito", Toast.LENGTH_SHORT).show();
                limpiarCampos();
            }
        }

    }

    public void consultarArrayList() {
        pos = 0;
        int sw = 0;
        String usuario2 = jetUsuario2.getText().toString();

        if (usuario2.isEmpty()) {
            Toast.makeText(this, "El usuario es obligatorio", Toast.LENGTH_SHORT).show();
            jetUsuario2.requestFocus();
        } else {
            while (pos < alPersonas.size() && sw == 0) {

                oPersona = alPersonas.get(pos);

                if (usuario2.equals(oPersona.getIdentificacion())) {
                    sw = 1;
                } else {
                    pos += 1;
                }

            }//END while

            if (sw == 0) {
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                pos = -1;
            } else {
                jetNombre.setText(oPersona.getNombre());
                jetClave1.setText((oPersona.getClave()));
                jetClave2.setText(oPersona.getClave());
            }

        }

    }

    public void consultar(View v) {
        consultarArrayList();
    }

    public void limpiarFormulario(View v) {
        limpiarCampos();
    }

    private void limpiarCampos() {
        jetUsuario2.setText("");
        jetNombre.setText("");
        jetClave1.setText("");
        jetClave2.setText("");
        jetUsuario2.requestFocus();
    }

    public void eliminar(View v){
        if(pos != -1){
            alPersonas.remove(pos);
            limpiarCampos();
            Toast.makeText(this, "El registro ha sido eliminado", Toast.LENGTH_SHORT).show();
        }
    }

    public void modificar(View v) {
        if (pos != -1) {
            String usuario2, nombre, clave1, clave2;
            usuario2 = jetUsuario2.getText().toString();
            nombre = jetNombre.getText().toString();
            clave1 = jetClave1.getText().toString();
            clave2 = jetClave2.getText().toString();

            if (usuario2.isEmpty() || nombre.isEmpty() || clave1.isEmpty() || clave2.isEmpty()) {
                Toast.makeText(this, "todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                jetUsuario2.requestFocus();
            } else {
                if (!clave1.equals(clave2)) {
                    Toast.makeText(this, "Las claves son diferentes", Toast.LENGTH_SHORT).show();
                    jetClave1.setText("");
                    jetClave2.setText("");
                    jetClave1.requestFocus();
                } else {
                    alPersonas.get(pos).setNombre(nombre);
                    alPersonas.get(pos).setClave(clave1);
                    limpiarCampos();
                }
            }
        }
    }//END modificar

    public void regresar(View v){
        Intent intMain = new Intent(this, MainActivity.class);
        intMain.putExtra("listas", alPersonas);
        startActivity(intMain);
    }

}//END RegistrarActivity