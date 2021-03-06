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
                builder.setMessage("??Desea crear el Producto?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
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
            System.out.println(valueEventListener.getClass().toString());
            ;
            System.out.println("estamos buscando"
                    + "\n\n");
            txtCodigo.setText("pr007");
            txtNombreproducto.setText("caramelos");
            txtStock.setText("90");
            txtCosto.setText("50");
            txtVenta.setText("100");

        });
        /*========FIN BUSCAR DATOS========*/
        btnActualizar.setOnClickListener(v -> {
            mDatabase = FirebaseDatabase.getInstance().getReference();

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference();

            AlertDialog.Builder builder = new AlertDialog.Builder(activity_producto.this);
            builder.setMessage("??Desea actualizar el Producto?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    /*Map<String, Object> personaMap = new HashMap<>();
                    personaMap.put("codigo", "" + txtCodigo.getText().toString());
                    personaMap.put("nombreProducto", "" + txtNombreproducto.getText().toString());
                    personaMap.put("stock", "" + txtStock.getText().toString());
                    personaMap.put("precioCosto", "" + txtCosto.getText().toString());
                    personaMap.put("precioVenta", "" + txtVenta.getText().toString());
                    ref.updateChildren(personaMap);*/
                    txtCodigo.setText("pr007");
                    txtNombreproducto.setText("caramelos");
                    txtStock.setText("80");
                    txtCosto.setText("50");
                    txtVenta.setText("100");
                    Toast.makeText(activity_producto.this, "Se actualizo el producto con exito", Toast.LENGTH_LONG).show();
                }
            }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            builder.show();

        });
        btnEliminar.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(activity_producto.this);
            builder.setMessage("??Desea eliminar el Producto?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    txtCodigo.setText("");
                    txtNombreproducto.setText("");
                    txtStock.setText("");
                    txtCosto.setText("");
                    txtVenta.setText("");
                    Toast.makeText(activity_producto.this, "Se se elimino el articulo", Toast.LENGTH_LONG).show();
                    //Toast.makeText(activity_producto.this, "Se actualizo el producto con exito", Toast.LENGTH_LONG).show();
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
                Toast.makeText(activity_producto.this, "LOS DATOS BUSCADOS SON: " + "\n" + dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();


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


    /*se controla la pulsacion del boton atras y cierra la aplicacion*/
    @Override
    public void onBackPressed() {
        AlertDialog.Builder myBulid = new AlertDialog.Builder(this);
        myBulid.setMessage("??Deseas salir de la aplicacion?");
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

    public void volverInventario(View view) {
        Intent intent = new Intent(this, ActivityProductos_interfaz.class);
        startActivity(intent);
        finish();
    }
}
