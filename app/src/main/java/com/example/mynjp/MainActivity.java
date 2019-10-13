package com.example.mynjp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynjp.fragments.AboutFragment;
import com.example.mynjp.fragments.HomeFragment;
import com.example.mynjp.fragments.RatesFragment;
import com.example.mynjp.model.User;

public class MainActivity extends AppCompatActivity implements RatesFragment.OnFragmentInteractionListener {
    // variable fragment
    private HomeFragment homeFragment;
    private AboutFragment aboutFragment;
    //  variable data
    private String nama, alamat;
    long previous;

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
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_container, aboutFragment,"ABOUT")
                .commit();
    }

    public void ShowHome(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, homeFragment,"HOME")
                .commit();
    }

    public void ShowRates(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new RatesFragment(),"RATES")
                .commit();
    }

    @Override
    public void onBackPressed() {
        if(2000+previous>(previous=System.currentTimeMillis())){
            super.onBackPressed();
        }else{
            Toast toast = Toast.makeText(this,"Click one more time to exit",Toast.LENGTH_SHORT);
            TextView text = (TextView) toast.getView().findViewById(android.R.id.message);
            text.setTextColor(Color.WHITE);
            text.setTextSize(14);
            toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
            toast.getView().getBackground().setColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_IN);
            toast.show();
        }
    }

    public void onCheckButtonClicked() {
    }

}
