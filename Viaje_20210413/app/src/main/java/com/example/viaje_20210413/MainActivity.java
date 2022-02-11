package com.example.viaje_20210413;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText jetCode, jetDestiny, jetPassengers, jetPrice;
    Button jbtnQuery, jbtnAdd, jbtnModify, jbtnDelete, jbtnClear, jbtnGoToSQLite;
    ClassTravel objTravel;
    ArrayList<ClassTravel> alTravels;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        jetCode =findViewById(R.id.etCode);
        jetDestiny = findViewById(R.id.etDestiny);
        jetPassengers = findViewById(R.id.etPassengers);
        jetPrice = findViewById(R.id.etPrice);
        jbtnAdd = findViewById(R.id.btnAdd);
        jbtnGoToSQLite = findViewById(R.id.btnGoToSQLite);
        jbtnClear = findViewById(R.id.btnClear);
        jbtnQuery = findViewById(R.id.btnQuery);
        jbtnModify = findViewById(R.id.btnModify);
        jbtnDelete = findViewById(R.id.btnDelete);
        alTravels = new ArrayList<ClassTravel>();
        //

    }

    public void addTravel(View v) {
        String code, destiny, passengers, price;
        code = jetCode.getText().toString();
        destiny = jetDestiny.getText().toString();
        passengers = jetPassengers.getText().toString();
        price = jetPrice.getText().toString();

        if (code.isEmpty() || destiny.isEmpty() || passengers.isEmpty() || price.isEmpty()) {
            Toast.makeText(this, "todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            jetCode.requestFocus();
        }else{
            objTravel = new ClassTravel(code, destiny, passengers, price);
            alTravels.add(objTravel);
            Toast.makeText(this, "Succesfully stored!", Toast.LENGTH_SHORT).show();
            clearForm();
        }
    }


    public void clearAll(View v){clearForm();}

    private void clearForm(){

        jetCode.setText("");
        jetPrice.setText("");
        jetPassengers.setText("");
        jetDestiny.setText("");
        jetCode.requestFocus();
    }


    public void queryArrayList() {
        pos = 0;
        int sw = 0;
        String code = jetCode.getText().toString();

        if (code.isEmpty()) {
            Toast.makeText(this, "The code is required", Toast.LENGTH_SHORT).show();
            jetCode.requestFocus();
        } else {
            while (pos < alTravels.size() && sw == 0) {

                objTravel = alTravels.get(pos);

                if (code.equals(objTravel.getCode())) {
                    sw = 1;
                } else {
                    pos += 1;
                }

            }//END while

            if (sw == 0) {
                Toast.makeText(this, "Code NOT found", Toast.LENGTH_SHORT).show();
                pos = -1;
            } else {
                jetDestiny.setText(objTravel.getDestiny());
                jetPassengers.setText((objTravel.getPassengers()));
                jetPrice.setText(objTravel.getPrice());
            }

        }

    }

    public void queryTravels(View v) {
        queryArrayList();
    }


    public void delete(View v){
        if(pos != -1){
            alTravels.remove(pos);
            clearForm();
            Toast.makeText(this, "The travel has been deleted", Toast.LENGTH_SHORT).show();
        }
    }


    public void modify(View v) {
        if (pos != -1) {
            String code, destiny, passengers, price;
            code = jetCode.getText().toString();
            destiny = jetDestiny.getText().toString();
            passengers = jetPassengers.getText().toString();
            price = jetPrice.getText().toString();

            if (code.isEmpty() || destiny.isEmpty() || passengers.isEmpty() || price.isEmpty()) {
                Toast.makeText(this, "All the files are required", Toast.LENGTH_SHORT).show();
                jetCode.requestFocus();
            } else {
                alTravels.get(pos).setCode(code);
                alTravels.get(pos).setDestiny(destiny);
                alTravels.get(pos).setPassengers(passengers);
                alTravels.get(pos).setPrice(price);
                Toast.makeText(this, "Successfully modified!", Toast.LENGTH_SHORT).show();
                clearForm();

            }
        }
    }

    public  void toSQLiteDB(View v){
        Intent intentToSQLite = new Intent(this,toSqlLiteDB.class);
        intentToSQLite.putExtra("lists", alTravels);
        startActivity(intentToSQLite);
    }


}//END MAIN CLASS!!!

