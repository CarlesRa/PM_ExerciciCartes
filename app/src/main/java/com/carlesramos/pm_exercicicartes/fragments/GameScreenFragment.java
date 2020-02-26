package com.carlesramos.pm_exercicicartes.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
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
import com.carlesramos.pm_exercicicartes.model.Cartas;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Juan Carlos Ramos
 * Clase controladora de la pantalla de juego
 */
public class GameScreenFragment extends Fragment implements ICartaSeleccionada {

    private TextView tvCaracteristica;
    private TextView tvRonda;
    private TextView tvGanadasPlayer;
    private TextView tvGanadasCPU;
    private TextView tvDificult;
    private RecyclerView rvCartasJugador;
    private IApiInterface mApiInterface;
    private String idSession;
    private static ArrayList<Cartas> cartas;
    private int idCliente;
    private NuevaPartida partida;
    private JugadaCpu jugadaCpu;
    private int ronda;
    private int rondaAux;
    private int ganadasPlayer;
    private int ganadasCPU;

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
        tvCaracteristica = getActivity().findViewById(R.id.tvCaracteristicaScreen);
        tvRonda = getActivity().findViewById(R.id.tvRonda);
        tvGanadasCPU = getActivity().findViewById(R.id.tvGanadaCpu);
        tvGanadasPlayer = getActivity().findViewById(R.id.tvGanadaPlayer);
        rvCartasJugador = getActivity().findViewById(R.id.rvCartasJugador);
        tvDificult = getActivity().findViewById(R.id.tvDificult);
        ronda = 1;
        tvRonda.setText(String.valueOf(ronda));
        nuevaPartida();
    }

    @Override
    public void onResume() {
        super.onResume();

        setDificultad();
        if (cartas != null){
            CartaAdapter adapter = new CartaAdapter(cartas,
                    getActivity(),GameScreenFragment.this);
            rvCartasJugador.setHasFixedSize(true);
            rvCartasJugador.setAdapter(adapter);
            rvCartasJugador.setLayoutManager(new LinearLayoutManager(getActivity() ,
                    LinearLayoutManager.HORIZONTAL,false));
            if (ronda > rondaAux){
                rondaAux = ronda;
                ronda++;
                if (ronda <= 6){
                    juegaCpu();
                }
                else {
                    finalizarPartida();
                }
                tvRonda.setText(String.valueOf(ronda));
            }
        }
    }

    @Override
    public void onCartaSeleccionada(int position) {

        Intent i = new Intent(getActivity(), CartaDetalleActivity.class);
        i.putExtra(Configurations.EXTRA_CARTA,partida.getCartasCliente().get(position));
        i.putExtra(Configurations.EXTRA_PARTIDA, partida);
        i.putExtra(Configurations.EXTRA_CARTA_CPU, jugadaCpu);
        i.putExtra(Configurations.EXTRA_POSITION, position);
        startActivity(i);
    }

    /**
     * Metodo para iniciar una nueva partida
     */
    public void nuevaPartida(){
        //Conectamos con la api y le pasamos el id de session y el del cliente.
        mApiInterface.nuevaPartida(idSession, idCliente).enqueue(new Callback<NuevaPartida>() {
            @Override
            public void onResponse(Call<NuevaPartida> call, Response<NuevaPartida> response) {
                /**
                 * Si la respuesta es correcta rellenamos el recyclerview con las cartas que nos da
                 * la api
                 */
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
                    rvCartasJugador.setLayoutManager(new LinearLayoutManager(getActivity() ,
                            LinearLayoutManager.HORIZONTAL,false));
                    cartas = partida.getCartasCliente();
                    if (partida.getJugadorInicial() == 1){
                        Toast.makeText(getActivity(), "Empieza CPU", Toast.LENGTH_SHORT).show();
                       juegaCpu();
                    }
                    else {
                        Toast.makeText(getActivity(), "Empiezas Tu!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<NuevaPartida> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void juegaCpu() {
        mApiInterface.juegaCPU().enqueue(new Callback<JugadaCpu>() {
            @Override
            public void onResponse(Call<JugadaCpu> call, Response<JugadaCpu> response) {
                if (response.isSuccessful()){

                    jugadaCpu = response.body();
                    tvCaracteristica.setText(jugadaCpu.getCaracteristica());
                    tvGanadasCPU.setText(String.valueOf(jugadaCpu.getGanadasCPU()));
                    tvGanadasPlayer.setText(String.valueOf(jugadaCpu.getGanadasPlayer()));
                }
            }

            @Override
            public void onFailure(Call<JugadaCpu> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Metodo que rellena el textview del nivel de dificultad.
     */
    public void setDificultad(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String level = prefs.getString("level", "Easy");
        tvDificult.setText(level);
    }
    public void eliminarCarta(int position){
        cartas.remove(position);
    }

    public void finalizarPartida() {
        mApiInterface.resultadoPartida(partida.getIdPartida(), idCliente).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()){
                    if (response.body() == 1){
                        Toast.makeText(getActivity(), "OOOOO has perdido la partida.....", Toast.LENGTH_SHORT).show();
                        int rondasGanadas = Integer.parseInt(tvGanadasCPU.getText().toString()) + 1;
                        tvGanadasCPU.setText(String.valueOf(rondasGanadas));
                        ronda = 0;
                    }
                    else if (response.body() == 2){
                        Toast.makeText(getActivity(), "ENHORABUENA has ganado!!!!", Toast.LENGTH_SHORT).show();
                        int rondasGanadas = Integer.parseInt(tvGanadasPlayer.getText().toString()) + 1;
                        tvGanadasPlayer.setText(String.valueOf(rondasGanadas));
                        ronda = 0;
                    }
                    else {
                        Toast.makeText(getActivity(), "Has empatado la partida....", Toast.LENGTH_SHORT).show();
                        ronda = 0;
                    }
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        ronda = 0;
    }
}
