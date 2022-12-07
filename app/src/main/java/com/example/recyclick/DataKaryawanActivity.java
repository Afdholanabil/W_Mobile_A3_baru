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
import com.example.recyclick.Adapter.AdapterDataBarang;
import com.example.recyclick.Adapter.AdapterKaryawan;
import com.example.recyclick.Model.DataBarang.BarangGetInfo;
import com.example.recyclick.Model.DataBarang.DataItem;
import com.example.recyclick.Model.DataBarang.DeleteBarang;
import com.example.recyclick.Model.DataKaryawan.DeleteKaryawan;
import com.example.recyclick.Model.DataKaryawan.KaryawanGetInfo;
import com.example.recyclick.Model.DataKaryawan.KaryawanItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataKaryawanActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lymanager;
    private List<KaryawanItem> listdata = new ArrayList<>();
    public static DataKaryawanActivity dba;
    APIRequestData API;
    String pesan;
    FloatingActionButton tambahData;
    TextView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_karyawan);
        recyclerView = (RecyclerView) findViewById(R.id.rcyKaryawan);
        dba = this;
        lymanager = new LinearLayoutManager(DataKaryawanActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lymanager);
        tambahData = (FloatingActionButton) findViewById(R.id.btnAddKaryawan);
        tambahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DataKaryawanActivity.this, new TambahKaryawanActivity().getClass()));
            }
        });
        tampilDataKaryawan();

        btnBack = (TextView)findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void tampilDataKaryawan(){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<KaryawanGetInfo> call = API.getKaryawanData();
        call.enqueue(new Callback<KaryawanGetInfo>() {
            @Override
            public void onResponse(Call<KaryawanGetInfo> call, Response<KaryawanGetInfo> response) {
                pesan = response.body().getMessage();
                listdata = response.body().getData();
                Log.e("data", "onResponse: "+listdata );
                adapter = new AdapterKaryawan(DataKaryawanActivity.this,listdata);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Toast.makeText(DataKaryawanActivity.this, pesan, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<KaryawanGetInfo> call, Throwable t) {
                Toast.makeText(DataKaryawanActivity.this,pesan, Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void hapusDataKaryawan(String idprd){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<DeleteKaryawan> call = API.postDeleteKaryawan(idprd);
        call.enqueue(new Callback<DeleteKaryawan>() {
            @Override
            public void onResponse(Call<DeleteKaryawan> call, Response<DeleteKaryawan> response) {
                if(response.isSuccessful() && response.body() != null){
                    String pesan = response.body().getPesan();
                    if(response.body().isKondisi() == true){
                        Toast.makeText(DataKaryawanActivity.this, pesan, Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(DataKaryawanActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DeleteKaryawan> call, Throwable t) {
                Log.e("error", "onFailure: "+t );
            }

        });
    }
}