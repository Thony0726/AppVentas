package com.example.appventas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

public class activity_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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

    public void Inventario(View view) {
        Intent intent = new Intent(this, activity_inventario.class);
        startActivity(intent);
        finish();
    }

    public void productos(View view) {
        Intent intent = new Intent(this, activity_producto.class);
        startActivity(intent);
        finish();
    }

    public void ventas(View view) {
        Intent intent = new Intent(this, activity_ventas.class);
        startActivity(intent);
        finish();
    }

    public void compras(View view) {
        Intent intent = new Intent(this, activity_compras.class);
        startActivity(intent);
        finish();
    }

    public void sesion(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity_menu.this);
        builder.setMessage("¿Desea cerrar sesion?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(activity_menu.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(activity_menu.this, "Cerraste sesion", Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}