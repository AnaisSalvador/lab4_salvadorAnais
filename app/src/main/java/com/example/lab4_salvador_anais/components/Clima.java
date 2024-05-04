package com.example.lab4_salvador_anais.components;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Clima extends RecyclerView.Adapter {
    private String nombre;
    private Coordenadas coordenadas;
    private Main main;

    public Clima(List<Clima> list) {
    }

    public String getNombre() {
        return nombre;
    }

    public Coordenadas getCoordenadas() {
        return coordenadas;
    }

    public Main getMain() {
        return main;
    }

    public Clima (String nombre, Coordenadas coordenadas, Main main){
        this.nombre= nombre;
        this.coordenadas = coordenadas;
        this.main = main;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
