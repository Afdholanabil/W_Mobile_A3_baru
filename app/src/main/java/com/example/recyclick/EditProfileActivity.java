package com.example.recyclick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class EditProfileActivity extends AppCompatActivity {
TextView btnBack;
TextInputEditText tUser,tPassLama,tPassBaru,tNama,tHp;
CardView btnSimpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        btnBack = (TextView) findViewById(R.id.btn_back);
        tUser = findViewById(R.id.inputUsername);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tUser.setText(SaveAccount.readDataPembeli(EditProfileActivity.this).getUsername());
        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}