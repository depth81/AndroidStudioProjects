package com.example.appmomento2_moviles1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView jtvTitle, jtvCostoTte, jtvCostoComida, jtvValorPropina, jtvTotal;
    EditText jetCantPersonas;
    RadioGroup jrgAlimentacion;
    RadioButton jrbAvion, jrbBus, jrbTaxiExp, jrbLasagna, jrbPizza, jrbHamburguesa;
    CheckBox jcbPropina;
    Button jbtCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        jtvTitle = findViewById(R.id.tvTitle);
        jtvCostoTte = findViewById(R.id.tvCostoTte);
        jtvCostoComida = findViewById(R.id.tvCostoComida);
        jtvValorPropina = findViewById(R.id.tvValorPropina);
        jtvTotal = findViewById(R.id.tvTotal);
        jetCantPersonas = findViewById(R.id.etCantPersonas);
        jrbAvion = findViewById(R.id.rbAvion);
        jrbBus = findViewById(R.id.rbBus);
        jrbTaxiExp = findViewById(R.id.rbTaxiExp);
        jrbLasagna = findViewById(R.id.rbLasagna);
        jrbPizza = findViewById(R.id.rbPizza);
        jrbHamburguesa = findViewById(R.id.rbHamburguesa);
        jcbPropina = findViewById(R.id.cbPropina);
        jbtCalcular = findViewById(R.id.btCalcular);
        jrgAlimentacion = findViewById(R.id.rgAlimentacion);

    }

    public void Alimentacion(View v){

        if(jrbAvion.isChecked()){
            jrbLasagna.setEnabled(true);
            jrbPizza.setEnabled(true);
            jrbHamburguesa.setEnabled(true);

            jtvCostoComida.setText("Costo de la alimentacion");
        }
        else{
            jrbLasagna.setEnabled(false);
            jrbPizza.setEnabled(false);
            jrbHamburguesa.setEnabled(false);

            jtvCostoComida.setText("0");
          }

    }

    public void calcularTotal(View v){

        String cantPersonas_Str;

        cantPersonas_Str = jetCantPersonas.getText().toString();

        if (cantPersonas_Str.isEmpty()){
            Toast.makeText(this, "Debe ingresar la cantidad de personas", Toast.LENGTH_SHORT).show();
            jetCantPersonas.requestFocus();
        }
        else{

            double cantPersonas, Propina, ValorBruto, ValorNeto, ValorTte, ValorAlimentacion;

            cantPersonas = Double.parseDouble(cantPersonas_Str);

            if(jrbAvion.isChecked())
                ValorTte = cantPersonas * 500000;
            else if(jrbBus.isChecked())
                ValorTte = cantPersonas * 100000;
            else
                ValorTte = cantPersonas * 200000;

            jtvCostoTte.setText(String.valueOf(ValorTte));

            if(jrbAvion.isChecked()){
                if(jrbLasagna.isChecked()) {
                    ValorAlimentacion = cantPersonas * 30000;
                }
                else if(jrbPizza.isChecked()){
                    ValorAlimentacion = cantPersonas * 25000;
                }
                else{
                    ValorAlimentacion = cantPersonas * 20000;
                }
                jtvCostoComida.setText(String.valueOf(ValorAlimentacion));
            }
            else{
                ValorAlimentacion = 0;
            }

            ValorBruto = ValorTte + ValorAlimentacion;

            if(jcbPropina.isChecked()){
                Propina = ValorBruto * 0.1;
                jtvValorPropina.setText(String.valueOf(Propina));
                ValorNeto = ValorBruto + Propina;
                jtvTotal.setText(String.valueOf(ValorNeto));
            }
            else{
                jtvValorPropina.setText("0");
                jtvTotal.setText(String.valueOf(ValorBruto));
            }

        }

    }//END calcularTotal()

    public void limiparDatos(View v){
        jetCantPersonas.setText("");
        jtvCostoTte.setText("");
        jtvCostoComida.setText("");
        jtvValorPropina.setText("");
        jtvTotal.setText("");
        jrbAvion.setChecked(true);
        jrbBus.setChecked(false);
        jrbLasagna.setEnabled(true);
        jrbLasagna.setChecked(true);
        jrbPizza.setEnabled(true);
        jrbHamburguesa.setEnabled(true);
        jrbTaxiExp.setChecked(false);
        jcbPropina.setChecked(true);
    }

}//END MainActivity