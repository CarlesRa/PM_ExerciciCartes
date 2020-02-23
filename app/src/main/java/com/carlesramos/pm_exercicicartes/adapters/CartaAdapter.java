package com.carlesramos.pm_exercicicartes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.carlesramos.pm_exercicicartes.R;
import com.carlesramos.pm_exercicicartes.interfaces.ICartaSeleccionada;
import com.carlesramos.pm_exercicicartes.model.Cartas;
import java.util.ArrayList;

public class CartaAdapter extends RecyclerView.Adapter<CartaAdapter.CartasViewHolder> {

    private ArrayList<Cartas> cartas;
    private Context context;
    private ICartaSeleccionada listener;

    public CartaAdapter(ArrayList<Cartas>cartas, Context context, ICartaSeleccionada listener){
        this.cartas = cartas;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carta_item, parent, false);
        CartasViewHolder viewHolder = new CartasViewHolder(itemView, context, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartasViewHolder holder, int position) {
        Cartas carta = cartas.get(position);
        holder.bindCarta(carta);
    }

    @Override
    public int getItemCount() {
        return cartas.size();
    }

    public static class CartasViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        private Context context;
        private ICartaSeleccionada listener;
        private ImageView ivCoche;

        public CartasViewHolder(@NonNull View itemView, Context context,
                                ICartaSeleccionada listener) {
            super(itemView);
            this.context = context;
            this.listener = listener;
            ivCoche = itemView.findViewById(R.id.ivCoche);
            itemView.setOnClickListener(this);
        }


        public void bindCarta(Cartas carta){
            String imageName = "a" + carta.getIdCarta();
            int resID = context.getResources().getIdentifier(imageName, "drawable",
                    context.getPackageName());

            if (resID != 0){
                ivCoche.setImageResource(resID);
            }
            else {
                //TODO ficar imatge per defecte
            }
        }

        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.onCartaSeleccionada(getAdapterPosition());
            }
        }

    }
}
