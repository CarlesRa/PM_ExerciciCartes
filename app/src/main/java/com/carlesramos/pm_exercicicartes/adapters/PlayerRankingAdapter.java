package com.carlesramos.pm_exercicicartes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carlesramos.pm_exercicicartes.R;
import com.carlesramos.pm_exercicicartes.model.Jugadores;

import java.util.ArrayList;

public class PlayerRankingAdapter extends RecyclerView.Adapter<PlayerRankingAdapter.PlayerRankingViewHolder> {

    private ArrayList<Jugadores> jugadores;
    private static int posicion;

    public PlayerRankingAdapter(ArrayList<Jugadores> jugadores) {
        this.jugadores = jugadores;
        posicion = 0;
    }

    @NonNull
    @Override
    public PlayerRankingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ranking, parent, false);
        PlayerRankingViewHolder viewHolder = new PlayerRankingViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerRankingViewHolder holder, int position) {
        Jugadores jugador = jugadores.get(position);
        holder.bindJugador(jugador);
    }

    @Override
    public int getItemCount() {
        return jugadores.size();
    }


    public static class PlayerRankingViewHolder extends RecyclerView.ViewHolder {

        private TextView tvPosicion;
        private TextView tvName;
        private TextView tvGanadas;
        private TextView tvEmpatadas;
        private TextView tvPerdidas;

        public PlayerRankingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPosicion = itemView.findViewById(R.id.tvPosicionPlayer);
            tvName = itemView.findViewById(R.id.tvnickName);
            tvGanadas = itemView.findViewById(R.id.tvGanadasP);
            tvEmpatadas = itemView.findViewById(R.id.tvEmpatadas);
            tvPerdidas = itemView.findViewById(R.id.tvPerdidas);
        }

        public void bindJugador(Jugadores jugador) {
            posicion++;
            tvPosicion.setText(String.valueOf(posicion));
            tvName.setText(jugador.getNickName());
            tvGanadas.setText(String.valueOf(jugador.getGanadas()));
            tvEmpatadas.setText(String.valueOf(jugador.getEmpatadas()));
            tvPerdidas.setText(String.valueOf(jugador.getPerdidas()));
        }
    }


}
