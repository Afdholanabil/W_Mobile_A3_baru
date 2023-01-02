package com.example.recyclick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Adapter.AdapterDataBarang;
import com.example.recyclick.Adapter.AdapterKategori;
import com.example.recyclick.Adapter.AdapterSearch;
import com.example.recyclick.Model.DataBarang.BarangGetInfo;
import com.example.recyclick.Model.DataBarang.DataItem;
import com.example.recyclick.Model.DataBarang.DeleteBarang;
import com.example.recyclick.Model.Home.DataItemSearch;
import com.example.recyclick.Model.Home.SearchGetInfo;
import com.example.recyclick.Model.Kategori.KategoriInfo;
import com.example.recyclick.Model.Kategori.KategoriItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataBarangActivity extends AppCompatActivity  {
    private RecyclerView recyclerView, recyclerView2;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lymanager;
    private List<DataItem> listdata = new ArrayList<>();
    private List<DataItemSearch> item = new ArrayList<>();
    private List<KategoriItem> listdata2 = new ArrayList<>();
    public static DataBarangActivity dbanew;
    private int role ;
    APIRequestData API;
    String pesan;
    FloatingActionButton tambahData;
    BottomNavigationView bottomNavigationView;
    EditText src;
    TextView tDataKosong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_barang);
        recyclerView = (RecyclerView)findViewById(R.id.rcy_barang);
        dbanew= this;
        role = SaveAccount.readDataPembeli(DataBarangActivity.this).getUserRole();
        lymanager = new LinearLayoutManager(DataBarangActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lymanager);
//        tampilDataBarang();
        tambahData = (FloatingActionButton) findViewById(R.id.btn_addBarang);
        tambahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DataBarangActivity.this, TambahBarangActivity.class));
            }
        });

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.id_nav_home:
                        startActivity(new Intent(DataBarangActivity.this, new HomeActivity().getClass()));
                        break;
                    case R.id.id_nav_edit:
                        startActivity(new Intent(DataBarangActivity.this, new DataBarangActivity().getClass()));
                        break;
                    case R.id.id_nav_laporan:
                        if (role == 1) {
                            startActivity(new Intent(DataBarangActivity.this, new LaporanActivity().getClass()));
                            break;
                        } else {
                            Toast.makeText(DataBarangActivity.this, "Anda Tidak Dapat Menggunakan Fitur Ini !", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    case R.id.id_nav_setting:
                        startActivity(new Intent(DataBarangActivity.this, new PengaturanActivity().getClass()));

                        break;
                }
                return false;
            }
        });
        src = findViewById(R.id.txt_search);
        src.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String kata = src.getText().toString();
                showPencarian(kata);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Intent intent2 = getIntent();
        String idKat = intent2.getStringExtra("IdKat");

//
//
//
        if(idKat != null) {
            tampilProdukWithKat(idKat);

        }else{
            tampilDataBarang();
        }



        recyclerView2 = findViewById(R.id.rcy_kategoriBarang);
        dbanew = this;
        lymanager = new LinearLayoutManager(DataBarangActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(lymanager);
        tampilKategori();

        tDataKosong = findViewById(R.id.txt_kondisiProdukKosong);

    }

    public void setTeksDataKosong(){
        tDataKosong.setText("Data Produk Kosong");
    }

    public void hideteksKosong(){
        findViewById(R.id.txt_kondisiProdukKosong).setVisibility(View.GONE);
    }
//    public void kirimBarang(){
//        SharedPreferences sharedPreferences = getSharedPreferences("PREF_MODEL", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        SaveDataBarang.writeDataBarang(DataBarangActivity.this,listdata);
//    }

    public void tampilDataBarang(){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        View view = getLayoutInflater().inflate(R.layout.toast_loading, null);
        view.findViewById(R.id.toast_noConnection);
        Toast toast = new Toast(getApplicationContext());
        toast.setView(view);
        toast.show();
        toast.setGravity(Gravity.CENTER,0,0);
        Call<BarangGetInfo> call = API.getDataBarang();
        call.enqueue(new Callback<BarangGetInfo>() {
            @Override
            public void onResponse(Call<BarangGetInfo> call, Response<BarangGetInfo> response) {
                if(response.isSuccessful() && response.body() != null) {
                    toast.cancel();
//                pesan = response.body().getMessage();
                    listdata = response.body().getData();
                    adapter = new AdapterDataBarang(DataBarangActivity.this, listdata);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<BarangGetInfo> call, Throwable t) {
                View view = getLayoutInflater().inflate(R.layout.toast_no_internet, null);
                view.findViewById(R.id.toast_noConnection);
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(view);
                toast.show();
                toast.setGravity(Gravity.TOP | Gravity.CENTER,0,0);
            }
        });
    }

    public void hapusDataBarang(String idprd,String gambar){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
            Call<DeleteBarang> call = API.postDeleteBarang(idprd,gambar);
            call.enqueue(new Callback<DeleteBarang>() {
                @Override
                public void onResponse(Call<DeleteBarang> call, Response<DeleteBarang> response) {
                    if(response.isSuccessful() && response.body() != null){
                        String pesan = response.body().getPesan();
                        if(response.body().isKondisi() == true){
                            listdata.clear();
                            View view = getLayoutInflater().inflate(R.layout.toast_hapus_produk, null);
                            view.findViewById(R.id.toast_deleteProduk);
                            Toast toast = new Toast(getApplicationContext());
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.setView(view);
                            toast.show();
                            toast.setGravity(Gravity.TOP | Gravity.CENTER,0,0);
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

//    public void editDataBarang(String idbr){
//
//    }
//
//    public void ClearBarang(){
//        listdata.clear();
//    }

    private void showPencarian(String dicari){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<BarangGetInfo> call = API.getInfoSrcLive(dicari);
        call.enqueue(new Callback<BarangGetInfo>() {
            @Override
            public void onResponse(Call<BarangGetInfo> call, Response<BarangGetInfo> response) {
                if(response.body() != null && response.isSuccessful()){
                    listdata = response.body().getData();
                    adapter = new AdapterDataBarang(DataBarangActivity.this, listdata);
                    recyclerView.setAdapter(adapter);

                }else{
                    Log.d("gagal", "onResponse: "+response.body().getKode()+" pesan "+response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<BarangGetInfo> call, Throwable t) {
                Log.d("error", "onFailure: "+t.getMessage());
                View view = getLayoutInflater().inflate(R.layout.toast_no_internet, null);
                view.findViewById(R.id.toast_noConnection);
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(view);
                toast.show();
                toast.setGravity(Gravity.TOP | Gravity.CENTER,0,0);
            }
        });


    }

    public void tampilKategori() {
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<KategoriInfo> call = API.getKategoriData();
        call.enqueue(new Callback<KategoriInfo>() {
            @Override
            public void onResponse(retrofit2.Call<KategoriInfo> call, Response<KategoriInfo> response) {
                pesan = response.body().getPesan();
                listdata2 = response.body().getData();
                adapter = new AdapterKategori(DataBarangActivity.this, listdata2);
                recyclerView2.setAdapter(adapter);
                adapter.notifyDataSetChanged();
//                int kata = listdata.size();
//                Log.e("Error", String.valueOf(kata));
            }

            @Override
            public void onFailure(retrofit2.Call<KategoriInfo> call, Throwable t) {
                View view = getLayoutInflater().inflate(R.layout.toast_no_internet, null);
                view.findViewById(R.id.toast_noConnection);
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(view);
                toast.show();
                toast.setGravity(Gravity.TOP | Gravity.CENTER,0,0);
            }
        });

    }

    public void tampilProdukWithKat(String idKat){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<BarangGetInfo> call = API.getProdukWithKat(idKat);
        call.enqueue(new Callback<BarangGetInfo>() {
            @Override
            public void onResponse(Call<BarangGetInfo> call, Response<BarangGetInfo> response) {
                pesan = response.body().getMessage();
                listdata = response.body().getData();
                adapter = new AdapterDataBarang(DataBarangActivity.this, listdata);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<BarangGetInfo> call, Throwable t) {

            }
        });
    }


}