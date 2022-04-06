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

public class ActivityProductos_interfaz extends AppCompatActivity {

    private static final String TAGLOG = "firebase-db";

    RecyclerView recyclerView;
    adpatadorProductos_lista myMdapter;
    ArrayList<Productos> list;
    ImageButton btn_agregarProducto;

    private DatabaseReference dbProductos;
    private ValueEventListener eventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_interfaz);
        // Botton nav
        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationViewProductos);
        btnNav.setSelectedItemId(R.id.itemProductosParametros1);
        btnNav.setOnNavigationItemSelectedListener(navListener);
        /**/
        btn_agregarProducto = (ImageButton)findViewById(R.id.btn_agregarProducto);
        recyclerView = findViewById(R.id.recyclerProductosInterfaz);
        dbProductos = FirebaseDatabase.getInstance().getReference().child("Productos");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myMdapter = new adpatadorProductos_lista(this, list);
        recyclerView.setAdapter(myMdapter);
        btn_agregarProducto.setOnClickListener(view -> {
            startActivity(new Intent(ActivityProductos_interfaz.this, activity_producto.class));
            finish();
        });
        /**/
        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //Opcion 2
                //Productos pro = dataSnapshot.getValue(Productos.class);
                Toast.makeText(ActivityProductos_interfaz.this, "Base de datos actualizada", Toast.LENGTH_SHORT).show();
                list.clear();
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

                Toast.makeText(ActivityProductos_interfaz.this, "Se a actualizado la base de datos", Toast.LENGTH_LONG);
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
                    Toast.makeText(ActivityProductos_interfaz.this, "Se a actualizado la base de datos", Toast.LENGTH_LONG);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Log.d(TAGLOG, "onChildChanged{" + snapshot.getKey() + ": " + snapshot.getValue() + "}");
                    Toast.makeText(ActivityProductos_interfaz.this, "Se a actualizado la base de datos", Toast.LENGTH_LONG);

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                    Log.d(TAGLOG, "onChildRemoved{" + snapshot.getKey() + ": " + snapshot.getValue() + "}");
                    Toast.makeText(ActivityProductos_interfaz.this, "Se han eliminado elementos de la base de datos", Toast.LENGTH_LONG);

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Log.d(TAGLOG, "onChildMoved{" + snapshot.getKey() + ": " + snapshot.getValue() + "}");
                    Toast.makeText(ActivityProductos_interfaz.this, "Se han eliminado elementos de la base de datos", Toast.LENGTH_LONG);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e(TAGLOG, "Error!", error.toException());
                    Toast.makeText(ActivityProductos_interfaz.this, "Se han producido un error en la base de datos", Toast.LENGTH_LONG);

                }
            };
        };
        dbProductos.addValueEventListener(eventListener);
    }


    /**/

    //Listener
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.itemCasa:
                    startActivity(new Intent(ActivityProductos_interfaz.this, MainActivity.class));
                    finish();
                    break;
                case R.id.itemProductosParametros1:

                    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ActivityProductos_interfaz.this, R.style.BottomSheetDialogTheme);
                    /*=========LISTA PRODUCTOS=========*/
                    View bottomsheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.menu_productos, (LinearLayout) findViewById(R.id.bottonSheetContainer));
                    bottomsheetView.findViewById(R.id.btn_ListaProductos).setOnClickListener(v -> {
                        startActivity(new Intent(ActivityProductos_interfaz.this, ActivityProductos_interfaz.class));
                        finish();
                        Toast.makeText(ActivityProductos_interfaz.this, "LISTA DE PRODUCTOS", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    });

                    /*=========AGREGAR PODUCTOS=========*/
                    bottomsheetView.findViewById(R.id.btn_AgregarProductosMenu).setOnClickListener(v -> {
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new fragment_Empleados()).commit();
                        startActivity(new Intent(ActivityProductos_interfaz.this, activity_producto.class));
                        finish();
                        Toast.makeText(ActivityProductos_interfaz.this, "AGREGA UN NUEVO PRODUCTO", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    });

                    /*=========MOSTRAMOS EL DIALOGO=========*/
                    bottomSheetDialog.setContentView(bottomsheetView);
                    bottomSheetDialog.show();
                    break;
                case R.id.itemAjustes:
                    startActivity(new Intent(ActivityProductos_interfaz.this, ActivityAjustes_interfaz.class));
                    finish();
                    Toast.makeText(ActivityProductos_interfaz.this, "ajustes", Toast.LENGTH_SHORT).show();
                    break;

            }
            return true;
        }
    };

    /**/
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


//    //Listener
//    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//            switch (item.getItemId()) {
//                case R.id.item1:
//                    startActivity(new Intent(ActivityProductos_interfaz.this, MainActivity.class));
//                    finish();
//                    break;
//                case R.id.item2:
//
//                    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ActivityProductos_interfaz.this, R.style.BottomSheetDialogTheme);
//                    /*=========CLIENTES=========*/
//                    View bottomsheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.menu_parametros, (LinearLayout) findViewById(R.id.bottonSheetContainer));
//                    bottomsheetView.findViewById(R.id.btn_param_cliente).setOnClickListener(v -> {
//                        startActivity(new Intent(ActivityProductos_interfaz.this, ActivityClientes_interfaz.class));
//                        finish();
//                        Toast.makeText(ActivityProductos_interfaz.this, "Clientes", Toast.LENGTH_SHORT).show();
//                        bottomSheetDialog.dismiss();
//                    });
//
//                    /*=========EMPLEADOS=========*/
//                    bottomsheetView.findViewById(R.id.btn_param_empleados).setOnClickListener(v -> {
////                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new fragment_Empleados()).commit();
//                        startActivity(new Intent(ActivityProductos_interfaz.this, ActivityEmpleados_interfaz.class));
//                        finish();
//                        Toast.makeText(ActivityProductos_interfaz.this, "empleados", Toast.LENGTH_SHORT).show();
//                        bottomSheetDialog.dismiss();
//                    });
//
//                    /*=========PROVEEDORES=========*/
//                    bottomsheetView.findViewById(R.id.btn_param_proveedores).setOnClickListener(v -> {
////                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new fragment_Proveedores()).commit();
//                        startActivity(new Intent(ActivityProductos_interfaz.this, ActivityProveedores_interfaz.class));
//                        finish();
//                        Toast.makeText(ActivityProductos_interfaz.this, "proveedores", Toast.LENGTH_SHORT).show();
//                        bottomSheetDialog.dismiss();
//                    });
//
//                    /*=========PRODUCTOS=========*/
//                    bottomsheetView.findViewById(R.id.btn_param_productos).setOnClickListener(v -> {
////                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new fragment_Productos()).commit();
//                        startActivity(new Intent(ActivityProductos_interfaz.this, ActivityProductos_interfaz.class));
//                        finish();
//                        Toast.makeText(ActivityProductos_interfaz.this, "productos", Toast.LENGTH_SHORT).show();
//                        bottomSheetDialog.dismiss();
//                    });
//
//                    /*=========MOSTRAMOS EL DIALOGO=========*/
//                    bottomSheetDialog.setContentView(bottomsheetView);
//                    bottomSheetDialog.show();
//                    break;
//                case R.id.item3:
//                    startActivity(new Intent(ActivityProductos_interfaz.this, ActivityInventario_interfaz.class));
//                    finish();
//                    Toast.makeText(ActivityProductos_interfaz.this, "inventario", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.item4:
//                    startActivity(new Intent(ActivityProductos_interfaz.this, ActivityFacturacion_interfaz.class));
//                    finish();
//                    Toast.makeText(ActivityProductos_interfaz.this, "facturacion", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.item5:
//
//                    startActivity(new Intent(ActivityProductos_interfaz.this, ActivityAjustes_interfaz.class));
//                    finish();
//                    Toast.makeText(ActivityProductos_interfaz.this, "ajustes", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//            return true;
//        }
//    };
}
