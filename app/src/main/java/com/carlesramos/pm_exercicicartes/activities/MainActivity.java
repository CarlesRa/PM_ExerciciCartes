package com.carlesramos.pm_exercicicartes.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.carlesramos.pm_exercicicartes.R;
import com.carlesramos.pm_exercicicartes.configurations.Configurations;
import com.carlesramos.pm_exercicicartes.fragments.GameScreenFragment;
import com.carlesramos.pm_exercicicartes.fragments.PreferencesFragment;
import com.carlesramos.pm_exercicicartes.fragments.RankingCartasFragment;
import com.carlesramos.pm_exercicicartes.fragments.RankingFragment;
import com.carlesramos.pm_exercicicartes.fragments.WelcomeGameScreenFragment;
import com.carlesramos.pm_exercicicartes.interfaces.IButtonSelected;

/**
 * @author Juan Carlos Ramos
 * Clase encargada de alojar los fragments relacionados con el juego.
 */
public class MainActivity extends AppCompatActivity implements IButtonSelected {

    private FragmentManager manager;
    private String idSession;
    private int idCliente;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        WelcomeGameScreenFragment welcomeGameScreenFragment =
                new WelcomeGameScreenFragment(this);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.contenedor, welcomeGameScreenFragment)
        .commit();
        idSession = getIntent().getStringExtra(Configurations.EXTRA_IDSESSION);
        idCliente = Integer.valueOf(getIntent().getStringExtra(Configurations.EXTRA_IDCLIENTE));
        preferences = this.getSharedPreferences("preferencias", Context.MODE_PRIVATE);

    }

    /**
     * Listener para los botones de la pantalla principal
     * dependiendo de la ID del View reemplaza por el fragment correspondiente.
     * @param v le pasamos un View.
     */
    @Override
    public void onButtonSelected(View v) {
        switch (v.getId()){
            case R.id.btScores : {
                RankingFragment rankingFragment = new RankingFragment();
                manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.contenedor, rankingFragment)
                        .addToBackStack(null).commit();
                break;
            }
            case R.id.btPlayGame : {

                GameScreenFragment gameScreenFragment = new GameScreenFragment(idSession, idCliente);
                manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.contenedor, gameScreenFragment)
                        .addToBackStack(null).commit();
                break;
            }
            case R.id.btMetaGame: {
                RankingCartasFragment rankingCartasFragment = new RankingCartasFragment();
                manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.contenedor,rankingCartasFragment)
                        .addToBackStack(null).commit();
                break;
            }
            case R.id.btPrefs : {
                PreferencesFragment fragment = new PreferencesFragment();
                manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.contenedor, fragment)
                        .addToBackStack(null).commit();
            }
        }
    }
}
