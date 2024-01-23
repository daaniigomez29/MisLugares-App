package com.example.miapp;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.miapp.Modelo.Lugar;
import com.example.miapp.Modelo.TipoLugar;
import com.example.miapp.Repository.Impl.RepositorioLugaresImpl;
import com.example.miapp.databinding.PantallaInicioBinding;

import java.util.ArrayList;

public class PantallaInicio extends Fragment {

    private PantallaInicioBinding binding;
    private ListView listViewLugares;
    private ArrayList<Lugar> listaLugares = new ArrayList<>();
    private CustomAdapterLugares customAdapterLugares;
    private Button boton;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = PantallaInicioBinding.inflate(inflater, container, false);



        for (Lugar lugar : listaLugares) {
            Log.d("Pantalla inicio", lugar.toString());
        }
        listViewLugares = binding.getRoot().findViewById(R.id.listViewLugares);
        customAdapterLugares = new CustomAdapterLugares(requireActivity(), listaLugares);
        listViewLugares.setAdapter(customAdapterLugares);
        listViewLugares.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                onLugarClick(i);
            }
        });

        return binding.getRoot();

    }

    public PantallaInicio() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onLugarClick(int i) {
        Lugar lugarSeleccionado = listaLugares.get(i);
        VistaLugar vistaLugar = new VistaLugar();

        //Pasar datos del lugar al fragmento
        Bundle bundle = new Bundle();
        bundle.putSerializable("lugar", lugarSeleccionado);
        vistaLugar.setArguments(bundle);

        //Reemplazar fragmentos
        NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentoLugar);
        navController.navigate(R.id.SecondFragment, bundle);
    }

    /*public void setListaLugares(ArrayList<Lugar> listaLugares) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            bundle.putSerializable("listaLugares", listaLugares);

            // Puedes hacer lo que necesites con el adaptador si es necesario
            if (customAdapterLugares != null) {
                customAdapterLugares.notifyDataSetChanged();
            }
        }
     */
}