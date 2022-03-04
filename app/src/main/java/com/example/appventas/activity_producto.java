package com.example.appventas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class activity_producto extends AppCompatActivity {
    DatabaseReference databaseReference;
    private EditText txtCodigo;
    private EditText txtNombreproducto;
    private EditText txtStock;
    private EditText txtCosto;
    private EditText txtVenta;
    private Button btnGuardar;
    private Button btnActualizar;
    private Button btnBuscar;
    private Button btnEliminar;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        txtCodigo = findViewById(R.id.txtCodigo);
        txtNombreproducto = findViewById(R.id.txtNombreproducto);
        txtStock = findViewById(R.id.txtStock);
        txtCosto = findViewById(R.id.txtCosto);
        txtVenta = findViewById(R.id.txtVenta);



        mDatabase = FirebaseDatabase.getInstance().getReference();
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // String mensaje = meditTextMensaje.getText().toString();
                // mDatabase.child("Productod").setValue(mensaje);
                //coementario



                Map<String, Object> personaMap =new HashMap<>();
                //   personaMap.put( "codigo", ""+meditTextMensaje.getText().toString());
                personaMap.put( "Nombre producto", ""+txtNombreproducto.getText().toString());
                personaMap.put( "Stock", ""+ txtStock.getText().toString());
                personaMap.put( "precio costo", ""+ txtCosto.getText().toString());
                personaMap.put( "precio venta", ""+ txtVenta.getText().toString());

                mDatabase.child("Producto").setValue(personaMap);

            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                // String mensaje = meditTextMensaje.getText().toString();
                // mDatabase.child("Productod").setValue(mensaje);


                Map<String, Object> personaMap =new HashMap<>();
                //   personaMap.put( "codigo", ""+meditTextMensaje.getText().toString());
                personaMap.put( "Nombre producto", ""+txtNombreproducto.getText().toString());
                personaMap.put( "Stock", ""+ txtStock.getText().toString());
                personaMap.put( "precio costo", ""+ txtCosto.getText().toString());
                personaMap.put( "precio venta", ""+ txtVenta.getText().toString());

                mDatabase.child("Producto").setValue(personaMap);
                mDatabase.child("Producto").child("Administrador").push().setValue(personaMap);


            }
        });

    }
    public void aumentar(){


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
