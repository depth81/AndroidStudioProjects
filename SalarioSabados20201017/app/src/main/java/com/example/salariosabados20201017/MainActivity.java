    package com.example.salariosabados20201017;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText jetnumero, jetvalor;
    TextView jtvsalariobasico, jtvdeducciones, jtvtransporte, jtvsalarioneto;
    Button jbtcalcular, jbtlimpiar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        jetnumero = findViewById(R.id.etnumero);
        jetvalor = findViewById(R.id.etvalor);
        jtvsalariobasico = findViewById(R.id.tvsalariobasico);
        jtvdeducciones = findViewById(R.id.tvdeducciones);
        jtvtransporte = findViewById(R.id.tvtransporte);
        jtvsalarioneto =  findViewById(R.id.tvsalarioneto);
        jbtcalcular = findViewById(R.id.btcalcular);
        jbtlimpiar = findViewById(R.id.btlimpiar);

    }

    public void calcularSalario(View v){

        String numero, valor;

        /* Enviar a la memoria RAM. Inicialmente las entradas del usuario estan en tipo Object y hay que pasarlo a String */
        numero = jetnumero.getText().toString();
        valor = jetvalor.getText().toString();

        if(numero.isEmpty() || valor.isEmpty()){
            Toast.makeText(this, "Numero de horas y el valor son requeridos", Toast.LENGTH_LONG).show();
            jetnumero.requestFocus();
        }
        else{
            double numero1, valor1, basico, deducciones, transporte, neto;
            numero1=Double.parseDouble(numero);
            valor1=Double.parseDouble(valor);
            basico=numero1*valor1;

            if(basico <= 882000 * 4)
                deducciones=basico*0.08;
            else
                deducciones=basico*0.09;

            /*Auxilio de transporte */
            if(basico <= 882000 * 2)
                transporte=102854;
            else
                transporte=0;

            neto= basico - deducciones + transporte;

            jtvsalariobasico.setText(String.valueOf(basico));
            jtvdeducciones.setText(String.valueOf(deducciones));
            jtvtransporte.setText(String.valueOf(transporte));
            jtvsalarioneto.setText("" + neto);  //otra forma...
        }

    }

    public  void limpiarDatos(View v){
        jetnumero.setText("");
        jetvalor.setText("");
        jtvsalarioneto.setText("");
        jtvtransporte.setText("");
        jtvdeducciones.setText("");
        jtvsalariobasico.setText("");
        jetnumero.requestFocus();
    }

}