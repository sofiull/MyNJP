package com.example.mynjp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mynjp.fragments.LoginFragment;
import com.example.mynjp.fragments.RegisterFragment;
import com.example.mynjp.model.User;

public class LoginActivity extends AppCompatActivity {
    private EditText namainput;
    private EditText alamatinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        namainput = findViewById(R.id.input_email);
        alamatinput = findViewById(R.id.input_password);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new LoginFragment())
                .commit();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu,menu);
//        return true;
//    }

    public void submit(View view) {
        String nama = namainput.getText().toString();
        String alamat = alamatinput.getText().toString();
        if(!TextUtils.isEmpty(nama) && !TextUtils.isEmpty(alamat)) {
            User user = new User(nama, alamat);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("USER_DATA", user);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "Harap lengkapi data untuk memulai", Toast.LENGTH_SHORT).show();
        }
    }
}
