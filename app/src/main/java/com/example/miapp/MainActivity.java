package com.example.miapp;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import com.example.miapp.Modelo.Lugar;
import com.example.miapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements VistaLugar.OnLugarChangeListener{

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private Lugar lugarEditar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        aplicarTema();

        super.onCreate(savedInstanceState);
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

        //Navigate to fragment Acerca de
        if (id == R.id.acercaDe) {
            NavController navController = Navigation.findNavController(this, R.id.fragmentoLugar);
            navController.navigate(R.id.FourthFragment);
        }

        //Navigate to fragment preferencias
        if(id == R.id.preferencias){
            NavController navController = Navigation.findNavController(this, R.id.fragmentoLugar);
            navController.navigate(R.id.SixthFragment);
        }

        //Navigate to fragment Añadir lugar
        if(id == R.id.anadir){
            NavController navController = Navigation.findNavController(this, R.id.fragmentoLugar);
            navController.navigate(R.id.FifthFragment);
        }

        //Navigate to fragment Editar lugar
        if(id == R.id.editarLugar){
            PantallaEditar pantallaEditar = new PantallaEditar();
            Bundle bundle = new Bundle();
            bundle.putSerializable("editarLugar", lugarEditar);
            pantallaEditar.setArguments(bundle);

            NavController navController = Navigation.findNavController(this, R.id.fragmentoLugar);
            navController.navigate(R.id.ThirdFragment, bundle); //Pass the bundle to fragment
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.fragmentoLugar);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


    //Method to obtain place selected
    @Override
    public void onLugarChanged(Lugar lugar) {
        this.lugarEditar = lugar;
    }

    private void aplicarTema() {
        //Obtain actual state from preferences of dark theme by SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isDarkThemeEnabled = sharedPreferences.getBoolean("key_dark_theme", false);

        //Configure theme about the actual state
        if (isDarkThemeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

}