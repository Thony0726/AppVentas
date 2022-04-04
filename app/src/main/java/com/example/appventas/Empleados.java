package com.example.appventas;

public class Empleados {
    private String Apellidos;
    private String Cedula;
    private String Nombres;
    private String Telefono;

    public Empleados() {

    }

    public Empleados(String apellidos, String Cedula, String nombres, String Telefono) {
        Apellidos = apellidos;
        Cedula = Cedula;
        Nombres = nombres;
        this.Telefono = Telefono;
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
        Cedula = Cedula;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        this.Telefono = telefono;
    }
    @Override
    public String toString() {
        return "Empleados{" +
                "Apellidos='" + Apellidos + ':' +
                ", Nombres=" + Nombres +
                ", Cedula=" + Cedula +
                ", telefono=" + Telefono +
                '}' +"\n";
    }
}
