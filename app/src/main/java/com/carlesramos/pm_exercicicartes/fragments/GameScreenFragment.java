package com.carlesramos.pm_exercicicartes.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.carlesramos.pm_exercicicartes.R;
import com.carlesramos.pm_exercicicartes.activities.CartaDetalleActivity;
import com.carlesramos.pm_exercicicartes.adapters.CartaAdapter;
import com.carlesramos.pm_exercicicartes.apiclient.APIUtils;
import com.carlesramos.pm_exercicicartes.clasesaux.JugadaCpu;
import com.carlesramos.pm_exercicicartes.configurations.Configurations;
import com.carlesramos.pm_exercicicartes.interfaces.IApiInterface;
import com.carlesramos.pm_exercicicartes.interfaces.ICartaSeleccionada;
import com.carlesramos.pm_exercicicartes.clasesaux.NuevaPartida;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameScreenFragment extends Fragment implements ICartaSeleccionada {

    private TextView tvMarca;
    private TextView tvModelo;
    private TextView tvCaracteristica;
    private TextView tvRonda;
    private TextView tvGanadasPlayer;
    private TextView tvGanadasCPU;
    private RecyclerView rvCartasJugador;
    private IApiInterface mApiInterface;
    private String idSession;
    private int idCliente;
    private NuevaPartida partida;
    private JugadaCpu jugadaCpu;
    private int ronda;

    public GameScreenFragment(String idSession, int idCliente){
        mApiInterface = APIUtils.getIApiInterface();
        this.idSession = idSession;
        this.idCliente = idCliente;
    }

    public GameScreenFragment(){

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
        tvMarca = getActivity().findViewById(R.id.tvMarcaScreen);
        tvModelo = getActivity().findViewById(R.id.tvModeloScreen);
        tvCaracteristica = getActivity().findViewById(R.id.tvCaracteristicaScreen);
        tvRonda = getActivity().findViewById(R.id.tvRonda);
        tvGanadasCPU = getActivity().findViewById(R.id.tvGanadaCpu);
        tvGanadasPlayer = getActivity().findViewById(R.id.tvGanadaPlayer);
        rvCartasJugador = getActivity().findViewById(R.id.rvCartasJugador);
        ronda = 1;
        tvRonda.setText(String.valueOf(ronda));
        nuevaPartida(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCartaSeleccionada(int position) {

        Intent i = new Intent(getActivity(), CartaDetalleActivity.class);
        i.putExtra(Configurations.EXTRA_CARTA,partida.getCartasCliente().get(position));
        i.putExtra(Configurations.EXTRA_PARTIDA, partida);
        i.putExtra(Configurations.EXTRA_CARTA_CPU, jugadaCpu);
        startActivity(i);
    }

    public void nuevaPartida(final Context context){
        mApiInterface.nuevaPartida(idSession, idCliente).enqueue(new Callback<NuevaPartida>() {
            @Override
            public void onResponse(Call<NuevaPartida> call, Response<NuevaPartida> response) {

                if (response.isSuccessful()){
                    DividerItemDecoration dividerItemDecoration =
                            new DividerItemDecoration(rvCartasJugador.getContext(),
                                    DividerItemDecoration.VERTICAL);
                    rvCartasJugador.addItemDecoration(dividerItemDecoration);

                    partida = response.body();

                    CartaAdapter adapter = new CartaAdapter(partida.getCartasCliente(),
                            getActivity(),GameScreenFragment.this);
                    rvCartasJugador.setHasFixedSize(true);
                    rvCartasJugador.setAdapter(adapter);
                    rvCartasJugador.setLayoutManager(new LinearLayoutManager(context ,
                            LinearLayoutManager.HORIZONTAL,false));
                    if (partida.getJugadorInicial() == 1){
                        Toast.makeText(context, "Empieza CPU", Toast.LENGTH_SHORT).show();
                        mApiInterface.juegaCPU().enqueue(new Callback<JugadaCpu>() {
                            @Override
                            public void onResponse(Call<JugadaCpu> call, Response<JugadaCpu> response) {
                                if (response.isSuccessful()){

                                    jugadaCpu = response.body();
                                    tvMarca.setText(jugadaCpu.getCarta().getMarca());
                                    tvModelo.setText(jugadaCpu.getCarta().getModelo());
                                    tvCaracteristica.setText(jugadaCpu.getCaracteristica());

                                }
                            }

                            @Override
                            public void onFailure(Call<JugadaCpu> call, Throwable t) {
                                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else {
                        Toast.makeText(context, "Empiezas Tu!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<NuevaPartida> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
