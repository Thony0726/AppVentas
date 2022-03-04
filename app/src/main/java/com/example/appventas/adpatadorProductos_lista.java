package com.example.appventas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adpatadorProductos_lista extends RecyclerView.Adapter<adpatadorProductos_lista.MyViewHolder> {

    public adpatadorProductos_lista(){

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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Productos pro = list.get(position);
        holder.nombreProducto.setText(pro.getNombreProducto());
        holder.proCodigo.setText((pro.getCodigo()).toString());
        holder.proStock.setText((pro.getStock()).toString());
        String costo = pro.getPrecioCosto();
        String venta = pro.getPrecioVenta();
        holder.proCosto.setText(costo);
        holder.proVenta.setText(venta);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nombreProducto, proCodigo, proStock, proCosto, proVenta;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreProducto = itemView.findViewById(R.id.productoNombre);
            proCodigo = itemView.findViewById(R.id.proCodigo);
            proStock = itemView.findViewById(R.id.proStock);
            proCosto = itemView.findViewById(R.id.proCosto);
            proVenta = itemView.findViewById(R.id.proVenta);
        }
    }

}
