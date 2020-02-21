package com.carlesramos.pm_exercicicartes.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.carlesramos.pm_exercicicartes.R;
import com.carlesramos.pm_exercicicartes.fragments.WelcomeGameScreenFragment;
import com.carlesramos.pm_exercicicartes.interfaces.IButtonSelected;

public class MainActivity extends AppCompatActivity implements IButtonSelected {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        WelcomeGameScreenFragment welcomeGameScreenFragment = new WelcomeGameScreenFragment(this);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.contenedor, welcomeGameScreenFragment)
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
