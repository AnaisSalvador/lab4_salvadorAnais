package com.example.lab4_salvador_anais.servicios;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.example.lab4_salvador_anais.components.Clima;
import com.example.lab4_salvador_anais.components.Geolozalicion;
public interface Geolocalizacionservices {
    @GET("/geo/1.0/direct")
    Call<List<Geolozalicion>>
    getGeo(@Query("q") String query,
           @Query("limit") int limit,
           @Query("appid") String appId);
}
