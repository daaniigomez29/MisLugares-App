package com.example.miapp.Modelo;

import com.example.miapp.R;

public enum TipoLugar {
    GAME ("Game", R.drawable.carrito),
    MCDONALDS ("Mcdonalds", R.drawable.mcdonalds),
    KEBAB("Kebab", R.drawable.kebab);


    private String nombre;
    private int imagen;

    TipoLugar(String nombre, int imagen){
    this.nombre = nombre;
    this.imagen = imagen;
}

    public String getNombre() {
        return nombre;
    }

    public int getImagen() {
        return imagen;
    }
}
