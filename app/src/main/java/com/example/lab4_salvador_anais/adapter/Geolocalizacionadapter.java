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
public class Geolocalizacionadapter extends RecyclerView.Adapter<Geolocalizacionadapter.ViewHolder>{
    private List<Geolozalicion> geolozalicionlist;
    public Geolocalizacionadapter(List<Geolozalicion> list){
        this.geolozalicionlist = geolozalicionlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_geolocalizacion, parent, false);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView lugar;
        TextView longitud;
        TextView latitud;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lugar = itemView.findViewById(R.id.lugar);
            longitud = itemView.findViewById(R.id.longitud2);
            latitud = itemView.findViewById(R.id.latitud2);

        }
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Geolozalicion geolozalicion = geolozalicionlist.get(position);
        holder.lugar.setText(geolozalicion.getLugar());
        holder.longitud.setText(geolozalicion.getLongitud());
        holder.latitud.setText(geolozalicion.getLatitud());
    }

    @Override
    public int getItemCount() {
        return geolozalicionlist.size();
    }

}
