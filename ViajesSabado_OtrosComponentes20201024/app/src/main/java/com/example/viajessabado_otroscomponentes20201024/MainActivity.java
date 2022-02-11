package com.example.viajessabado_otroscomponentes20201024;

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

    RadioButton jrbcartagena, jrbcancun, jrbleticia, jrbdecameron, jrbhilton, jrbsolcaribe;
    TextView jtvciudad, jtvhotel,jtvautomovil, jtvtotal;
    EditText jetcantidad;
    CheckBox jcbautomovil;
    Button jbtcalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        jrbcartagena = findViewById(R.id.rbcartagena);
        jrbcancun = findViewById(R.id.rbcancun);
        jrbleticia = findViewById(R.id.rbleticia);
        jtvciudad = findViewById(R.id.tvciudad);
        jrbdecameron = findViewById(R.id.rbdecameron);
        jrbhilton = findViewById(R.id.rbhilton);
        jrbsolcaribe = findViewById(R.id.rbsolcaribe);
        jtvhotel = findViewById(R.id.tvhotel);
        jtvautomovil = findViewById(R.id.tvautomovil);
        jetcantidad = findViewById(R.id.etcantidad);
        jcbautomovil = findViewById(R.id.cbautomovil);
        jtvtotal = findViewById(R.id.tvtotal);
        jbtcalcular = findViewById(R.id.btcalcular);

    }

    public void calcularCiudad(View v){

      if(jrbcartagena.isChecked())
          jtvciudad.setText("600000");
      else
          if(jrbcancun.isChecked())
              jtvciudad.setText("1500000");
          else
              jtvciudad.setText("1100000");

    }//END calcularCiudad

    public void calcularHotel(View v){

        if(jrbdecameron.isChecked())
            jtvhotel.setText("500000");
        else
            if(jrbhilton.isChecked())
                jtvhotel.setText("900000");
            else
                jtvhotel.setText("700000");

    }//END calcularHotel

    public  void calcularAutomovil(View v){

        if(jcbautomovil.isChecked())
            jtvautomovil.setText("500000");
        else
            jtvautomovil.setText("0");

    }//END calcularAutomovil

    public void calcularTotal(View v){

        if(jetcantidad.getText().toString().isEmpty())
        {
            Toast.makeText(this, "La cantidad de personas no puede ser cero", Toast.LENGTH_LONG).show();
            jetcantidad.requestFocus();
        }
        else{

            int ciudad, hotel, automovil, cantidad, total;

            ciudad = Integer.parseInt(jtvciudad.getText().toString());
            hotel = Integer.parseInt(jtvhotel.getText().toString());
            automovil = Integer.parseInt(jtvautomovil.getText().toString());
            cantidad = Integer.parseInt(jetcantidad.getText().toString());

            total = (ciudad + hotel) *cantidad + automovil;

            //Mostrar datos en tvtotal pero antes se debe convertir total a string
            jtvtotal.setText(String.valueOf(total));

        }//END if (validacion de cantidad de personas)

    }//END calcularTotal




}//END MainActivity