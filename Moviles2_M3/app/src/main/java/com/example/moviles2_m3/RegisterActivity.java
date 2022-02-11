package com.example.moviles2_m3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText jetUsuario, jetNombre, jetClave1, jetClave2;
    Button jbtnConsultar, jbtnAdicionar, jbtnModificar, jbtnEliminar, jbtnLimpiar, jbtnRegresar;
    String usuario, nombre, clave1, clave2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();

        jetUsuario = findViewById(R.id.etUsuario);
        jetNombre = findViewById(R.id.etNombre);
        jetClave1 = findViewById(R.id.etClave1);
        jetClave2 = findViewById(R.id.etClave2);
        jbtnConsultar = findViewById(R.id.btnConsultar);
        jbtnAdicionar = findViewById(R.id.btnAdicionar);
        jbtnModificar = findViewById(R.id.btnModificar);
        jbtnEliminar = findViewById(R.id.btnEliminar);
        jbtnLimpiar = findViewById(R.id.btnLimpiar);
        jbtnRegresar = findViewById(R.id.btnRegresarV);

    }

    public void Guardar(View v){

        MainSQLiteOpenHelper Admin = new MainSQLiteOpenHelper(this, "bdtercermomento", null, 1);
        //Abrir la conexión con la BD
        SQLiteDatabase mydb = Admin.getWritableDatabase();

        //Pasar los datos a la memoria "intermedia" antes de insertar en la BD.
        usuario=jetUsuario.getText().toString();
        nombre=jetNombre.getText().toString();
        clave1=jetClave1.getText().toString();
        clave2=jetClave2.getText().toString();

        if(usuario.isEmpty() || nombre.isEmpty() || clave1.isEmpty() || clave2.isEmpty()){
            Toast.makeText(this, "Todos los datos son obligatorios", Toast.LENGTH_SHORT).show();
            jetUsuario.requestFocus();
        }
        else{
            if(!clave1.equals(clave2)){
                Toast.makeText(this, "Las claves no coinciden", Toast.LENGTH_SHORT).show();
                jetClave1.requestFocus();
            }
            else{
                //Definir un contenedor (ContentValues)
                ContentValues dato = new ContentValues();
                //En blanco lo de la memoria RAM intermedia o el contenedor y en verde la BD.
                dato.put("idUsuario", usuario);
                dato.put("nombre", nombre);
                dato.put("clave", clave1);  //Ya se validaron las dos claves. Sirve cualquiera.

                //Guardar en la BD. Siempre responde en un "long"
                long resp = mydb.insert("tblUsuarios", null, dato);

                if(resp > 0){
                    Toast.makeText(this, "Registro insertado con éxito", Toast.LENGTH_SHORT).show();
                    limpiarDatos();
                }
                else{
                    Toast.makeText(this, "Error guardando el registro", Toast.LENGTH_SHORT).show();
                }

            }
        }
        mydb.close();
    }//END Guardar

    public void Consultar(View v){
        usuario= jetUsuario.getText().toString();

        if(usuario.isEmpty()){
            Toast.makeText(this, "El usuario es requerido", Toast.LENGTH_SHORT).show();
            jetUsuario.requestFocus();
        }else{
            MainSQLiteOpenHelper Admin = new MainSQLiteOpenHelper(this,"bdtercermomento", null, 1);
            SQLiteDatabase db = Admin.getReadableDatabase();
            Cursor fila = db.rawQuery("select * from tblUsuarios where idUsuario= '" + usuario + "'",null);
            if(fila.moveToFirst()){
                jetNombre.setText(fila.getString(1));
                jetClave1.setText(fila.getString(2));
                jetClave2.setText(fila.getString(2));
            }else{
                Toast.makeText(this, "El usuario no existe", Toast.LENGTH_SHORT).show();
            }

            db.close();

        }
    }//END Consultar()

    public void Modificar(View v){

        //Pasar los datos a la memoria "intermedia" antes de insertar en la BD.
        usuario=jetUsuario.getText().toString();
        nombre=jetNombre.getText().toString();
        clave1=jetClave1.getText().toString();
        clave2=jetClave2.getText().toString();

        if(usuario.isEmpty() || nombre.isEmpty() || clave1.isEmpty() || clave2.isEmpty()){
            Toast.makeText(this, "Todos los datos son obligatorios", Toast.LENGTH_SHORT).show();
            jetUsuario.requestFocus();
        }
        else{
            if(!clave1.equals(clave2)){
                Toast.makeText(this, "Las claves no coinciden", Toast.LENGTH_SHORT).show();
                jetClave1.requestFocus();
            }else{
                MainSQLiteOpenHelper Admin = new MainSQLiteOpenHelper(this, "bdtercermomento", null, 1);
                //Abrir la conexión con la BD
                SQLiteDatabase db = Admin.getWritableDatabase();
                ContentValues dato = new ContentValues();
                dato.put("nombre",nombre);
                dato.put("clave", clave1);
                long resp = db.update("tblUsuarios", dato, "idUsuario='" + usuario +"'", null );
                if(resp>0){
                    Toast.makeText(this,"Registro modificado", Toast.LENGTH_SHORT).show();
                    limpiarDatos();
                }else{
                    Toast.makeText(this, "No se puede modificar el registro", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }//END Modificar


    public void Eliminar(View v){
        usuario = jetUsuario.getText().toString();
        if(usuario.isEmpty()){
            Toast.makeText(this, "El usuario es requerido", Toast.LENGTH_SHORT).show();
            jetUsuario.requestFocus();
        }else{
            MainSQLiteOpenHelper Admin = new MainSQLiteOpenHelper(this, "bdtercermomento", null, 1);
            SQLiteDatabase db = Admin.getWritableDatabase();
            int resp = db.delete("tblUsuarios", "idUsuario = '" + usuario +  "'", null);
            if(resp>0){
                Toast.makeText(this,"Registro eliminado", Toast.LENGTH_SHORT).show();
                limpiarDatos();
            }else{
                Toast.makeText(this,"No se pudo eliminar", Toast.LENGTH_SHORT).show();
            }
        }
    }//END Eliminar()

    public void Limpiar(View v){
        limpiarDatos();
    }

    //No tiene view como parámetro porque no está asociado a ninguna vista.
    public void limpiarDatos(){

        jetUsuario.setText("");
        jetNombre.setText("");
        jetClave1.setText("");
        jetClave2.setText("");
        jetUsuario.requestFocus();

    }

    public void Regresar(View v){

        //Regresar al MainActivity
        Intent intMain = new Intent(this,MainActivity.class);
        //Lanzar la actividad
        startActivity(intMain);
    }


}//END RegisterActivity()