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

public class ActivityLogin_interfaz extends AppCompatActivity {

    private EditText etClave, etUsuario;
    private CheckBox checkBox;
    Button btnIngresar, btnIrRegistro;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_interfaz);
        etClave = (EditText) findViewById(R.id.etClaveLogin);
        etUsuario = (EditText) findViewById(R.id.etUsuarioLogin);
        checkBox = (CheckBox) findViewById(R.id.checkBoxLogin);
        btnIngresar = (Button) findViewById(R.id.btnIngresarLogin);
        btnIrRegistro = (Button) findViewById(R.id.btnIrRegistro);
        /*FIREBASE AUTH*/
        mAuth = FirebaseAuth.getInstance();
        /**/
        verClave();
        btnIngresar.setOnClickListener(v -> {
            loginUser();
        });
        btnIrRegistro.setOnClickListener(v -> {
            startActivity(new Intent(ActivityLogin_interfaz.this, ActivityRegistro_interfaz.class));
            finish();
        });
    }

    /*VERIFCAMOS SI EL USUARIO ESTA EN NUESTRA BASE DE DATOS*/
    private void loginUser() {
        String email = etUsuario.getText().toString();
        String clave = etClave.getText().toString();

        if (TextUtils.isEmpty(email)) {
            etUsuario.setError("El usuario no debe estar vacio");
            etUsuario.requestFocus();
        } else if (TextUtils.isEmpty(clave)) {
            etClave.setError("La contraseña no debe estar vacia");
            etClave.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email, clave).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(ActivityLogin_interfaz.this, "Inciaste sesion como:"+"\n" + email, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ActivityLogin_interfaz.this, MainActivity.class));
                        finish();
                    } else {
                        etUsuario.setText("");
                        etClave.setText("");
                        Toast.makeText(ActivityLogin_interfaz.this, "Error de inicio de sesion:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    /*MOSTRAR CLAVE*/
    public void verClave() {
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