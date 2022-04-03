package com.example.appventas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityRegistro_interfaz extends AppCompatActivity {

    private EditText etClave, etUsuario;
    private CheckBox checkBox;
    Button btnRegistrar, btnVolverLogin;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_interfaz);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        btnVolverLogin = (Button) findViewById(R.id.btnVolverLogin);
        etClave = (EditText) findViewById(R.id.etClaveRegistro);
        etUsuario = (EditText) findViewById(R.id.etUsuarioRegistro);
        checkBox = (CheckBox) findViewById(R.id.checkBoxRegistrar);
        /*FIREBASE AUTH*/
        mAuth = FirebaseAuth.getInstance();
        /*REGISTRAR*/
        btnRegistrar.setOnClickListener(view -> {
            crearUsuario();
        });

        /*MOSTRAR CLAVE*/
        verClave();
        /*VOLVER AL LOGIN*/
        btnVolverLogin.setOnClickListener(v -> {
            startActivity(new Intent(ActivityRegistro_interfaz.this, ActivityLogin_interfaz.class));
            finish();
        });
    }

    /*MOSTRAR CLAVE*/
    public void verClave() {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // depende del estado que se encuentre el checkbox para que funcione el metodo checked.
                if (!isChecked) {  //mostrar contrasenia
                    etClave.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else { // ocultar contrasenia
                    etClave.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }

    /*METODO CREAR USUARIO*/
    protected void crearUsuario() {
        String email = etUsuario.getText().toString();
        String clave = etClave.getText().toString();

        if (TextUtils.isEmpty(email)) {
            etUsuario.setError("El usuario no debe estar vacio");
            etUsuario.requestFocus();
        } else if (TextUtils.isEmpty(clave)) {
            etClave.setError("La contraseña no debe estar vacia");
            etClave.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email, clave).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(ActivityRegistro_interfaz.this, "usuario creado con exito", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ActivityRegistro_interfaz.this, ActivityLogin_interfaz.class));
                        finish();
                    } else {
                        Toast.makeText(ActivityRegistro_interfaz.this, "Registro fallido: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        etUsuario.setText("");
                        etClave.setText("");
                    }
                }
            });
        }
    }

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