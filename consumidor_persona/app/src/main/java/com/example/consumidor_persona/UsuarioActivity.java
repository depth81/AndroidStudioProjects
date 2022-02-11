package com.example.consumidor_persona;
//package com.example.appwservicesphp;

import androidx.appcompat.app.AppCompatActivity;



import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
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


import android.os.Bundle;
import android.widget.TextView;

public class UsuarioActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    EditText jetusr,jetnombre,jetcorreo,jetclave;
    Button jbtregistrar,jbtregresar, jbtconsultar, jbteliminar;
    RequestQueue rq;
    JsonRequest jrq;
    int sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        getSupportActionBar().hide();

        jetusr = findViewById(R.id.etuser);
        jetnombre = findViewById(R.id.etnombre);
        jetcorreo = findViewById(R.id.etcorreo);
        jetclave = findViewById(R.id.etclave);
        jbtregistrar = findViewById(R.id.btregistrar);
        jbtregresar = findViewById(R.id.btregresar);
        jbtconsultar = findViewById(R.id.btconsultar);
        jbteliminar = findViewById(R.id.bteliminar);

        rq = Volley.newRequestQueue(this);//conexion a internet

        jbtregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar_usuario();
            }
        });

        jbtconsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultar_usuario();
            }
        });

        jbteliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar_usuario();
            }
        });

        jbtregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresar();
            }
        });

    }//END onCreate


    private void guardar_usuario() {

        String url;

        if(sw==0){

            url = "http://192.168.1.3:8080/usuarios/registrocorreo.php";

        }else{

            url = "http://192.168.1.3:8080/usuarios/actualiza.php";
            sw = 0;

        }

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        jetclave.setText("");
                        jetcorreo.setText("");
                        jetnombre.setText("");
                        jetusr.setText("");
                        jetusr.requestFocus();
                        Toast.makeText(getApplicationContext(), "Registro de usuario realizado correctamente!", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Registro de usuario incorrecto!", Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("usr",jetusr.getText().toString().trim());
                params.put("nombre", jetnombre.getText().toString().trim());
                params.put("correo",jetcorreo.getText().toString().trim());
                params.put("clave",jetclave.getText().toString().trim());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(postRequest);

    }//END guardar_usuario


    public void eliminar_usuario(){

        if(sw == 0){

            Toast.makeText(this,"Por favor consultar el usuario primero", Toast.LENGTH_SHORT).show();
            jetusr.requestFocus();

        }else{

            sw = 0;
            String url = "http://192.168.1.3:8080/usuarios/elimina.php";

            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            jetclave.setText("");
                            jetcorreo.setText("");
                            jetnombre.setText("");
                            jetusr.setText("");
                            jetusr.requestFocus();
                            Toast.makeText(getApplicationContext(), "Registro eliminado!", Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "No se pudo eliminar!", Toast.LENGTH_LONG).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("usr",jetusr.getText().toString().trim());
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(postRequest);

        }//END if-else

    }//END eliminar_usuario


    public void consultar_usuario(){

        String url = "http://192.168.1.3:8080/usuarios/consulta.php?usr="+jetusr.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        rq.add(jrq);

    }//END consultar_usuario

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this,"NO se ha encontrado el usuario "+jetusr.getText().toString(),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResponse(JSONObject response) {

        Toast.makeText(this, "OH MUY BIEN! Se ha encontrado el usuario " + jetusr.getText().toString(), Toast.LENGTH_SHORT).show();

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);//posicion 0 del arreglo....
            jetnombre.setText(jsonObject.optString("nombre"));
            jetcorreo.setText(jsonObject.optString("correo"));
            jetclave.setText(jsonObject.optString("clave"));
            jetnombre.requestFocus();
            sw = 1;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

    }

    public void regresar()
    {
        //Limpiar datos de la actividad que se invoca
        Intent i1=new Intent(getApplicationContext(),MainActivity.class);
        i1.setAction(Intent.ACTION_MAIN);
        i1.addCategory(Intent.CATEGORY_HOME);
        i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i1);
        finish();

    }//END regresar

}//END!!!