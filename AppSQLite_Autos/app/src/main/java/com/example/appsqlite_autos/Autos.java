package com.example.appsqlite_autos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Autos extends AppCompatActivity {

    TextView jtvMensajeAutos, jtvDisponible, jtvSINO;
    EditText jetPlaca, jetModelo, jetMarca, jetValor;
    Button jbtnConsultarA, jbtnAdicionarA, jbtnModificarA, jbtnEliminarA, jbtnLimpiarA, jbtnRegresarA;
    String Placa, Modelo, Marca, Valor, SINO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autos);

        getSupportActionBar().hide();

        jtvMensajeAutos = findViewById(R.id.tvMensajeAutos);
        jtvDisponible = findViewById(R.id.tvDisponible);
        jetPlaca = findViewById(R.id.etPlaca);
        jetModelo = findViewById(R.id.etModelo);
        jetMarca = findViewById(R.id.etMarca);
        jetValor = findViewById(R.id.etValor);
        jtvSINO = findViewById(R.id.tvSINO);

        jbtnConsultarA = findViewById(R.id.btnConsultar);
        jbtnAdicionarA = findViewById(R.id.btnAdicionar);
        jbtnModificarA = findViewById(R.id.btnModificar);
        jbtnEliminarA = findViewById(R.id.btnEliminar);
        jbtnLimpiarA = findViewById(R.id.btnLimpiar);
        jbtnRegresarA = findViewById(R.id.btnRegresarV);

    }//END onCreate


    //***GUARDAR***
    public void GuardarAutos(View v){

        MainSQLiteOpenHelper Admin = new MainSQLiteOpenHelper(this, "Concesionario", null, 1);
        SQLiteDatabase db = Admin.getWritableDatabase();

        Placa = jetPlaca.getText().toString();
        Modelo = jetModelo.getText().toString();
        Marca = jetMarca.getText().toString();
        Valor = jetValor.getText().toString();
        SINO = "SI";

        if(Placa.isEmpty() || Modelo.isEmpty() || Marca.isEmpty() || Valor.isEmpty()){
            Toast.makeText(this,"Todos los datos son obligatorios", Toast.LENGTH_SHORT).show();
            jetPlaca.requestFocus();
        }else{
            ContentValues dato = new ContentValues();
            dato.put("placa", Placa);
            dato.put("marca", Marca);
            dato.put("modelo", Modelo);
            dato.put("disponible", SINO);
            dato.put("valor", Valor);

            long resp = db.insert("tblAuto", null, dato);

            if (resp>0){
                Toast.makeText(this, "Registro insertado con exito", Toast.LENGTH_SHORT).show();
                limpiarDatos();
            }else{
                Toast.makeText(this, "Error guardando el registro", Toast.LENGTH_SHORT).show();
            }
        }

        db.close();

    }//END GuardarAutos

    public void ConsultarAutos(View v){

        Placa = jetPlaca.getText().toString();

        if(Placa.isEmpty()){
            Toast.makeText(this,"La placa es obligatoria", Toast.LENGTH_SHORT).show();
            jetPlaca.requestFocus();
        }else{
            MainSQLiteOpenHelper Admin = new MainSQLiteOpenHelper(this, "Concesionario", null, 1);
            SQLiteDatabase db = Admin.getReadableDatabase();
            Cursor fila = db.rawQuery("select * from tblAuto where placa= '" + Placa + "'", null);

            if(fila.moveToFirst()){
                jetPlaca.setText(fila.getString(0));
                jetMarca.setText(fila.getString(1));
                jetModelo.setText(fila.getString(2));
                jetValor.setText(fila.getString(3));
                jtvSINO.setText(fila.getString(4));
            }
            else{
                Toast.makeText(this, "El Auto no existe", Toast.LENGTH_SHORT).show();
            }

            db.close();
        }

    }//END ConsultarAutos

    //***MODIFICAR***
    public void ModificarAutos(View v){

        Placa = jetPlaca.getText().toString();
        Modelo = jetModelo.getText().toString();
        Marca = jetMarca.getText().toString();
        Valor = jetValor.getText().toString();
        SINO = "SI";

        if(Placa.isEmpty() || Modelo.isEmpty() || Marca.isEmpty() || Valor.isEmpty()){
            Toast.makeText(this,"Todos los datos son obligatorios", Toast.LENGTH_SHORT).show();
            jetPlaca.requestFocus();
        }else{
            MainSQLiteOpenHelper Admin = new MainSQLiteOpenHelper(this, "Concesionario", null,1);
            SQLiteDatabase db = Admin.getWritableDatabase();
            ContentValues dato = new ContentValues();
            dato.put("placa", Placa);
            dato.put("marca", Marca);
            dato.put("modelo", Modelo);
            dato.put("disponible", SINO);
            dato.put("valor", Valor);

            long resp = db.update("tblAuto", dato, "placa='" + Placa +"'", null );

            if(resp>0){
                Toast.makeText(this, "Registro modificado", Toast.LENGTH_SHORT).show();
                limpiarDatos();
            }else{
                Toast.makeText(this, "No se pudo modificar el registro",Toast.LENGTH_SHORT).show();
            }

        }

    }//END ModificarAutos


    //***ELIMINAR***
    public void EliminarAutos(View v){

        Placa = jetPlaca.getText().toString();

        if(Placa.isEmpty()){
            Toast.makeText(this, "La placa es obligatoria", Toast.LENGTH_SHORT).show();
            jetPlaca.requestFocus();
        }else{
            MainSQLiteOpenHelper Admin = new MainSQLiteOpenHelper(this, "Concesionario", null, 1);
            SQLiteDatabase db = Admin.getWritableDatabase();
            int resp = db.delete("tblAuto", "placa='" + Placa +"'", null);
            if(resp>0){
                Toast.makeText(this, "Registro eliminado", Toast.LENGTH_SHORT).show();
                limpiarDatos();
            }else{
                Toast.makeText(this, "No se pudo eliminar", Toast.LENGTH_SHORT).show();
            }
        }

    }//END EliminarAutos


    public void LimpiarAutos(View V){limpiarDatos();}

    public void limpiarDatos(){

        jetValor.setText("");
        jtvSINO.setText("SI / NO");
        jetMarca.setText("");
        jetModelo.setText("");
        jetPlaca.setText("");

    }

    public void RegresarAutos(View v){

        //Regresar al MainActivity
        Intent intMain  = new Intent(this,MainActivity.class);
        //Lanzar la actividad
        startActivity(intMain);
    }

}//END Autos