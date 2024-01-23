package com.example.miapp.Repository.Impl;

import com.example.miapp.Modelo.Lugar;
import com.example.miapp.Repository.ConexionBBDD;
import com.example.miapp.Repository.RepositorioLugares;

import java.util.ArrayList;

public class RepositorioLugaresImpl implements RepositorioLugares {

    ConexionBBDD conexionBBDD;
    @Override
    public ArrayList<Lugar> getAll() {
        return conexionBBDD.getAll();
    }

    @Override
    public Lugar getLugarById(int id) {
        return conexionBBDD.getLugarById(id);
    }

    @Override
    public void anadirLugar(Lugar lugar) {
        conexionBBDD.anadirLugar(lugar);
    }

    @Override
    public void editarLugar(Lugar lugar) {
        conexionBBDD.editarLugar(lugar);
    }

    @Override
    public void eliminarLugar(int id) {
        conexionBBDD.eliminarLugar(id);
    }

    public void limpiarTablaLugares(){conexionBBDD.limpiarTablaLugares();};

    public void setConexionBBDD(ConexionBBDD conexionBBDD) {
        this.conexionBBDD = conexionBBDD;
    }
}
