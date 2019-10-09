package com.example.mynjp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mynjp.fragments.AboutFragment;
import com.example.mynjp.fragments.HomeFragment;
import com.example.mynjp.fragments.RatesFragment;
import com.example.mynjp.model.User;

public class MainActivity extends AppCompatActivity {
    private HomeFragment homeFragment;
    private AboutFragment aboutFragment;

    private String nama;
    private String alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Todo: Mengambil data parcelable dari Login
        User user = getIntent().getExtras().getParcelable("USER_DATA");
        nama = user.getNama();
        alamat = user.getAlamat();

        // Todo: Melakukan intance ke fragment
        homeFragment = HomeFragment.newInstance(nama);
        aboutFragment = AboutFragment.newInstance(nama,alamat);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, homeFragment)
                .commit();

    }

    public void ShowAbout(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, aboutFragment).commit();
    }

    public void ShowHome(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    public void ShowRates(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RatesFragment()).commit();
    }

}
