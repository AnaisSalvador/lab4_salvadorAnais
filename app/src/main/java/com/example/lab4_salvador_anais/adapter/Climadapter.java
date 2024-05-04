package com.example.lab4_salvador_anais.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab4_salvador_anais.R;
import com.example.lab4_salvador_anais.components.Clima;
import com.example.lab4_salvador_anais.components.Geolozalicion;
public class Climadapter extends RecyclerView.Adapter<Climadapter.ViewHolder> {
    private List<Clima> climaList;
    public Climadapter(List<Climadapter> list){
        this.climaList = climaList;
    }

    @NonNull
    @Override
    public Climadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_clima, parent, false);
        return new Climadapter.ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView lugar;
        TextView min;
        TextView max;
        TextView numk;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lugar = itemView.findViewById(R.id.lugar);
            min = itemView.findViewById(R.id.numK2);
            max = itemView.findViewById(R.id.numk3);
            numk = itemView.findViewById(R.id.numeroK);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull Climadapter.ViewHolder holder, int position) {
        Clima clima = climaList.get(position);
        String nombre = clima.getNombre();
        if (nombre != null) {
            holder.lugar.setText(nombre);
        } else {
            holder.lugar.setText("No está");
        }
        holder.min.setText(clima.getMain().getTemperaturaMinima());
        holder.max.setText(clima.getMain().getTemperaturaMaxima());
        holder.numk.setText(clima.getMain().getTemperatura());
    }

    @Override
    public int getItemCount() {
        return climaList.size();
    }
}
