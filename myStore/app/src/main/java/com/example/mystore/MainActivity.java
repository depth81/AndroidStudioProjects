package com.example.mystore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    EditText jetcode, jetdescription, jetstock, jetprice;
    Button jbtsave, jbtquery, jbtdelete, jbtclear;
    RequestQueue rq;
    JsonRequest jrq;
    int sw=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        jetcode = findViewById(R.id.etcode);
        jetdescription = findViewById(R.id.etdescription);
        jetstock = findViewById(R.id.etstock);
        jetprice = findViewById(R.id.etprice);
        jbtclear = findViewById(R.id.btclear);
        jbtdelete = findViewById(R.id.btdelete);
        jbtquery = findViewById(R.id.btquery);
        jbtsave = findViewById(R.id.btsave);

        rq = Volley.newRequestQueue(this); //Internet connection

        jbtclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        jbtdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

        jbtquery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });

        jbtsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

    }

    public void query(){

        String url = "http://192.168.1.3:8080/store/query.php?code="+jetcode.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        rq.add(jrq);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this,"The product does not exist",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {

        Toast.makeText(this, "We've got something for you! ", Toast.LENGTH_SHORT).show();

        JSONArray jsonArray = response.optJSONArray("data");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
            jetdescription.setText(jsonObject.optString("description"));
            jetstock.setText(jsonObject.optString("stock"));
            jetcode.setText(jsonObject.optString("code"));
            jetprice.setText(jsonObject.optString("price"));
            jetcode.requestFocus();
            sw = 1;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

    }

    public void save(){

        String url;

        if(sw==0){

            url = "http://192.168.1.3:8080/store/save.php";

        }else{

            url = "http://192.168.1.3:8080/store/update.php";
            sw = 0;

        }

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        jetcode.setText("");
                        jetdescription.setText("");
                        jetprice.setText("");
                        jetstock.setText("");
                        jetcode.requestFocus();
                        Toast.makeText(getApplicationContext(), "the product has been successfully saved!", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "The product could not be registered.", Toast.LENGTH_LONG).show();
                        //Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("code",jetcode.getText().toString().trim());
                params.put("description", jetdescription.getText().toString().trim());
                params.put("stock",jetstock.getText().toString().trim());
                params.put("price",jetprice.getText().toString().trim());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(postRequest);


    }//END save()


    public void delete(){

        if(sw == 0){

            Toast.makeText(this,"Please query the product first.", Toast.LENGTH_SHORT).show();
            jetcode.requestFocus();

        }else{

            sw = 0;
            String url = "http://192.168.1.3:8080/store/delete.php";

            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            jetcode.setText("");
                            jetstock.setText("");
                            jetprice.setText("");
                            jetdescription.setText("");
                            jetcode.requestFocus();
                            Toast.makeText(getApplicationContext(), "Product deleted!", Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Sorry, it could not be deleted!", Toast.LENGTH_LONG).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("code",jetcode.getText().toString().trim());
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(postRequest);

        }//END if-else

    }//END delete()


    public void clear(){

        jetprice.setText("");
        jetstock.setText("");
        jetdescription.setText("");
        jetcode.setText("");
        jetcode.requestFocus();

    }//END clear()




}