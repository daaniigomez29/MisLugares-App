package com.example.miapp.Repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.miapp.Modelo.GeoPunto;
import com.example.miapp.Modelo.Lugar;
import com.example.miapp.Modelo.TipoLugar;

import java.util.ArrayList;

public class ConexionBBDD extends SQLiteOpenHelper implements RepositorioLugares {
    private static final String NOMBRE_BD = "lugares";
    private static final int VERSION_BD = 1;

    //Name of the table
    private static final String TABLA_LUGARES = "lugares";

    // Name of the columns of the table
    private static final String COL_ID = "id";
    private static final String nombre = "nombre";
    private static final String direccion = "direccion";
    private static final String telefono = "telefono";
    private static final String latitud = "latitud";
    private static final String longitud = "longitud";
    private static final String foto = "foto";
    private static final String url = "url";
    private static final String comentario = "comentario";
    private static final String fecha = "fecha";
    private static final String tipoLugar = "tipo_lugar";
    private static final String valoracion = "valoracion";

    public ConexionBBDD(Context context){
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla
        String CREATE_TABLE = "CREATE TABLE " + TABLA_LUGARES + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + nombre + " TEXT,"
                + direccion + " TEXT,"
                + telefono + " INTEGER,"
                + latitud + " REAL,"
                + longitud + " REAL,"
                + foto + " INTEGER,"
                + url + " TEXT,"
                + comentario + " TEXT,"
                + fecha + " TEXT,"
                + tipoLugar + " TEXT,"
                + valoracion + " REAL)";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Aquí puedes realizar acciones en caso de una actualización de la base de datos
    }

    @SuppressLint("Range")
    @Override
    public ArrayList<Lugar> getAll() {
        ArrayList<Lugar> lugares = new ArrayList<>();
        String SELECT_ALL = "SELECT * FROM " + TABLA_LUGARES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SELECT_ALL, null);

        if (cursor.moveToFirst()) {
            do {
                Lugar lugar = new Lugar();
                lugar.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
                lugar.setNombre(cursor.getString(cursor.getColumnIndex(nombre)));
                lugar.setDireccion(cursor.getString(cursor.getColumnIndex(direccion)));
                lugar.setTelefono(cursor.getInt(cursor.getColumnIndex(telefono)));
                Double latitudd =(double) cursor.getFloat(cursor.getColumnIndex(latitud));
                Double longitudd =(double) cursor.getFloat(cursor.getColumnIndex(longitud));
                GeoPunto geoPunto = new GeoPunto(longitudd, latitudd);
                lugar.setPosicion(geoPunto);
                lugar.setFoto(cursor.getInt(cursor.getColumnIndex(foto)));
                lugar.setUrl(cursor.getString(cursor.getColumnIndex(url)));
                lugar.setComentario(cursor.getString(cursor.getColumnIndex(comentario)));
                TipoLugar tipoLugar1 = TipoLugar.GAME;
                if(cursor.getString(cursor.getColumnIndex(tipoLugar)).equals("Game")){
                    tipoLugar1 = TipoLugar.GAME;
                } else if(cursor.getString(cursor.getColumnIndex(tipoLugar)).equals("Mcdonalds")){
                    tipoLugar1 = TipoLugar.MCDONALDS;
                } else if(cursor.getString(cursor.getColumnIndex(tipoLugar)).equals("Kebab")){
                    tipoLugar1 = TipoLugar.KEBAB;
                }
                lugar.setTipoLugar(tipoLugar1);
                lugar.setFecha(cursor.getString(cursor.getColumnIndex(fecha)));
                lugar.setValoracion(cursor.getFloat(cursor.getColumnIndex(valoracion)));                lugares.add(lugar);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return lugares;
    }

    @SuppressLint("Range")
    @Override
    public Lugar getLugarById(int id) {
        Lugar lugar = null;
        String SELECT_BY_ID = "SELECT * FROM " + TABLA_LUGARES + " WHERE " + COL_ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SELECT_BY_ID, null);

        if (cursor.moveToFirst()) {
            lugar = new Lugar();
            lugar.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
            lugar.setNombre(cursor.getString(cursor.getColumnIndex(nombre)));
            lugar.setDireccion(cursor.getString(cursor.getColumnIndex(direccion)));
            lugar.setTelefono(cursor.getInt(cursor.getColumnIndex(telefono)));
            Double latitudd =(double) cursor.getFloat(cursor.getColumnIndex(latitud));
            Double longitudd =(double) cursor.getFloat(cursor.getColumnIndex(longitud));
            GeoPunto geoPunto = new GeoPunto(longitudd, latitudd);
            lugar.setPosicion(geoPunto);
            lugar.setFoto(cursor.getInt(cursor.getColumnIndex(foto)));
            lugar.setUrl(cursor.getString(cursor.getColumnIndex(url)));
            lugar.setComentario(cursor.getString(cursor.getColumnIndex(comentario)));
            TipoLugar tipoLugar1 = TipoLugar.GAME;
            if(cursor.getString(cursor.getColumnIndex(tipoLugar)).equals("Game")){
                tipoLugar1 = TipoLugar.GAME;
            } else if(cursor.getString(cursor.getColumnIndex(tipoLugar)).equals("Mcdonalds")){
                tipoLugar1 = TipoLugar.MCDONALDS;
            } else if(cursor.getString(cursor.getColumnIndex(tipoLugar)).equals("Kebab")){
                tipoLugar1 = TipoLugar.KEBAB;
            }
            lugar.setTipoLugar(tipoLugar1);
            lugar.setFecha(cursor.getString(cursor.getColumnIndex(fecha)));
            lugar.setValoracion(cursor.getFloat(cursor.getColumnIndex(valoracion)));
        }

        cursor.close();
        db.close();

        return lugar;
    }

    @Override
    public void anadirLugar(Lugar lugar) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(nombre, lugar.getNombre());
        values.put(direccion, lugar.getDireccion());
        values.put(telefono, lugar.getTelefono());
        values.put(direccion, lugar.getDireccion());
        values.put(latitud, lugar.getPosicion().getLatitud());
        values.put(longitud, lugar.getPosicion().getLongitud());
        values.put(foto, lugar.getFoto());
        values.put(url, lugar.getUrl());
        values.put(comentario, lugar.getComentario());
        values.put(tipoLugar, lugar.getTipoLugar().getNombre());
        values.put(fecha, lugar.getFecha());
        values.put(valoracion, lugar.getValoracion());

        db.insert(TABLA_LUGARES, null, values);
        db.close();
    }

    @Override
    public void editarLugar(Lugar lugar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(nombre, lugar.getNombre());
        values.put(direccion, lugar.getDireccion());
        values.put(telefono, lugar.getTelefono());
        values.put(direccion, lugar.getDireccion());
        values.put(latitud, lugar.getPosicion().getLatitud());
        values.put(longitud, lugar.getPosicion().getLongitud());
        values.put(foto, lugar.getFoto());
        values.put(url, lugar.getUrl());
        values.put(comentario, lugar.getComentario());
        values.put(tipoLugar, lugar.getTipoLugar().getNombre());
        values.put(fecha, lugar.getFecha());
        values.put(valoracion, lugar.getValoracion());

        db.update(TABLA_LUGARES, values, COL_ID + " = ?", new String[]{String.valueOf(lugar.getId())});
        db.close();
    }

    @Override
    public void eliminarLugar(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TABLA_LUGARES, COL_ID + " = ?", new String[]{String.valueOf(id)});
            Log.d("ELIMINADO", "Lugar eliminado correctamente. ID: " + id);
        } catch (Exception e) {
            Log.e("ELIMINADO", "Error al eliminar el lugar. ID: " + id, e);
        } finally {
            db.close();
        }
    }

    public void limpiarTablaLugares() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLA_LUGARES, null, null);
        db.close();
    }
}
