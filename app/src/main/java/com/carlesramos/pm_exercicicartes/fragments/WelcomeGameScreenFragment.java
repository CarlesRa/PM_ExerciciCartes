package com.carlesramos.pm_exercicicartes.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.carlesramos.pm_exercicicartes.R;
import com.carlesramos.pm_exercicicartes.interfaces.IButtonSelected;

public class WelcomeGameScreenFragment extends Fragment implements View.OnClickListener {

    private IButtonSelected listener;
    private Button btScores;
    private Button btPlayGame;
    private Button btExit;

    public WelcomeGameScreenFragment(IButtonSelected listener){
        this.listener = listener;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.welcome_game_screen_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btExit = getActivity().findViewById(R.id.btExit);
        btPlayGame = getActivity().findViewById(R.id.btPlayGame);
        btScores = getActivity().findViewById(R.id.btScores);

        btScores.setOnClickListener(this);
        btPlayGame.setOnClickListener(this);
        btExit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        listener.onButtonSelected(v);
    }
}
