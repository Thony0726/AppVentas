package com.example.appventas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class activity_persona extends AppCompatActivity {

    private TextView tvCorreo;
    private EditText txtcedula;
    private Button btnactualizar;
    private Spinner spinnerPais;
    private EditText txtnombre;
    private EditText txtprovincia;
    private DatabaseReference mDatabase;
    private RadioButton rdbtnHombre, rdbtnMujer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona);
        tvCorreo = (TextView) findViewById(R.id.tvCorreo);
        Bundle b = getIntent().getExtras();
        String correoR = b.getString("correo");
        tvCorreo.setText("CORREO: " + correoR);

        spinnerPais = (Spinner) findViewById(R.id.spinner2);

        String[] respuesta = {"Ecuador", "Venezuela", "Colombia"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, respuesta);
        spinnerPais.setAdapter(adapter);

        txtcedula = (EditText) findViewById(R.id.txtCedula);

        btnactualizar = (Button) findViewById(R.id.btnactualizar);
        txtnombre = (EditText) findViewById(R.id.txtnombre);
        txtprovincia = (EditText) findViewById(R.id.txtprovincia);
        rdbtnHombre = (RadioButton) findViewById(R.id.rdbtnHombre);
        rdbtnMujer = (RadioButton) findViewById(R.id.rdbtnMujer);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        btnactualizar.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity_persona.this);
            builder.setMessage("¿Desea actualizar el Usuario?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    /**/
                    String sexo = null;
                    if (rdbtnHombre.isSelected()) {
                        sexo = "Hombre";
                    } else if (rdbtnMujer.isSelected()) {
                        sexo = "Mujer";
                    }
                    /**/
                    String seleccionado = spinnerPais.getSelectedItem().toString();
                    String pais = null;
                    if (seleccionado.equals("Venezuela")) {
                        pais = "Venezuela";
                    } else if (seleccionado.equals("Ecuador")) {
                        pais = "Ecuador";
                    } else if (seleccionado.equals("Colombia")) {
                        pais = "Colombia";
                    }
                    //mDatabase.child("Usuarios").child("usuUsuario").setValue(txtnombre.getText().toString());
                    /*
                    Map<String, Object> personaMap = new HashMap<>();
                    personaMap.put("usuCedula", txtcedula.getText().toString());
                    personaMap.put("usuNombre", txtnombre.getText().toString());
                    personaMap.put("usuSexo", sexo);
                    personaMap.put("usuPais", pais);
                    personaMap.put("usuProvincia", txtprovincia.getText().toString());
                    mDatabase = FirebaseDatabase.getInstance().getReference("Usuarios");
                    mDatabase.updateChildren(personaMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(activity_persona.this, "Se actualizo el perfil con exito", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(activity_persona.this, "No se pudo actualizar el perfil", Toast.LENGTH_LONG).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                    /*ref.updateChildren(personaMap);
                    ref.updateChildren(personaMap);*/
                }
            }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            builder.show();
        });
    }

    /*se controla la pulsacion del boton atras*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea salir de la app?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void volverInicio(View view) {
        Intent intent = new Intent(this, activity_menu.class);
        startActivity(intent);
        finish();
    }


}
