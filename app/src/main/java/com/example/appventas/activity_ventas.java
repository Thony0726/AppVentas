package com.example.appventas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class activity_ventas extends AppCompatActivity {

    private EditText etCodigo, etCantidad;
    private TextView tvProducto, tvStock, tvPrecioVenta, tvTotalPagar;
    private Button btnvender ;
    private ImageButton btnBuscarVenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);
        /*CONCATENACION FRONTEND CON BACKEND*/
        etCodigo = (EditText) findViewById(R.id.etCodigoVentas);
        etCantidad = (EditText) findViewById(R.id.etCantidadVentas);
        tvProducto = (TextView) findViewById(R.id.tvNomProductoVentas);
        tvStock = (TextView) findViewById(R.id.tvProStockVentas);
        tvPrecioVenta = (TextView) findViewById(R.id.tvPrecioVenta);
        tvTotalPagar = (TextView) findViewById(R.id.tvTotalVentas);
        btnvender = (Button) findViewById(R.id.btnVender);
        btnBuscarVenta = (ImageButton) findViewById(R.id.btnBuscarVenta);
        /*==================================*/
        btnvender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcular();
            }
        });

        btnBuscarVenta.setOnClickListener(v -> {
            System.out.println("\n\n");
            String nombreProducto1 = tvProducto.getText().toString();
            System.out.println("estamos buscando"
                    + "\n\n");
            tvProducto.setText("caramelos");
            tvStock.setText("90");
            tvPrecioVenta.setText("100");

        });
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

    /*===============CALCULAR VENTA===============*/
    public void calcular() {
        int stock = Integer.parseInt(tvStock.getText().toString());
        int cantidad = Integer.parseInt(etCantidad.getText().toString());
        double precioVenta = Double.parseDouble(tvPrecioVenta.getText().toString());
        double totalPagar;
        if (cantidad > stock) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Cantidad solictada no disponible")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            etCantidad.setText("");
                        }
                    });
            builder.show();
            Toast.makeText(activity_ventas.this, "Cantidad solictada no disponible", Toast.LENGTH_SHORT).show();
        } else {
            totalPagar = precioVenta * stock;
            tvTotalPagar.setText("$" + totalPagar);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea vender el producto?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            AlertDialog.Builder alrt = new AlertDialog.Builder(activity_ventas.this);
                            alrt.setMessage("Venta realizada con exito" + "\n" + "Total a recibir: $" + totalPagar)
                                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent intent = new Intent(activity_ventas.this, activity_ventas.class);
                                            startActivity(intent);
                                            finish();
                                            Toast.makeText(activity_ventas.this, "Venta realizada con exito!", Toast.LENGTH_LONG).show();
                                        }
                                    });
                            alrt.show();
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            builder.show();

        }
    }

    public void volverInventario(View view) {
        Intent intent = new Intent(this, activity_inventario.class);
        startActivity(intent);
        finish();
    }
}