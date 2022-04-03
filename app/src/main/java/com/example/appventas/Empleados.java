package com.example.appventas;

public class Empleados {
    private String Apellidos;
    private String Cedula;
    private String Nombres;
    private String telefono;

    public Empleados() {

    }

    public Empleados(String apellidos, String cedula, String nombres, String telefono) {
        Apellidos = apellidos;
        Cedula = cedula;
        Nombres = nombres;
        this.telefono = telefono;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    @Override
    public String toString() {
        return "Empleados{" +
                "Apellidos='" + Apellidos + ':' +
                ", Nombres=" + Nombres +
                ", Cedula=" + Cedula +
                ", telefono=" + telefono +
                '}' +"\n";
    }
}
