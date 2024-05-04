package com.example.lab4_salvador_anais.components;

public class Geolozalicion {

    private String longitud;
    private String latitud;
    private String lugar;

    public String getLongitud() {
        return longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLugar() {
        return lugar;
    }

    public Geolozalicion(String nombre, String longitud, String latitud){
        this.lugar = nombre;
        this.longitud = longitud;
        this.latitud = latitud;
    }

}
