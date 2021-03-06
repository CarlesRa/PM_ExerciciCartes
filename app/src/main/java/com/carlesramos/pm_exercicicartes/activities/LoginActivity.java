package com.carlesramos.pm_exercicicartes.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.carlesramos.pm_exercicicartes.R;
import com.carlesramos.pm_exercicicartes.apiclient.APIUtils;
import com.carlesramos.pm_exercicicartes.configurations.Configurations;
import com.carlesramos.pm_exercicicartes.interfaces.IApiInterface;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Juan Carlos Ramos
 * Clase controladora de la pantalla de login.
 */
public class LoginActivity extends AppCompatActivity{

    private EditText etNombre;
    private EditText etPasswd;
    private Button btLogin;
    private IApiInterface mApiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        mApiInterface = APIUtils.getIApiInterface();
        etNombre = findViewById(R.id.etNickName);
        etPasswd = findViewById(R.id.etPasswd);
        btLogin = findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNombre.getText().length() > 0 && etPasswd.getText().length() > 0){
                    getResultadoLogin();
                }
                else Toast.makeText(LoginActivity.this,
                        getText(R.string.not_null), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Se encarga de comunicar con la Api para ejecutar el método de comprobación de login.
     * En caso de no estar registrado nos muestra un diálogo para confirmar si el usuario
     * quiere registrarse. si pulsa "si" abrirá el activity de registro.
     */
    public void getResultadoLogin(){

        mApiInterface.validarLogin(etNombre.getText().toString(),
                etPasswd.getText().toString()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()){

                    // Si no se encuentra en la base de datos.
                    if (response.body().equals(Configurations.LOGIN_FAIL)){
                        Toast.makeText(LoginActivity.this, response.body(),
                                Toast.LENGTH_SHORT).show();
                        //Creo el diálogo
                        AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this)
                                .create();
                        alertDialog.setTitle("Login Fail!");
                        alertDialog.setMessage(getText(R.string.passwordfail));
                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getText(R.string.no),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        etPasswd.setText("");
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getText(R.string.yes),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent i = new Intent(LoginActivity.this,
                                                RegistroActivity.class);
                                        i.putExtra(Configurations.EXTRA_NICKNAME,etNombre.getText().
                                                toString());
                                        i.putExtra(Configurations.EXTRA_PASSWORD,etPasswd.getText().
                                                toString());
                                        startActivity(i);
                                    }
                                });

                        alertDialog.show();
                    }
                    //En el caso que este en la base de datos abro la activity de juego
                    else {
                        String [] datos = response.body().split(" ");
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        i.putExtra(Configurations.EXTRA_IDSESSION, datos[0]);
                        i.putExtra(Configurations.EXTRA_IDCLIENTE, datos[1]);
                        startActivity(i);
                    }
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
