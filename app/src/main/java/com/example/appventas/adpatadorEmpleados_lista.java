package com.example.appventas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adpatadorEmpleados_lista extends RecyclerView.Adapter<adpatadorEmpleados_lista.MyViewHolder> {

    public adpatadorEmpleados_lista(){

    }
    Context contex;

    ArrayList<Empleados> list;


    public adpatadorEmpleados_lista(Context contex, ArrayList<Empleados> list) {
        this.contex = contex;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(contex).inflate(R.layout.item_lista_empleados, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Empleados emp = list.get(position);
        holder.Apellidos.setText(emp.getApellidos());
        holder.Nombres.setText((emp.getNombres()).toString());
        holder.Cedula.setText((emp.getCedula()).toString());
        holder.telefono.setText((emp.getTelefono()).toString());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Apellidos, Nombres, Cedula, telefono;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Apellidos = itemView.findViewById(R.id.empApellido);
            Nombres = itemView.findViewById(R.id.empNombreItem);
            Cedula = itemView.findViewById(R.id.empCedula);
            telefono = itemView.findViewById(R.id.empTelefono);

        }
    }

}
