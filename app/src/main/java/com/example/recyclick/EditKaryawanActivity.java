package com.example.recyclick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Model.DataBarang.EditBarang;
import com.example.recyclick.Model.Login.EditProfil;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditKaryawanActivity extends AppCompatActivity {
    private static final String TAG = "EditKaryawanActivity";
    APIRequestData API;
    String pesan;
    public static EditKaryawanActivity dba;
    TextInputEditText tUser,tpass,tpassBaru,tNama,tNohp;
    CardView btnSimpan;
    TextView btnBack;
    int role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_karyawan);
        role =2;
        tUser = findViewById(R.id.inputUsername);
        tpass = findViewById(R.id.inputPassLama);
        tpassBaru = findViewById(R.id.inputPassBaru);
        tNama = findViewById(R.id.inputNama);
        tNohp = findViewById(R.id.inputNoHp);
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent it = getIntent();
        String userKr =it.getStringExtra("USER");
        tUser.setText(userKr);

        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = tUser.getText().toString();
                String pass = tpass.getText().toString();
                String passBaru = tpassBaru.getText().toString();
                String nama = tNama.getText().toString();
                String noHp = tNohp.getText().toString();
                Intent it2 = getIntent();
                String passKr = it2.getStringExtra("PASS");

                if (username.equals(null) || nama.equals(null) || pass.equals(null) ||passBaru.equals(null) || noHp.equals(null) ) {
                    Toast.makeText(EditKaryawanActivity.this, "Data Tidak Boleh Kosong !", Toast.LENGTH_SHORT).show();
                }else {
                    if(passBaru.length()>10){
                        Toast.makeText(EditKaryawanActivity.this, "Password Baru tidak boleh lebih dari 10 karakter", Toast.LENGTH_SHORT).show();
                    }else if(passBaru.length()<5){
                        Toast.makeText(EditKaryawanActivity.this, "Password baru harus lebih dari 5 karakter", Toast.LENGTH_SHORT).show();
                    }else if(noHp.length()>13){
                        Toast.makeText(EditKaryawanActivity.this, "Nomor HP anda lebih dari 13 karakter", Toast.LENGTH_SHORT).show();
                    }
                    else if (pass.equals(passKr)){
                        EditDataKaryawan(username,passBaru,nama,noHp,role);
                        finish();
                    }else {
                        Toast.makeText(EditKaryawanActivity.this, "Passwrod Lama harus sama", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    public void EditDataKaryawan(String username,String pass,String nama, String noHp,int role){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<EditProfil> call = API.postEditKaryawan(username,pass,nama,noHp,role);
        call.enqueue(new Callback<EditProfil>() {
            @Override
            public void onResponse(Call<EditProfil> call, Response<EditProfil> response) {
                if(response.isSuccessful() && response.body() != null){
                    String pesan = response.body().getPesan();
                    if(response.body().isKondisi() == true){
                        Toast.makeText(EditKaryawanActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(EditKaryawanActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<EditProfil> call, Throwable t) {
                Log.e("error", "onFailure: "+t );
            }

        });
    }
}