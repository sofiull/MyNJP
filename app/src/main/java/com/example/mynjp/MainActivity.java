package com.example.mynjp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynjp.fragments.AboutFragment;
import com.example.mynjp.fragments.HomeFragment;
import com.example.mynjp.fragments.RatesFragment;
import com.example.mynjp.model.User;
import com.example.mynjp.util.RatesCalc;

public class MainActivity extends AppCompatActivity implements AboutFragment.OnFragmentInteractionListener,RatesFragment.OnFragmentInteractionListener {

    //  variable data
    private static final int GALLERY_REQUEST_CODE = 1;
    private String nama, alamat;
    private Uri imageUrl,imageUrlTemp;
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

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        AboutFragment aboutFragment = (AboutFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        ImageView avatar = aboutFragment.getView().findViewById(R.id.profilImage);
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_REQUEST_CODE){
            if(data!=null) {
                try{
                    imageUrlTemp=data.getData();
                    Uri imageUri = imageUrlTemp;
                    avatar.setImageURI(imageUri);
                }catch (Exception e){
                    Toast.makeText(this, "Can't Load image "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public Uri getImageUrlTemp() {
        return imageUrlTemp;
    }

    public Uri getImageUrl() {
        return imageUrl;
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

    public void onchangeAvatarButtonClicked() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    public void onlogoutButtonCLicked() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void onsaveButtonClicked(String nama, String alamat){
        this.nama = nama;
        this.alamat = alamat;
        this.imageUrl = imageUrlTemp;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,new HomeFragment())
                .commit();
    }

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

    public void  oncheckButtonClicked(String berat, int originLocation, int destinationLocation){
        RatesFragment ratesFragment = (RatesFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        TextView hargaYES=ratesFragment.getView().findViewById(R.id.harga1text);
        TextView hargaREG=ratesFragment.getView().findViewById(R.id.harga2text);
        TextView beratText=ratesFragment.getView().findViewById(R.id.weightText);

        //  Check kondisi jika berat = kosong
        if(TextUtils.isEmpty(berat)){
            Toast.makeText(this, "Masukkan berat barang", Toast.LENGTH_SHORT).show();
        }
        // Check kondisi jika berat = 0
        if(!TextUtils.isEmpty(berat) && Float.parseFloat(berat) <= 0) {
            Toast.makeText(this, "Berat harus lebih dari 0", Toast.LENGTH_SHORT).show();
        }
        // Check kondisi jika berat != kosong dan berat > 0
        if(!TextUtils.isEmpty(berat) && Float.parseFloat(berat) > 0) {
            RatesCalc ratesCalc = new RatesCalc(originLocation, destinationLocation, Float.parseFloat(berat));
            hargaYES.setText("Rp. " + ratesCalc.getRatesYES() + " -- 1 Hari Sampai");
            hargaREG.setText("Rp. " + ratesCalc.getRatesREG() + " -- 2-4 Hari Sampai");
            beratText.setText(berat+" KG");
        }
    }

    public void onincreaseButtonClicked(String berat){
        RatesFragment ratesFragment = (RatesFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        EditText beratInput=ratesFragment.getView().findViewById(R.id.weightInput);
        if(TextUtils.isEmpty(berat)){
            beratInput.setText(0.5+"");
        }else {
            beratInput.setText(Float.parseFloat(berat) + 0.5 + "");
        }
    }

    public void ondecreaseButtonClicked(String berat){
        RatesFragment ratesFragment = (RatesFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        EditText beratInput=ratesFragment.getView().findViewById(R.id.weightInput);
        if (TextUtils.isEmpty(berat) || Float.parseFloat(berat) <= 0 ) {
            beratInput.setText("0.0");
        } else {
            beratInput.setText(Float.parseFloat(berat) - 0.5 + "");
        }
    }

    public void onresetButtonClicked(){
        RatesFragment ratesFragment = (RatesFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        TextView hargaYES=ratesFragment.getView().findViewById(R.id.harga1text);
        TextView hargaREG=ratesFragment.getView().findViewById(R.id.harga2text);
        TextView beratText=ratesFragment.getView().findViewById(R.id.weightText);
        TextView berat=ratesFragment.getView().findViewById(R.id.weightInput);

        hargaYES.setText("Rp. 0 -- 1 Hari Sampai");
        hargaREG.setText("Rp. 0 -- 2-4 Hari Sampai");
        beratText.setText("0.0 KG");
        berat.setText("1.0");
    }
}
