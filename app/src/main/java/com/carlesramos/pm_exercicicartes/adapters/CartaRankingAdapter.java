package com.carlesramos.pm_exercicicartes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carlesramos.pm_exercicicartes.R;
import com.carlesramos.pm_exercicicartes.model.Cartas;

import java.util.ArrayList;

public class CartaRankingAdapter extends RecyclerView.Adapter<CartaRankingAdapter.CartaRankingViewHolder> {

    private ArrayList<Cartas> cartas;
    private Context context;
    private static int posicion;

    public CartaRankingAdapter(ArrayList<Cartas> cartas, Context context) {
        this.cartas = cartas;
        this.context = context;
    }

    @NonNull
    @Override
    public CartaRankingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ranking_cartas, parent, false);
        CartaRankingViewHolder viewHolder = new CartaRankingViewHolder(itemView, context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartaRankingViewHolder holder, int position) {
        Cartas carta = cartas.get(position);
        holder.bindCarta(carta);
    }

    @Override
    public int getItemCount() {
        return cartas.size();
    }

    public static class CartaRankingViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPosition;
        private ImageView ivCarta;
        private Context context;


        public CartaRankingViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            tvPosition = itemView.findViewById(R.id.tvPosicionCarta);
            ivCarta = itemView.findViewById(R.id.ivCartaRanking);
            this.context = context;
        }

        public void bindCarta(Cartas carta) {
            posicion++;
            String imageName = "a" + carta.getIdCarta();
            int resID = context.getResources().getIdentifier(imageName, "drawable",
                    context.getPackageName());

            if (resID != 0){
                ivCarta.setImageResource(resID);
            }
            else {
                //TODO ficar imatge per defecte
            }

            tvPosition.setText("Position: " + String.valueOf(posicion));
        }
    }
}
