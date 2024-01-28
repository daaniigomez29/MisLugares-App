package com.example.miapp;

import android.app.Application;

import com.example.miapp.Modelo.GeoPunto;
import com.example.miapp.Repository.ConexionBBDD;
import com.example.miapp.Repository.Impl.RepositorioLugaresImpl;

public class Aplicacion extends Application {
    public ConexionBBDD conexionBBDD;
    public RepositorioLugaresImpl repositorioLugares;

    @Override public void onCreate() {
        super.onCreate();
        conexionBBDD = new ConexionBBDD(this);
        repositorioLugares = new RepositorioLugaresImpl(this);
        repositorioLugares.insertarDatos();
    }
}
