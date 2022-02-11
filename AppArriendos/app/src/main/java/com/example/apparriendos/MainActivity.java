package com.example.apparriendos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView jtvAppTitle, jtvValorProp, jtvValorxHab, jtvValorParq, jtvTotal;
    RadioButton jrbCasa, jrbApartamento, jrbFinca, jrbHab1, jrbHab2, jrbHab3;
    EditText jetCantHab;
    CheckBox jcbParqueadero;
    Button jbtCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        jtvAppTitle = findViewById(R.id.tvAppTitle);
        jtvValorProp = findViewById(R.id.tvValorProp);
        jtvValorxHab = findViewById(R.id.tvValorxHab);
        jtvValorParq = findViewById(R.id.tvValorParq);
        jtvTotal = findViewById(R.id.tvTotal);
        jrbCasa = findViewById(R.id.rbCasa);
        jrbApartamento = findViewById(R.id.rbApartamento);
        jrbFinca = findViewById(R.id.rbFinca);
        jcbParqueadero = findViewById(R.id.cbParqueadero);
        jbtCalcular = findViewById(R.id.btCalcular);
        jrbHab1 = findViewById(R.id.rbHab1);
        jrbHab2 = findViewById(R.id.rbHab2);
        jrbHab3 = findViewById(R.id.rbHab3);

    }

    public void calcularPropiedad(View v){

        if(jrbCasa.isChecked())
            jtvValorProp.setText("400000");
        else if(jrbApartamento.isChecked())
            jtvValorProp.setText("300000");
        else
            jtvValorProp.setText("600000");

    }//END calcularPropiedad()

    public void calcularParqueadero(View v){

        if(jcbParqueadero.isChecked())
            jtvValorParq.setText("100000");
        else
            jtvValorParq.setText("0");

    }//END calcularParqueadero()

    public void calcularHabitaciones(View v){

        if(jrbHab1.isChecked())
            jtvValorxHab.setText("100000");
        else if(jrbHab2.isChecked())
            jtvValorxHab.setText("200000");
        else
            jtvValorxHab.setText("300000");

    }//END calcularHabitaciones()

    public void calcularTotal(View v){

        int valorProp, valorxHab, valorParq, total;

        valorProp =Integer.parseInt(jtvValorProp.getText().toString());
        valorxHab = Integer.parseInt(jtvValorxHab.getText().toString());
        valorParq = Integer.parseInt(jtvValorParq.getText().toString());

        total = valorProp + valorxHab + valorParq;

        jtvTotal.setText(String.valueOf(total));

        }//END calcularTotal()

    }
