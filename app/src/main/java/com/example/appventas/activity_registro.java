package com.example.appventas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class activity_registro extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private EditText etUser;
    private EditText etPassword;
    private Button btnRegistrar;
    private CheckBox checkBox;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        progressDialog = new ProgressDialog(this);

        //Instanciamos igualmente
        auth = FirebaseAuth.getInstance();

        etUser = (EditText) findViewById(R.id.etUser);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // depende del estado que se encuentre el checlox para que funcione el metodo checked.
                if (!isChecked) {  //mostrar contrasenia
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else { // ocultar contrasenia
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userE = etUser.getText().toString();
                String passE = etPassword.getText().toString();
                //Ahora validamos por si uno de los campos esta vacío
                if (TextUtils.isEmpty(userE)) {
                    //por si falta correo
                    Toast.makeText(activity_registro.this, "Inserte correo", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passE)) {
                    //por si falta password
                    Toast.makeText(activity_registro.this, "Inserte contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog.setMessage("En proceso");
                progressDialog.show();

                //Ahora usamos el metodo
                auth.createUserWithEmailAndPassword(userE, passE).
                        //Le pasamos la clase registro
                                addOnCompleteListener(activity_registro.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //Task=Tareas devuelve si la tarea si se cumple
                                //En este caso si se cumplio
                                Toast.makeText(activity_registro.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                                //Si no logra registrarse
                                if (!task.isSuccessful()) {
                                    Toast.makeText(activity_registro.this, "Usuario no se ha podido registrar", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                Intent i = new Intent(activity_registro.this, activity_persona.class);
                                i.putExtra("correo",etUser.getText().toString());
                                startActivity(i);
                                finish();
                            }
                        });
            }
        });

    }


    public void volverInicio(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}