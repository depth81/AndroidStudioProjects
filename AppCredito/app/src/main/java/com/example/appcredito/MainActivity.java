package com.example.appcredito;

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

    RadioButton jrbEmprendimiento, jrbInteligencia, jrbAudSis, jrbSegInfo;
    TextView jtvValorIngles, jtvValMatAdd, jtvTotal;
    EditText jetNumCred, jetValorCred;
    CheckBox jcbIngles;
    Button jbtCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jrbEmprendimiento = findViewById(R.id.rbEmprendimiento);
        jrbInteligencia = findViewById(R.id.rbInteligencia);
        jrbAudSis = findViewById(R.id.rbAudSis);
        jrbSegInfo = findViewById(R.id.rbSegInfo);
        jtvValorIngles = findViewById(R.id.tvValorIngles);
        jtvValMatAdd = findViewById(R.id.tvValMatAdd);
        jtvTotal = findViewById(R.id.tvTotal);
        jetNumCred = findViewById(R.id.etNumCred);
        jetValorCred = findViewById(R.id.etValorCred);
        jcbIngles = findViewById(R.id.cbIngles);
        jbtCalcular = findViewById(R.id.btCalcular);

    }

    public void calcularMateriaAdd(View v) {

        if (jrbEmprendimiento.isChecked())
            jtvValMatAdd.setText("500000");
        else if (jrbInteligencia.isChecked())
            jtvValMatAdd.setText("400000");
        else if (jrbAudSis.isChecked())
            jtvValMatAdd.setText("600000");
        else
            jtvValMatAdd.setText("700000");

    } //END calcularMateriaAdd()

    public void calcularIngles(View v) {

        if (jcbIngles.isChecked())
            jtvValorIngles.setText("300000");
        else
            jtvValorIngles.setText("0");

    }//END calcularIngles()


    public void calcularTotal(View v) {

        if (jetNumCred.getText().toString().isEmpty() || jetValorCred.getText().toString().isEmpty()) {

            //Toast.makeText(this, "Debe llenar los campos Numero de creditos y valor del credito", Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Debe llenar los campos Numero de creditos y valor del credito", Toast.LENGTH_LONG).show();
            jetNumCred.requestFocus();

        } else {

            int numCred, valCred, valMatAd, valIngles, total;

            numCred = Integer.parseInt(jetNumCred.getText().toString());
            valCred = Integer.parseInt(jetValorCred.getText().toString());
            valMatAd = Integer.parseInt(jtvValMatAdd.getText().toString());
            valIngles = Integer.parseInt(jtvValorIngles.getText().toString());

            total = (numCred * valCred) + valMatAd + valIngles;

            jtvTotal.setText(String.valueOf(total));


        }//END IF

    }//END CalcularTotal()

}//END MainActivity