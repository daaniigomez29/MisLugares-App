package com.example.miapp;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import com.example.miapp.Modelo.GeoPunto;
import com.example.miapp.Modelo.Lugar;
import com.example.miapp.Modelo.TipoLugar;
import com.example.miapp.Repository.ConexionBBDD;
import com.example.miapp.Repository.Impl.RepositorioLugaresImpl;
import com.example.miapp.databinding.PantallaInicioBinding;

import java.io.Serializable;
import java.util.ArrayList;

public class PantallaInicio extends Fragment {

    private PantallaInicioBinding binding;
    private RepositorioLugaresImpl repositorioLugares;
    private ListView listViewLugares;
    private ArrayList<Lugar> listaLugares = new ArrayList<>();
    private CustomAdapterLugares customAdapterLugares;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = PantallaInicioBinding.inflate(inflater, container, false);

        repositorioLugares = ((Aplicacion) getActivity().getApplication()).repositorioLugares; //Obtains repository from Application
        listaLugares = repositorioLugares.getAll(); //Obtains all places of the BBDD

        Log.d("Vuelvo", "Vuelvo despues de eliminar");
        listViewLugares = binding.getRoot().findViewById(R.id.listViewLugares);
        customAdapterLugares = new CustomAdapterLugares(requireActivity(), listaLugares);
        customAdapterLugares.notifyDataSetChanged();
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
        //Pass data of place to Fragment
        Bundle bundle = new Bundle();
        bundle.putSerializable("lugar", lugarSeleccionado);
        vistaLugar.setArguments(bundle);

        //Navigate to fragment
        NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentoLugar);
        navController.navigate(R.id.SecondFragment, bundle);
    }
}