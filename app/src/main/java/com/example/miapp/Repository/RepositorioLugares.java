package com.example.miapp.Repository;

import com.example.miapp.Modelo.Lugar;

import java.util.ArrayList;

public interface RepositorioLugares {

    ArrayList<Lugar> getAll();
    Lugar getLugarById(int id);
    void anadirLugar(Lugar lugar);
    void editarLugar(Lugar lugar);
    void eliminarLugar(int id);
    void limpiarTablaLugares();
}
