package com.example.appventas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class ActivityAjustes_interfaz extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button button2, button3;
    private RadioButton estilos1, estilos2;



    private Double latC = 0.0287957200001;
    private Double  LongC= -78.1492354367;

    private Double latE = 0.0537;
    private Double  LongE= -78.1394 ;

    private CameraPosition posi;
    private LatLng DatosParis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes_interfaz);
        // Botton nav
        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationViewAjustes);
        btnNav.setSelectedItemId(R.id.item5);
        btnNav.setOnNavigationItemSelectedListener(navListener);

        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
//        estilos1=findViewById(R.id.estilos1);
//        estilos2=findViewById(R.id.estilos2);




        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


//        ArrayList<String> tipo = new ArrayList<>();
//        tipo.add("NORMAL");
//        tipo.add("SATELITE");
//        tipo.add("TERRENO");
//        tipo.add("HÍBRIDO");
//        modos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,tipo));


//        modos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                switch (i){
//                    case 0:
//                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//                        break;
//                    case 1 :
//                        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//                        break;
//                    case 2 :
//                        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
//                        break;
//                    case 3 :
//                        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//                        break;
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//            }
//        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        //bogota
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng suc1 = new LatLng(latC, LongC);

                mMap.addMarker(new MarkerOptions()
                        .position(suc1)
                        .title("Cayambe (Parque-Yaznan)"));

                CameraUpdate camaraBog = CameraUpdateFactory.newLatLngZoom(
                        new LatLng(latC, LongC),8
                );
                mMap.moveCamera(camaraBog);
                CameraPosition camPos = new CameraPosition.Builder()
                        .target(suc1)   //Centramos el mapa en Madrid
                        .zoom(17)         //Establecemos el zoom en 19
                        .bearing(50)      //Establecemos la orientación con el noreste arriba
                        .tilt(65)         //Bajamos el punto de vista de la cámara 70 grados
                        .build();

                CameraUpdate camUpd3 =
                        CameraUpdateFactory.newCameraPosition(camPos);

                mMap.animateCamera(camUpd3);
                Toast.makeText(
                        ActivityAjustes_interfaz.this,
                        "Parque-Yaznan\n" +
                                "Latitud: " + suc1.latitude + "\n" +
                                "Longitud: " + suc1.longitude + "\n" ,
                        Toast.LENGTH_SHORT).show();



            }
        });
        //zoom
//        zoom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                mMap.getUiSettings().setZoomControlsEnabled(b);
//            }
//        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

                LatLng suc2 = new LatLng(latE, LongE);

                mMap.addMarker(new MarkerOptions()
                        .position(suc2)
                        .title("Instituto Nelson Isauro Torres"));

                CameraPosition camPos = new CameraPosition.Builder()
                        .target(suc2)   //Centramos el mapa en Madrid
                        .zoom(17)         //Establecemos el zoom en 19
                        .bearing(50)      //Establecemos la orientación con el noreste arriba
                        .tilt(65)         //Bajamos el punto de vista de la cámara 70 grados
                        .build();

                CameraUpdate camUpd3 =
                        CameraUpdateFactory.newCameraPosition(camPos);
                mMap.animateCamera(camUpd3);
                Toast.makeText(
                        ActivityAjustes_interfaz.this,
                        "Instituto Nelson Isauro Torres\n" +
                                "Latitud: " + suc2.latitude + "\n" +
                                "Longitud: " + suc2.longitude + "\n" ,
                        Toast.LENGTH_SHORT).show();


            }
        });
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                Projection proj = mMap.getProjection();
                Point coord = proj.toScreenLocation(latLng);

                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Marcador"));

                DatosParis = latLng;

                Toast.makeText(
                        ActivityAjustes_interfaz.this,
                        "Click\n" +
                                "Lat: " + latLng.latitude + "\n" +
                                "Lng: " + latLng.longitude + "\n" +
                                "X: " + coord.x + " - Y: " + coord.y,
                        Toast.LENGTH_SHORT).show();

            }
        });

        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            public void onCameraChange(CameraPosition position) {
                posi = position;
            }
        });


//        toastP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(
//                        ActivityAjustes_interfaz.this,
//                        "Cambio Cámara\n" +
//                                "Orientación: " + posi.bearing + "\n" +
//                                "Ángulo: " + posi.tilt,
//                        Toast.LENGTH_SHORT).show();
//
//
//            }
//        });



    }
//    public void estilos1 (View v){
//        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        boolean success = mMap.setMapStyle(new MapStyleOptions(getResources()
//                .getString(R.string.style_json)));
//
//
//    }
//    public void estilos2 (View v){
//        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        boolean success = mMap.setMapStyle(new MapStyleOptions(getResources()
//                .getString(R.string.style_json2)));
//
//    }
//Listener
private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:
                startActivity(new Intent(ActivityAjustes_interfaz.this, MainActivity.class));
                finish();
                break;
            case R.id.item2:

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ActivityAjustes_interfaz.this, R.style.BottomSheetDialogTheme);
                /*=========CLIENTES=========*/
                View bottomsheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.menu_parametros, (LinearLayout) findViewById(R.id.bottonSheetContainer));
                bottomsheetView.findViewById(R.id.btn_param_cliente).setOnClickListener(v -> {
                    startActivity(new Intent(ActivityAjustes_interfaz.this, ActivityClientes_interfaz.class));
                    finish();
                    Toast.makeText(ActivityAjustes_interfaz.this, "Clientes", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();
                });

                /*=========EMPLEADOS=========*/
                bottomsheetView.findViewById(R.id.btn_param_empleados).setOnClickListener(v -> {
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new fragment_Empleados()).commit();
                    startActivity(new Intent(ActivityAjustes_interfaz.this, ActivityEmpleados_interfaz.class));
                    finish();
                    Toast.makeText(ActivityAjustes_interfaz.this, "empleados", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();
                });

                /*=========PROVEEDORES=========*/
                bottomsheetView.findViewById(R.id.btn_param_proveedores).setOnClickListener(v -> {
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new fragment_Proveedores()).commit();
                    startActivity(new Intent(ActivityAjustes_interfaz.this, ActivityProveedores_interfaz.class));
                    finish();
                    Toast.makeText(ActivityAjustes_interfaz.this, "proveedores", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();
                });

                /*=========PRODUCTOS=========*/
                bottomsheetView.findViewById(R.id.btn_param_productos).setOnClickListener(v -> {
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new fragment_Productos()).commit();
                    startActivity(new Intent(ActivityAjustes_interfaz.this, ActivityProductos_interfaz.class));
                    finish();
                    Toast.makeText(ActivityAjustes_interfaz.this, "productos", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();
                });

                /*=========MOSTRAMOS EL DIALOGO=========*/
                bottomSheetDialog.setContentView(bottomsheetView);
                bottomSheetDialog.show();
                break;
            case R.id.item3:
                startActivity(new Intent(ActivityAjustes_interfaz.this, ActivityInventario_interfaz.class));
                finish();
                Toast.makeText(ActivityAjustes_interfaz.this, "inventario", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item4:
                startActivity(new Intent(ActivityAjustes_interfaz.this, ActivityFacturacion_interfaz.class));
                finish();
                Toast.makeText(ActivityAjustes_interfaz.this, "facturacion", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item5:
                startActivity(new Intent(ActivityAjustes_interfaz.this, ActivityAjustes_interfaz.class));
                finish();
                Toast.makeText(ActivityAjustes_interfaz.this, "ajustes", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }
};

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