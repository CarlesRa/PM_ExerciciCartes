package com.carlesramos.pm_exercicicartes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.carlesramos.pm_exercicicartes.R;
import com.carlesramos.pm_exercicicartes.apiclient.APIUtils;
import com.carlesramos.pm_exercicicartes.configurations.Configurations;
import com.carlesramos.pm_exercicicartes.interfaces.IApiInterface;
import com.carlesramos.pm_exercicicartes.model.Jugadores;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Juan Carlos Rmamos
 * activity encargada del registro de usuarios.
 */
public class RegistroActivity extends AppCompatActivity {

    private EditText etNickNameRegistro;
    private EditText etPasswordRegistro;
    private EditText etEmailRegistro;
    private Button btSingIn;
    private IApiInterface mApiInterface;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mApiInterface = APIUtils.getIApiInterface();
        etNickNameRegistro = findViewById(R.id.etNickNamRegistre);
        etPasswordRegistro = findViewById(R.id.etPasswordRegistre);
        etEmailRegistro = findViewById(R.id.etEmailRegistre);
        btSingIn = findViewById(R.id.btSignIn);

        String nickName = getIntent().getStringExtra(Configurations.EXTRA_NICKNAME);
        String passwd = getIntent().getStringExtra(Configurations.EXTRA_PASSWORD);
        etNickNameRegistro.setText(nickName);
        etPasswordRegistro.setText(passwd);

        btSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistroActivity.this,
                        LoginActivity.class);
                if (etEmailRegistro.getText().length() > 0 && 
                        etNickNameRegistro.getText().length() > 0 &&
                        etPasswordRegistro.getText().length() > 0){
                    nickNameExists(etNickNameRegistro.getText().toString());
                }
                else{
                    Toast.makeText(RegistroActivity.this, getText(R.string.not_null),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * inserta un jugador en la base de datos a través de la api
     * @param jugador Recibe un objeto de tipo jugador
     */
    public void insertarJugador(Jugadores jugador){
        Gson g = new Gson();
        String j = g.toJson(jugador);
        mApiInterface.insertarJugador(j).enqueue(new Callback<Jugadores>() {
            @Override
            public void onResponse(Call<Jugadores> call, Response<Jugadores> response) {
                if (response.isSuccessful()){
                    Toast.makeText(RegistroActivity.this,
                            getText(R.string.registry_ok), Toast.LENGTH_SHORT).show();
                    finish();
                }
                else Toast.makeText(RegistroActivity.this, "una gena", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Jugadores> call, Throwable t) {

            }
        });
    }

    /**
     * Comprueba si existe algun usuario registrado con el mismo nickName
     * @param  nicName le pasamos el nickName del nuevo jugador a registrar
     */
    public void nickNameExists(final String nicName){
        mApiInterface.nickNameExists(nicName).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    if (response.body().equals("false")){
                        Jugadores jugador = new Jugadores(etNickNameRegistro.getText().toString(),
                                etEmailRegistro.getText().toString(), etPasswordRegistro.getText().toString(),
                                0,0,0);
                        insertarJugador(jugador);

                    }
                    else{
                        Toast.makeText(RegistroActivity.this,
                                getText(R.string.is_nickname), Toast.LENGTH_SHORT).show();
                        etNickNameRegistro.setText("");
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(RegistroActivity.this, "falla", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
