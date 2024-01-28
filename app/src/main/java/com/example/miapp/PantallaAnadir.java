package com.example.miapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.miapp.Modelo.Lugar;
import com.example.miapp.Modelo.TipoLugar;
import com.example.miapp.Repository.Impl.RepositorioLugaresImpl;
import com.example.miapp.databinding.FragmentPantallaAnadirBinding;
import com.example.miapp.databinding.FragmentPantallaEditarBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class PantallaAnadir extends Fragment {

    private RepositorioLugaresImpl repositorioLugares;
    FragmentPantallaAnadirBinding binding;
    private TextView nombreLugar;
    private Spinner tipoLugar;
    private TipoLugar tipoLugarClass;
    private String opcionSeleccionada;
    private ImageView iconoLugar;
    private TextView direccion;
    private TextView telefono;
    private TextView url;
    private TextView comentario;
    private Button fecha;
    private RatingBar ratingBar;
    private ImageView imagen;
    private Lugar lugar;
    private String fechaAEditar = "";
    public PantallaAnadir() {
        // Required empty public constructor
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.confirmarAnadir){
            String nombre = nombreLugar.getText().toString();

            if(opcionSeleccionada.equals("Kebab")){
                tipoLugarClass = TipoLugar.KEBAB;
            } else if(opcionSeleccionada.equals("Game")){
                tipoLugarClass = TipoLugar.GAME;
            } else if(opcionSeleccionada.equals("Mcdonalds")){
                tipoLugarClass = TipoLugar.MCDONALDS;
            }
            String direc = direccion.getText().toString();
            int tlf = Integer.parseInt(telefono.getText().toString());
            String nombreImagen = getResources().getResourceName(imagen.getId());
            int idImagen = getResources().getIdentifier(nombreImagen, "drawable",getActivity().getPackageName());
            //int foto = Integer.parseInt(imagen.getDrawable().toString());
            String link = url.getText().toString();
            String coment = comentario.getText().toString();
            String fecha = fechaAEditar;
            float valor = ratingBar.getRating();
            lugar = new Lugar(nombre, direc, tlf, idImagen, link, coment, fecha, valor, tipoLugarClass);
            repositorioLugares.anadirLugar(lugar);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_anadir, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPantallaAnadirBinding.inflate(inflater, container, false);

        repositorioLugares = ((Aplicacion) getActivity().getApplication()).repositorioLugares;

        nombreLugar = binding.getRoot().findViewById(R.id.nombreLugar);
        tipoLugar = binding.getRoot().findViewById(R.id.tipoLugar);

        // Lista de opciones para el Spinner
        List<String> opciones = Arrays.asList("Game", "Kebab", "Mcdonalds");
        // Crear un ArrayAdapter desde la lista de opciones
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_item, opciones);
        // Especificar el layout para las opciones al desplegar el Spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Asignar el ArrayAdapter al Spinner
        tipoLugar.setAdapter(adapter);

        tipoLugar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Manejar la selección
                opcionSeleccionada = parentView.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Acciones cuando no se selecciona nada (si es necesario)
            }
        });

        iconoLugar = binding.getRoot().findViewById(R.id.iconoLugar);
        direccion = binding.getRoot().findViewById(R.id.direccion);
        telefono =  binding.getRoot().findViewById(R.id.telefono);
        url = binding.getRoot().findViewById(R.id.url);
        comentario = binding.getRoot().findViewById(R.id.comentario);
        fecha = binding.getRoot().findViewById(R.id.botonFecha);
        ratingBar = binding.getRoot().findViewById(R.id.ratingBar);
        imagen = binding.getRoot().findViewById(R.id.foto);

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fechaAEditar = mostrarDatePickerDialog();

            }
        });


        return binding.getRoot();
    }

    private String mostrarDatePickerDialog() {
        final Calendar calendario = Calendar.getInstance();
        int año = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        // Crear un DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // La fecha ha sido seleccionada
                        fechaAEditar = dayOfMonth + "/" + (month + 1) + "/" + year;
                        fecha.setText(fechaAEditar);
                    }
                },
                año, mes, dia);

        // Mostrar el DatePickerDialog
        datePickerDialog.show();
        return fechaAEditar;
    }

    private static Calendar obtenerCalendarDesdeString(String fechaString) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            // Parsear la cadena y obtener un objeto Date
            Date fechaDate = sdf.parse(fechaString);

            // Configurar el objeto Calendar con la fecha obtenida
            calendar.setTime(fechaDate);
        } catch (ParseException e) {
            e.printStackTrace();
            // Manejar la excepción si hay un error al analizar la cadena
        }

        return calendar;
    }
}