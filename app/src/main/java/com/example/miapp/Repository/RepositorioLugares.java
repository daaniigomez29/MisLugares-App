package com.example.miapp.Repository;

import com.example.miapp.Modelo.Lugar;

import java.util.List;

public interface RepositorioLugares {

    List<Lugar> getAll();
    Lugar getLugarById(int id);
    void anadirLugar(Lugar lugar);
    void editarLugar(Lugar lugar);
    void eliminarLugar(int id);
}
