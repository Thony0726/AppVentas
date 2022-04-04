package com.example.appventas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class adpatadorProductos_lista extends RecyclerView.Adapter<adpatadorProductos_lista.MyViewHolder> {

    public adpatadorProductos_lista() {

    }

    Context contex;

    ArrayList<Productos> list;


    public adpatadorProductos_lista(Context contex, ArrayList<Productos> list) {
        this.contex = contex;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(contex).inflate(R.layout.item_lista_productos, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        DatabaseReference databaseReference;
        Productos pro = list.get(position);
        holder.nombreProducto.setText(pro.getNombreProducto());
        holder.proCodigo.setText((pro.getCodigo()).toString());
        holder.proStock.setText((pro.getStock()).toString());
        String costo = pro.getPrecioCosto();
        String venta = pro.getPrecioVenta();
        holder.proCosto.setText(costo);
        holder.proVenta.setText(venta);

        holder.imgEliminar.setOnClickListener(view -> {
            AlertDialog.Builder alerta = new AlertDialog.Builder(holder.imgEliminar.getContext());
            alerta.setTitle("Estas seguro de eliminar?");
            alerta.setMessage("Nota: no se puede revertir");
            alerta.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    FirebaseDatabase.getInstance().getReference().child("Productos").child(pro.getNombreProducto()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(contex, "Se elimino el dato", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(contex, "Eror al eliminar", Toast.LENGTH_SHORT).show();

                        }
                    });
//Alternativa 2
//                    dbRef.child(&quot;dias-semana&quot;).child(&quot;dia7&quot;).removeValue();
                }
            });
            alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            alerta.show();
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nombreProducto, proCodigo, proStock, proCosto, proVenta;
        ImageButton imgEliminar, imgEditar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreProducto = itemView.findViewById(R.id.productoNombre);
            proCodigo = itemView.findViewById(R.id.proCodigo);
            proStock = itemView.findViewById(R.id.proStock);
            proCosto = itemView.findViewById(R.id.proCosto);
            proVenta = itemView.findViewById(R.id.proVenta);
            imgEditar = itemView.findViewById(R.id.imgEditarProducto);
            imgEliminar = itemView.findViewById(R.id.imgEliminarProducto);
        }
    }
}
