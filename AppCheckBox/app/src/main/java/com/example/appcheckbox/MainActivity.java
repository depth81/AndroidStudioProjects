package com.example.appcheckbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;
    private CheckBox cb1, cb2;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText)findViewById(R.id.etValor1);
        et2 = (EditText)findViewById(R.id.etValor2);
        cb1 = (CheckBox)findViewById(R.id.cbSuma);
        cb2 = (CheckBox)findViewById(R.id.cbResta);
        tv1 = (TextView)findViewById(R.id.tvResultado);

    }

    public void Calcular(View v){

        String valor1_string = et1.getText().toString();
        String valor2_string = et2.getText().toString();

        int valor1_int = Integer.parseInt(valor1_string);
        int valor2_int = Integer.parseInt(valor2_string);

        String resultado = "";

        /*if(cb1.isChecked()) {
            int suma = valor1_int + valor2_int;
            if (cb2.isChecked()) {
                int resta = valor1_int - valor2_int;
                resultado = " La suma es: " + suma + " / La resta es: " + resta;
            }else {
                resultado = " La suma es: " + suma;
            }
        }
        else{
            int resta = valor1_int - valor2_int;
            resultado = " La resta es: " + resta ;
        } */

        if(cb1.isChecked()){
            int suma = valor1_int + valor2_int;
            resultado = " La suma es: " + suma + " / ";
        }
        if(cb2.isChecked()){
            int resta = valor1_int - valor2_int;
            resultado = resultado + " La Resta es: " + resta;
        }

        tv1.setText(resultado);

    }

}

