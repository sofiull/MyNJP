package com.example.mynjp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.example.mynjp.model.User;

public class LoginActivity extends AppCompatActivity {
    private EditText namainput;
    private EditText alamatinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        namainput = findViewById(R.id.input_name);
        alamatinput = findViewById(R.id.input_alamat);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu,menu);
//        return true;
//    }

    public void submit(View view) {
        String nama = namainput.getText().toString();
        String alamat = alamatinput.getText().toString();

        User user = new User(nama,alamat);
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("USER_DATA", user);
        startActivity(intent);
    }
}
