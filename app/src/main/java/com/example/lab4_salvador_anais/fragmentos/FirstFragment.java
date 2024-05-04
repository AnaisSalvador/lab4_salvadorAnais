package com.example.lab4_salvador_anais.fragmentos;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4_salvador_anais.R;
import com.example.lab4_salvador_anais.adapter.Geolocalizacionadapter;
import com.example.lab4_salvador_anais.components.Geolozalicion;
import com.example.lab4_salvador_anais.databinding.FragmentFirstBinding;
import com.example.lab4_salvador_anais.servicios.Geolocalizacionservices;

import java.util.List;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirstFragment extends Fragment {
    //Geolocalización
    /*******/
    private Button climabutton;
    private RecyclerView recyclerView;
    private Geolocalizacionadapter geolocalizacionadapter;
    private Geolocalizacionservices geolocalizacionservices;
    private List<Geolozalicion> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        climabutton = view.findViewById(R.id.climabutton);
        configuracion();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        list = new ArrayList<>();

        geolocalizacionadapter = new Geolocalizacionadapter(list);
        recyclerView.setAdapter(geolocalizacionadapter);
        geolocalizacionservices = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Geolocalizacionservices.class);

        EditText lugarcito = view.findViewById(R.id.Lugarcito);
        Button buttonBuscar = view.findViewById(R.id.button4);
        buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = lugarcito.getText().toString().trim();
                if (!userInput.isEmpty()) {
                    fetchProfileFromWs(userInput);
                }
            }
        });

        return view;

    }

    private void configuracion() {
        climabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = NavHostFragment.findNavController(FirstFragment.this);
                navController.navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    public void fetchProfileFromWs(String userInput){
        Log.d("msg-test-ws-profile","si entra" );
        Call<List<Geolozalicion>> call = geolocalizacionservices.getGeo(userInput, 1, "8dd6fc3be19ceb8601c2c3e811c16cf1");
        call.enqueue(new Callback<List<Geolozalicion>>() {
            @Override
            public void onResponse(Call<List<Geolozalicion>> call, Response<List<Geolozalicion>> response) {
                if (response.isSuccessful()) {
                    List<Geolozalicion> data = response.body();
                    Log.d("msg-test-ws-profile", data.get(0).getLugar());
                    boolean hallarGeo = false;
                    for (Geolozalicion geoData : list) {
                        if (geoData.getLugar().equals(data.get(0).getLugar())) {
                            hallarGeo = true;
                            break;
                        }
                    }
                    if (hallarGeo) {
                        showToastWithDelay("Disponible", 3000);
                        Log.d("msg-test-ws-profile", "Igual");
                    } else {
                        list.add(new Geolozalicion(data.get(0).getLugar(), data.get(0).getLatitud(), data.get(0).getLongitud()));
                        geolocalizacionadapter.notifyDataSetChanged();
                        Log.d("msg-test-ws-profile", "no");
                    }
                } else {
                    Log.d("msg-test-ws-profile", "Sin respuesta");
                }
            }
            @Override
            public void onFailure(Call<List<Geolozalicion>> call, Throwable t) {
                t.printStackTrace();
                Log.d("msg-test-ws-profile","Error" );
            }
        });
    }

    private void showToastWithDelay(String message, int duration) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, duration);
    }
}