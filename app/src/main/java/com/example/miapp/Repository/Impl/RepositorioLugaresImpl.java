package com.example.miapp.Repository.Impl;

import android.content.Context;

import com.example.miapp.Modelo.Lugar;
import com.example.miapp.Modelo.TipoLugar;
import com.example.miapp.R;
import com.example.miapp.Repository.ConexionBBDD;
import com.example.miapp.Repository.RepositorioLugares;

import java.io.Serializable;
import java.util.ArrayList;

public class RepositorioLugaresImpl implements RepositorioLugares, Serializable{

    Context context;
    ConexionBBDD conexionBBDD;

    public RepositorioLugaresImpl(Context context){
        this.context = context;
        conexionBBDD = new ConexionBBDD(context);
    }
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


    public void insertarDatos(){
        conexionBBDD.limpiarTablaLugares();
        Lugar lugar1=new Lugar("Chipiona","C/ Playa de Regla",111, R.drawable.kebab_sitio,"https://maps.app.goo.gl/UymTwpkZpCJv1zpG6","Playa de regla de chipiona","2024/01/24",4.4f, TipoLugar.KEBAB);
        Lugar lugar2=new Lugar("CLub de alterne","C/ Playa de Regla",111,R.drawable.game_arcos,"https://maps.app.goo.gl/UymTwpkZpCJv1zpG6","Playa de regla de chipiona","2024/01/24",1.5f,TipoLugar.GAME);
        conexionBBDD.anadirLugar(lugar1);
        conexionBBDD.anadirLugar(lugar2);
    }
}
