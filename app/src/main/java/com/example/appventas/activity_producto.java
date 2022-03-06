package com.example.appventas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class activity_producto extends AppCompatActivity {


    private static final String TAGLOG = "firebase-db";

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
        /*=============BASE DE DATOS=============*/

        /**/
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        txtCodigo = findViewById(R.id.txtCodigo);
        txtNombreproducto = findViewById(R.id.txtNombreproducto);
        txtStock = findViewById(R.id.txtStock);
        txtCosto = findViewById(R.id.txtCosto);
        txtVenta = findViewById(R.id.txtVenta);

        /*=======BASE DE DATOS======*/
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //Buscar por nombreProducto;

        /*=============*/


        btnGuardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity_producto.this);
                builder.setMessage("¿Desea crear el Producto?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Map<String, Object> personaMap = new HashMap<>();
                        personaMap.put("codigo", "" + txtCodigo.getText().toString());
                        personaMap.put("nombreProducto", "" + txtNombreproducto.getText().toString());
                        personaMap.put("stock", "" + txtStock.getText().toString());
                        personaMap.put("precioCosto", "" + txtCosto.getText().toString());
                        personaMap.put("precioVenta", "" + txtVenta.getText().toString());
                        mDatabase.child("Productos").push().setValue(personaMap);
                        Toast.makeText(activity_producto.this, "Se creo con exito el producto", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
        /*========BUSCAR DATOS========*/
        btnBuscar.setOnClickListener(v -> {
            System.out.println("\n\n");
            String nombreProducto1 = txtNombreproducto.getText().toString();
            Query query3 = FirebaseDatabase.getInstance().getReference("Productos")
                    .orderByChild("nombreProducto")
                    .equalTo(nombreProducto1);
            query3.addListenerForSingleValueEvent(valueEventListener);
            System.out.println(valueEventListener.getClass().toString());;
            System.out.println("estamos buscando"
                    + "\n\n");

        });
        /*========FIN BUSCAR DATOS========*/
        mDatabase = FirebaseDatabase.getInstance().getReference();
        btnActualizar.setOnClickListener(v->{
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference();

            AlertDialog.Builder builder = new AlertDialog.Builder(activity_producto.this);
            builder.setMessage("¿Desea actualizar el Producto?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Map<String, Object> personaMap = new HashMap<>();
                    personaMap.put("codigo", "" + txtCodigo.getText().toString());
                    personaMap.put("nombreProducto", "" + txtNombreproducto.getText().toString());
                    personaMap.put("stock", "" + txtStock.getText().toString());
                    personaMap.put("precioCosto", "" + txtCosto.getText().toString());
                    personaMap.put("precioVenta", "" + txtVenta.getText().toString());
                    ref.updateChildren(personaMap);
                    Toast.makeText(activity_producto.this, "Se actualizo el producto con exito", Toast.LENGTH_LONG).show();
                }
            }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            builder.show();
        });
    }

    /*========BUSCAR DATOS========*/

    /*========BUSCAR DATOS========*/



    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            if (dataSnapshot.exists()) {
                String cod = dataSnapshot.child("codigo").getValue().toString();
                txtCodigo.setText(cod);
                Log.e(TAGLOG, "onDataChange:" + dataSnapshot.getValue().toString());
                Toast.makeText(activity_producto.this, "Datos encontrados", Toast.LENGTH_SHORT).show();
                Toast.makeText(activity_producto.this, "LOS DATOS BUSCADOS SON: "+"\n"+dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();


            } else {
                Toast.makeText(activity_producto.this, "No se encontraton los datos solicitados", Toast.LENGTH_SHORT).show();
            }
        }
    /*========BUSCAR DATOS========*/

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.e(TAGLOG, "Error:" + databaseError);
            Toast.makeText(activity_producto.this, "ERROR EN LA BASE DE DATOS", Toast.LENGTH_SHORT).show();

        }
    };


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
