package com.example.appradiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etValor1, etValor2;
    private TextView tvResultado;
    private RadioButton rbSuma, rbResta, rbProducto, rbDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etValor1 = (EditText)findViewById(R.id.txtValor1);
        etValor2 = (EditText)findViewById(R.id.txtValor2);
        tvResultado = (TextView)findViewById(R.id.txtView);
        rbSuma =  (RadioButton)findViewById(R.id.rbSuma);
        rbResta = (RadioButton)findViewById(R.id.rbResta);
        rbProducto = (RadioButton)findViewById(R.id.rbMultiplicar);
        rbDiv = (RadioButton)findViewById(R.id.rbDividir);

    }

    //Metodo para el boton CALCULAR
    public void Calcular(View v){

        String valor1_String = etValor1.getText().toString();
        String valor2_String = etValor2.getText().toString();

        if(valor1_String.isEmpty()||valor2_String.isEmpty()){
            Toast.makeText(this, "Debe introducir ambos valores", Toast.LENGTH_SHORT).show();
        }else{

            //convertir String a double
            double valor1_double = Double.parseDouble(valor1_String);
            double valor2_double = Double.parseDouble(valor2_String);

            if(rbSuma.isChecked()){
                double suma = valor1_double + valor2_double;
                String resultado = String.valueOf(suma);
                tvResultado.setText(resultado);
            }else if(rbResta.isChecked()){
                double resta = valor1_double - valor2_double;
                String resultado = String.valueOf(resta);
                tvResultado.setText(resultado);
            }else if(rbProducto.isChecked()){
                double producto = valor1_double * valor2_double;
                String resultado = String.valueOf(producto);
                tvResultado.setText(resultado);
            }else{
                if(valor2_double == 0){
                    Toast.makeText(this, "El divisor NO puede ser cero", Toast.LENGTH_SHORT).show();
                }else{
                    double division = valor1_double / valor2_double;
                    String resultado = String.valueOf(division);
                    tvResultado.setText(resultado);
                }
            }

        }//END Validacion

    }//END Calcular()

}//END MainActivity()