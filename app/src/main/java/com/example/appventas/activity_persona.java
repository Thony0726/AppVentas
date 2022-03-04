package com.example.appventas;

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
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Map;

public class activity_persona extends AppCompatActivity {

    private TextView tvCorreo  ;
    private EditText txtcedula;
    private Button btnactualizar;
    private Spinner spinner2;
    private EditText txtnombre;
    private EditText txtprovincia;
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona);
        tvCorreo = (TextView)findViewById(R.id.tvCorreo);
        Bundle b = getIntent().getExtras();
        String correoR= b.getString("correo");
        tvCorreo.setText("CORREO: "+correoR);

        spinner2 = (Spinner) findViewById(R.id.spinner2);

        String[] respuesta = {"Ecuador", "Venezuela", "Colombia"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, respuesta);
        spinner2.setAdapter(adapter);

        txtcedula = (EditText) findViewById(R.id.txtCedula);

        btnactualizar = (Button) findViewById(R.id.btnactualizar);
        txtnombre = (EditText) findViewById(R.id.txtnombre);
        txtprovincia = (EditText) findViewById(R.id.txtprovincia);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // String mensaje = mEditTextMensaje.getText().toString();
                //  mDatabase.child("persona").setValue(mensaje);
                String cedula = txtcedula.getText().toString();
                mDatabase.child("persona").setValue(cedula);
                Map<String, Object> personaMap = new HashMap<>();
                personaMap.put("cedula", "" + txtcedula.getText().toString());
                personaMap.put("Nombre", "" + txtnombre.getText().toString());
                personaMap.put("provincia", "" + txtprovincia.getText().toString());
                mDatabase.child("usuarios").setValue(personaMap);
                mDatabase.child("Usuario").child("Administrador").push().setValue(personaMap);

            }
        });
    }

    public void mostrar (View view){
        String seleccionado = spinner2.getSelectedItem().toString();
        if (seleccionado.equals("Ecuador ")) {

            // tv_respuesta.setText("Si eres inteligente ");
        } else if (seleccionado.equals("Venezuela")) {
            // tv_respuesta.setText("Si eres Experto ");


        } else if (seleccionado.equals("Colombia")) {
            //  tv_respuesta.setText("Si eres Sabio ");

        }

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

    public void volverInicio(View view) {
        Intent intent = new Intent(this, activity_menu.class);
        startActivity(intent);
        finish();
    }



}
