package com.example.viaje_20210413;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class toSqlLiteDB extends AppCompatActivity {

    EditText jetCode2, jetDestiny2, jetPassengers2, jetPrice2;
    Button jbtnSaveToSQLite, jbtnQuerySQLite, jbtnLoadData, jbtnBack2, jbtnClear2;
    ClassTravel objTravel;
    ArrayList<ClassTravel> alTravels;
    String code2, destiny2, passengers2, price2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_sql_lite_d_b);

        getSupportActionBar().hide();

        jetCode2 = findViewById(R.id.etCode2);
        jetDestiny2 = findViewById(R.id.etDestiny2);
        jetPassengers2 = findViewById(R.id.etPassengers2);
        jetPrice2 = findViewById(R.id.etPrice2);
        jbtnSaveToSQLite = findViewById(R.id.btnSaveToSQLite);
        jbtnQuerySQLite = findViewById(R.id.btnQuerySQLite);
        jbtnLoadData = findViewById(R.id.btnLoadData);
        jbtnBack2 = findViewById(R.id.btnBack2);
        jbtnClear2 = findViewById(R.id.btnClear2);

        alTravels =(ArrayList<ClassTravel>)getIntent().getSerializableExtra("lists");

    }


    public void loadData(View v){
        code2 = jetCode2.getText().toString();
        if(getIntent().getSerializableExtra("lists") == null){
            Toast.makeText(this, "The list is empty", Toast.LENGTH_SHORT).show();
        }else{

            int sw=0, pos=0;

            while (pos < alTravels.size() && sw == 0){
                objTravel = alTravels.get(pos);
                if (code2.equals(objTravel.getCode()))
                    sw = 1;
                else
                    pos ++;
            }//END WHILE

            if(sw == 0){
                Toast.makeText(this,"Data not found",Toast.LENGTH_SHORT ).show();
                jetCode2.requestFocus();
            }else{
                jetCode2.setText(objTravel.getCode());
                jetDestiny2.setText(objTravel.getDestiny());
                jetPrice2.setText(objTravel.getPrice());
                jetPassengers2.setText(objTravel.getPassengers());
            }
        }

    }//END loadData()

    public void clearAll(View v){clearForm();}

    private void clearForm(){

        jetCode2.setText("");
        jetPrice2.setText("");
        jetPassengers2.setText("");
        jetDestiny2.setText("");
        jetCode2.requestFocus();
    }

    //***Saving travel***
    public void saveTravelToSQLite(View v){

        mySQLiteOnlineHelper Admin = new mySQLiteOnlineHelper(this, "Travels", null, 1);
        SQLiteDatabase mydb = Admin.getWritableDatabase();

        code2 = jetCode2.getText().toString();
        destiny2 = jetDestiny2.getText().toString();
        passengers2 = jetPassengers2.getText().toString();
        price2 = jetPrice2.getText().toString();

        if(code2.isEmpty() || destiny2.isEmpty() || passengers2.isEmpty() || price2.isEmpty()){
            Toast.makeText(this,"All the fields are required", Toast.LENGTH_SHORT).show();
            jetCode2.requestFocus();
        }else{
            ContentValues data = new ContentValues();
            data.put("code", code2);
            data.put("destiny", destiny2);
            data.put("passengers", passengers2);
            data.put("price", price2);

            long answer = mydb.insert("tblTravels", null, data);

            if (answer>=0){
                Toast.makeText(this, "Succesfully stored in SQLite!", Toast.LENGTH_SHORT).show();
                clearForm();
            }else{
                Toast.makeText(this, "There was an error", Toast.LENGTH_SHORT).show();
            }
        }

        mydb.close();

    }//END


    public void querySQLiteDB(View v){

        code2 = jetCode2.getText().toString();

        if(code2.isEmpty()){
            Toast.makeText(this,"The travel's code is mandatory", Toast.LENGTH_SHORT).show();
            jetCode2.requestFocus();
        }else{
            mySQLiteOnlineHelper Admin = new mySQLiteOnlineHelper(this, "Travels", null, 1);
            SQLiteDatabase db = Admin.getReadableDatabase();
            Cursor myRow = db.rawQuery("select * from tblTravels where code= '" + code2 + "'", null);

            if(myRow.moveToFirst()){
                jetCode2.setText(myRow.getString(0));
                jetDestiny2.setText(myRow.getString(1));
                jetPassengers2.setText(myRow.getString(2));
                jetPrice2.setText(myRow.getString(3));
            }
            else{
                Toast.makeText(this, "This code does not exist.", Toast.LENGTH_SHORT).show();
            }

            db.close();
        }

    }//END


    public void backToMain(View v){
        Intent intMain = new Intent(this, MainActivity.class);
        startActivity(intMain);
    }


    public void clearAll2(View V){clearForm2();}

    public void clearForm2(){

        jetPrice2.setText("");
        jetCode2.setText("");
        jetPassengers2.setText("");
        jetDestiny2.setText("");

    }

}//END MAIN!!!