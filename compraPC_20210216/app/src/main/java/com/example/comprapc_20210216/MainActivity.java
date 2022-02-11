
package com.example.comprapc_20210216;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText jetValor, jetCantidad;
    TextView jtvTotal;
    Button jbtnTotal;
    int total, valor, cantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        jetValor = findViewById(R.id.etValor);
        jetCantidad = findViewById(R.id.etCantidad);
        jtvTotal = findViewById(R.id.tvTotal);
        jbtnTotal = findViewById(R.id.btnTotal);

//        jbtnTotal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                hallarTotal();
//            }
//        });
    }

    public void hallarTotal(View v){

        String cant, val;

        cant = jetCantidad.getText().toString();
        val = jetValor.getText().toString();

        if(cant.isEmpty() || val.isEmpty()){
            Toast.makeText(this,"Todos los datos son obligatorios",Toast.LENGTH_SHORT).show();
            jetCantidad.requestFocus();
        }else{
            valor = Integer.parseInt(val);
            cantidad = Integer.parseInt(cant);
            total = valor*cantidad;
            jtvTotal.setText(String.valueOf(total));
        }

    }

}//END MainActivity