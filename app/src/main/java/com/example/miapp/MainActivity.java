package com.example.miapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.miapp.Modelo.Lugar;
import com.example.miapp.Modelo.TipoLugar;
import com.example.miapp.Repository.ConexionBBDD;
import com.example.miapp.Repository.Impl.RepositorioLugaresImpl;
import com.example.miapp.databinding.ActivityMainBinding;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements VistaLugar.OnLugarChangeListener, PantallaAnadir.OnPantallaAnadirChangeListener {

    private ArrayList<Lugar> listaLugares;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private RepositorioLugaresImpl repositorioLugares;
    private Lugar lugarEditar;
    private Lugar lugarAnadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String temaSeleccionado = obtenerTemaSeleccionado();

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repositorioLugares = new RepositorioLugaresImpl(this);

        setSupportActionBar(binding.toolbar);
        NavController navController = Navigation.findNavController(this, R.id.fragmentoLugar);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        /*

        NavController navController = Navigation.findNavController(this, R.id.fragmentoLugar);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

         */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.acercaDe) {
            NavController navController = Navigation.findNavController(this, R.id.fragmentoLugar);
            navController.navigate(R.id.FourthFragment);
        }

        if(id == R.id.preferencias){
            NavController navController = Navigation.findNavController(this, R.id.fragmentoLugar);
            navController.navigate(R.id.SixthFragment);
        }

        if(id == R.id.anadir){
            PantallaAnadir pantallaAnadir = new PantallaAnadir();
            Bundle bundle = new Bundle();
            bundle.putSerializable("repositorio", repositorioLugares);
            pantallaAnadir.setArguments(bundle);

            NavController navController = Navigation.findNavController(this, R.id.fragmentoLugar);
            navController.navigate(R.id.FifthFragment, bundle);
        }

        if(id == R.id.editarLugar){
            PantallaEditar pantallaEditar = new PantallaEditar();
           // pantallaEditar.setRepositorioLugares(repositorioLugares);
            Bundle bundle = new Bundle();
            bundle.putSerializable("editarLugar", lugarEditar);
            pantallaEditar.setArguments(bundle);

            Bundle args = new Bundle();
            args.putSerializable("repositorio", repositorioLugares);
            pantallaEditar.getArguments().putAll(args);

            pantallaEditar.setArguments(bundle);
            NavController navController = Navigation.findNavController(this, R.id.fragmentoLugar);
            navController.navigate(R.id.ThirdFragment, bundle);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.fragmentoLugar);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public void onLugarChanged(Lugar lugar) {
        this.lugarEditar = lugar;
    }

    @Override
    public void inyectarLugarAnadir(Lugar lugar) {
        this.lugarAnadir = lugar;
    }

    private String obtenerTemaSeleccionado() {
        SharedPreferences sharedPreferences = getSharedPreferences("ConfiguracionApp", Context.MODE_PRIVATE);
        return sharedPreferences.getString("temaSeleccionado", "temaPorDefecto");
    }

    private void aplicarTema(String tema) {
        if (tema.equals("temaOscuro")) {
            setTheme(R.style.TemaOscuro);
        } else {
            setTheme(R.style.TemaPorDefecto);
        }
    }

}