package com.carlesramos.pm_exercicicartes.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.carlesramos.pm_exercicicartes.R;
import com.carlesramos.pm_exercicicartes.apiclient.APIUtils;
import com.carlesramos.pm_exercicicartes.clasesaux.JugadaCpu;
import com.carlesramos.pm_exercicicartes.clasesaux.NuevaPartida;
import com.carlesramos.pm_exercicicartes.interfaces.IApiInterface;
import com.carlesramos.pm_exercicicartes.model.Cartas;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetalleCartaFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    private Switch swMotor;
    private Switch swCilindros;
    private Switch swPotencia;
    private Switch swRevoluciones;
    private Switch swVelocidad;
    private Switch swConsumo;
    private TextView tvMarca;
    private TextView tvModelo;
    private TextView tvMotor;
    private TextView tvCilindros;
    private TextView tvPotencia;
    private TextView tvRevoluciones;
    private TextView tvVelocidad;
    private TextView tvConsumo;
    private Button btPlayCard;
    private Cartas carta;
    private JugadaCpu jugadaCpu;
    private NuevaPartida partida;
    private IApiInterface apiInterface;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_carta_detalle_activity, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        swMotor = getActivity().findViewById(R.id.swMotor);
        swCilindros = getActivity().findViewById(R.id.swCilindros);
        swPotencia = getActivity().findViewById(R.id.swPotencia);
        swRevoluciones = getActivity().findViewById(R.id.swRevolucionesGame);
        swVelocidad = getActivity().findViewById(R.id.swVelocidad);
        swConsumo = getActivity().findViewById(R.id.swConsumo);

        tvMarca = getActivity().findViewById(R.id.tvMarcaGame);
        tvModelo = getActivity().findViewById(R.id.tvModeloGame);
        tvMotor = getActivity().findViewById(R.id.tvMotorGame);
        tvCilindros = getActivity().findViewById(R.id.tvCilindrosGame);
        tvPotencia = getActivity().findViewById(R.id.tvPotenciaGame);
        tvRevoluciones = getActivity().findViewById(R.id.tvRevGame);
        tvVelocidad = getActivity().findViewById(R.id.tvVelocidadGame);
        tvConsumo = getActivity().findViewById(R.id.tvConsumoGame);
        btPlayCard = getActivity().findViewById(R.id.btPlayCard);

        tvMarca.setText(carta.getMarca());
        tvModelo.setText(carta.getModelo());
        tvMotor.setText(String.valueOf(carta.getMotor()));
        tvCilindros.setText(String.valueOf(carta.getNumCilindros()));
        tvPotencia.setText(String.valueOf(carta.getPotenciaKv()));
        tvRevoluciones.setText(String.valueOf(carta.getRevoluciones()));
        tvVelocidad.setText(String.valueOf(carta.getVelocidad()));
        tvConsumo.setText(String.valueOf(carta.getConsumo()));

        swMotor.setOnCheckedChangeListener(this);
        swCilindros.setOnCheckedChangeListener(this);
        swPotencia.setOnCheckedChangeListener(this);
        swRevoluciones.setOnCheckedChangeListener(this);
        swVelocidad.setOnCheckedChangeListener(this);
        swConsumo.setOnCheckedChangeListener(this);

        btPlayCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (swMotor.isChecked() || swConsumo.isChecked() || swCilindros.isChecked() ||
                        swPotencia.isChecked() || swRevoluciones.isChecked() ||
                        swVelocidad.isChecked()){
                    if (partida.getJugadorInicial() == 1){
                        apiInterface.comprobarJugada(partida.getIdPartida(),
                                jugadaCpu.getCarta().getIdCarta(),
                                jugadaCpu.getCaracteristica(), carta.getIdCarta()).
                                enqueue(new Callback<Integer>() {
                                    @Override
                                    public void onResponse(Call<Integer> call,
                                                           Response<Integer> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().equals("1")){
                                                Toast.makeText(getActivity(),
                                                        "Has perdido...",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                            else if(response.body().equals("2")){
                                                Toast.makeText(getActivity(),
                                                        "Has Ganado!!",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                                Toast.makeText(getActivity(), "Empate!",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Integer> call, Throwable t) {

                                    }
                                });
                    }
                }
                else {
                    Toast.makeText(getActivity(),
                            "Tienes que indicar una característica!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.swMotor : {
                if (isChecked) {
                    swCilindros.setChecked(false);
                    swPotencia.setChecked(false);
                    swRevoluciones.setChecked(false);
                    swVelocidad.setChecked(false);
                    swConsumo.setChecked(false);
                }
                break;
            }
            case R.id.swCilindros : {
                if (isChecked) {
                    swMotor.setChecked(false);
                    swPotencia.setChecked(false);
                    swRevoluciones.setChecked(false);
                    swVelocidad.setChecked(false);
                    swConsumo.setChecked(false);
                }
                break;
            }
            case R.id.swPotencia : {
                if (isChecked) {
                    swCilindros.setChecked(false);
                    swMotor.setChecked(false);
                    swRevoluciones.setChecked(false);
                    swVelocidad.setChecked(false);
                    swConsumo.setChecked(false);
                }
                break;
            }
            case R.id.swRevolucionesGame : {
                if (isChecked) {
                    swCilindros.setChecked(false);
                    swPotencia.setChecked(false);
                    swMotor.setChecked(false);
                    swVelocidad.setChecked(false);
                    swConsumo.setChecked(false);
                }
                break;
            }
            case R.id.swVelocidad : {
                if (isChecked) {
                    swCilindros.setChecked(false);
                    swPotencia.setChecked(false);
                    swRevoluciones.setChecked(false);
                    swMotor.setChecked(false);
                    swConsumo.setChecked(false);
                }
                break;
            }
            case R.id.swConsumo : {
                if (isChecked) {
                    swCilindros.setChecked(false);
                    swPotencia.setChecked(false);
                    swRevoluciones.setChecked(false);
                    swVelocidad.setChecked(false);
                    swMotor.setChecked(false);
                }
                break;
            }
        }
    }
}
