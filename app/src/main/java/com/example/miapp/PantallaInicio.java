package com.example.miapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.miapp.Modelo.Lugar;
import com.example.miapp.databinding.PantallaInicioBinding;

import java.util.ArrayList;

public class PantallaInicio extends Fragment {

    private PantallaInicioBinding binding;
    private ListView listViewLugares;
    private ArrayAdapter<String> adapter;
    private ArrayList<Lugar> listaLugares;
    private CustomAdapterLugares customAdapterLugares;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = PantallaInicioBinding.inflate(inflater, container, false);

        listViewLugares = binding.getRoot().findViewById(R.id.listViewLugares);
        listaLugares = new ArrayList<>();
        Lugar lugar= new Lugar("Game", "Calle 111", 111, R.drawable.game_arcos, "URL", "Muy bueno", "22/01/2024", 1.5f);
        listaLugares.add(lugar);
        customAdapterLugares = new CustomAdapterLugares(requireActivity(), listaLugares);
        listViewLugares.setAdapter(customAdapterLugares);

        listViewLugares.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                onLugarClick(i);
            }
        });

        return binding.getRoot();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

public void onLugarClick(int i){
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

}