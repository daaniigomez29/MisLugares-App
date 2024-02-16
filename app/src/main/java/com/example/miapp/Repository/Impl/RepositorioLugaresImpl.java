package com.example.miapp.Repository.Impl;

import android.content.Context;

import com.example.miapp.Modelo.GeoPunto;
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
        Lugar lugar1=new Lugar("Kebab","C/ ",111, new GeoPunto(37.393880136657074, -5.960012921592743),R.drawable.kebab_sitio,"https://maps.app.goo.gl/UymTwpkZpCJv1zpG6","Kebab torreblanca","2024/01/24",4.4f, TipoLugar.KEBAB);
        Lugar lugar2=new Lugar("Game arcos","C.C. Los Arcos, Av. de Andalucía", 111,new GeoPunto(-5.960012921592743, 37.393880136657074),R.drawable.game_arcos,"https://maps.app.goo.gl/UymTwpkZpCJv1zpG6","Game arcos","2024/01/24",1.5f,TipoLugar.GAME);
        conexionBBDD.anadirLugar(lugar1);
        conexionBBDD.anadirLugar(lugar2);
    }
}
