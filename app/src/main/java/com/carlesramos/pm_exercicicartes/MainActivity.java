package com.carlesramos.pm_exercicicartes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.carlesramos.pm_exercicicartes.fragments.GameScreenFragment;
import com.carlesramos.pm_exercicicartes.interfaces.IButtonSelected;

public class MainActivity extends AppCompatActivity implements IButtonSelected {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GameScreenFragment gameScreenFragment = new GameScreenFragment(this);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.contenedor, gameScreenFragment)
        .commit();
    }

    @Override
    public void onButtonSelected(View v) {
        switch (v.getId()){
            case R.id.btScores : {
                Toast.makeText(this, "Scores", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btPlayGame : {
                Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btExit : {
                Toast.makeText(this, "Exit", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
