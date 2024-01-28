package com.example.miapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.miapp.databinding.FragmentPreferenciasBinding;


public class Preferencias extends Fragment {

    private FragmentPreferenciasBinding binding;
    private boolean cambiarTemaOscuro = true;
    private CheckBox temaOscuro;

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
       binding = FragmentPreferenciasBinding.inflate(inflater, container, false);
       temaOscuro = binding.getRoot().findViewById(R.id.temaOscuro);

        temaOscuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aplicarTema(cambiarTemaOscuro);
            }
        });

        return binding.getRoot();
    }

    private void obtenerTemaSeleccionado() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("ConfiguracionApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("usarTemaOscuro", true);
        editor.apply();
        ((MainActivity) requireActivity()).aplicarTema(true);
    }

    private void aplicarTema(boolean temaOscuro) {
        if (temaOscuro) {
            requireActivity().setTheme(R.style.TemaOscuro);
        } else {
            requireActivity().setTheme(R.style.TemaPorDefecto);
        }
    }
}