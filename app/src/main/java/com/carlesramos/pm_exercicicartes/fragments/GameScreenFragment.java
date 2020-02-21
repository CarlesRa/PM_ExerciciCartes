package com.carlesramos.pm_exercicicartes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carlesramos.pm_exercicicartes.R;
import com.carlesramos.pm_exercicicartes.adapters.CartaAdapter;
import com.carlesramos.pm_exercicicartes.apiclient.APIUtils;
import com.carlesramos.pm_exercicicartes.interfaces.IApiInterface;
import com.carlesramos.pm_exercicicartes.interfaces.ICartaSeleccionada;
import com.carlesramos.pm_exercicicartes.clasesaux.NuevaPartida;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameScreenFragment extends Fragment implements ICartaSeleccionada{

    private RecyclerView rvCartasJugador;
    private IApiInterface mApiInterface;
    private String idSession;
    private int idCliente;
    private NuevaPartida partida;
    private Gson g = new Gson();

    public GameScreenFragment(String idSession, int idCliente){
        mApiInterface = APIUtils.getIApiInterface();
        this.idSession = idSession;
        this.idCliente = idCliente;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.game_screen_fragment, container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvCartasJugador = getActivity().findViewById(R.id.rvCartasJugador);
        nuevaPartida(getActivity());
    }

    @Override
    public void onCartaSeleccionada(int position) {

    }

    public void nuevaPartida(final Context context){
        mApiInterface.nuevaPartida(idSession, idCliente).enqueue(new Callback<NuevaPartida>() {
            @Override
            public void onResponse(Call<NuevaPartida> call, Response<NuevaPartida> response) {
                if (response.isSuccessful()){
                    partida = g.fromJson(response.body().toString(),NuevaPartida.class);
                    CartaAdapter adapter = new CartaAdapter(partida.getCartasCliente(),
                            getActivity(),GameScreenFragment.this);
                    rvCartasJugador.setAdapter(adapter);
                    rvCartasJugador.setLayoutManager(new LinearLayoutManager(context ,
                            LinearLayoutManager.HORIZONTAL,false));
                }
            }

            @Override
            public void onFailure(Call<NuevaPartida> call, Throwable t) {

            }
        });
    }
}
