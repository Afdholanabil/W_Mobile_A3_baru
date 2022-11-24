package com.example.recyclick;

<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Model.DataBarang.AddBarang;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahBarangActivity extends AppCompatActivity {

    EditText id, nama, stok, harga, deksripsi, kategori;
    Button simpan;
    int rating;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahbarang);
        id = findViewById(R.id.idPrd);
        nama = findViewById(R.id.namaPrd);
        stok = findViewById(R.id.stokPrd);
        harga = findViewById(R.id.hargaprd);
        deksripsi = findViewById(R.id.deskPrd);
        kategori = findViewById(R.id.kategoriprd);
        simpan = findViewById(R.id.btn_spn);
        rating = 5;
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idbr = id.getText().toString();
                String namabr = nama.getText().toString();
                int stokbr = Integer.parseInt(String.valueOf(stok.getText()));
                int hargabr = Integer.parseInt(String.valueOf(harga.getText()));
                String deskripsi = deksripsi.getText().toString();
                int Kategoribr = Integer.parseInt(String.valueOf(kategori.getText()));
                if (idbr.equals(null) || namabr.equals(null) || stok.equals(null) || harga.equals(null) || deskripsi.equals(null) || kategori.equals(null)) {
                    Toast.makeText(TambahBarangActivity.this, "data Tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else{
                    addProduk(idbr, namabr, stokbr, hargabr, deskripsi, Kategoribr, rating);
                }

            }
        });

    }

    public void addProduk(String id, String nama, int stok, int harga, String deskripsi, int kategori, int rating) {
        String img = "imgae";
        APIRequestData api = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<AddBarang> call = api.postDataBarang(id, nama, stok, harga, img, deskripsi, kategori, rating);
        call.enqueue(new Callback<AddBarang>() {
            @Override
            public void onResponse(Call<AddBarang> call, Response<AddBarang> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isKondisi() == true) {
                        Toast.makeText(TambahBarangActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(TambahBarangActivity.this, DataBarangActivity.class));
                    } else {
                        Toast.makeText(TambahBarangActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<AddBarang> call, Throwable t) {

            }
        });
    }


}
=======
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TambahBarangActivity extends AppCompatActivity {
TextView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_barang);
        btnBack = (TextView) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TambahBarangActivity.this, new DataBarangActivity().getClass()));
            }
        });
    }
}
>>>>>>> f9300b5 (commit 3)
