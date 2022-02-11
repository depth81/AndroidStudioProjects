package com.example.appfiesta;

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

    TextView jtvtitle, jtvValorxP, jtvValorProp, jtvTotal, jtvValorBotellas;
    RadioButton jrbVIP, jrbPalco, jrbLateral, jrbGeneral, jrbAguardiente, jrbRon, jrbWhiskey;
    EditText jetPersonas, jetBotellas;
    CheckBox jcbPropina;
    Button jbtCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        jtvtitle = findViewById(R.id.tvTitle);
        jtvValorxP = findViewById(R.id.tvValorxP);
        jtvValorProp = findViewById(R.id.tvValorPropina);
        jtvValorBotellas = findViewById(R.id.tvValorBotellas);
        jtvTotal = findViewById(R.id.tvTotal);
        jrbVIP = findViewById(R.id.rbVIP);
        jrbPalco = findViewById(R.id.rbPalco);
        jrbLateral = findViewById(R.id.rbLateral);
        jrbGeneral = findViewById(R.id.rbGeneral);
        jrbAguardiente = findViewById(R.id.rbAguardiente);
        jrbRon = findViewById(R.id.rbRon);
        jrbWhiskey = findViewById(R.id.rbWhiskey);
        jetPersonas = findViewById(R.id.etPersonas);
        jetBotellas = findViewById(R.id.etBotellas);
        jcbPropina = findViewById(R.id.cbPropina);
        jbtCalcular = findViewById(R.id.btCalcular);

    }

    public void calcularTotal(View v){

        String Personas, Botellas;

        Personas = jetPersonas.getText().toString();
        Botellas = jetBotellas.getText().toString();

        if (Personas.isEmpty() || Botellas.isEmpty()){
            Toast.makeText(this, "Debe ingresar la cantidad de personas y la cantidad de botellas consumidas", Toast.LENGTH_SHORT).show();
            jetPersonas.requestFocus();
        }
        else{

            double ValorxP_double, Personas_double, Botellas_double, valorBotellas_double, Propina, TotalBruto, TotalNeto;

            Personas_double = Double.parseDouble(Personas);
            Botellas_double = Double.parseDouble(Botellas);

            if(jrbVIP.isChecked())
                ValorxP_double = Personas_double * 50000;
            else if(jrbPalco.isChecked())
                ValorxP_double = Personas_double * 35000;
            else if(jrbLateral.isChecked())
                ValorxP_double = Personas_double * 20000;
            else
                ValorxP_double = Personas_double * 25000;

            if(jrbAguardiente.isChecked())
                valorBotellas_double = Botellas_double * 150000;
            else if(jrbRon.isChecked())
                valorBotellas_double = Botellas_double * 180000;
            else
                valorBotellas_double = Botellas_double * 250000;

            TotalBruto = ValorxP_double + valorBotellas_double;

            if(jcbPropina.isChecked()){
                Propina = TotalBruto * 0.1;
                jtvValorProp.setText(String.valueOf(Propina));
                TotalNeto = TotalBruto + Propina;
                jtvTotal.setText(String.valueOf(TotalNeto));
            }
            else{
                jtvValorProp.setText("0");
                TotalNeto = TotalBruto;
                jtvTotal.setText(String.valueOf(TotalNeto));
            }

            //Propina = TotalBruto * 0.1;

            jtvValorxP.setText(String.valueOf(ValorxP_double));
            jtvValorBotellas.setText(String.valueOf(valorBotellas_double));
            //jtvValorProp.setText(String.valueOf(Propina));
            //jtvTotal.setText(String.valueOf(TotalNeto));

        }



    }

}