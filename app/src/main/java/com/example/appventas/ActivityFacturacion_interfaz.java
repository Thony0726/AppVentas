package com.example.appventas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class ActivityFacturacion_interfaz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facturacion_interfaz);
// Botton nav
        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationViewFacturacion);
        btnNav.setOnNavigationItemSelectedListener(navListener);
    }

    //Listener
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.item1:
                    finish();
                    break;
                case R.id.item2:

                    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ActivityFacturacion_interfaz.this, R.style.BottomSheetDialogTheme);
                    /*=========CLIENTES=========*/
                    View bottomsheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.menu_parametros, (LinearLayout) findViewById(R.id.bottonSheetContainer));
                    bottomsheetView.findViewById(R.id.btn_param_cliente).setOnClickListener(v -> {
                        startActivity(new Intent(ActivityFacturacion_interfaz.this, ActivityClientes_interfaz.class));
                        finish();
                        Toast.makeText(ActivityFacturacion_interfaz.this, "Clientes", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    });

                    /*=========EMPLEADOS=========*/
                    bottomsheetView.findViewById(R.id.btn_param_empleados).setOnClickListener(v -> {
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new fragment_Empleados()).commit();
                        startActivity(new Intent(ActivityFacturacion_interfaz.this, ActivityEmpleados_interfaz.class));
                        finish();
                        Toast.makeText(ActivityFacturacion_interfaz.this, "empleados", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    });

                    /*=========PROVEEDORES=========*/
                    bottomsheetView.findViewById(R.id.btn_param_proveedores).setOnClickListener(v -> {
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new fragment_Proveedores()).commit();
                        startActivity(new Intent(ActivityFacturacion_interfaz.this, ActivityProveedores_interfaz.class));
                        finish();
                        Toast.makeText(ActivityFacturacion_interfaz.this, "proveedores", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    });

                    /*=========PRODUCTOS=========*/
                    bottomsheetView.findViewById(R.id.btn_param_productos).setOnClickListener(v -> {
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new fragment_Productos()).commit();
                        startActivity(new Intent(ActivityFacturacion_interfaz.this, ActivityProductos_interfaz.class));
                        finish();
                        Toast.makeText(ActivityFacturacion_interfaz.this, "productos", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    });

                    /*=========MOSTRAMOS EL DIALOGO=========*/
                    bottomSheetDialog.setContentView(bottomsheetView);
                    bottomSheetDialog.show();
                    break;
                case R.id.item3:
                    startActivity(new Intent(ActivityFacturacion_interfaz.this, ActivityInventario_interfaz.class));
                    finish();
                    Toast.makeText(ActivityFacturacion_interfaz.this, "inventario", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item4:
                    startActivity(new Intent(ActivityFacturacion_interfaz.this, ActivityFacturacion_interfaz.class));
                    finish();
                    Toast.makeText(ActivityFacturacion_interfaz.this, "facturacion", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item5:
                    startActivity(new Intent(ActivityFacturacion_interfaz.this, ActivityAjustes_interfaz.class));
                    finish();
                    Toast.makeText(ActivityFacturacion_interfaz.this, "ajustes", Toast.LENGTH_SHORT).show();
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