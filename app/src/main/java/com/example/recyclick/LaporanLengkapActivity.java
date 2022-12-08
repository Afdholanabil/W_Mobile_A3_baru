package com.example.recyclick;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Adapter.AdapterLaporan;
import com.example.recyclick.Model.Laporan.DataItemLengkap;
import com.example.recyclick.Model.Laporan.LaporanLengkap;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporanLengkapActivity  extends AppCompatActivity {

    private RecyclerView recycle;
    private APIRequestData api;
    private List<DataItemLengkap> item = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deklarasiVariable();
        showDataLaporanLengkap();
    }

    private void deklarasiVariable(){
        setContentView(R.layout.activty_laporandata);
        recycle = findViewById(R.id.lapsdt_recycle);
        api = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
    }
    private void showDataLaporanLengkap(){
        Call<LaporanLengkap> call = api.getInfoLaporan();
        call.enqueue(new Callback<LaporanLengkap>() {
            @Override
            public void onResponse(Call<LaporanLengkap> call, Response<LaporanLengkap> response) {
                if(response.body() != null && response.isSuccessful()){
                    Log.d("berhasil", " onResponse: "+response.body().getKode()+"|| pesan "+response.body().getMessage());
                    item = response.body().getData();
                    AdapterLaporan laporan = new AdapterLaporan(item);
                    recycle.setAdapter(laporan);
                    recycle.setLayoutManager(new LinearLayoutManager(LaporanLengkapActivity.this));
                }
            }

            @Override
            public void onFailure(Call<LaporanLengkap> call, Throwable t) {

            }
        });
    }
}
