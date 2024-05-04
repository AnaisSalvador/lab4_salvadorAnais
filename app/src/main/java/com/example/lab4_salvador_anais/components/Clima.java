package com.example.lab4_salvador_anais.components;

public class Clima {
    private String nombre;
    private Coordenadas coordenadas;
    private Main main;

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
}
