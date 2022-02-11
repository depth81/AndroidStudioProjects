package com.example.persona_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText jetidentificacion, jetnombre, jetdireccion, jettelefono;
    Button jbtguardar, jbtconsultar, jbtmodificar, jbteliminar, jbtlimpiar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String TAG="MsgPer";
    String collection = "personas";
    String iduser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        jetidentificacion = findViewById(R.id.etidentificacion);
        jetnombre = findViewById(R.id.etnombre);
        jetdireccion = findViewById(R.id.etdireccion);
        jettelefono = findViewById(R.id.ettelefono);
        jbtguardar = findViewById(R.id.btguardar);
        jbtconsultar = findViewById(R.id.btconsultar);
        jbtmodificar = findViewById(R.id.btmodificar);
        jbteliminar = findViewById(R.id.bteliminar);
        jbtlimpiar = findViewById(R.id.btlimpiar);

    }

    public void Guardar(View v){

        String identificacion, nombre, direccion, telefono;

        identificacion = jetidentificacion.getText().toString();
        nombre = jetnombre.getText().toString();
        direccion = jetdireccion.getText().toString();
        telefono = jettelefono.getText().toString();

        if(identificacion.isEmpty() || nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty()){
            Toast.makeText(this, "Todos los datos son obligatorios", Toast.LENGTH_SHORT).show();
        }else{
            // Create a new user with a first and last name
            Map<String, Object> user = new HashMap<>();
            user.put("identificacion", identificacion);
            user.put("nombre", nombre);
            user.put("direccion", direccion);
            user.put("telefono", telefono);

            // Add a new document with a generated ID
            db.collection(collection)
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(MainActivity.this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
                            limpiar_campos();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error agregando los datos", e);
                        }
                    });
        }

    }//END Guardar()


    public void buscar(View v){

        String identificacion;
        identificacion = jetidentificacion.getText().toString();

        if(identificacion.isEmpty()){
            Toast.makeText(this, "No se encuentra el usuario", Toast.LENGTH_SHORT).show();
            jetidentificacion.requestFocus();
        }else{
            db.collection("personas")
                    .whereEqualTo("identificacion", jetidentificacion.getText().toString())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    iduser = document.getId();
                                    jetnombre.setText(document.getString("nombre"));
                                    jetdireccion.setText(document.getString("direccion"));
                                    jettelefono.setText(document.getString("telefono"));
                                    Toast.makeText(MainActivity.this, "Registro encontrado", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Error buscando el registro", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }//END buscar()

    public void eliminar(View v){

        db.collection(collection).document(iduser)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this,"Usuario eliminado correctamente...",Toast.LENGTH_SHORT).show();
                        limpiar_campos();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,"ERROR elminando el usuario...",Toast.LENGTH_SHORT).show();
                    }
                });

    }//END eliminar()


    public void modificar(View v){

        Map<String, Object> uuser = new HashMap<>();
        uuser.put("identificacion", jetidentificacion.getText().toString());
        uuser.put("nombre", jetnombre.getText().toString());
        uuser.put("direccion", jetdireccion.getText().toString());
        uuser.put("telefono", jettelefono.getText().toString());

        db.collection(collection).document(iduser)
                .set(uuser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this,"Usuario actualizado correctamente...",Toast.LENGTH_SHORT).show();
                        limpiar_campos();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "ERROR editando la persona", Toast.LENGTH_SHORT).show();
                    }
                });
    }//END modificar()


    public void limpiar(View v){
        limpiar_campos();
    }//END limpiar()

    public void limpiar_campos(){

        jetidentificacion.setText("");
        jettelefono.setText("");
        jetdireccion.setText("");
        jetnombre.setText("");
        jetidentificacion.requestFocus();

    }//END limpiar_campos()





}//END MAIN