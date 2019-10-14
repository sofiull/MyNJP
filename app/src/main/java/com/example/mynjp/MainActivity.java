package com.example.mynjp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity implements AboutFragment.OnFragmentInteractionListener {

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

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void ShowAbout(View view) {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_container, new AboutFragment(),"ABOUT")
                .commit();
    }

    public void ShowHome(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment(),"HOME")
                .commit();
    }

    public void ShowRates(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new RatesFragment(),"RATES")
                .commit();
    }

    public void onsaveButtonClicked(String nama, String alamat){
        this.nama = nama;
        this.alamat = alamat;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,new HomeFragment())
                .commit();
    }

    @Override
    public void onlogoutButtonCLicked() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
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

}
