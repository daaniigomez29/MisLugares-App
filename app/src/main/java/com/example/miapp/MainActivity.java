package com.example.miapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
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

public class MainActivity extends AppCompatActivity implements VistaLugar.OnLugarChangeListener {

    private ArrayList<Lugar> listaLugares;
    RepositorioLugaresImpl repositorioLugares = new RepositorioLugaresImpl();
    ConexionBBDD conexionBBDD = new ConexionBBDD(this);
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private Lugar lugar;
    private PantallaInicio pantallaInicio = new PantallaInicio();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repositorioLugares.setConexionBBDD(conexionBBDD);
        repositorioLugares.limpiarTablaLugares();
        lugar= new Lugar("Arcos", "Calle 111", 111, R.drawable.game_arcos, "URL", "Muy bueno", "23/01/2024", 1.5f, TipoLugar.GAME);
        Lugar lugar2= new Lugar("Kebab", "Calle 222", 222, R.drawable.kebab_sitio, "URL", "Muy bueno", "23/01/2024", 1.5f, TipoLugar.KEBAB);
        repositorioLugares.anadirLugar(lugar);
        //repositorioLugares.anadirLugar(lugar2);

        listaLugares = repositorioLugares.getAll();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        NavController navController = Navigation.findNavController(this, R.id.fragmentoLugar);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

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

        System.out.println(id);
        //noinspection SimplifiableIfStatement
        if (id == R.id.acercaDe) {
            NavController navController = Navigation.findNavController(this, R.id.fragmentoLugar);
            navController.navigate(R.id.FourthFragment);
        }

        if(id == R.id.preferencias){

        }

        if(id == R.id.anadir){

        }

        if(id == R.id.editarLugar){
            PantallaEditar pantallaEditar = new PantallaEditar();
            Bundle bundle = new Bundle();
            bundle.putSerializable("editarLugar", lugar);
            pantallaEditar.setArguments(bundle);
            NavController navController = Navigation.findNavController(this, R.id.fragmentoLugar);
            navController.navigate(R.id.ThirdFragment, bundle);
        }

        if(id == R.id.eliminarLugar){

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
        this.lugar = lugar;
    }
/*
    @Override
    public void setListaLugares(ArrayList<Lugar> listaLugares) {
        this.listaLugares = listaLugares;
    }
 */

}