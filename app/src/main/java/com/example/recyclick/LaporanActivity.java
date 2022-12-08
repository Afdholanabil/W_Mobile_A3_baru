package com.example.recyclick;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Model.Laporan.DataItemUtama;
import com.example.recyclick.Model.Laporan.LaporanUtama;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporanActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lymanager;
    private CardView card;
    public static DataBarangActivity dba;
    APIRequestData API;
    List<DataItemUtama> item;
    TextView penjualan, dikirim, penjualanbulan, penjualantahun;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);
        deklarasiVariabel();
        showLaporan();
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LaporanActivity.this, LaporanLengkapActivity.class));
            }
        });


    }
    public void deklarasiVariabel(){
        penjualan = findViewById(R.id.laps_text1);
        dikirim = findViewById(R.id.laps_text2);
        penjualanbulan = findViewById(R.id.laps_text3);
        penjualantahun = findViewById(R.id.laps_text4);
        card = findViewById(R.id.laps_carddatalengkap);
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
    }

    public void showLaporan(){
        Call<LaporanUtama> call = API.getInfoLaporanUtama();
        call.enqueue(new Callback<LaporanUtama>() {
            @Override
            public void onResponse(Call<LaporanUtama> call, Response<LaporanUtama> response) {
                if(response.isSuccessful() && response.body() != null){
                    Log.d("berhasil", "kode "+response.body().getKode()+" || message "+response.body().getMessage());
                    item = response.body().getData();
                    int nilai = item.get(0).getBarangTerjual();
                    int nilai2 = item.get(0).getBarangDiantar();
                    int nilai3 = item.get(0).getPendapatanSebulan();
                    int nilai4 = item.get(0).getPendapatanSetahun();

                    penjualan.setText(nilai+" barang");
                    dikirim.setText(nilai2+" barang");
                    penjualanbulan.setText("Rp "+nilai3);
                    penjualantahun.setText("Rp "+nilai4);
                }
            }

            @Override
            public void onFailure(Call<LaporanUtama> call, Throwable t) {
                Log.d("error", "onFailure: "+t.getMessage());
            }
        });
    }

}
