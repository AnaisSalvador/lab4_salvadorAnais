package com.example.lab4_salvador_anais.servicios;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.example.lab4_salvador_anais.components.Clima;
import com.example.lab4_salvador_anais.components.Geolozalicion;
public interface Climaservices {
    @GET("data/2.5/weather")
    Call<List<Clima>> getClima(
            @Query("lat") double latitud1,
            @Query("lon") double longitud1,
            @Query("appid") String appId);
}
