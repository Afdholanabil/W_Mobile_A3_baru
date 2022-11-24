package com.example.recyclick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Adapter.AdapterDataBarang;
import com.example.recyclick.Model.DataBarang.BarangGetInfo;
import com.example.recyclick.Model.DataBarang.DataItem;
import com.example.recyclick.Model.DataBarang.DeleteBarang;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataBarangActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lymanager;
    private List<DataItem> listdata = new ArrayList<>();
    public static DataBarangActivity dba;
    APIRequestData API;
    String pesan;
    FloatingActionButton tambahData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_barang);
        recyclerView = (RecyclerView)findViewById(R.id.rcy_barang);
        dba = this;
        lymanager = new LinearLayoutManager(DataBarangActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lymanager);
        tambahData = (FloatingActionButton) findViewById(R.id.btn_addBarang);
        tambahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DataBarangActivity.this, TambahBarangActivity.class));
            }
        });

        tampilDataBarang();
    }

    public void tampilDataBarang(){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<BarangGetInfo> call = API.getDataBarang();
        call.enqueue(new Callback<BarangGetInfo>() {
            @Override
            public void onResponse(Call<BarangGetInfo> call, Response<BarangGetInfo> response) {
                pesan = response.body().getMessage();
                listdata = response.body().getData();

                adapter = new AdapterDataBarang(DataBarangActivity.this, listdata);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<BarangGetInfo> call, Throwable t) {

            }
        });
    }

    public void hapusDataBarang(String idprd){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<DeleteBarang> call = API.postDeleteBarang(idprd);
        call.enqueue(new Callback<DeleteBarang>() {
            @Override
            public void onResponse(Call<DeleteBarang> call, Response<DeleteBarang> response) {
                if(response.isSuccessful() && response.body() != null){
                    String pesan = response.body().getPesan();
                    if(response.body().isKondisi() == true){
                        Toast.makeText(DataBarangActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(DataBarangActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DeleteBarang> call, Throwable t) {
                Log.e("error", "onFailure: "+t );
            }
        });
    }

    public void editDataBarang(String idbr){

    }
}