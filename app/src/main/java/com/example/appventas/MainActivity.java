package com.example.appventas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    //========================================================================================================================
    //variables
    private static final String TAGLOG = "firebase-db";
    private EditText etClave, etUsuario;
    private CheckBox checkBox;
    private Spinner spinner;
    private ProgressDialog mensajeProgreso;
    private DatabaseReference mUserDataBase;
    private Button ingresar;
    FirebaseAuth mAuth;
    //========================================================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserDataBase = FirebaseDatabase.getInstance().getReference("Usuarios");

        etClave = (EditText) findViewById(R.id.etClave);
        etUsuario = (EditText) findViewById(R.id.etUsuario);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        spinner = (Spinner) findViewById(R.id.spinner);
        ingresar = (Button) findViewById(R.id.btnIngresar);
        //opciones del spinner
        String[] opciones = {"Producto", "Inventario", "Persona"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        spinner.setAdapter(adapter);

        //progresBar
        mensajeProgreso = new ProgressDialog(MainActivity.this);
        //verClave
        verClave();
        /*========BUSCAR DATOS========*/
        ingresar.setOnClickListener(v -> {
            System.out.println("\n\n");
            String usuario1 = etUsuario.getText().toString();
            Query query3 = FirebaseDatabase.getInstance().getReference("Usuarios").orderByChild("usuUsuario")
                    .equalTo(usuario1);
            System.out.println("estamos buscando" + "\n\n");
            query3.addListenerForSingleValueEvent(valueEventListener);
            /*======================================================================================*/
        });
        /*========FIN BUSCAR DATOS========*/
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            if (dataSnapshot.exists()) {
                Log.e(TAGLOG, "Datos Obtennidos" + dataSnapshot.getValue().toString());
                mostrar();
                Toast.makeText(MainActivity.this, "ACCEDISTE COMO: " + etUsuario.getText().toString().toUpperCase(), Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(MainActivity.this, "Datos encontrados", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "No existe ese usuario!!" + "\n" + " crea una centa y vuelvelo a intentar", Toast.LENGTH_SHORT).show();
            }
        }

        /*========BUSCAR DATOS========*/
        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.e(TAGLOG, "Error:" + databaseError);
            Toast.makeText(MainActivity.this, "ERROR EN LA BASE DE DATOS", Toast.LENGTH_SHORT).show();

        }
    };


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


    //========================================================================================================================

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

    public void mostrar() {
        String seleccionado = spinner.getSelectedItem().toString();
        if (seleccionado.equals("Persona")) {
            Intent i = new Intent(MainActivity.this, activity_persona.class);
            i.putExtra("correo", etUsuario.getText().toString());
            startActivity(i);
        } else if (seleccionado.equals("Producto")) {
            Intent i = new Intent(MainActivity.this, activity_producto.class);
            startActivity(i);

        } else if (seleccionado.equals("Inventario")) {
            Intent i = new Intent(MainActivity.this, activity_inventario.class);
            startActivity(i);
        }

    }


}