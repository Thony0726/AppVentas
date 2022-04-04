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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityEmpleados_interfaz extends AppCompatActivity {

    private static final String TAGLOG = "firebase-db";

    RecyclerView recyclerView;
    adpatadorEmpleados_lista myMdapter;
    ArrayList<Empleados> list;
    ImageButton btn_agregarEmpleados;

    private DatabaseReference dbEmpleados;
    private ValueEventListener eventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados_interfaz);
        dbEmpleados = FirebaseDatabase.getInstance().getReference().child("Empleados");
        // Botton nav
        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationViewEmpleados);
        btnNav.setSelectedItemId(R.id.item2);
        btnNav.setOnNavigationItemSelectedListener(navListener);

        /**/
        btn_agregarEmpleados = (ImageButton)findViewById(R.id.btn_agregarEmpleados);
        recyclerView = findViewById(R.id.rvEmpleados1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myMdapter = new adpatadorEmpleados_lista(this, list);
        recyclerView.setAdapter(myMdapter);
        btn_agregarEmpleados.setOnClickListener(view -> {
            startActivity(new Intent(ActivityEmpleados_interfaz.this, activity_producto.class));
            finish();
        });
        /**/
        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //Opcion 2
                //Productos pro = dataSnapshot.getValue(Productos.class);
                try {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Empleados pro = dataSnapshot.getValue(Empleados.class);
                        list.add(pro);
                        myMdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    e.printStackTrace();
                }

                Toast.makeText(ActivityEmpleados_interfaz.this, "Se a actualizado la base de datos", Toast.LENGTH_LONG);
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
                    Toast.makeText(ActivityEmpleados_interfaz.this, "Se a actualizado la base de datos", Toast.LENGTH_LONG);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Log.d(TAGLOG, "onChildChanged{" + snapshot.getKey() + ": " + snapshot.getValue() + "}");
                    Toast.makeText(ActivityEmpleados_interfaz.this, "Se a actualizado la base de datos", Toast.LENGTH_LONG);

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                    Log.d(TAGLOG, "onChildRemoved{" + snapshot.getKey() + ": " + snapshot.getValue() + "}");
                    Toast.makeText(ActivityEmpleados_interfaz.this, "Se han eliminado elementos de la base de datos", Toast.LENGTH_LONG);

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Log.d(TAGLOG, "onChildMoved{" + snapshot.getKey() + ": " + snapshot.getValue() + "}");
                    Toast.makeText(ActivityEmpleados_interfaz.this, "Se han eliminado elementos de la base de datos", Toast.LENGTH_LONG);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e(TAGLOG, "Error!", error.toException());
                    Toast.makeText(ActivityEmpleados_interfaz.this, "Se han producido un error en la base de datos", Toast.LENGTH_LONG);

                }
            };
        };
        dbEmpleados.addValueEventListener(eventListener);
    }
    //Listener
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.item1:
                    startActivity(new Intent(ActivityEmpleados_interfaz.this, MainActivity.class));
                    finish();
                    break;
                case R.id.item2:

                    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ActivityEmpleados_interfaz.this, R.style.BottomSheetDialogTheme);
                    /*=========CLIENTES=========*/
                    View bottomsheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.menu_parametros, (LinearLayout) findViewById(R.id.bottonSheetContainer));
                    bottomsheetView.findViewById(R.id.btn_param_cliente).setOnClickListener(v -> {
                        startActivity(new Intent(ActivityEmpleados_interfaz.this, ActivityClientes_interfaz.class));
                        finish();
                        Toast.makeText(ActivityEmpleados_interfaz.this, "Clientes", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    });

                    /*=========EMPLEADOS=========*/
                    bottomsheetView.findViewById(R.id.btn_param_empleados).setOnClickListener(v -> {
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new fragment_Empleados()).commit();
                        startActivity(new Intent(ActivityEmpleados_interfaz.this, ActivityEmpleados_interfaz.class));
                        finish();
                        Toast.makeText(ActivityEmpleados_interfaz.this, "empleados", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    });

                    /*=========PROVEEDORES=========*/
                    bottomsheetView.findViewById(R.id.btn_param_proveedores).setOnClickListener(v -> {
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new fragment_Proveedores()).commit();
                        startActivity(new Intent(ActivityEmpleados_interfaz.this, ActivityProveedores_interfaz.class));
                        finish();
                        Toast.makeText(ActivityEmpleados_interfaz.this, "proveedores", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    });

                    /*=========PRODUCTOS=========*/
                    bottomsheetView.findViewById(R.id.btn_param_productos).setOnClickListener(v -> {
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new fragment_Productos()).commit();
                        startActivity(new Intent(ActivityEmpleados_interfaz.this, ActivityProductos_interfaz.class));
                        finish();
                        Toast.makeText(ActivityEmpleados_interfaz.this, "productos", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    });

                    /*=========MOSTRAMOS EL DIALOGO=========*/
                    bottomSheetDialog.setContentView(bottomsheetView);
                    bottomSheetDialog.show();
                    break;
                case R.id.item3:
                    startActivity(new Intent(ActivityEmpleados_interfaz.this, ActivityInventario_interfaz.class));
                    finish();
                    Toast.makeText(ActivityEmpleados_interfaz.this, "inventario", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item4:
                    startActivity(new Intent(ActivityEmpleados_interfaz.this, ActivityFacturacion_interfaz.class));
                    finish();
                    Toast.makeText(ActivityEmpleados_interfaz.this, "facturacion", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item5:

                    startActivity(new Intent(ActivityEmpleados_interfaz.this, ActivityAjustes_interfaz.class));
                    finish();
                    Toast.makeText(ActivityEmpleados_interfaz.this, "ajustes", Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }
    };

    /*se controla la pulsacion del boton atras y cierra la aplicacion*/
    @Override
    public void onBackPressed() {
        AlertDialog.Builder myBulid = new AlertDialog.Builder(this);
        myBulid.setMessage("¿Deseas salir de la aplicacion?");
        myBulid.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        myBulid.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = myBulid.create();
        dialog.show();
    }
}