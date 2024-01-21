package com.example.miapp.Repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.miapp.Modelo.Lugar;

import java.util.List;

public class ConexionBBDD extends SQLiteOpenHelper implements RepositorioLugares {

    Context context;

    public ConexionBBDD(Context context){
        super(context, "lugares", null, 1);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public List<Lugar> getAll() {
        return null;
    }

    @Override
    public Lugar getLugarById(int id) {
        return null;
    }

    @Override
    public void anadirLugar(Lugar lugar) {

    }

    @Override
    public void editarLugar(Lugar lugar) {

    }

    @Override
    public void eliminarLugar(int id) {

    }
}
