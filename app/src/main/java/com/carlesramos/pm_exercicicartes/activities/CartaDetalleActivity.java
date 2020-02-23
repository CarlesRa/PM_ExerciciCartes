package com.carlesramos.pm_exercicicartes.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.carlesramos.pm_exercicicartes.R;
import com.carlesramos.pm_exercicicartes.apiclient.APIUtils;
import com.carlesramos.pm_exercicicartes.clasesaux.JugadaCpu;
import com.carlesramos.pm_exercicicartes.clasesaux.NuevaPartida;
import com.carlesramos.pm_exercicicartes.configurations.Configurations;
import com.carlesramos.pm_exercicicartes.fragments.GameScreenFragment;
import com.carlesramos.pm_exercicicartes.interfaces.IApiInterface;
import com.carlesramos.pm_exercicicartes.model.Cartas;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartaDetalleActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

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
    private ImageButton ibVolver;
    private Cartas carta;
    private JugadaCpu jugadaCpu;
    private NuevaPartida partida;
    private IApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_carta_detalle_activity);

        Intent i = getIntent();
        carta = (Cartas)i.getSerializableExtra(Configurations.EXTRA_CARTA);
        partida = (NuevaPartida)i.getSerializableExtra(Configurations.EXTRA_PARTIDA);
        jugadaCpu = (JugadaCpu)i.getSerializableExtra(Configurations.EXTRA_CARTA_CPU);

        swMotor = findViewById(R.id.swMotor);
        swCilindros = findViewById(R.id.swCilindros);
        swPotencia = findViewById(R.id.swPotencia);
        swRevoluciones = findViewById(R.id.swRevolucionesGame);
        swVelocidad = findViewById(R.id.swVelocidad);
        swConsumo = findViewById(R.id.swConsumo);
        tvMarca = findViewById(R.id.tvMarcaGame);
        tvModelo = findViewById(R.id.tvModeloGame);
        tvMotor = findViewById(R.id.tvMotorGame);
        tvCilindros = findViewById(R.id.tvCilindrosGame);
        tvPotencia = findViewById(R.id.tvPotenciaGame);
        tvRevoluciones = findViewById(R.id.tvRevGame);
        tvVelocidad = findViewById(R.id.tvVelocidadGame);
        tvConsumo = findViewById(R.id.tvConsumoGame);
        btPlayCard = findViewById(R.id.btPlayCard);
        ibVolver = findViewById(R.id.ibVolver);
        apiInterface = APIUtils.getIApiInterface();

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
                swPotencia.isChecked() || swRevoluciones.isChecked() || swVelocidad.isChecked()){
                    if (partida.getJugadorInicial() == 1){
                        apiInterface.comprobarJugada(partida.getIdPartida(),
                                jugadaCpu.getCarta().getIdCarta(),
                                jugadaCpu.getCaracteristica(), carta.getIdCarta()).
                                enqueue(new Callback<Integer>() {
                            @Override
                            public void onResponse(Call<Integer> call, Response<Integer> response) {
                                if (response.isSuccessful()) {
                                    String aux = response.body().toString();
                                    if (response.body().equals("1")){
                                        Toast.makeText(CartaDetalleActivity.this,
                                                "Has perdido!!", Toast.LENGTH_SHORT).show();
                                    }
                                    else if (response.body().equals("2")){
                                        Toast.makeText(CartaDetalleActivity.this,
                                                "Has Ganado!!", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(CartaDetalleActivity.this,
                                                "Empate!!", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(CartaDetalleActivity.this, 
                            "Tienes que indicar una característica!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ibVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.swMotor : {
                if (jugadaCpu.getCaracteristica().equals("Motor")) {
                    swCilindros.setChecked(false);
                    swPotencia.setChecked(false);
                    swRevoluciones.setChecked(false);
                    swVelocidad.setChecked(false);
                    swConsumo.setChecked(false);
                }
                else{
                    swMotor.setChecked(false);
                    mostrarMensajeError();
                }
                break;
            }
            case R.id.swCilindros : {
                if (jugadaCpu.getCaracteristica().equals("Cilindros")) {
                    swMotor.setChecked(false);
                    swPotencia.setChecked(false);
                    swRevoluciones.setChecked(false);
                    swVelocidad.setChecked(false);
                    swConsumo.setChecked(false);
                }
                else{
                    swCilindros.setChecked(false);
                    mostrarMensajeError();
                }
                break;
            }
            case R.id.swPotencia : {
                if (jugadaCpu.getCaracteristica().equals("Potencia")) {
                    swCilindros.setChecked(false);
                    swMotor.setChecked(false);
                    swRevoluciones.setChecked(false);
                    swVelocidad.setChecked(false);
                    swConsumo.setChecked(false);
                }
                else{
                    swPotencia.setChecked(false);
                    mostrarMensajeError();
                }
                break;
            }
            case R.id.swRevolucionesGame : {
                if (jugadaCpu.getCaracteristica().equals("Revoluciones")) {
                    swCilindros.setChecked(false);
                    swPotencia.setChecked(false);
                    swMotor.setChecked(false);
                    swVelocidad.setChecked(false);
                    swConsumo.setChecked(false);
                }
                else{
                    swRevoluciones.setChecked(false);
                    mostrarMensajeError();
                }
                break;
            }
            case R.id.swVelocidad : {

                if (jugadaCpu.getCaracteristica().equals("Velocidad")) {
                    swCilindros.setChecked(false);
                    swPotencia.setChecked(false);
                    swRevoluciones.setChecked(false);
                    swMotor.setChecked(false);
                    swConsumo.setChecked(false);
                }
                else{
                    swVelocidad.setChecked(false);
                    mostrarMensajeError();
                }
                break;
            }
            case R.id.swConsumo : {
                if (jugadaCpu.getCaracteristica().equals("Consumo")) {
                    swCilindros.setChecked(false);
                    swPotencia.setChecked(false);
                    swRevoluciones.setChecked(false);
                    swVelocidad.setChecked(false);
                }
                else{
                    swConsumo.setChecked(false);
                    mostrarMensajeError();
                }
                break;
            }
        }
    }

    public void mostrarMensajeError(){
        Toast.makeText(this, "Solo puedes seleccionar " +
                jugadaCpu.getCaracteristica(), Toast.LENGTH_SHORT).show();
    }
}
