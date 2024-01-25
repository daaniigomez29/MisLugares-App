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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.miapp.Modelo.Lugar;
import com.example.miapp.databinding.FragmentPantallaAnadirBinding;
import com.example.miapp.databinding.FragmentPantallaEditarBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class PantallaAnadir extends Fragment {

    public interface OnPantallaAnadirChangeListener {
        void inyectarLugarAnadir(Lugar lugar);
    }

    OnPantallaAnadirChangeListener onPantallaAnadirChangeListener;
    FragmentPantallaAnadirBinding binding;
    private TextView nombreLugar;
    private TextView tipoLugar;
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

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_anadir, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public static PantallaAnadir newInstance(String param1, String param2) {
        PantallaAnadir fragment = new PantallaAnadir();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
        nombreLugar = binding.getRoot().findViewById(R.id.nombreLugar);
        tipoLugar = binding.getRoot().findViewById(R.id.tipoLugar);
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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            onPantallaAnadirChangeListener = (PantallaAnadir.OnPantallaAnadirChangeListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " debe implementar");
        }
    }

    private void inyectarLugar() {
        if (onPantallaAnadirChangeListener != null) {
            onPantallaAnadirChangeListener.inyectarLugarAnadir(lugar);
        }
    }
}