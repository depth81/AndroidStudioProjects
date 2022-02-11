package com.example.consumidor_persona;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SesionFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

    //Definir los objetos que se requieren para la conexion

    RequestQueue rq;
    JsonRequest jrq;
    EditText jetcorreo, jetclave;
    Button jbtingresar;
    TextView jtvregistrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sesion, container, false);
        View vista = inflater.inflate(R.layout.fragment_sesion,container,false);
        jetcorreo = vista.findViewById(R.id.etcorreo);
        jetclave = vista.findViewById(R.id.etclave);
        jbtingresar = vista.findViewById(R.id.btingresar);

        rq = Volley.newRequestQueue(getContext());//conexion a internet
        jbtingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciar_sesion();
            }
        });
        return vista;
    }

    public void iniciar_sesion()
    {
        String url = "http://192.168.1.3:8080/usuarios/sesion.php?correo="+jetcorreo.getText().toString()+"&clave="+jetclave.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        rq.add(jrq);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No se ha encontrado el correo "+jetcorreo.getText().toString(),Toast.LENGTH_SHORT).show();
        //Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(), "OH MUY BIEN! Se ha encontrado el correo " + jetcorreo.getText().toString(), Toast.LENGTH_SHORT).show();

        ClsUsuarios usuario = new ClsUsuarios();

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        Toast.makeText(getContext(), "Se ha encontrado el correo " + jetcorreo.getText().toString(), Toast.LENGTH_SHORT).show();

        try {
            jsonObject = jsonArray.getJSONObject(0);//posicion 0 del arreglo....
            usuario.setUsr(jsonObject.optString("usr"));
            usuario.setNombre(jsonObject.optString("nombre"));
            usuario.setCorreo(jsonObject.optString("correo"));
            usuario.setClave(jsonObject.optString("clave"));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        Intent IntDatos = new Intent(getContext(),UsuarioActivity.class);
        //IntDatos.putExtra(UsuarioActivity.nombre,usuario.getNombre());
        startActivity(IntDatos);

    }
}