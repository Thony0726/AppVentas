package com.example.appventas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    //========================================================================================================================
    //variables
    private EditText etClave, etUsuario;
    private CheckBox checkBox;
    private Spinner spinner1;
    private ProgressDialog mensajeProgreso;
    FirebaseAuth mAuth;
    //========================================================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etClave = (EditText) findViewById(R.id.etClave);
        etUsuario = (EditText) findViewById(R.id.etUser);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        spinner1 = (Spinner) findViewById(R.id.spinner);

        //opciones del spinner
        String[] opciones = {"Persona", "Producto", "Inventario"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        spinner1.setAdapter(adapter);

        //progresBar
        mensajeProgreso = new ProgressDialog(MainActivity.this);

        //verClave
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // depende del estado que se encuentre el checlox para que funcione el metodo checked.
                if (!isChecked) {  //mostrar contrasenia
                    etClave.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else { // ocultar contrasenia
                    etClave.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        //Firebase
        mAuth = FirebaseAuth.getInstance();
    }

    //========================================================================================================================
    //validamos el usario y la contrasenia
    private void validar(View view) {
        String correo = etUsuario.getText().toString();
        String clave = etClave.getText().toString();
        if (correo.isEmpty() || !correo.contains("@")) {
            //Mensaje error
            mostrarError(etUsuario,"Usuario no valido");
        } else if (clave.isEmpty() || clave.length() < 7) {
            //Mensaje error
            mostrarError(etClave,"Clave no valida");
        } else {
            //Parte para ingresar a la otra interfaz
            mensajeProgreso.setTitle("Login");
            mensajeProgreso.setMessage("Iniciando sesion, espera un momento");
            mensajeProgreso.setCanceledOnTouchOutside(false);
            mensajeProgreso.show();
            mAuth.signInWithEmailAndPassword(correo, clave).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        mensajeProgreso.dismiss();
                        Intent intent = new Intent(MainActivity.this, activity_menu.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "No se pudo acceder con ese usuario y contraseña", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
    }


    //========================================================================================================================
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

    //========================================================================================================================
    public void registro(View view) {
        Intent intent = new Intent(this, activity_registro.class);
        startActivity(intent);
        finish();
    }

    //========================================================================================================================
    private void mostrarError(EditText input, String mensaje){
        input.setError(mensaje);
        input.requestFocus();
    }

}