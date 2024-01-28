package com.example.miapp;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.miapp.Modelo.GeoPunto;
import com.example.miapp.Modelo.Lugar;
import com.example.miapp.Modelo.TipoLugar;
import com.example.miapp.Repository.Impl.RepositorioLugaresImpl;
import com.example.miapp.databinding.FragmentPantallaEditarBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PantallaEditar extends Fragment {

    FragmentPantallaEditarBinding binding;
    private RepositorioLugaresImpl repositorioLugares;
    private EditText nombreLugar;
    private Spinner tipoLugar;
    String opcionSeleccionada;
    private ImageView iconoLugar;
    private EditText direccion;
    private EditText telefono;
    private EditText longitud;
    private EditText latitud;
    private EditText url;
    private EditText comentario;
    private Button fecha;
    private RatingBar ratingBar;
    private ImageView imagen;
    private Lugar lugar;
    private String fechaAEditar = "";


    public PantallaEditar() {
        // Required empty public constructor
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Método que obtiene el botón del menú seleccionado
        int id = item.getItemId();

        if(id == R.id.confirmarEdit){
            lugar = obtenerDatos(lugar);
            repositorioLugares.editarLugar(lugar);
            VistaLugar vistaLugar = new VistaLugar();
            Bundle bundle = new Bundle();
            bundle.putSerializable("lugar", lugar);
            vistaLugar.setArguments(bundle);
            NavController navController = Navigation.findNavController(requireView());
            navController.popBackStack(R.id.SecondFragment, false);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_editar, menu);
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
        binding = FragmentPantallaEditarBinding.inflate(inflater, container, false);
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

        iconoLugar = binding.getRoot().findViewById(R.id.iconoLugar);
        direccion = binding.getRoot().findViewById(R.id.direccion);
        telefono =  binding.getRoot().findViewById(R.id.telefono);
        longitud =  binding.getRoot().findViewById(R.id.longitud);
        latitud =  binding.getRoot().findViewById(R.id.latitud);
        url = binding.getRoot().findViewById(R.id.url);
        comentario = binding.getRoot().findViewById(R.id.comentario);
        fecha = binding.getRoot().findViewById(R.id.botonFecha);
        ratingBar = binding.getRoot().findViewById(R.id.ratingBar);
        imagen = binding.getRoot().findViewById(R.id.foto);

        Bundle args = getArguments();
        if(args != null && args.containsKey("editarLugar")){
            lugar = (Lugar) args.getSerializable("editarLugar");
            nombreLugar.setText(lugar.getNombre());
            // Establecer el valor deseado en el Spinner (por ejemplo, "Game")
            String valorDeseado = lugar.getTipoLugar().getNombre();
            int posicion = opciones.indexOf(valorDeseado);
            if (posicion != -1) {
                tipoLugar.setSelection(posicion);
            }
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
            iconoLugar.setImageResource(lugar.getTipoLugar().getImagen());
            direccion.setText(lugar.getDireccion());
            telefono.setText(String.valueOf(lugar.getTelefono()));
            longitud.setText(String.valueOf(lugar.getPosicion().getLongitud()));
            latitud.setText(String.valueOf(lugar.getPosicion().getLatitud()));
            url.setText(lugar.getUrl());
            comentario.setText(lugar.getComentario());
            fecha.setText(lugar.getFecha());
            fechaAEditar = lugar.getFecha();
            ratingBar.setRating(lugar.getValoracion());
            imagen.setImageResource(lugar.getFoto());
        }

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               fechaAEditar = mostrarDatePickerDialog();

            }
        });

        return binding.getRoot();
    }

    private String mostrarDatePickerDialog() {
        // Obtener la fecha actual
        String fechaString = String.valueOf(fecha.getText());
        final Calendar calendario = obtenerCalendarDesdeString(fechaString);
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

    public Lugar obtenerDatos(Lugar lugar) {
        lugar.setNombre(nombreLugar.getText().toString());
        if(opcionSeleccionada.equals("Kebab")){
            lugar.setTipoLugar(TipoLugar.KEBAB);
        } else if(opcionSeleccionada.equals("Game")){
            lugar.setTipoLugar(TipoLugar.GAME);
        } else if(opcionSeleccionada.equals("Mcdonalds")){
            lugar.setTipoLugar(TipoLugar.MCDONALDS);
        }

        lugar.setDireccion(direccion.getText().toString());
        lugar.setTelefono(Integer.parseInt(telefono.getText().toString()));
        Double longi = Double.parseDouble(longitud.getText().toString());
        Double lat = Double.parseDouble(latitud.getText().toString());
        GeoPunto geoPunto = new GeoPunto(longi, lat);
        lugar.setPosicion(geoPunto);
        lugar.setUrl(url.getText().toString());
        lugar.setComentario(comentario.getText().toString());
        lugar.setFecha(fechaAEditar);
        lugar.setValoracion(ratingBar.getRating());
        return lugar;
    }
}