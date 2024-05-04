package com.example.lab4_salvador_anais.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.NavController;
import com.example.lab4_salvador_anais.R;
import com.example.lab4_salvador_anais.components.Clima;
import com.example.lab4_salvador_anais.components.Coordenadas;
import com.example.lab4_salvador_anais.components.Main;
import com.example.lab4_salvador_anais.databinding.FragmentSecondBinding;
import com.example.lab4_salvador_anais.servicios.Climaservices;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondFragment extends Fragment {
    //CLima
    private FragmentSecondBinding binding;
    private Main main;
    private RecyclerView recyclerView;
    private Clima climadapter;
    private Climaservices climaServices;
    private List<Clima> list;
    private Button geolocalizacionbutton;
    private Coordenadas coordenadas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        geolocalizacionbutton = view.findViewById(R.id.geolocalizacionbutton);
        configuracion();
        recyclerView = view.findViewById(R.id.recyclerView_clima);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        climadapter = new Clima(list);
        recyclerView.setAdapter(climadapter);
        climaServices = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Climaservices.class);

        EditText latitud = view.findViewById(R.id.Latitud);
        EditText longitud = view.findViewById(R.id.Longitud);
        ConstraintLayout buttonBuscar = view.findViewById(R.id.button3);

        buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String latitud1 = latitud.getText().toString().trim();
                String longitud1 = longitud.getText().toString().trim();
                if (!latitud1.isEmpty() && !longitud1.isEmpty()) {

                    fetchProfileFromWs(latitud1,longitud1);
                }
            }
        });



        return view;
    }

    private void configuracion() {
        geolocalizacionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = NavHostFragment.findNavController(SecondFragment.this);
                navController.navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

    }

    public void fetchProfileFromWs(String latitud,String longitud){
        Log.d("msg-test-ws-profile","si entra" );
        Call<List<Clima>> call = climaServices.getClima(Double.parseDouble(latitud), Double.parseDouble(longitud), "792edf06f1f5ebcaf43632b55d8b03fe");
        call.enqueue(new Callback<List<Clima>>() {
            @Override
            public void onResponse(Call<List<Clima>> call, Response<List<Clima>> response) {
                if (response.isSuccessful()) {
                    List<Clima> data = response.body();
                    Log.d("msg-test-ws-profile",data.get(0).getNombre( ));
                    list.add(new Clima(data.get(0).getNombre(), data.get(0).getCoordenadas(), data.get(0).getMain()));
                    climadapter.notifyAll();
                    Log.d("msg-test-ws-profile", "no");
                } else {
                    Log.d("msg-test-ws-profile","Sin respuesta" );
                }
            }
            @Override
            public void onFailure(Call<List<Clima>> call, Throwable t) {
                t.printStackTrace();
                Log.d("msg-test-ws-profile","Error" );
            }
        });
    }
}