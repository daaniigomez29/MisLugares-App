package com.example.miapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class Preferencias extends Fragment {


    public Preferencias() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_preferencias, container, false);
    }

    private String obtenerTemaSeleccionado() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("ConfiguracionApp", Context.MODE_PRIVATE);
        return sharedPreferences.getString("temaSeleccionado", "temaPorDefecto");
    }

    private void aplicarTema(String tema) {
        if (tema.equals("temaOscuro")) {
            requireActivity().setTheme(R.style.TemaOscuro);
        } else {
            requireActivity().setTheme(R.style.TemaPorDefecto);
        }
    }
}