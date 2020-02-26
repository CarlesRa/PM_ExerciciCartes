package com.carlesramos.pm_exercicicartes.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.carlesramos.pm_exercicicartes.R;
import com.carlesramos.pm_exercicicartes.interfaces.IButtonSelected;

/**
 * @author Juan Carlos Ramos Moll
 * Clase controladora de la pantalla principal del juego.
 */
public class WelcomeGameScreenFragment extends Fragment implements View.OnClickListener {

    private IButtonSelected listener;
    private Button btScores;
    private Button btPlayGame;
    private Button btExit;
    private Button btPrefs;
    private Button btDeveloperMode;

    public WelcomeGameScreenFragment(IButtonSelected listener){
        this.listener = listener;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.welcome_game_screen_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btExit = getActivity().findViewById(R.id.btMetaGame);
        btPlayGame = getActivity().findViewById(R.id.btPlayGame);
        btScores = getActivity().findViewById(R.id.btScores);
        btPrefs = getActivity().findViewById(R.id.btPrefs);
        btDeveloperMode = getActivity().findViewById(R.id.btDeveloperMode);

        btScores.setOnClickListener(this);
        btPlayGame.setOnClickListener(this);
        btExit.setOnClickListener(this);
        btPrefs.setOnClickListener(this);
        btDeveloperMode.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        habilitarDeveloperMode();
    }

    @Override
    public void onClick(View v) {
        listener.onButtonSelected(v);
    }

    /**
     * Se encarga de leer en las preferencias y habilita-deshabilita el botón
     * de "Developer Mode".
     */
    public void habilitarDeveloperMode() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean habilitado = prefs.getBoolean("modedeveloper", false);
        if (habilitado) {
            btDeveloperMode.setVisibility(View.VISIBLE);
        }
        else {
            btDeveloperMode.setVisibility(View.GONE);
        }
    }
}
