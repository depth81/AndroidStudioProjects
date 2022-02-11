package com.example.appspinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner sp1;
    private EditText et1, et2;
    private TextView tv1;
    private Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText)findViewById(R.id.etValor1);
        et2 = (EditText)findViewById(R.id.etValor2);
        sp1 = (Spinner)findViewById(R.id.spinner);
        tv1 = (TextView)findViewById(R.id.tvResultado);

        //Opciones para el texto del Spinner.
        String[] opciones = {"sumar", "restar", "multiplicar", "dividir"};

        //Crear un objeto tipo ArrayAdapter para enlazar comunicaciones[] y lo que se va a desplegar en el Spinner.
        //ArrayAdapter <String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        ArrayAdapter <String> adaptador = new ArrayAdapter<String>(this, R.layout.spinner_item_petv, opciones);

        //Colocarl el objeto (adaptador) anterior dentro del Spinner (sp1)
        sp1.setAdapter(adaptador);

    }

    //Metodo del boton
    public void Calcular(View v){

        String valor1_string = et1.getText().toString();
        String valor2_string = et2.getText().toString();

        int valor1_int = Integer.parseInt(valor1_string);
        int valor2_int = Integer.parseInt(valor2_string);

        String seleccion = sp1.getSelectedItem().toString();

        if(seleccion.equals("sumar")){
            int suma = valor1_int + valor2_int;
            String resultado = String.valueOf(suma);
            tv1.setText(resultado);
        }else if(seleccion.equals("restar")){
            int resta = valor1_int - valor2_int;
            String resultado = String.valueOf(resta);
            tv1.setText(resultado);
        }
        else if(seleccion.equals("multiplicar")){
            int multiplicacion = valor1_int * valor2_int;
            String resultado = String.valueOf(multiplicacion);
            tv1.setText(resultado);
        }else{
            if(valor2_int != 0){
                int division = valor1_int / valor2_int;
                String resultado = String.valueOf(division);
                tv1.setText(resultado);
            }else{
                Toast.makeText(this, "El divisor no puede ser cero (0)", Toast.LENGTH_LONG).show();
            }
        }//END if


    }

}