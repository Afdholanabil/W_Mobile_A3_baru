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
import com.example.recyclick.Model.DataKaryawan.EditKaryawan;
import com.example.recyclick.Model.Login.EditProfil;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    TextView btnBack;
    TextInputEditText tUser,tPassLama,tPassBaru,tNama,tHp;
    int role;
    CardView btnSimpan;
    APIRequestData API;
    String pesan;
    public static EditProfileActivity dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        btnBack = (TextView) findViewById(R.id.btn_back);
        tUser = findViewById(R.id.inputUsername);
        tPassLama = findViewById(R.id.inputPassLama);
        tPassBaru = findViewById(R.id.inputPassBaru);
        tNama = findViewById(R.id.inputNama);
        tHp = findViewById(R.id.inputNoHp);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        role = 2;
        tNama.setText(SaveAccount.readDataPembeli(EditProfileActivity.this).getNama());
        tUser.setText(SaveAccount.readDataPembeli(EditProfileActivity.this).getUsername());
        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = tUser.getText().toString();
                String pass = tPassLama.getText().toString();
                String passBaru = tPassBaru.getText().toString();
                String nama = tNama.getText().toString();
                String noHp = tHp.getText().toString();
                Intent it = getIntent();
                String passLogin = it.getStringExtra("PASS");

                if (username.equals(null) || nama.equals(null) || pass.equals(null) ||passBaru.equals(null) || noHp.equals(null) ) {
                    Toast.makeText(EditProfileActivity.this, "Data Tidak Boleh Kosong !", Toast.LENGTH_SHORT).show();
                }else {
                    if(passBaru.length()>10){
                        Toast.makeText(EditProfileActivity.this, "Password Baru tidak boleh lebih dari 10 karakter", Toast.LENGTH_SHORT).show();
                    }else if(passBaru.length()<5){
                        Toast.makeText(EditProfileActivity.this, "Password baru harus lebih dari 5 karakter", Toast.LENGTH_SHORT).show();
                    }else if(noHp.length()>13){
                        Toast.makeText(EditProfileActivity.this, "Nomor HP anda lebih dari 13 karakter", Toast.LENGTH_SHORT).show();
                    }
                    else if (pass.equals(passLogin)){
                        EditProfile(username,passBaru,nama,noHp,role);
                        finish();
                    }else {
                        Toast.makeText(EditProfileActivity.this, "Passwrod Lama harus sama", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }

    public void EditProfile(String username, String passBaru, String nama, String noHp, int role){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<EditProfil> call = API.postEditKaryawan(username,passBaru,nama,noHp,role);
        call.enqueue(new Callback<EditProfil>() {
            @Override
            public void onResponse(Call<EditProfil> call, Response<EditProfil> response) {
                if(response.isSuccessful() && response.body() != null){
                    String pesan = response.body().getPesan();
                    if(response.body().isKondisi() == true){
                        Toast.makeText(EditProfileActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(EditProfileActivity.this, pesan, Toast.LENGTH_SHORT).show();
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