package com.example.appventas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class activity_inventario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);
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

    public void compras(View view){
        Intent intent = new Intent(this, activity_compras.class);
        startActivity(intent);
        finish();
    }

    public void ventas(View view){
        Intent intent = new Intent(this, activity_ventas.class);
        startActivity(intent);
        finish();
    }

    public void listaProductos(View view){
        Intent intent = new Intent(this, activity_lista_productos.class);
        startActivity(intent);
        finish();
    }

    public void menu(View view){
        Intent intent = new Intent(this, activity_menu.class);
        startActivity(intent);
        finish();
    }
    public void sesion(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity_inventario.this);
        builder.setMessage("¿Desea cerrar sesion?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(activity_inventario.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(activity_inventario.this, "Cerraste sesion", Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}