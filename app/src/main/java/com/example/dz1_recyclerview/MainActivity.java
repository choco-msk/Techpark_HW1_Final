package com.example.dz1_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.dz1_recyclerview.R;

public class MainActivity extends AppCompatActivity implements ActivityAccess {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // создаем фрагмент главного окна
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fr_place, new FragmentMain())
                    .commit();
        }

    }

    @Override
    public void onItemClick(int digit) {
        // Заменяем фрагмент айтема списка
        Fragment fragment = FragmentClose.newInstance(digit);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_place, fragment)
                .addToBackStack("Fragment close")
                .commit();

    }

}
