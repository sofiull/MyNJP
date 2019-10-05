package com.example.mynjp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mynjp.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeFragment = new HomeFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, homeFragment)
                .commit();

    }
}
