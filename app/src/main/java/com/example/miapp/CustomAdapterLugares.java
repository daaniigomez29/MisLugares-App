package com.example.miapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.miapp.Modelo.Lugar;

import java.util.ArrayList;

public class CustomAdapterLugares extends ArrayAdapter<Lugar> {

    public CustomAdapterLugares(@NonNull Context context, ArrayList<Lugar> arrayList){
        super(context, R.layout.elemento_lista, arrayList);
    }

    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent){
        Lugar a = getItem(position);

        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.elemento_lista, parent, false);
        }

        ImageView imagen = view.findViewById(R.id.foto);
        TextView nombre = view.findViewById(R.id.nombreLugar);
        RatingBar ratingBar = view.findViewById(R.id.valoracion);

        imagen.setImageResource(Integer.parseInt(a.getFoto()));
        nombre.setText(a.getNombre());
        ratingBar.setRating(a.getValoracion());

        return view;
    }
}
