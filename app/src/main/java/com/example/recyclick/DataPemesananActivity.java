package com.example.recyclick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Adapter.AdapterDataPemesanan;
import com.example.recyclick.Adapter.AdapterInfoPemesanan;
import com.example.recyclick.Model.DataBarang.EditBarang;
import com.example.recyclick.Model.Pembeli.EditPembeli;
import com.example.recyclick.Model.Pembeli.PembeliData;
import com.example.recyclick.Model.Transaksi.transaksiData;
import com.example.recyclick.Model.Transaksi.transaksiInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataPemesananActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lymanager;
    private List<transaksiData> listdata = new ArrayList<>();
    private List<PembeliData> listdata2 = new ArrayList<>();
    public static DataPemesananActivity dba;
    APIRequestData API;
    String pesan;
    TextView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pemesanan);
        recyclerView = (RecyclerView)findViewById(R.id.rcyTransaksi);
        dba = this;
        lymanager = new LinearLayoutManager(DataPemesananActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lymanager);
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tampilDataTransaksi();
        Intent intent = getIntent();
        String kodetr = intent.getStringExtra("KODETR");
        int idStatusProses = intent.getIntExtra("STATUSPROSES",1);
        int idStatusAntar = intent.getIntExtra("STATUSANTAR",2);
        int idStatusTerima = intent.getIntExtra("STATUSTERIMA",3);

        ubahTerimaStatus(kodetr,idStatusTerima);
        ubahProsesStatus(kodetr,idStatusProses);
        ubahAntarStatus(kodetr,idStatusAntar);
    }

    public void tampilDataTransaksi(){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<transaksiInfo> call = API.getTransaksiData();
        call.enqueue(new Callback<transaksiInfo>() {
            @Override
            public void onResponse(Call<transaksiInfo> call, Response<transaksiInfo> response) {
              pesan = response.body().getMessage();
                listdata = response.body().getData();
                adapter = new AdapterDataPemesanan(DataPemesananActivity.this,listdata);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<transaksiInfo> call, Throwable t) {

            }
        });
    }
    public void ubahTerimaStatus(String kodetran1,int IdStatus1){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<EditPembeli> call = API.postEditPembeli(kodetran1,IdStatus1);
        call.enqueue(new Callback<EditPembeli>() {
            @Override
            public void onResponse(Call<EditPembeli> call, Response<EditPembeli> response) {
                if(response.isSuccessful() && response.body() != null){
                    String pesan = response.body().getPesan();
                    if(response.body().isKondisi() == true){
                        Toast.makeText(DataPemesananActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(DataPemesananActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<EditPembeli> call, Throwable t) {
                Log.e("error", "onFailure: "+t );
            }

        });
    }

    public void ubahProsesStatus(String kodetran2,int IdStatus2){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<EditPembeli> call = API.postEditPembeli(kodetran2,IdStatus2);
        call.enqueue(new Callback<EditPembeli>() {
            @Override
            public void onResponse(Call<EditPembeli> call, Response<EditPembeli> response) {
                if(response.isSuccessful() && response.body() != null){
                    String pesan = response.body().getPesan();
                    if(response.body().isKondisi() == true){
                        Toast.makeText(DataPemesananActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(DataPemesananActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<EditPembeli> call, Throwable t) {
                Log.e("error", "onFailure: "+t );
            }

        });
    }

    public void ubahAntarStatus(String kodetran3,int IdStatus3){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<EditPembeli> call = API.postEditPembeli(kodetran3,IdStatus3);
        call.enqueue(new Callback<EditPembeli>() {
            @Override
            public void onResponse(Call<EditPembeli> call, Response<EditPembeli> response) {
                if(response.isSuccessful() && response.body() != null){
                    String pesan = response.body().getPesan();
                    if(response.body().isKondisi() == true){
                        Toast.makeText(DataPemesananActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(DataPemesananActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<EditPembeli> call, Throwable t) {
                Log.e("error", "onFailure: "+t );
            }

        });
    }
}