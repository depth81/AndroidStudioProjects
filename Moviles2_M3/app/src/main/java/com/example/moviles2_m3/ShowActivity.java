package com.example.moviles2_m3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class ShowActivity extends AppCompatActivity {

    EditText jetnombrec, jetartista, jetgenero, jetfecha;
    Button jbtguardar, jbtconsultar, jbtmodificar, jbteliminar, jbtlimpiar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String TAG="MsgPer";
    String collection = "canciones";
    String idcancion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        getSupportActionBar().hide();

        jetnombrec = findViewById(R.id.etnombrec);
        jetartista = findViewById(R.id.etartista);
        jetgenero = findViewById(R.id.etgenero);
        jetfecha = findViewById(R.id.etfecha);
        jbtguardar = findViewById(R.id.btguardar);
        jbtconsultar = findViewById(R.id.btconsultar);
        jbtmodificar = findViewById(R.id.btmodificar);
        jbteliminar = findViewById(R.id.bteliminar);
        jbtlimpiar = findViewById(R.id.btlimpiar);

    }

    public void Guardar(View v){

        String nombrec, artista ,genero, fecha;

        nombrec = jetnombrec.getText().toString();
        artista = jetartista.getText().toString();
        genero = jetgenero.getText().toString();
        fecha = jetfecha.getText().toString();

        if(nombrec.isEmpty() || artista.isEmpty() || genero.isEmpty() || fecha.isEmpty()){
            Toast.makeText(this, "Todos los datos son obligatorios", Toast.LENGTH_SHORT).show();
        }else{
            // Create a new user with a first and last name
            Map<String, Object> user = new HashMap<>();
            user.put("nombrec", nombrec);
            user.put("artista", artista);
            user.put("genero", genero);
            user.put("fecha", fecha);

            // Add a new document with a generated ID
            db.collection(collection)
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(ShowActivity.this, "Canción guardada correctamente", Toast.LENGTH_SHORT).show();
                            limpiar_campos();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error agregando la canción", e);
                        }
                    });
        }

    }//END Guardar()

    public void buscar(View v){

        String nombrec;
        nombrec = jetnombrec.getText().toString();

        if(nombrec.isEmpty()){
            Toast.makeText(this, "No se encuentra la canción", Toast.LENGTH_SHORT).show();
            jetnombrec.requestFocus();
        }else{
            db.collection("canciones")
                    .whereEqualTo("nombrec", jetnombrec.getText().toString())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    idcancion = document.getId();
                                    jetartista.setText(document.getString("artista"));
                                    jetgenero.setText(document.getString("genero"));
                                    jetfecha.setText(document.getString("fecha"));
                                    Toast.makeText(ShowActivity.this, "Canción encontrada", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(ShowActivity.this, "Error buscando la canción", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }//END buscar()

    public void eliminar(View v){

        db.collection(collection).document(idcancion)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ShowActivity.this,"Canción eliminada correctamente...",Toast.LENGTH_SHORT).show();
                        limpiar_campos();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ShowActivity.this,"ERROR elminando la canción...",Toast.LENGTH_SHORT).show();
                    }
                });

    }//END eliminar()

    public void modificar(View v){

        Map<String, Object> uuser = new HashMap<>();
        uuser.put("nombrec", jetnombrec.getText().toString());
        uuser.put("artista", jetartista.getText().toString());
        uuser.put("genero", jetgenero.getText().toString());
        uuser.put("fecha", jetfecha.getText().toString());

        db.collection(collection).document(idcancion)
                .set(uuser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ShowActivity.this,"Canción actualizada correctamente...",Toast.LENGTH_SHORT).show();
                        limpiar_campos();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ShowActivity.this, "ERROR editando la canción", Toast.LENGTH_SHORT).show();
                    }
                });
    }//END modificar()

    public void limpiar(View v){
        limpiar_campos();
    }//END limpiar()

    public void limpiar_campos(){

        jetnombrec.setText("");
        jetartista.setText("");
        jetgenero.setText("");
        jetfecha.setText("");
        jetnombrec.requestFocus();

    }//END limpiar_campos()

    public void Regresar(View b){

        //Cambio de actividad
        Intent intMain = new Intent(this, MainActivity.class);

        //Lanzar la actividad
        startActivity(intMain);

    }

}//END MAIN()