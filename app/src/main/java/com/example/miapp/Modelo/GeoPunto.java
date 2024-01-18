package com.example.miapp.Modelo;

import java.util.Date;

public class GeoPunto {

    private Double longitud, latitud;

    public GeoPunto(Double longitud, Double latitud){
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }
}
