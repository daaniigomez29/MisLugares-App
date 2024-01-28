    package com.example.miapp;

    import android.content.Context;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.Menu;
    import android.view.MenuInflater;
    import android.view.MenuItem;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.RatingBar;
    import android.widget.TextView;

    import androidx.annotation.NonNull;
    import androidx.fragment.app.Fragment;
    import androidx.navigation.NavController;
    import androidx.navigation.Navigation;

    import com.example.miapp.Modelo.Lugar;
    import com.example.miapp.Modelo.TipoLugar;
    import com.example.miapp.Repository.Impl.RepositorioLugaresImpl;
    import com.example.miapp.databinding.VistaLugarBinding;

    public class VistaLugar extends Fragment {

        public interface OnLugarChangeListener {
            void onLugarChanged(Lugar lugar);
        }

        private OnLugarChangeListener mListener;
        RepositorioLugaresImpl repositorioLugares;
        private VistaLugarBinding binding;
        private TextView nombreLugar;
        private TextView tipoLugar;
        private ImageView iconoLugar;

        private TextView direccion;
        private TextView telefono;
        private TextView longitud;
        private TextView latitud;
        private TextView url;
        private TextView comentario;
        private TextView fecha;
        private RatingBar ratingBar;
        private ImageView imagen;
        private Lugar lugar;


        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.menu_vista_lugar, menu);
            super.onCreateOptionsMenu(menu, inflater);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) { //Método que obtiene el botón del menú seleccionado
            int id = item.getItemId();

            if(id == R.id.eliminarLugar){
                repositorioLugares.eliminarLugar(lugar.getId());
                NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentoLugar);
                navController.navigate(R.id.FirstFragment);
            }

            if(id == R.id.iconoGeoPunto){

            }

            return super.onOptionsItemSelected(item);
        }



        @Override
        public View onCreateView(
                LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState
        ) {
            binding = VistaLugarBinding.inflate(inflater, container, false);
            repositorioLugares = ((Aplicacion) getActivity().getApplication()).repositorioLugares;
            setHasOptionsMenu(true);
            nombreLugar = binding.getRoot().findViewById(R.id.nombreLugar);
            tipoLugar = binding.getRoot().findViewById(R.id.tipoLugar);
            iconoLugar = binding.getRoot().findViewById(R.id.iconoLugar);
            direccion = binding.getRoot().findViewById(R.id.direccion);
            telefono =  binding.getRoot().findViewById(R.id.telefono);
            longitud = binding.getRoot().findViewById(R.id.longitud);
            latitud = binding.getRoot().findViewById(R.id.latitud);
            url = binding.getRoot().findViewById(R.id.url);
            comentario = binding.getRoot().findViewById(R.id.comentario);
            fecha = binding.getRoot().findViewById(R.id.fecha);
            ratingBar = binding.getRoot().findViewById(R.id.ratingBar);
            imagen = binding.getRoot().findViewById(R.id.foto);



            Bundle args = getArguments();
            if(args != null && args.containsKey("lugar")){
                lugar = (Lugar) args.getSerializable("lugar");
                nombreLugar.setText(lugar.getNombre());
                tipoLugar.setText(lugar.getTipoLugar().getNombre());
                iconoLugar.setImageResource(lugar.getTipoLugar().getImagen());
                direccion.setText(lugar.getDireccion());
                telefono.setText(String.valueOf(lugar.getTelefono()));
                longitud.setText(String.valueOf(lugar.getPosicion().getLongitud()));
                latitud.setText(String.valueOf(lugar.getPosicion().getLatitud()));
                url.setText(lugar.getUrl());
                comentario.setText(lugar.getComentario());
                fecha.setText(lugar.getFecha());
                ratingBar.setRating(lugar.getValoracion());
                ratingBar.setIsIndicator(true);
                imagen.setImageResource(lugar.getFoto());
            }
            inyectarLugar();
            return binding.getRoot();

        }

        public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        }

        @Override
        public void onAttach(@NonNull Context context) {
            super.onAttach(context);
            try {
                mListener = (OnLugarChangeListener) context;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString() + " debe implementar OnLugarChangeListener");
            }
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }


          //Método para inyectar el objeto lugar en la clase MainActivity, su realización es a través de la interfaz OnLugarChangeListener
        private void inyectarLugar() {
            if (mListener != null) {
                mListener.onLugarChanged(lugar);
            }
        }
    }