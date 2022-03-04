package com.example.appventas;

public class Productos {

    private String codigo;
    private String nombreProducto;
    private String precioCosto;
    private String precioVenta;
    private String stock;

    public Productos() {
    }

    public Productos(String codigo, String nombreProducto, String precioCosto, String precioVenta, String stock) {
        this.codigo = codigo;
        this.nombreProducto = nombreProducto;
        this.precioCosto = precioCosto;
        this.precioVenta = precioVenta;
        this.stock = stock;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(String precioCosto) {
        this.precioCosto = precioCosto;
    }

    public String getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(String precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "codigo='" + codigo + ':' +
                ", nombreProducto=" + nombreProducto +
                ", precioCosto=" + precioCosto +
                ", precioVenta=" + precioVenta +
                ", stock=" + stock +
                '}' +"\n";
    }
}
