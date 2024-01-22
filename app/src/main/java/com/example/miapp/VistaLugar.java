    package com.example.miapp;

    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.Menu;
    import android.view.MenuInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.RatingBar;
    import android.widget.TextView;

    import androidx.annotation.NonNull;
    import androidx.fragment.app.Fragment;
    import androidx.navigation.fragment.NavHostFragment;

    import com.example.miapp.Modelo.Lugar;
    import com.example.miapp.databinding.VistaLugarBinding;

    public class VistaLugar extends Fragment {

        private VistaLugarBinding binding;
        private TextView nombreLugar;
        private TextView tipoLugar;
        private TextView direccion;
        private TextView telefono;
        private TextView url;
        private TextView comentario;
        private TextView fecha;
        private RatingBar ratingBar;
        private ImageView imagen;

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            // Inflate the menu; this adds items to the action bar if it is present.
            inflater.inflate(R.menu.menu_main, menu);
            super.onCreateOptionsMenu(menu, inflater);
            // Resto de tu código si es necesario
        }

        @Override
        public View onCreateView(
                LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState
        ) {

            binding = VistaLugarBinding.inflate(inflater, container, false);

            nombreLugar = binding.getRoot().findViewById(R.id.nombreLugar);
            tipoLugar = binding.getRoot().findViewById(R.id.tipoLugar);
            direccion = binding.getRoot().findViewById(R.id.direccion);
            telefono =  binding.getRoot().findViewById(R.id.telefono);
            url = binding.getRoot().findViewById(R.id.url);
            comentario = binding.getRoot().findViewById(R.id.comentario);
            fecha = binding.getRoot().findViewById(R.id.fecha);
            ratingBar = binding.getRoot().findViewById(R.id.ratingBar);
            imagen = binding.getRoot().findViewById(R.id.foto);



            Bundle args = getArguments();
            if(args != null && args.containsKey("lugar")){
                Lugar lugar = (Lugar) args.getSerializable("lugar");
                nombreLugar.setText(lugar.getNombre());
                direccion.setText(lugar.getDireccion());
                telefono.setText(String.valueOf(lugar.getTelefono()));
                url.setText(lugar.getUrl());
                comentario.setText(lugar.getComentario());
                fecha.setText(lugar.getFecha());
                ratingBar.setRating(lugar.getValoracion());
                imagen.setImageResource(lugar.getFoto());
            }

            return binding.getRoot();

        }

        public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        }


        @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }
    }