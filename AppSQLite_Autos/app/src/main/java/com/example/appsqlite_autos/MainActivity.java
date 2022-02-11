package com.example.appsqlite_autos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText jetUsuario, jetClave;
    Button jbtnIngresar, jbtnRegistrarse, jbtnAutos, jbtnVentas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        jetUsuario = findViewById(R.id.etUsuario);
        jetClave = findViewById(R.id.etClave);
        jbtnIngresar = findViewById(R.id.btnIngresar);
        jbtnRegistrarse = findViewById(R.id.btnRegistrarse);
        jbtnAutos = findViewById(R.id.btnAutos);
        jbtnVentas = findViewById(R.id.btnVentas);

    }

    public void Ingresar(View b){

        String usuario, clave;
        usuario = jetUsuario.getText().toString();
        clave = jetClave.getText().toString();

        if(usuario.isEmpty() || clave.isEmpty()){
            Toast.makeText(this, "Debe ingresar el usuario y la clave", Toast.LENGTH_LONG).show();
            jetUsuario.requestFocus();
        }else{

            MainSQLiteOpenHelper Admin = new MainSQLiteOpenHelper(this, "Concesionario", null, 1);
            SQLiteDatabase db = Admin.getReadableDatabase();
            Cursor fila = db.rawQuery("select idUsuario from tblUsuario where idUsuario = '"+usuario+"' and clave='"+clave+"'",null);
            if(fila.moveToFirst()){
                //Cambio de actividad
                Intent intMostrar = new Intent(this, ShowActivity.class);
                //Pasar datos
                intMostrar.putExtra("datos", usuario);
                //Lanzar la actividad
                startActivity(intMostrar);
            }else{
                Toast.makeText(this,"Revise los datos. No puede ingresar", Toast.LENGTH_SHORT).show();
            }

            db.close();

        }

    }//END Ingresar()

    public void Registrarse(View v){

        //Cambio de actividad
        Intent intRegistrarse = new Intent(this,RegisterActivity.class);
        //Lanzar la actividad
        startActivity(intRegistrarse);

    }

    public void Autos(View v){

        Intent intAutos = new Intent(this, Autos.class);
        startActivity(intAutos);
    }

    public void Ventas(View v){

        Intent intVentas = new Intent(this,Ventas.class);
        startActivity(intVentas);

    }

}