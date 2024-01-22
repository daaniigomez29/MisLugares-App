package com.example.miapp.Modelo;

import java.io.Serializable;

public class Lugar implements Serializable {
    private int id;
    private String nombre;
    private String direccion;
    private int telefono;
    private GeoPunto posicion;
    private int foto;
    private String url;
    private String comentario;
    private String fecha;
    private TipoLugar tipoLugar;
    private float valoracion;

    public Lugar(String nombre, String direccion, int telefono, int foto, String url, String comentario, String fecha, float valoracion) {
        this.id = 0;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.posicion = new GeoPunto(0.0,0.0);
        this.foto = foto;
        this.url = url;
        this.comentario = comentario;
        this.fecha = fecha;
        this.valoracion = valoracion;
    }

    public Lugar(String nombre, int foto, float valoracion){
        this.id = 0;
        this.nombre = nombre;
        this.foto = foto;
        this.valoracion = valoracion;
    }

    public Lugar(){
    posicion = new GeoPunto(0.0, 0.0);
    tipoLugar = TipoLugar.GAME;
    }


    public int getId() {return id;}

    public void setId(int id) {this.id = id;}
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public GeoPunto getPosicion() {
        return posicion;
    }

    public void setPosicion(GeoPunto posicion) {
        this.posicion = posicion;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public TipoLugar getTipoLugar() {
        return tipoLugar;
    }

    public void setTipoLugar(TipoLugar tipoLugar) {
        this.tipoLugar = tipoLugar;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }


}
