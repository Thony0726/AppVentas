package com.example.appventas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class activity_lista_productos extends AppCompatActivity {

    private static final String TAGLOG = "firebase-db";

    RecyclerView recyclerView;
    adpatadorProductos_lista myMdapter;
    ArrayList<Productos> list;

    private DatabaseReference dbProductos;
    private ValueEventListener eventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);

        recyclerView = findViewById(R.id.recyclerProductos);
        dbProductos = FirebaseDatabase.getInstance().getReference().child("Productos");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myMdapter = new adpatadorProductos_lista(this, list);
        recyclerView.setAdapter(myMdapter);


        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //Opcion 2
                //Productos pro = dataSnapshot.getValue(Productos.class);
                try {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Productos pro = dataSnapshot.getValue(Productos.class);
                        list.add(pro);
                    myMdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    e.printStackTrace();
                }

                Toast.makeText(activity_lista_productos.this, "Se a actualizado la base de datos", Toast.LENGTH_LONG);
                Log.e(TAGLOG, "onDataChange:" + snapshot.getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAGLOG, "Error!", databaseError.toException());
            }

            ChildEventListener childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Log.d(TAGLOG, "onChildAdded{" + snapshot.getKey() + ": " + snapshot.getValue() + "}");
                    Toast.makeText(activity_lista_productos.this, "Se a actualizado la base de datos", Toast.LENGTH_LONG);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Log.d(TAGLOG, "onChildChanged{" + snapshot.getKey() + ": " + snapshot.getValue() + "}");
                    Toast.makeText(activity_lista_productos.this, "Se a actualizado la base de datos", Toast.LENGTH_LONG);

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                    Log.d(TAGLOG, "onChildRemoved{" + snapshot.getKey() + ": " + snapshot.getValue() + "}");
                    Toast.makeText(activity_lista_productos.this, "Se han eliminado elementos de la base de datos", Toast.LENGTH_LONG);

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Log.d(TAGLOG, "onChildMoved{" + snapshot.getKey() + ": " + snapshot.getValue() + "}");
                    Toast.makeText(activity_lista_productos.this, "Se han eliminado elementos de la base de datos", Toast.LENGTH_LONG);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e(TAGLOG, "Error!", error.toException());
                    Toast.makeText(activity_lista_productos.this, "Se han producido un error en la base de datos", Toast.LENGTH_LONG);

                }
            };
        };
        dbProductos.addValueEventListener(eventListener);
    }


    /*se controla la pulsacion del boton atras*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea salir de la app?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            builder.show();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void volverInventario(View view) {
        Intent intent = new Intent(this, activity_inventario.class);
        startActivity(intent);
        finish();
    }
}