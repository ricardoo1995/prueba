package com.example.camk1.app_tienda.Clases;

/**
 * Created by hola on 27/11/2016.
 */

public class TarjetaInicio {
    String nombre;
    String urlImagen;
    String cantidad;
    String nombrep;
    String importe;
    String precio;


    public TarjetaInicio(String nombre, String urlImagen,String cantidad,String nombrep,String importe,String precio) {
        this.nombre = nombre;
        this.urlImagen = urlImagen;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
