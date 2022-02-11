package com.example.appsqlite_autos;

import androidx.appcompat.app.AppCompatActivity;

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

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class Ventas extends AppCompatActivity {

    TextView jtvVentas, jtvSINOv;
    EditText jetPlacaV, jetModeloV, jetMarcaV, jetValorV;
    Button jbtnVender, jbtnRegresarv,jbtnLimpiarv,jbtnConsultarv;
    String Placav, Modelov, Marcav, Valorv, SINOv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);

        getSupportActionBar().hide();

        jtvVentas = findViewById(R.id.tvVentas);
        jtvSINOv = findViewById(R.id.tvSINOv);
        jetPlacaV = findViewById(R.id.etPlacaV);
        jetModeloV = findViewById(R.id.etModeloV);
        jetMarcaV = findViewById(R.id.etMarcaV);
        jetValorV = findViewById(R.id.etValorV);
        jbtnVender = findViewById(R.id.btnVender);
        jbtnRegresarv = findViewById(R.id.btnRegresarv);
        jbtnLimpiarv = findViewById(R.id.btnLimpiarv);
        jbtnConsultarv = findViewById(R.id.btnConsultarv);

        jetModeloV.setEnabled(false);
        jetMarcaV.setEnabled(false);
        jetValorV.setEnabled(false);

    }

    public void ConsultarVenta(View v){

        Placav = jetPlacaV.getText().toString();

        if(Placav.isEmpty()){
            Toast.makeText(this,"La placa es obligatoria", Toast.LENGTH_SHORT).show();
            jetPlacaV.requestFocus();
        }else{
            MainSQLiteOpenHelper Admin = new MainSQLiteOpenHelper(this, "Concesionario", null, 1);
            SQLiteDatabase db = Admin.getReadableDatabase();
            Cursor fila = db.rawQuery("select * from tblAuto where placa= '" + Placav + "'", null);

            if(fila.moveToFirst()){
                jetPlacaV.setText(fila.getString(0));
                jetMarcaV.setText(fila.getString(1));
                jetModeloV.setText(fila.getString(2));
                jetValorV.setText(fila.getString(3));
                jtvSINOv.setText(fila.getString(4));

                if (jtvSINOv.getText().toString().equals("NO")){
                    jbtnVender.setEnabled(false);
                }else{
                    jbtnVender.setEnabled(true);
                }

            }
            else{
                Toast.makeText(this, "El Auto no existe", Toast.LENGTH_SHORT).show();
            }

            db.close();
        }

    }

    public void Vender(View v) {

        MainSQLiteOpenHelper Admin = new MainSQLiteOpenHelper(this, "Concesionario", null, 1);
        SQLiteDatabase db = Admin.getWritableDatabase();

        Placav = jetPlacaV.getText().toString();

        if (Placav.isEmpty()) {
            Toast.makeText(this, "La placa es obligatoria", Toast.LENGTH_SHORT).show();
            jetPlacaV.requestFocus();
        } else {
            ContentValues dato = new ContentValues();
            SINOv = "NO";
            dato.put("placa", Placav);
            //dato.put("marca", Marcav);
            //dato.put("modelo", Modelov);
            dato.put("disponible", SINOv);
            //dato.put("valor", Valorv);

            long resp = db.update("tblAuto", dato, "placa='" + Placav + "'", null);

            if (resp > 0) {
                Toast.makeText(this, "El auto ha sido vendido", Toast.LENGTH_SHORT).show();
                limpiarDatos();
            } else {
                Toast.makeText(this, "No se pudo vender el auto", Toast.LENGTH_SHORT).show();
            }

            db.close();
        }

    }



    public void LimpiarVentas(View V) {
        limpiarDatos();
    }

    public void limpiarDatos() {

        jetValorV.setText("");
        jtvSINOv.setText("SI / NO");
        jetMarcaV.setText("");
        jetModeloV.setText("");
        jetPlacaV.setText("");
        jbtnVender.setEnabled(true);

    }

    public void RegresarVentas(View v) {

        //Regresar al MainActivity
        Intent intMain = new Intent(this, MainActivity.class);
        //Lanzar la actividad
        startActivity(intMain);
    }

}//END Ventas
