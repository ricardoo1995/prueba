package com.example.camk1.app_tienda.Clases;

/**
 * Created by hola on 27/11/2016.
 */

public class Producto {
    private String key;
    private String nombre;
    private String categoria;
    String cantidad;
    String nombrep;
    String importe;
    String precio;


    public Producto(String categoria, String key, String nombre,String cantidad,String nombrep,String importe,String precio) {
        this.categoria = categoria;
        this.key = key;
        this.nombre = nombre;
        this.cantidad=cantidad;
        this.nombrep=nombrep;
        this.importe=importe;
        this.precio=precio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombrep() {
        return nombrep;
    }

    public void setNombrep(String nombrep) {
        this.nombrep = nombrep;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
